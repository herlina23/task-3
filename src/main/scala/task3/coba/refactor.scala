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
import play.api.libs.json.JsValue
import task3.models.CleanStream
import akka.util.ByteString
import java.nio.file.Path

case class FileTag(fileName: String, fileType: String) {
  lazy val body = {
    val fileBuffer = io.Source.fromFile(fileName)(Codec.UTF8)
    val fileContent = fileBuffer.getLines().toList
    fileBuffer.close()
    fileContent(1)
  }
}

object FileTag {
  def apply(fileName: File): FileTag = {
    val fileStr = fileName.toString()
    val fileType = if (fileStr.contains("instagram")) {
      "instagram"
    } else if (fileStr.contains("facebook")) {
      "facebook"
    } else if (fileStr.contains("twitter")) {
      "twitter"
    } else "unidentified"
    FileTag(fileStr, fileType)
  }
}

object Refactor {

  implicit class FileTagFutureSupport(fileTag: FileTag) {
    def futureBody(implicit ec: ExecutionContext) =
      Future {
        fileTag.body
      }(ec)
  }

  def readStream(bs: ByteString, path: Path): Option[List[CleanStream]] = {
    val fileContent = bs.utf8String
    val head = path.toString()
    val sl = fileContent.split("\n")
    val jsonValue = Json.parse(sl(1))

    val optList = {
      if (head.contains("instagram"))
        jsonValue.asOpt[List[Instagram]].map { list =>
          list.map(_.toCleanStream)
        } else if (head.contains("facebook"))
        jsonValue.asOpt[List[Fb]].map { list =>
          list.map(_.toCleanStream)
        } else if (head.contains("twitter"))
        jsonValue.asOpt[List[Twitter]].map { list =>
          list.map(_.toCleanStream)
        } else None

    }
    optList

  }

  def loading(path: String): List[File] = {
    val file = new File(path)
    val files = file.listFiles().toList
    files
  }

  def reading(file: File): Future[Option[List[CleanStream]]] = {
    val body = FileTag(file).futureBody
    val head = file.toString
    val parsing = body.map { exp =>
      val jsonValue = Json.parse(exp)

      val optList =
        if (head.contains("instagram"))
          jsonValue.asOpt[List[Instagram]].map { list =>
            list.map(_.toCleanStream)
          } else if (head.contains("facebook"))
          jsonValue.asOpt[List[Fb]].map { list =>
            list.map(_.toCleanStream)
          } else if (head.contains("twitter"))
          jsonValue.asOpt[List[Twitter]].map { list =>
            list.map(_.toCleanStream)
          } else None

      optList
    }
    parsing

  }

  def flattening(
      writing: List[Option[List[CleanStream]]]
  ): List[List[CleanStream]] = {
    val flattenFile = writing.flatten.flatten
    val divi = flattenFile.grouped(20).toList
    divi
  }

  def flattenStream(
      writing: Option[List[CleanStream]]
  ): List[List[CleanStream]] = {
    val flattenFile = writing.toList.flatten
    val divi = flattenFile.grouped(20).toList
    divi
  }

  def write2File(a: List[CleanStream], b: Int) {
    // val print2json = Json.toJson(a).toString()
    // val writer = new PrintWriter(new File(s"New1/future$b.json"))
    // writer.write(print2json)
    // writer.close()
    println(s"future$b is generated")
  }

}
