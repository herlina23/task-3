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

//https://github.com/akka/akka/blob/v2.5.25/akka-docs/src/test/scala/docs/stream/io/StreamFileDocSpec.scala

object Bacafile {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("KafkaProducer")
    implicit val materializer = ActorMaterializer()

    val fileSource = Paths.get("E:\\Project_A\\hasil_crawler\\try1.json")

    val fileSink = Paths.get("E:\\Project_A\\hasil_crawler\\hasilProses.json")

    def handle(b: ByteString): Unit //#file-source
    = ()

    //#file-source

    val foreach: Future[IOResult] =
      FileIO.fromPath(fileSource).to(Sink.ignore).run()

    // val foreach: Future[IOResult] =
    //   FileIO.fromPath(fileSource).to(Sink.head).run()
    //#custom-dispatcher-code
    FileIO
      .fromPath(fileSource)
      .withAttributes(
        ActorAttributes.dispatcher("custom-blocking-io-dispatcher")
      )
    //#custom-dispatcher-code

    //#file-sink
    val text = Source.single("hihihihi")
    val result: Future[IOResult] =
      text.map(t => ByteString(t)).runWith(FileIO.toPath(fileSink))
    //#file-sink

  }

}
