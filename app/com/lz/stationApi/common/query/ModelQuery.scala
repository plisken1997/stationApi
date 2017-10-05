package com.lz.stationApi.common.query

import scala.concurrent.Future

trait ModelQuery[A, B] {
  /**
    *
    * @param id
    * @return
    */
  def find(id: Int): Future[Option[A]]

  /**
    *
    * @param filters
    * @return
    */
  def findAll(filters: List[QueryFilter] = Nil): Future[Map[B, A]]
}
