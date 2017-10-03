package com.lz.stationApi.dealer.model

import com.lz.stationApi.station.model.entity.Station
import play.api.libs.json.Json

import scala.util.{Failure, Success, Try}

package object entity {

  type DealerId = Int
  type DealerName = String
  type Stations = Iterable[Station]
  type DealerType = Int

  object DealerType {
    def fromType(matchedType: Int): Try[DealerType] = matchedType match {
      case t if t == 1 => Success(1)
      case t if t == 2 => Success(2)
      case _ => Failure(new RuntimeException(s"unmatched dealer type $matchedType"))
    }
  }

  //import com.lz.stationApi.station.model.entity.stationWrites
  implicit val dealerWrites = Json.writes[Dealer]
}
