package com.lz.stationApi.station.model.repository

import javax.inject.{Inject, Singleton}

import com.lz.stationApi.common.repository.EntityUpdater
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.commands.WriteResult
import reactivemongo.bson.{BSONDocument, BSONObjectID}
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection
import com.lz.stationApi.station.model.entity.Station
import reactivemongo.api.ReadPreference

import scala.concurrent.{ExecutionContext, Future}

case class StationDocument(_id: Option[BSONObjectID], station: Station)

object JsonFormats {

  import play.api.libs.json._

  implicit val stationDocumentFormat: OFormat[StationDocument] = Json.format[StationDocument]
}

@Singleton
class DocumentStationRepository @Inject()(
                                           implicit ec: ExecutionContext,
                                           reactiveMongoApi: ReactiveMongoApi
                                         ) extends StationRepository with EntityUpdater[BSONObjectID, Station] {

  import JsonFormats._

  def stationsCollection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection("stations"))

  /**
    *
    * @param entity
    * @return
    */
  override def save(entity: Station): Future[Option[Station]] = {
    stationsCollection
      .flatMap(_.insert(StationDocument(None, entity)))
      .map {
        case w: WriteResult if w.ok => Some(entity)
        case _ => None
      }
  }

  /**
    * @todo implements !!
    * @param entity
    * @return
    */
  override def upsert(entity: Station): Future[Option[Station]] = save(entity)

  /**
    *
    * @param entity
    * @return
    */
  override def update(id: BSONObjectID, entity: Station): Future[Option[Station]] = {
    val selector = BSONDocument("_id" -> id)
    val updateModifier = BSONDocument(
      "$set" -> BSONDocument(
        "id" -> entity.id,
        "dealerId" -> entity.dealerId,
        "countryCode" -> entity.countryCode,
        "stationName" -> entity.stationName,
        "latitude" -> entity.latitude,
        "longitude" -> entity.longitude,
        "address" -> entity.address,
        "postalCode" -> entity.postalCode,
        "city" -> entity.city,
      )
    )

    stationsCollection
      .flatMap(
        _.findAndUpdate(selector, updateModifier, fetchNewObject = true).map(_.result[StationDocument])
      )
      .map { maybeDocument =>
        maybeDocument.map(_.station)
      }
  }
}
