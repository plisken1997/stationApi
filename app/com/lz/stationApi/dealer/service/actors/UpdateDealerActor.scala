package com.lz.stationApi.dealer.service.actors

import javax.inject.{Inject, Named, Singleton}

import akka.actor.{Actor, Props}
import com.lz.stationApi.dealer.model.repository.DealerRepository
import com.lz.stationApi.dealer.service.DealerCSVFactory
import com.lz.stationApi.dealer.service.actors.UpdateDealerActor.UpdateDealer
import com.lz.stationApi.station.model.query.StationQuery
import play.api.Logger

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

@Singleton
class UpdateDealerActor @Inject()(
                                   entityFactory: DealerCSVFactory,
                                   @Named("DealerRepository") repository: DealerRepository,
                                   @Named("InMemoryStationQuery") query: StationQuery,
                                 ) extends Actor {
  override def receive = {
    case UpdateDealer(row) =>
      entityFactory.fromCSV(row) match {
        case Success(dealer) => query.findByDealer(dealer.id).map(stations => repository.upsert(dealer.copy(stations = stations)))
        case Failure(error) =>
          Logger.error(error.getMessage)
          Future.failed(error)
    }
  }
}

object UpdateDealerActor {

  case class UpdateDealer(row: Array[String])

  def props = Props[UpdateDealerActor]
}
