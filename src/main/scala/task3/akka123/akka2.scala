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
import scala.util.Random

object cobaRandom {
  def main(args: Array[String]): Unit = {
    def randomString(length: Int) =
      scala.util.Random.alphanumeric.take(length).mkString
    val aa = randomString(5)

    val bb = scala.util.Random.alphanumeric.take(4).mkString
    println(bb)
  }

}
