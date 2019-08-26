package jsn

import play.api.libs.json.Reads._
import play.api.libs.json._
import org.joda.time.DateTime
import scala.util.Try
import task3.models.CleanStream

case class Fb123(
    originalId: String,
    content: String,
    timestamp: String
)

object Fb123 {
  implicit val jsonFormat: Format[Fb123] = new Format[Fb123] {
    override def reads(json: JsValue) =
      Try {

        val originalId = (json \ "id").as[String]
        val content = (json \ "message").as[String]
        val timestamp = (json \ "created_time").as[String]
        Fb123(originalId, content, timestamp)
      }.fold(err => JsError(err.getMessage), JsSuccess(_))

    override def writes(o: Fb123) = Json.obj(
      "id" -> o.originalId,
      "message" -> o.content,
      "created_time" -> o.timestamp
    )

  }

//   def convert(c: Fb123) =
//     Json.toJson(
//       "id" -> c.originalId,
//       "message" -> c.content,
//       "created_time" -> c.timestamp
//     )
}
