package modules
import play.api.inject.{SimpleModule, _}
import tasks.UpdateDealersTask

class MongoDBUpdateDealersTasksModule extends SimpleModule(bind[UpdateDealersTask].toSelf.eagerly())
