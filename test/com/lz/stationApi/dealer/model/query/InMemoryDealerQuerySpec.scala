package com.lz.retailApi.dealer.model.query

import com.lz.stationApi.dealer.model.entity.{Dealer, DealerType}
import com.lz.stationApi.dealer.model.query.InMemoryDealerQuery
import com.lz.stationApi.station.model.entity.Station
import org.scalatest.FunSpec

class InMemoryDealerQuerySpec extends FunSpec {
  describe("A dealerquery object") {
    val tryFirstDealer = DealerType.fromType(1).map(dealerType => Dealer(1086, "RTC", dealerType, List(
      Station(75388, 1086, "DE", "REIFEN USZECK", 53.62815, 14.00238, "Wilhelmstraße 14", "17358", "Torgelow"),
      Station(75389, 1086, "DE", "REIFEN USZECK v2", 53.62815, 14.00238, "Wilhelmstraße 14 v2", "17358", "Torgelow")
    )))
    val firstDealer = tryFirstDealer.get

    val dealers = Map(
      firstDealer.id -> firstDealer,
      1085 -> DealerType.fromType(1).map(Dealer(1085, "Reifen Helm", _)).get
    )
    val dealerQuery = new InMemoryDealerQuery(dealers)

    describe("when initialized with a dealer list") {
      it("should have the same size as the given list") {
        assert(dealerQuery.findAll().size == dealers.size)
      }

      it("should returns the expected dealer with its stations") {
        val d = dealerQuery.find(1086)
        assert(d.nonEmpty)
        assert(d.get.stations.size == firstDealer.stations.size)
      }

      it("should count the stations for a required dealer") {
        assert(dealerQuery.countStations().filter(_._1 == "RTC").head._2 == 2)
        assert(dealerQuery.countStations().filter(_._1 == "Reifen Helm").head._2 == 0)
      }
    }
  }
}
