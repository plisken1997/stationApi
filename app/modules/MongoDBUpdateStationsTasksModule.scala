package modules
import play.api.inject.{SimpleModule, _}
import tasks.UpdateStationsTask

class MongoDBUpdateStationsTasksModule extends SimpleModule(bind[UpdateStationsTask].toSelf.eagerly())
