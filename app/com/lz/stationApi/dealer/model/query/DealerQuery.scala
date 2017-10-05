package com.lz.stationApi.dealer.model.query

import com.lz.stationApi.common.query.{ModelQuery, QueryFilter}
import com.lz.stationApi.dealer.model.entity.Dealer

import scala.concurrent.Future

trait DealerQuery extends ModelQuery[Dealer, Int] {
  /**
    *
    * @return
    */
  def countStations(filters: List[QueryFilter] = Nil): Future[List[(String, Int)]]
}
