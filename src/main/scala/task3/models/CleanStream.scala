package task3.models

import org.joda.time.DateTime
import play.api.libs.json.Writes
import play.api.libs.json.JsValue
import play.api.libs.json.Json

case class CleanStream(
    originalId: String,
    content: String,
    timestamp: DateTime
)

// object CleanStream {
//   implicit val writes: Writes[CleanStream] = new Writes[CleanStream] {
//     override def writes(o: CleanStream): JsValue = Json.obj(
//       "id" -> o.originalId,
//       "message" -> o.content,
//       "created_time" -> o.timestamp.toString
//     )
//   }

// }

object CleanStream {
  implicit val writes: Writes[CleanStream] = new Writes[CleanStream] {
    override def writes(o: CleanStream): JsValue = Json.obj(
      "originalId" -> o.originalId,
      "content" -> o.content,
      "timestamp" -> o.timestamp.toString
    )
  }

}

// list of cleanstream
