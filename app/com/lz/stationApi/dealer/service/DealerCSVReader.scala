package com.lz.stationApi.dealer.service

import javax.inject.{Inject, Singleton}

import com.lz.stationApi.common.service.InputDataReader
import com.lz.stationApi.dealer.model.entity.Dealer

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class DealerCSVReader @Inject()(entityFactory: DealerCSVFactory) extends InputDataReader[Dealer] {
  /**
    *
    * @param filepath
    * @param separ
    * @return
    */
  def read(filepath: String, separ: String = ","): Future[List[Dealer]] = super.read(filepath, separ, entityFactory)
}
