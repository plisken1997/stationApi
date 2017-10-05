package com.lz.stationApi.dealer.model.query

class DocumentDealerQuery extends DealerQuery {
  /**
    *
    * @return
    */
  override def countStations(filters: List[(String, String)]) = ???

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
