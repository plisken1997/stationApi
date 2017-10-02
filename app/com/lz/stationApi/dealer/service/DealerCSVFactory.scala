package com.lz.stationApi.dealer.service

import com.lz.stationApi.common.service.CSVEntityFactory
import com.lz.stationApi.dealer.model.entity.Dealer

import scala.util.Try

class DealerCSVFactory extends CSVEntityFactory[Dealer] {
  /**
    *
    * @param values
    * @return
    */
  def fromCSV(values: Array[String]): Try[Dealer] = Dealer.fromCSV(values)
}
