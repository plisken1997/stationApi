package com.lz.stationApi.station.service

import com.lz.stationApi.common.service.CSVEntityFactory
import com.lz.stationApi.station.model.entity.Station

import scala.util.Try

class StationCSVFactory extends CSVEntityFactory[Station]{
  /**
    * @param values
    * @return
    */
  def fromCSV(values: Array[String]): Try[Station] = Station.fromCSV(values)

}
