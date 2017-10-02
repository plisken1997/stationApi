package com.lz.stationApi.dealer.model

import com.lz.stationApi.station.model.entity.Station

import scala.util.{Failure, Success, Try}

package object entity {

  sealed trait DealerType {
    val dealerType = -1
  }
  case class DealerTypeA() extends DealerType {
    override val dealerType = 1
  }
  case class DealerTypeB() extends DealerType {
    override val dealerType = 2
  }

  object DealerType {
    def fromType(matchedType: Int): Try[DealerType] = matchedType match {
      case t if t == 1  => Success(DealerTypeA())
      case t if t == 2  => Success(DealerTypeB())
      case _ => Failure(new RuntimeException(s"unmatched dealer type $matchedType"))
    }
  }

  type DealerId = Int
  type DealerName = String
  type Stations = List[Station]
}
