package controllers

import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import play.api._

class Application extends Controller {

  case class SampleForm(message : String)

  val form1 = Form(
    mapping("input" -> text)(SampleForm.apply)(SampleForm.unapply)
  )

  def index = Action {
    Ok(views.html.index("何か書いてください", form1))
  }

  public static Result send() {
    val f = form1.bindFromRequest

    if( !f.hasErrors) {

      SampleForm data = f.get
      String msg = "You typed: " + data.message
      return ok(index.render(msg, f))
    }else {
      return ok(index.render("Error", form1 ) )
    }

  }

}
