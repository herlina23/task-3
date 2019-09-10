package jsn

import play.api.libs.json.Reads._
import play.api.libs.json._
import org.joda.time.DateTime
import scala.util.Try
import task3.models.CleanStream

trait SocialMedia {
  def toCleanStream: CleanStream
}

case class Fb(
    originalId: String,
    content: String,
    timestamp: String
) extends SocialMedia {
  override def toCleanStream: CleanStream =
    CleanStream(originalId, content, DateTime.now())
}

object Fb {
  implicit val jsonFormat: Format[Fb] = new Format[Fb] {
    override def reads(json: JsValue) =
      Try {

        val originalId = (json \ "id").as[String]
        val content = (json \ "message").as[String]
        val timestamp = (json \ "created_time").as[String]
        Fb(originalId, content, timestamp)
      }.fold(err => JsError(err.getMessage), JsSuccess(_))

    override def writes(o: Fb) = Json.obj(
      "id" -> o.originalId,
      "message" -> o.content,
      "created_time" -> o.timestamp
    )
  }
}
