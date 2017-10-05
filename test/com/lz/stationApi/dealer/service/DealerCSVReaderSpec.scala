package com.lz.retailApi.dealer.service

import com.lz.stationApi.dealer.service.{DealerCSVFactory, DealerCSVReader}
import org.scalatest.AsyncFunSpec

class DealerCSVReaderSpec extends AsyncFunSpec {
  describe("Dealer csv reader") {
    describe("when reading a csv file") {
      it("should build a list with Dealder entities") {
        val reader = new DealerCSVReader(new DealerCSVFactory)
        val filepath = "resources/csv/dealers.csv"
        reader
          .read(filepath)
          .map(entities => assert(entities.size == 32))
      }
    }
  }
}
