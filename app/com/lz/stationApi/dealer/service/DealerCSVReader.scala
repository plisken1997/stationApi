package com.lz.stationApi.dealer.service

import com.lz.stationApi.common.service.{CSVEntityFactory, InputDataReader}
import com.lz.stationApi.dealer.model.entity.Dealer

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class DealerCSVReader(val entityFactory: CSVEntityFactory[Dealer]) extends InputDataReader[Dealer] {
  /**
    *
    * @param filepath
    * @param separ
    * @return
    */
  def read(filepath: String, separ: String = ","): Future[List[Dealer]] = super.read(filepath, separ, entityFactory)
}
