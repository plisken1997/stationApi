package com.lz.stationApi.dealer.model.query

import com.lz.stationApi.common.query.QueryFilter
import com.lz.stationApi.dealer.model.entity.Dealer

class InMemmoryDealerQuery(private val stations: Map[Int, Dealer]) extends DealerQuery {
  /**
    *
    * @param id
    * @return
    */
  override def find(id: Int) = stations.get(id)

  /**
    *
    * @param filters
    * @return
    */
  override def findAll(filters: List[QueryFilter]) = stations

  /**
    *
    * @param dealerId
    * @return
    */
  override def countStations(dealerId: Int) = find(dealerId).map(_.stations.size).getOrElse(0)
}
