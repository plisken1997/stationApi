package controllers

import javax.inject.{Inject, Singleton}

import com.lz.stationApi.station.model.entity.Station
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.libs.json.{Json}

@Singleton
class StationController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  import com.lz.stationApi.station.model.entity

  def station(id: Int) = Action { implicit request =>
    Ok(Json.toJson(
      Station(38332, 13, "FR", "GARAGE DE MONTBEL", 45.6760293, 4.7733092, "2 CHE DES BASSES VALLIERE", "69530", "BRIGNAIS")
    ))
  }

  def stations(countryCode: String) = Action {
    Ok
  }
}
