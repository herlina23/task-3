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

object Coba {
  def main(args: Array[String]): Unit = {

    implicit val actorSystem = ActorSystem("akka-streams-example")
    implicit val materializer = ActorMaterializer()
    val source = Source(1 to 10)
    val sink = Sink.fold[Int, Int](0)(_ + _)

// connect the Source to the Sink, obtaining a RunnableGraph
    val runnable: RunnableGraph[Future[Int]] = source.toMat(sink)(Keep.right)

// materialize the flow and get the value of the FoldSink
    val sum: Future[Int] = runnable.run()

    val try1 = sum.map { coba =>
      println(coba)
    }
    var result = Await.result(try1, 1 second)
  }

}
