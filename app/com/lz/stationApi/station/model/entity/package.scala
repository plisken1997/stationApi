package com.lz.stationApi.station.model

import play.api.libs.json.Json

package object entity {
  type StationId = Int
  type DealerId = Int
  type CountryCode = String
  type StationName = String
  type Latitude = Double
  type Longitude = Double
  type StationAddress = String
  type PostalCode = String
  type City = String

  implicit val stationWrites = Json.writes[Station]
}
