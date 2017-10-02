package com.lz.retailApi.station.service

import com.lz.stationApi.station.service.{StationCSVFactory, StationCSVReader}
import org.scalatest.FunSpec

import scala.util.{Failure, Success}

class StationCSVReaderSpec extends FunSpec {
  describe("Station csv reader") {
    describe("when reading a csv file") {
      it("should build a list with station entities") {
        import scala.concurrent.ExecutionContext.Implicits.global

        val reader = new StationCSVReader(new StationCSVFactory)
        val filepath = "resources/csv/stations.csv"
        reader.read(filepath).onComplete {
          case Success(entities) => assert(entities.size == 99)
          case Failure(err) => fail(err.getMessage)
        }
      }
    }

    describe("when call with a non existing file") {
      it("should failed at building a list ") {
        import scala.concurrent.ExecutionContext.Implicits.global

        val reader = new StationCSVReader(new StationCSVFactory)
        val filepath = "/var/www/resources/csv/stations.csv"
        reader.read(filepath) onComplete {
          case Success(entities) => fail(s"$filepath should not be processed !")
          case Failure(err) => succeed
        }
      }
    }
  }
}