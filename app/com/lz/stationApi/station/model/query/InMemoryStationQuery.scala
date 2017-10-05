package com.lz.stationApi.station.model.query

import javax.inject.Singleton

import com.lz.stationApi.common.query.QueryFilter
import com.lz.stationApi.station.model.entity.Station

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class InMemoryStationQuery(private val stations: Map[Int, Station]) extends StationQuery {
  /**
    *
    * @param id
    * @return
    */
  override def find(id: Int): Future[Option[Station]] = Future(stations.get(id))

  /**
    *
    * @param filters
    * @return
    */
  override def findAll(filters: List[QueryFilter] = Nil): Future[Map[Int, Station]] = {
    def stationFilter(station: Station): Boolean = {
      filters.foldLeft(true) { case (accept: Boolean, f: QueryFilter) =>
        accept && Station.matchValue(station, f)
      }
    }
    Future(stations.filter(v => stationFilter(v._2)))
  }

  /**
    *
    * @param dealerId
    * @return
    */
  override def findByDealer(dealerId: Int): Future[Iterable[Station]] = findAll().map(_.filter(_._2.dealerId == dealerId).values)
}
