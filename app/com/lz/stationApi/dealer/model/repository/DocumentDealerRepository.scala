package com.lz.stationApi.dealer.model.repository

import javax.inject.{Inject, Singleton}

import com.lz.stationApi.common.repository.EntityUpdater
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.commands.WriteResult
import reactivemongo.bson.{BSONDocument, BSONObjectID}
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection
import com.lz.stationApi.dealer.model.entity.Dealer
import reactivemongo.api.ReadPreference

import scala.concurrent.{ExecutionContext, Future}

case class DealerDocument(_id: Option[BSONObjectID], dealer: Dealer)

object JsonFormats {

  import play.api.libs.json._

  implicit val dealerDocumentFormat: OFormat[DealerDocument] = Json.format[DealerDocument]
}

@Singleton
class DocumentDealerRepository @Inject()(implicit ec: ExecutionContext,
                                         reactiveMongoApi: ReactiveMongoApi
                                        ) extends DealerRepository with EntityUpdater[BSONObjectID, Dealer] {

  import JsonFormats._

  def stationsCollection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection("dealers"))

  /**
    *
    * @param entity
    * @return
    */
  override def save(entity: Dealer): Future[Option[Dealer]] =
    stationsCollection
      .flatMap(_.insert(DealerDocument(None, entity)))
      .map {
        case w: WriteResult if w.ok => Some(entity)
        case _ => None
      }

  /**
    *
    * @param entity
    * @return
    */
  override def upsert(entity: Dealer): Future[Option[Dealer]] = save(entity)

  /**
    *
    * @param id
    * @param entity
    * @return
    */
  override def update(id: BSONObjectID, entity: Dealer): Future[Option[Dealer]] = {
    val selector = BSONDocument("_id" -> id)
    val updateModifier = BSONDocument(
      "$set" -> BSONDocument(
        "id" -> entity.id,
        "name" -> entity.name,
        "dealerType" -> entity.dealerType,
        "stations" -> BSONDocument(entity.stations)
      )
    )

    stationsCollection
      .flatMap(
        _.findAndUpdate(selector, updateModifier, fetchNewObject = true).map(_.result[DealerDocument])
      )
      .map { maybeDocument =>
        maybeDocument.map(_.dealer)
      }
  }
}
