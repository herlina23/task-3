package jsn

import play.api.libs.json.Reads._
import play.api.libs.json._
import org.joda.time.DateTime
import scala.util.Try
import task3.models.CleanStream

trait Insta {
  def toCleanStream: CleanStream
}

case class Instagram(
    originalId: String,
    content: String,
    timestamp: String
) extends Insta {
  override def toCleanStream: CleanStream =
    CleanStream(originalId, content, DateTime.now())
}

object Instagram {
  implicit val jsonFormat: Format[Instagram] = new Format[Instagram] {
    override def reads(json: JsValue) =
      Try {

        val originalId = (json \ "id").as[String]
        val content = (json \ "caption" \ "text").as[String]
        val timestamp = (json \ "created_time").as[String]
        Instagram(originalId, content, timestamp)
      }.fold(err => JsError(err.getMessage), JsSuccess(_))

    override def writes(o: Instagram) = Json.obj(
      "id" -> o.originalId,
      "text" -> o.content,
      "created_time" -> o.timestamp
    )
  }
}
