package com.lz.stationApi.station.model

import play.api.libs.functional.syntax.unlift
import play.api.libs.json.{JsPath, Writes}
import play.api.libs.functional.syntax._

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


  implicit val stationWrites: Writes[Station] = (
    (JsPath \ "id").write[StationId] and
      (JsPath \ "dealerId").write[DealerId] and
      (JsPath \ "countryCode").write[CountryCode] and
      (JsPath \ "stationName").write[StationName] and
      (JsPath \ "latitude").write[Latitude] and
      (JsPath \ "longitude").write[Longitude] and
      (JsPath \ "address").write[StationAddress] and
      (JsPath \ "postalCode").write[PostalCode] and
      (JsPath \ "city").write[City]
    ) (unlift(Station.unapply))
}
