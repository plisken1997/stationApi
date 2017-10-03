package com.lz.stationApi.dealer.model.query

import com.lz.stationApi.common.query.ModelQuery
import com.lz.stationApi.dealer.model.entity.Dealer

trait DealerQuery extends ModelQuery[Dealer, Int] {
  /**
    *
    * @param dealerId
    * @return
    */
  def countStations(dealerId: Int): Int
}
