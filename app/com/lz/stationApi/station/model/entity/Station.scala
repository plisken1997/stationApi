package com.lz.stationApi.station.model.entity

import scala.util.{Failure, Success, Try}

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
                  ) {
  override def toString = s"{$id | $dealerId | $countryCode | $stationName | $address | $city}"
}

object Station {

  import play.api.libs.json._
  implicit val stationFormat: OFormat[Station] = Json.format[Station]

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
      case v: String => v.toLowerCase() == filter._2.toLowerCase()
      case v: Double => v == filter._2.toDouble
      case _ => false
    }
  }

  /**
    * @todo use a type class
    *
    * @param values
    * @return
    */
  def fromCSV(values: Array[String]): Try[Station] = values match {
    case Array(id, dealerId, countryCode, stationName, latitude, longitude, address, postalCode, city) =>
      try {
        Success(Station(
          id.toInt,
          dealerId.toInt,
          countryCode,
          stationName,
          latitude.toDouble,
          longitude.toDouble,
          address,
          postalCode,
          city
        ))
      } catch {
        case e: Error => Failure(new RuntimeException(e.getMessage))
      }
    case _ => Failure(new RuntimeException(s"the csv file contains invalid rows number (${values.mkString(",")})"))
  }
}