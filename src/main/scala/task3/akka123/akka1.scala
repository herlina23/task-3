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

object NumberSum {
  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem("cobaScala")
    implicit val materializer = ActorMaterializer()

    val logFile =
      Paths.get("E:\\Project_A\\hasil_crawler")

    val source: Source[Path, NotUsed] = Directory.ls(logFile)

    // ini source-nya

    // val streamSource: Source[CleanStream, NotUsed] = Directory
    //   .ls(logFile)
    //   .mapAsyncUnordered(3) { path =>
    //     FileIO
    //       .fromPath(path)
    //       .runFold(ByteString(""))(_ ++ _)
    //       .map { bs =>
    //         val fileContent = bs.utf8String
    //         ///parsing jadi clean stream
    //       }
    //   }

    // #ls
    val result = source.runWith(Sink.seq)

    val pathlist = result.map { res =>
      println(res)

      val res1 = res.map { coba =>
        val fileBuffer = FileIO.fromPath(coba)
        val flow = Framing
          .delimiter(
            ByteString(System.lineSeparator()),
            maximumFrameLength = Int.MaxValue,
            allowTruncation = true
          )
          .map(_.utf8String)

        val sink = Sink.foreach(println)

        fileBuffer
          .via(flow)
          .runWith(sink)
          .andThen {
            case _ =>
              system.terminate()
              Await.ready(system.whenTerminated, 1 minute)
          }

      }

    }

    val last = Await.result(pathlist, 1 second)

  }

}
