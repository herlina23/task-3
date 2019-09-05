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

//https://github.com/akka/akka/blob/v2.5.25/akka-docs/src/test/scala/docs/stream/io/StreamFileDocSpec.scala
//https://stackoverflow.com/questions/40224457/reading-a-csv-files-using-akka-streams

object Bacafile2 {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("system")
    implicit val materializer = ActorMaterializer()

    // читать строки из файла журнала
    val logFile =
      Paths.get("E:\\Project_A\\hasil_crawler\\instagram_media_1566062221972")

    val fileSink = Paths.get("E:\\Project_A\\hasil_crawler\\hasilProses1.json")

    val source = FileIO.fromPath(logFile)

    val flow = Framing
      .delimiter(
        ByteString(System.lineSeparator()),
        maximumFrameLength = Int.MaxValue,
        allowTruncation = true
      )
      //.map(_.utf8String)

    val sink = Sink.foreach(println)

    // source
    //   .via(flow)
    //   .runWith(sink)
    //   .andThen {
    //     case _ =>
    //       system.terminate()
    //       Await.ready(system.whenTerminated, 1 minute)
    //   }

    val runnable = source.via(flow).toMat(FileIO.toPath(fileSink))(Keep.right)

    //val folding = runnable.run()

    val runnableGraph ={
      source
        .toMat(
          FileIO.toPath(fileSink)
        )(Keep.right)
    }
    val running=runnableGraph.run()

     val try1 =running.map { fd =>
      println(fd)
    }
    

    val result = Await.result(try1, 1 second)

  }

}
