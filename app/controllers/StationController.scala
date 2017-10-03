package controllers

import javax.inject.{Inject, Named, Singleton}

import com.lz.stationApi.station.model.query.StationQuery
import com.lz.stationApi.station.service.{StationCSVFactory, StationCSVReader}
import play.api.mvc.{AbstractController, ControllerComponents, Result}
import play.api.libs.json.Json

@Singleton
class StationController @Inject()(cc: ControllerComponents, @Named("InMemoryStationQuery") query: StationQuery) extends AbstractController(cc) {

  import com.lz.stationApi.station.model.entity.stationWrites

  def station(id: Int) = Action {
    val reader = new StationCSVReader(new StationCSVFactory)
    val filepath = "resources/csv/stations.csv"
    reader.read(filepath)
    query
      .find(id)
      .fold[Result](NotFound(s"id $id not found"))(station => Ok(Json.toJson(station)))
  }

  def stations(countryCode: String) = Action {
    Ok(Json.toJson(query.findAll()))
  }
}
