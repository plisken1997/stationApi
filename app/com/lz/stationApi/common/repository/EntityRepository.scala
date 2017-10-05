package com.lz.stationApi.common.repository

import scala.concurrent.Future

trait EntityRepository[ENTITY] {
  /**
    *
    * @param entity
    * @return
    */
  def save(entity: ENTITY): Future[Option[ENTITY]]

  /**
    *
    * @param entity
    * @return
    */
  def upsert(entity: ENTITY): Future[Option[ENTITY]]
}
