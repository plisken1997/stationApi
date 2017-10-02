package com.lz.stationApi.station.model.entity

case class Station(
                    id: StationId,
                    dealerId: DealerId,
                    countryCode: CountryCode,
                    stationName: StationName,
                    latitude: Latitude,
                    longitude: Longitude,
                    address: StationAddress,
                    postalCode: PostalCode,
                    city: City
                  )

object Station {

  val propertyMap = Seq(
    "id",
    "dealerId",
    "countryCode",
    "stationName",
    "latitude",
    "longitude",
    "address",
    "postalCode",
    "city",
  ).zipWithIndex.toMap

  private def eltAt(pos: String): Option[Int] = propertyMap.get(pos)

  /**
    *
    * @param station
    * @param filter
    * @return
    */
  def matchValue(station: Station, filter: (String, String)): Boolean = eltAt(filter._1) exists { pos =>
    station.productElement(pos) match {
      case v: Int => v == filter._2.toInt
      case v: String => v == filter._2
      case v: Double => v == filter._2.toDouble
      case _ => false
    }
  }
}