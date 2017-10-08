package com.lz.stationApi.station.service.actors

import javax.inject.{Inject, Named, Singleton}

import akka.actor.{Actor, Props}
import com.lz.stationApi.station.model.repository.StationRepository
import com.lz.stationApi.station.service.StationCSVFactory

@Singleton
final class UpdateStationActor @Inject()(
                                    entityFactory: StationCSVFactory,
                                    @Named("StationRepository") repository: StationRepository
                                  )  extends Actor {

  import com.lz.stationApi.station.service.actors.UpdateStationActor._

  /**
    *
    * @return
    */
  override def receive = {
    // @todo handle errors
    case UpdateStation(stationValues) => entityFactory.fromCSV(stationValues).map(station => repository.upsert(station))
  }
}

object UpdateStationActor {
  def props = Props[UpdateStationActor]

  case class UpdateStation(stationValues: Array[String])
}
