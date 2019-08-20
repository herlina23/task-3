package task3

/**
  * Create a program that:
  *   1. receive 1 argument from command line
  *   2. read all files from the path based on the argument
  *   3. parse the content of the files into JsValue
  *   4. transform all of the JsValues into CleanStreams
  *   5. write the CleanStreams into several files
  *
  * */

object Main {

  def main(args: Array[String]): Unit = {
    val path = args.head
    println(s"${args.toList}")
    println(s"arg: ${path}")
  }

}
