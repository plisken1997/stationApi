package modules

import java.nio.file.{Files, Paths}
import javax.inject.Inject

import com.google.inject.{AbstractModule, Provides}
import com.google.inject.name.Names
import com.lz.stationApi.dealer.model.query.{DealerQuery, DocumentDealerQuery, InMemoryDealerQuery}
import com.lz.stationApi.dealer.model.repository.{DealerRepository, DocumentDealerRepository}
import com.lz.stationApi.dealer.service.InMemoryDealerQueryFactory
import com.lz.stationApi.station.model.repository.{DocumentStationRepository, StationRepository}

import scala.concurrent.Await
import scala.util.{Failure, Success}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.Logger

class DealerModule extends AbstractModule {

  def configure() = {
    bind(classOf[DealerQuery])
      .annotatedWith(Names.named("InMemoryDealerQuery"))
      .to(classOf[InMemoryDealerQuery]).asEagerSingleton()

    bind(classOf[DealerRepository])
      .annotatedWith(Names.named("DealerRepository"))
      .to(classOf[DocumentDealerRepository]).asEagerSingleton()

    bind(classOf[DealerQuery])
      .annotatedWith(Names.named("DealerQuery"))
      .to(classOf[DocumentDealerQuery]).asEagerSingleton()
  }

  /**
    *
    * @param factory
    * @return
    */
  @Inject() @Provides() def provideInMemoryDealerQuery(factory: InMemoryDealerQueryFactory): InMemoryDealerQuery = {
    // @todo provide csv path as a parameter
    val filepath = "resources/csv/dealers.csv"
    // @todo handle error (should kill app !)
    val result = factory.createQueryObject(filepath)

    Logger.info(s"load $filepath content")
    assert(Files.isReadable(Paths.get(filepath)), "unreadable dealers.csv file")

    result.onComplete {
      case Success(query) =>
        query.findAll().map { dealers =>
          Logger.info(s"query handler successfully loaded : ${dealers.size} dealer(s) loaded")
        }
        query
      case Failure(err) =>
        Logger.error(err.getMessage)
        new InMemoryDealerQuery(Map())
    }

    Await.result(
      result,
      5000 millis
    )
  }


}
