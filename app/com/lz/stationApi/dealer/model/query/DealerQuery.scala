package com.lz.stationApi.dealer.model.query

import com.lz.stationApi.common.query.{ModelQuery, QueryFilter}
import com.lz.stationApi.dealer.model.entity.Dealer

trait DealerQuery extends ModelQuery[Dealer, Int] {
  /**
    *
    * @return
    */
  def countStations(filters: List[QueryFilter] = Nil): List[(String, Int)]
}
