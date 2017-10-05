package com.lz.stationApi.common.repository

import scala.concurrent.Future

trait EntityUpdater[ID, ENTITY] {

  /**
    *
    * @param id
    * @param entity
    * @return
    */
  def update(id: ID, entity: ENTITY): Future[Option[ENTITY]]
}
