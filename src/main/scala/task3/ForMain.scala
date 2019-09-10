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

object ForMain {

  implicit class FileTagFutureSupport(fileTag: FileTag) {
    def futureBody(implicit ec: ExecutionContext) =
      Future {
        fileTag.body
      }(ec)
  }

  def main(args: Array[String]): Unit = {

    val load2 = for {
      fileList <- Future {
        val path = "E:\\Project_A\\hasil_crawler"
        val file = new File(path)
        val files = file.listFiles().toList
        files
      }
      writing <- {
        val listOfBody = fileList
          .map { file =>
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

        Future
          .sequence(listOfBody)
      }
      result <- {
        val flattenFile = writing.flatten.flatten
        val divi = flattenFile.grouped(20).toList
        val generated = divi.zipWithIndex.map {
          case (a, b) =>
            Future {
              //   val print2json = Json.toJson(a).toString()
              //   val writer = new PrintWriter(new File(s"jsonfile$b.json"))
              //   writer.write(print2json)
              //   writer.close()
              println(s"jsonfile$b is generated")
            }

        }
        Future.sequence(generated)
      }
    } yield result

    val result = Await.result(load2, 20.seconds)

  }

}