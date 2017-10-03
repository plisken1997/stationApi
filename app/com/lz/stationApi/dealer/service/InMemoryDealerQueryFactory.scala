package com.lz.stationApi.dealer.service


import javax.inject.{Inject, Named, Singleton}

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
  def createQueryObject(filepath: String): Future[InMemoryDealerQuery] =
    csvReader
      .read(filepath)
      .map { dealers =>
        new InMemoryDealerQuery(dealers.map { dealer =>
          (
            dealer.id,
            dealer.copy(stations = stationQuery.findByDealer(dealer.id))
          )
        }.toMap)
      }
}
