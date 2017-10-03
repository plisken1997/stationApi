package com.lz.retailApi.station.service

import com.lz.stationApi.station.service.{StationCSVFactory, StationCSVReader}
import org.scalatest.AsyncFunSpec

class StationCSVReaderSpec extends AsyncFunSpec {
  describe("Station csv reader") {
    val reader = new StationCSVReader(new StationCSVFactory)

    describe("when reading a csv file") {
      it("should build a list with 95 stations entities") {
        val filepath = "resources/csv/stations.csv"
        reader.read(filepath).map { stations =>
          assert(stations.size == 95)
        }
      }
    }

    describe("when call with a non existing file") {
      it("should failed at building a list ") {
        val filepath = "/var/www/resources/csv/stations.csv"
        reader
          .read(filepath)
          .map(stations => fail(s"$filepath should not be processed ! ${stations.size} found"))
          .recover {
            case e: RuntimeException => succeed
            case _ => fail("unexpected error")
          }
      }
    }
  }
}