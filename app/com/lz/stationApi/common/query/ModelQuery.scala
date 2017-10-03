package com.lz.stationApi.common.query

trait ModelQuery[A, B] {
  /**
    *
    * @param id
    * @return
    */
  def find(id: Int): Option[A]

  /**
    *
    * @param filters
    * @return
    */
  def findAll(filters: List[QueryFilter] = Nil): Map[B, A]
}
