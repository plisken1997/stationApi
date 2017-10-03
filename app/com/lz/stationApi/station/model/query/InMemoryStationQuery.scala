package com.lz.stationApi.station.model.query

import javax.inject.Singleton

import com.lz.stationApi.common.query.QueryFilter
import com.lz.stationApi.station.model.entity.Station

@Singleton
class InMemoryStationQuery(private val stations: Map[Int, Station]) extends StationQuery {
  /**
    *
    * @param id
    * @return
    */
  override def find(id: Int): Option[Station] = stations.get(id)

  /**
    *
    * @param filters
    * @return
    */
  override def findAll(filters: List[QueryFilter] = Nil): Map[Int, Station] = {
    def stationFilter(station: Station): Boolean = {
      filters.foldLeft(true) { case (accept: Boolean, f: QueryFilter) =>
        accept && Station.matchValue(station, f)
      }
    }
    stations.filter(v => stationFilter(v._2))
  }

  /**
    *
    * @param dealerId
    * @return
    */
  override def findByDealer(dealerId: Int): Iterable[Station] = findAll().filter(_._2.dealerId == dealerId).values
}
