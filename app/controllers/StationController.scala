package controllers

import javax.inject.{Inject, Named, Singleton}

import com.lz.stationApi.station.model.query.StationQuery
import com.lz.stationApi.station.service.{StationCSVFactory, StationCSVReader}
import play.api.mvc.{AbstractController, ControllerComponents, Result}
import play.api.libs.json.Json
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class StationController @Inject()(cc: ControllerComponents, @Named("InMemoryStationQuery") query: StationQuery) extends AbstractController(cc) {

  import com.lz.stationApi.station.model.entity.stationWrites

  def station(id: Int) = Action.async {
    query
      .find(id)
        .map { maybeStation =>
          maybeStation.fold[Result](NotFound(Json.toJson(Map("error" -> s"id $id not found"))))(station => Ok(Json.toJson(station)))
        }
  }

  def stations(countryCode: Option[String]) = Action.async {
    val filters = countryCode.map(c => List(("countryCode", c))).getOrElse(Nil)
    query.findAll(filters).map { stations =>
      Ok(Json.toJson(stations.map(_._2)))
    }
  }
}
