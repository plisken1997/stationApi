package com.lz.stationApi.dealer.service.actors

import java.nio.file.{Files, Paths}
import javax.inject.{Inject, Named, Singleton}

import akka.actor.{Actor, ActorRef, Props}
import com.lz.stationApi.station.service.actors.UpdateStationActor
import play.api.Logger

import scala.io.Source

@Singleton
final class UpdateDealersActor @Inject()(@Named("update-dealer-actor") updateDealerActor: ActorRef) extends Actor {
  import com.lz.stationApi.dealer.service.actors.UpdateDealersActor._

  /**
    *
    * @return
    */
  override def receive = {
    case UpdateDealers(filename) => updateStations(filename)

  }

  private def updateStations(filepath: String): Unit = {
    assert(Files.isReadable(Paths.get(filepath)))
    val separ = ","
    val bufferedSource = Source.fromFile(filepath)

    Logger.info(s"load dealers from $filepath...")

    for (line <- bufferedSource.getLines.drop(1)) {
      val row = line.split(separ).map(_.trim)
      updateDealerActor ! UpdateDealerActor.UpdateDealer(row)
    }
    bufferedSource.close
  }
}

object UpdateDealersActor {
  case class UpdateDealers(filename: String)

  def props = Props[UpdateDealersActor]
}
