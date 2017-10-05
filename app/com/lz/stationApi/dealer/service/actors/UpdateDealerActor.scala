package com.lz.stationApi.dealer.service.actors

import javax.inject.{Inject, Named}

import akka.actor.{Actor, Props}
import com.lz.stationApi.dealer.model.repository.DealerRepository
import com.lz.stationApi.dealer.service.DealerCSVFactory
import com.lz.stationApi.dealer.service.actors.UpdateDealerActor.UpdateDealer
import com.lz.stationApi.station.model.query.StationQuery
import scala.concurrent.ExecutionContext.Implicits.global

class UpdateDealerActor @Inject()(
                                   entityFactory: DealerCSVFactory,
                                   @Named("DealerRepository") repository: DealerRepository,
                                   @Named("StationQuery") query: StationQuery,
                                 ) extends Actor {
  override def receive = {
    case UpdateDealer(row) => entityFactory.fromCSV(row).map { dealer =>
      query.findByDealer(dealer.id).map(stations => repository.upsert(dealer.copy(stations = stations)))
    }
  }
}

object UpdateDealerActor {

  case class UpdateDealer(row: Array[String])

  def props = Props[UpdateDealerActor]
}
