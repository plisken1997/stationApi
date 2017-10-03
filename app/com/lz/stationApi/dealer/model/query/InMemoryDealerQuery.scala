package com.lz.stationApi.dealer.model.query

import javax.inject.Singleton

import com.lz.stationApi.common.query.QueryFilter
import com.lz.stationApi.dealer.model.entity.Dealer

@Singleton
class InMemoryDealerQuery(private val dealers: Map[Int, Dealer]) extends DealerQuery {
  /**
    *
    * @param id
    * @return
    */
  override def find(id: Int) = dealers.get(id)

  /**
    *
    * @param filters
    * @return
    */
  override def findAll(filters: List[QueryFilter]) = {
    // @todo filter on countryCode
    // @see com.lz.stationApi.station.model.query.InMemoryStationQuery.findAll() to an implementation example
    dealers
  }

  /**
    *
    * @return
    */
  override def countStations(filters: List[QueryFilter]): List[(String, Int)] = dealers.map(d => (d._2.name, d._2.stations.size)).toList
}
