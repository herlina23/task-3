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

object Coba1 {

  def main(args: Array[String]): Unit = {
    val load = Future {
      println("1")
    }

    val read = Future {
      println("2")
    }

    val filtering = Future {
      println("3")
    }

    val parsing = Future {
      println("4")
    }

    val grouping = Future {
      println("5")
    }

    val x = Future.sequence(List(load, read, filtering, parsing, grouping))
    Await.ready(x, 20.seconds)

  }

}
