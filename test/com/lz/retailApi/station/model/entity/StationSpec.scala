package com.lz.retailApi.station.model.entity

import com.lz.stationApi.station.model.entity.Station
import org.scalatest.FunSpec

class StationSpec extends FunSpec {
  describe("Station entity object") {
    describe("when created from some string data") {
      it("should be created if all the data are given") {
        assert(Station.fromCSV(Array("70970","700","DE","BIKE & AUTO SCHAUER GMBH & CO. KG","50.03776","10.23654","Carl-Zeiss-Straße 6","97424","Schweinfurt")).isSuccess)
      }
      it("should not be created if expecte values are not matched") {
        assert(Station.fromCSV(Array("70970","700","DE","50.03776","10.23654","Carl-Zeiss-Straße 6","97424","Schweinfurt")).isFailure)
      }
    }
  }
}
