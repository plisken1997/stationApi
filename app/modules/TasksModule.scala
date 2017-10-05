package modules
import play.api.inject.{SimpleModule, _}
import tasks.UpdateStationsTask

class TasksModule extends SimpleModule(bind[UpdateStationsTask].toSelf.eagerly())
