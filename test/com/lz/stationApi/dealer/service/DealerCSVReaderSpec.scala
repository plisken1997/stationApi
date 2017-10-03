package com.lz.retailApi.dealer.service

import com.lz.stationApi.dealer.service.{DealerCSVFactory, DealerCSVReader}
import org.scalatest.FunSpec

import scala.util.{Failure, Success}

class DealerCSVReaderSpec extends FunSpec {
  describe("Dealer csv reader") {
    describe("when reading a csv file") {
      it("should build a list with Dealder entities") {
        import scala.concurrent.ExecutionContext.Implicits.global

        val reader = new DealerCSVReader(new DealerCSVFactory)
        val filepath = "resources/csv/dealers.csv"
        reader.read(filepath) onComplete  {
          case Success(entities) => assert(entities.size == 32)
          case Failure(err) => fail(err.getMessage)
        }
      }
    }
  }
}
