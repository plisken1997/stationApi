package tasks

import javax.inject.{Inject, Named}

import akka.actor.{ActorRef, ActorSystem}
import com.lz.stationApi.dealer.service.actors.UpdateDealersActor

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

class UpdateDealersTask @Inject() (
                                    actorSystem: ActorSystem,
                                    @Named("update-dealers-actor") updateDealersActor: ActorRef
                                  )(implicit executionContext: ExecutionContext) {

  actorSystem.scheduler.schedule(
    initialDelay = 10 seconds,
    interval = 12 hours,
    receiver = updateDealersActor,
    message = UpdateDealersActor.UpdateDealers("resources/csv/dealers.csv")
  )
}

