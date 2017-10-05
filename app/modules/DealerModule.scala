package modules

import java.nio.file.{Files, Paths}
import javax.inject.Inject

import com.google.inject.{AbstractModule, Provides}
import com.google.inject.name.Names
import com.lz.stationApi.dealer.model.query.{DealerQuery, InMemoryDealerQuery}
import com.lz.stationApi.dealer.service.InMemoryDealerQueryFactory

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
        // @todo use play logger
        Logger.info(s"query handler successfully loaded : ${query.findAll().size} dealer(s) loaded")
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
