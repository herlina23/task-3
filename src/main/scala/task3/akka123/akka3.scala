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

object Akka3 {
  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem("cobaScala")
    implicit val materializer = ActorMaterializer()

    val logFile =
      Paths.get("E:\\Project_A\\hasil_crawler\\coba1")

    val streamSource = Directory
      .ls(logFile)
      .mapAsyncUnordered(3) { path =>
        FileIO
          .fromPath(path)
          .runFold(ByteString(""))(_ ++ _)
          .map { bs =>
            val fileContent = bs.utf8String

            val sl = fileContent.split("\n")
            val jsonValue = Json.parse(sl(1))

            val parsing = jsonValue.asOpt[List[Instagram]].map { list =>
              list.map(_.toCleanStream)
            }

            val flattenFile = parsing.toList.flatten

            //println(path + "\n" + flattenFile.length)

            ///parsing jadi clean stream

            val divi = flattenFile.grouped(20).toList
            divi.zipWithIndex.foreach {
              case (a, b) =>
                val print2json = Json.toJson(a).toString()
                println(print2json)
              // val writer = new PrintWriter(new File(s"try$b.json"))
              // writer.write(print2json)
              // writer.close()
              //println(s"jsonfile$b is generated")
            }

          }
      }

    val result = streamSource.runWith(Sink.seq)

    val hasil = result.map { coba =>
      println(coba)
    }

    val last = Await.result(hasil, 1 second)

  }

}
