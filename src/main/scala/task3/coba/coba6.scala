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

// /**
//   * Create a program that:
//   *   1. receive 1 argument from command line
//   *   2. read all files from the path based on the argument
//   *   3. parse the content of the files into JsValue
//   *   4. transform all of the JsValues into CleanStreams
//   *   5. write the CleanStreams into several files
//   *
//   * */

object Coba6 {

  def main(args: Array[String]): Unit = {

    //println("##############################################")
    //val path = args.head
    val path = "E:\\Project_A\\hasil_crawler"
    val file = new File(path)
    val files = file.listFiles().toList.map(_.toString)

    case class FileTag(fileName: String, fileType: String) {
      lazy val body = {
        val fileBuffer = io.Source.fromFile(fileName)(Codec.UTF8)
        val fileContent = fileBuffer.getLines().toList
        fileBuffer.close()
        fileContent(1)
        //println(fileContent.split("\n")(1))
      }
    }

    val fileParser = files
      .map { file =>
        val fileStr = file.toString()
        val fileType = if (fileStr.contains("instagram")) {
          "instagram"
        } else if (fileStr.contains("facebook")) {
          "facebook"
        } else if (fileStr.contains("twitter")) {
          "twitter"
        } else "unidentified"

        FileTag(fileStr, fileType)

      }
      .map { fileTag =>
        val body = fileTag.body
        val fileType = fileTag.fileType
        val jsonValue = Json.parse(body)
        val optList =
          if (fileType == "instagram") jsonValue.asOpt[List[Instagram]].map {
            list =>
              list.map(_.toCleanStream)
          } else if (fileType == "facebook") jsonValue.asOpt[List[Fb]].map {
            list =>
              list.map(_.toCleanStream)
          } else if (fileType == "twitter") jsonValue.asOpt[List[Twitter]].map {
            list =>
              list.map(_.toCleanStream)
          } else None

        optList
      }

    val flattenFile = fileParser.flatten.flatten

    val divi = flattenFile.grouped(20).toList

    divi.zipWithIndex.foreach {
      case (a, b) =>
        val print2json = Json.toJson(a).toString()
        val writer = new PrintWriter(new File(s"try$b.json"))
        writer.write(print2json)
        writer.close()
        println("Json is generated")

    }

  }

}
