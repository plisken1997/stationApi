package com.lz.stationApi.dealer.model.query

import com.lz.stationApi.common.query.stationApiQuery
import com.lz.stationApi.dealer.model.entity.Dealer

trait DealerQuery extends stationApiQuery[Dealer, Int] {
  /**
    *
    * @param dealerId
    * @return
    */
  def countStations(dealerId: Int): Int
}
