package com.lz.stationApi.station.service.actors

import java.nio.file.{Files, Paths}
import javax.inject.{Inject, Named, Singleton}

import akka.actor.{Actor, ActorRef, Props}
import play.api.Logger
import scala.io.Source

@Singleton
final class UpdateStationsActor @Inject() (@Named("update-station-actor") updateStationActor: ActorRef) extends Actor {
  import com.lz.stationApi.station.service.actors.UpdateStationsActor._

  override def receive = {
    case UpdateStations(filepath) => updateStations(filepath)
  }

  private def updateStations(filepath: String): Unit = {
    assert(Files.isReadable(Paths.get(filepath)))
    val separ = ","
    val bufferedSource = Source.fromFile(filepath)
    // @todo handle errors !
    // @todo handle columns positions !
    // @todo use a real csv parser !
    Logger.info(s"load stations from $filepath...")


    for (line <- bufferedSource.getLines.drop(1)) {
      val row = line.split(separ).map(_.trim)
      updateStationActor ! UpdateStationActor.UpdateStation(row)
    }
    bufferedSource.close
  }
}

object UpdateStationsActor {
  def props = Props[UpdateStationsActor]

  case class UpdateStations(filepath: String)
}
