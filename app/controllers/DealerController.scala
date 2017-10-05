package controllers

import javax.inject.{Inject, Named}

import com.lz.stationApi.dealer.model.query.DealerQuery
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import scala.concurrent.ExecutionContext.Implicits.global

class DealerController @Inject()(cc: ControllerComponents, @Named("InMemoryDealerQuery") query: DealerQuery) extends AbstractController(cc) {
  import com.lz.stationApi.dealer.model.entity.dealerWrites
  /**
    *
    * @param dealerId
    * @return
    */
  def stations(dealerId: Int) = Action.async {
    query.find(dealerId).map { maybeDealer =>
      maybeDealer
        .fold(NotFound(Json.toJson(Map("error" -> s"dealer $dealerId not found"))))(d => Ok(Json.toJson(d)))
    }
  }

  def countByDealer(countryCode: Option[String]) = Action.async {
    val filters = countryCode.map(c => List(("countryCode", c))).getOrElse(Nil)
    query.countStations(filters).map { count =>
      Ok(Json.toJson(count))
    }
  }
}
