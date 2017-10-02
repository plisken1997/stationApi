package com.lz.retailApi.station.model.query

import com.lz.stationApi.station.model.entity.Station
import com.lz.stationApi.station.model.query.InMemoryStationQuery
import org.scalatest.FunSpec

class InMemoryStationQuerySpec extends FunSpec {
  describe("An InMemoryStationQuery") {
    val stations = Map(
      38332 -> Station(38332, 13, "FR", "GARAGE DE MONTBEL", 45.6760293, 4.7733092, "2 CHE DES BASSES VALLIERE", "69530", "BRIGNAIS"),
      47529 -> Station(47529, 63, "FR", "GARAGE LANGLOIS SAS", 48.11432067647415, -1.599868871704075, "Route de Paris - Parc d'Activité Les Peupliers II", "35510", "CESSON-SEVIGNE"),
      85787 -> Station(85787, 220, "DE", "PIT STOP", 49.96728770000001, 8.228472699999998, "Haifa-Allee 1 Gutenberg Center", "55128", "Mainz"),
      64695 -> Station(64695, 698, "DE", "REIFEN & SERVICE J. RADECK KG", 52.0056017, 8.5792807, "Elpke 61", "33605", "Bielefeld")
    )

    val stationQuery = new InMemoryStationQuery(stations)

    describe("when initialized with a station list") {
      it("should have the same size as the given list") {
        assert(stationQuery.findAll().size == stations.size)
      }

      it("should returns a filtered list with the given inputs") {
        assert(stationQuery.findAll(List(("countryCode", "FR"))).size == 2)
        assert(stationQuery.findAll(List(
          ("countryCode", "FR"),
          ("dealerId", "63"),
          ("longitude", "-1.599868871704075")
        )).size == 1)
      }

      it("should returns the required element") {
        val elt = stationQuery.find(38332)
        assert(elt.nonEmpty)
        assert(elt.get == stations.get(38332).get)
      }

      it("should not find the required element if it does not exists") {
        val elt = stationQuery.find(38332111)
        assert(elt.isEmpty)
      }
    }
  }
}
