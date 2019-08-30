/**
  * @author sjalipar
  */
object myMain {
  def main(args: Array[String]): Unit = {

    println("test - start")
    import scala.concurrent.Future
    import scala.util.Success
    import scala.util.Failure
    import scala.concurrent.Await
    import scala.concurrent.duration.DurationInt
    import scala.concurrent.ExecutionContext.Implicits.global

    val x1 = Future {
      Thread.sleep(2000)
      println("x1 is completed")
      "Hello x1"
    }

    val x2 = Future {
      Thread.sleep(4000)
      println("x2 is completed")
      throw new Exception("Exception occured with x2")
    }

    val x3 = Future {
      Thread.sleep(10000)
      println("x3 is completed")
      "Hello x3"
    }

    val x = Future.sequence(List(x1, x2, x3))

    x.onComplete {
      case Success(res) => println("Success: " + res)
      case Failure(ex)  => println("Ohhh Exception: " + ex.getMessage)
    }

    Await.ready(x, 20.seconds)

    println(s"Future val is: ${x.value}")
    Thread.sleep(2000)
    println("test - end")
  }
}
