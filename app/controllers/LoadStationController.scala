package controllers

import javax.inject.{Inject, Named}

import akka.actor.ActorRef
import com.lz.stationApi.station.service.actors.UpdateStationsActor
import play.api.mvc.{AbstractController, ControllerComponents}

class LoadStationController  @Inject()(cc: ControllerComponents, @Named("update-stations-actor") actorRef: ActorRef ) extends AbstractController(cc) {

  /**
    *
    * @return
    */
  def load = Action {
    actorRef ! UpdateStationsActor.UpdateStations("resources/csv/stations.csv")
    Ok("load stations...")
  }
}
