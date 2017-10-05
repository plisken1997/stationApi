package com.lz.stationApi.station.model.query

import javax.inject.Singleton

@Singleton
class DocumentStationQuery extends StationQuery {
  /**
    *
    * @param dealerId
    * @return
    */
  override def findByDealer(dealerId: Int) = ???

  /**
    *
    * @param id
    * @return
    */
  override def find(id: Int) = ???

  /**
    *
    * @param filters
    * @return
    */
  override def findAll(filters: List[(String, String)]) = ???
}
