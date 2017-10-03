package com.lz.stationApi.station.service

import javax.inject.{Inject, Singleton}

import com.lz.stationApi.station.model.query.InMemoryStationQuery
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class InMemoryStationQueryFactory @Inject()(csvReader: StationCSVReader) {
  /**
    *
    * @param filepath
    * @return
    */
  def createQueryObject(filepath: String): Future[InMemoryStationQuery] =
    csvReader
      .read(filepath)
      .map { stations =>
        new InMemoryStationQuery(stations.map(s => (s.id, s)).toMap)
      }
}
