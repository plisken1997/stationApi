package com.lz.stationApi.station.service

import javax.inject.{Inject, Singleton}

import com.lz.stationApi.common.service.InputDataReader
import com.lz.stationApi.station.model.entity.Station

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class StationCSVReader @Inject()(entityFactory: StationCSVFactory) extends InputDataReader[Station] {

  /**
    *
    * @param filepath
    * @param separ
    * @return
    */
  def read(filepath: String, separ: String = ","): Future[List[Station]] = super.read(filepath, separ, entityFactory)
}
