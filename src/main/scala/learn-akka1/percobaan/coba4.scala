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

object Coba4 {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("KafkaProducer")
    implicit val materializer = ActorMaterializer()

    val source = Source(1 to 3)
    val sink = Sink.foreach[Int](println)

    val doubler = Flow[Int].map { elem =>
      elem * 2
    }

    val runnable = source via doubler to sink
    val folding = runnable.run()

  }

}
