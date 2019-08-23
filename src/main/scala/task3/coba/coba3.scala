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

object Coba3 {

  def main(args: Array[String]): Unit = {

    val path = "E:\\Project_A\\hasil_crawler"
    val path1 = "E:\\Project_A\\hasil_crawler\\twitter_crawl_status_445637"
    val file = new File(path)
    val files = file.listFiles().toList.map(_.toString)

    for (lt <- files) {
      val fileBuffer = io.Source.fromFile(lt)(Codec.UTF8)
      val fileContent = fileBuffer.getLines().toList.mkString

      val bfr = fileContent.split("\n").toList

      if (bfr.length >= 1) {
        val a = bfr(0)
        println(a)
      } else {
        println("stop!!!!")
      }

    }

  }

}
