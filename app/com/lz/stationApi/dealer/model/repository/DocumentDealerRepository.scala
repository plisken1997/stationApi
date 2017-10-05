package com.lz.stationApi.dealer.model.repository
import com.lz.stationApi.dealer.model.entity.Dealer

class DocumentDealerRepository extends DealerRepository {
  /**
    *
    * @param entity
    * @return
    */
  override def save(entity: Dealer) = ???

  /**
    *
    * @param entity
    * @return
    */
  override def upsert(entity: Dealer) = ???
}
