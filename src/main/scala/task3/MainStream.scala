package akka123
import java.nio.file.{Files, Path}
import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import play.api.libs.json.{JsError, JsSuccess, Json}
import akka.stream.alpakka.file.scaladsl.Directory
import java.nio.file.{Files, Path}
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import play.api.libs.json.{JsError, JsSuccess, Json}
import scala.util.Success
import scala.util.Failure
import akka.NotUsed
import java.nio.file.{Files, Paths}
import akka.stream._
import akka.stream.scaladsl.{FileIO, Sink, Source}
import akka.util.ByteString
import scala.concurrent.Future
import java.io._
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import play.api.libs.json.{JsError, JsSuccess, Json}
import scala.util.Success
import scala.util.Failure
import akka.NotUsed
import java.nio.file.{Files, Paths}
import akka.stream._
import akka.stream.scaladsl.{FileIO, Sink, Source}
import akka.util.ByteString
import scala.concurrent.Future
import akka.Done
import task3.models.CleanStream
import jsn.Instagram
import jsn.Twitter
import jsn.Fb
import coba.Refactor

object MainStream {
  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem("cobaScala")
    implicit val materializer = ActorMaterializer()

    val logFile =
      Paths.get("E:\\Project_A\\hasil_crawler")

    val streamSource = Directory
      .ls(logFile)
      .mapAsyncUnordered(3) { path =>
        FileIO
          .fromPath(path)
          .runFold(ByteString(""))(_ ++ _)
          .map { bs =>
            val readStream = Refactor.readStream(bs, path)
            val divi = Refactor.flattenStream(readStream)
            val generated = divi.zipWithIndex.map {
              case (a, b) =>
                Refactor.write2File2(a, b)

            }

          }
      }

    val result = streamSource.runWith(Sink.seq)

    val hasil = result.map { coba =>
      println(coba)
    }

    val last = Await.result(hasil, 1000 second)

  }

}
