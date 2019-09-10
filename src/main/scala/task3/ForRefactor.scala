package coba
import java.io._
import play.api.libs.json.{JsError, JsSuccess, Json}
import scala.util.Success
import scala.util.Failure
import org.joda.time
import org.joda.time.Seconds
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import jsn.Twitter
import jsn.Fb
import jsn.Instagram
import _root_.scala.io.Codec

object Coba5b {

  def main(args: Array[String]): Unit = {

    val load2 = for {
      fileList <- Future {
        val path = "E:\\Project_A\\hasil_crawler"
        val files = Refactor.loading(path)
        files
      }
      writing <- {
        val listOfBody = fileList
          .map { file =>
            val parsing = Refactor.reading(file)
            parsing
          }

        Future
          .sequence(listOfBody)
      }
      result <- {
        val divi = Refactor.flattening(writing)
        val generated = divi.zipWithIndex.map {

          case (a, b) =>
            Future {
              Refactor.write2File(a, b)
            }

        }
        Future.sequence(generated)
      }
    } yield result

    val result = Await.result(load2, 20.seconds)

  }

}
