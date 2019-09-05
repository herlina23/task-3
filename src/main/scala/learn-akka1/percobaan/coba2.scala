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

object Coba2 {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("KafkaProducer")
    implicit val materializer = ActorMaterializer()

    val source = Source(List("a", "b", "c"))
    val sink = Sink.fold[String, String]("")(_ + _)

    val runnable: RunnableGraph[Future[String]] = source.toMat(sink)(Keep.right)
    val folding: Future[String] = runnable.run()

    val try1 = folding.map { fd =>
      println(fd)
    }

    var result = Await.result(try1, 1 second)

  }

}
