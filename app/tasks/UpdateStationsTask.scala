package tasks

import javax.inject.{Inject, Named}

import akka.actor.{ActorRef, ActorSystem}
import com.lz.stationApi.station.service.actors.UpdateStationsActor

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

class UpdateStationsTask @Inject() (
                                     actorSystem: ActorSystem,
                                     @Named("update-stations-actor") updateStationsActor: ActorRef
                                   )(implicit executionContext: ExecutionContext) {

  actorSystem.scheduler.schedule(
    initialDelay = 10 seconds,
    interval = 12 hours,
    receiver = updateStationsActor,
    message = UpdateStationsActor.UpdateStations("resources/csv/stations.csv")
  )
}
