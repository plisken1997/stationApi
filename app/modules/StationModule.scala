package modules

import java.nio.file.{Files, Paths}

import com.google.inject.{AbstractModule, Provides}
import com.lz.stationApi.station.model.query.{InMemoryStationQuery, StationQuery}
import com.lz.stationApi.station.service.InMemoryStationQueryFactory
import javax.inject.Inject

import com.google.inject.name.Names

import scala.util.{Failure, Success}
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class StationModule extends AbstractModule {
  /**
    *
    */
  def configure() = {
    bind(classOf[StationQuery])
      .annotatedWith(Names.named("InMemoryStationQuery"))
      .to(classOf[InMemoryStationQuery]).asEagerSingleton()
  }

  /**
    *
    * @param factory
    * @return
    */
  @Inject() @Provides() def provideInMemoryStationQuery(factory: InMemoryStationQueryFactory): InMemoryStationQuery = {
    // @todo provide csv path as a parameter
    val filepath = "resources/csv/stations.csv"
    // @todo handle error (should kill app !)
    val result = factory.createQueryObject(filepath)

    println(s"load $filepath content")
    assert(Files.isReadable(Paths.get(filepath)), "unreadable stations.csv file")

    result.onComplete {
      case Success(query) =>
        // @todo use play logger
        println(s"query handler successfully loaded : ${query.findAll().size} station(s) loaded")
        query
      case Failure(err) =>
        println(err.getMessage)
        new InMemoryStationQuery(Map())
    }

    Await.result(
      result,
      5000 millis
    )
  }

}
