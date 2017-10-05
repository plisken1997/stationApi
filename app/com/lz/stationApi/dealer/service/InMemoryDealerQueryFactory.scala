package com.lz.stationApi.dealer.service


import javax.inject.{Inject, Named, Singleton}

import com.lz.stationApi.dealer.model.entity.Dealer
import com.lz.stationApi.dealer.model.query.InMemoryDealerQuery
import com.lz.stationApi.station.model.query.StationQuery

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


@Singleton
class InMemoryDealerQueryFactory @Inject()(csvReader: DealerCSVReader, @Named("InMemoryStationQuery") stationQuery: StationQuery) {
  /**
    *
    * @param filepath
    * @return
    */
  def createQueryObject(filepath: String): Future[InMemoryDealerQuery] = for {
    dealers <- csvReader.read(filepath)
    dealersWithStations <- addStations(dealers)
  } yield new InMemoryDealerQuery(dealersWithStations.toMap)

  /**
    *
    * @param dealers
    * @return
    */
  private def addStations(dealers: List[Dealer]): Future[Iterable[(Int, Dealer)]] =
    Future.sequence(dealers.map { dealer =>
      stationQuery.findByDealer(dealer.id).map { stations =>
        (
          dealer.id,
          dealer.copy(stations = stations)
        )
      }
    })
}
