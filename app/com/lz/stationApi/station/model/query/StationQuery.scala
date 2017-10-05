package com.lz.stationApi.station.model.query
import com.lz.stationApi.common.query.ModelQuery
import com.lz.stationApi.station.model.entity.Station

import scala.concurrent.Future

trait StationQuery extends ModelQuery[Station, Int] {
  /**
    *
    * @param dealerId
    * @return
    */
  def findByDealer(dealerId: Int): Future[Iterable[Station]]
}
