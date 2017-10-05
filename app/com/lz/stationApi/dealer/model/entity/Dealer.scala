package com.lz.stationApi.dealer.model.entity

import scala.util.Try

case class Dealer(
                   id: DealerId,
                   name: DealerName,
                   dealerType: DealerType,
                   stations: Stations = Nil
                 )

object Dealer {

  import play.api.libs.json._
  implicit val dealerFormat: OFormat[Dealer] = Json.format[Dealer]

  /**
    * @todo use a type class
    * @param values
    * @return
    */
  def fromCSV(values: Array[String]): Try[Dealer] =
    DealerType
      .fromType(values(2).toInt)
      .map(dealerType => Dealer(
        values(0).toInt,
        values(1),
        dealerType,
        List()
      ))
}