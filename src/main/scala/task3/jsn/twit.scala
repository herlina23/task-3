package jsn

import play.api.libs.json.Reads._
import play.api.libs.json._
import org.joda.time.DateTime
import scala.util.Try
import task3.models.CleanStream

trait Twit {
  def toCleanStream: CleanStream
}

case class Twitter(
    originalId: String,
    content: String,
    timestamp: String
) extends Twit {
  override def toCleanStream: CleanStream =
    CleanStream(originalId, content, DateTime.now())
}

object Twitter {
  implicit val jsonFormat: Format[Twitter] = new Format[Twitter] {
    override def reads(json: JsValue) =
      Try {

        val originalId = (json \ "id_str").as[String]
        val content = (json \ "full_text").as[String]
        val timestamp = (json \ "created_at").as[String]
        Twitter(originalId, content, timestamp)
      }.fold(err => JsError(err.getMessage), JsSuccess(_))

    override def writes(o: Twitter) = Json.obj(
      "id_str" -> o.originalId,
      "full_text" -> o.content,
      "created_at" -> o.timestamp
    )
  }
}
