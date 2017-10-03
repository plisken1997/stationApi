package com.lz.stationApi.station.model.query
import com.lz.stationApi.common.query.ModelQuery
import com.lz.stationApi.station.model.entity.Station

trait StationQuery extends ModelQuery[Station, Int] {
  /**
    *
    * @param dealerId
    * @return
    */
  def findByDealer(dealerId: Int): Iterable[Station]
}
