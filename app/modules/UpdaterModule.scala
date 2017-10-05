package modules

import com.google.inject.AbstractModule
import com.lz.stationApi.dealer.service.actors.{UpdateDealerActor, UpdateDealersActor}
import com.lz.stationApi.station.service.actors.UpdateStationActor
import com.lz.stationApi.station.service.actors.UpdateStationsActor
import play.api.libs.concurrent.AkkaGuiceSupport

class UpdaterModule extends AbstractModule with AkkaGuiceSupport {
  override def configure() = {
    bindActor[UpdateStationActor]("update-station-actor")
    bindActor[UpdateStationsActor]("update-stations-actor")
    bindActor[UpdateDealerActor]("update-dealer-actor")
    bindActor[UpdateDealersActor]("update-dealers-actor")
  }
}
