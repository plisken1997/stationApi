package com.lz.stationApi.common.service

import scala.util.Try

trait CSVEntityFactory[U] {
  /**
    *
    * @param values
    * @return
    */
  def fromCSV(values: Array[String]): Try[U]
}
