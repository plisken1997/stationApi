package com.lz.stationApi.station.service

import com.lz.stationApi.common.service.{CSVEntityFactory, InputDataReader}
import com.lz.stationApi.station.model.entity.Station

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class StationCSVReader(val entityFactory: CSVEntityFactory[Station]) extends InputDataReader[Station] {

  /**
    *
    * @param filepath
    * @param separ
    * @return
    */
  def read(filepath: String, separ: String = ","): Future[List[Station]] = super.read(filepath, separ, entityFactory)
}
