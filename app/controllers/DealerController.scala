package controllers

import javax.inject.Inject

import play.api.mvc.{AbstractController, ControllerComponents}

class DealerController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def stations(id: Int) = Action {
    Ok
  }

  def countByDealer = Action {
    Ok
  }
}
