// package task3
// import java.io._

// import scala.util.{Try, Success, Failure}

// /**
//   * Create a program that:
//   *   1. receive 1 argument from command line
//   *   2. read all files from the path based on the argument
//   *   3. parse the content of the files into JsValue
//   *   4. transform all of the JsValues into CleanStreams
//   *   5. write the CleanStreams into several files
//   *
//   * */
// object Main {

//   def main(args: Array[String]): Unit = {
//     val path = args.head
//     //val path = "E:\\Project_A\\hasil_crawler"
//     val file = new File(path)
//     val files = file.listFiles()
//     val rdfile = files.mkString("\n")
//     //val files = Try { file.listFiles() }
//     if (files1 == null) {
//       println(
//         "Path yang Anda masukkan salah!, contoh penulisan path" + "\n" + "E:\\(double backslash)Project_A\\(double backslash)hasil_crawler"
//       )
//     } else {
//       println(("\n") + "Success" + ("\n") + files.mkString("\n"))
//       for(lt<-files){
//         if(lt.con //lt contains suatu kata){
// // coba.filter(_.contains("ni"))
// // fls.map(_.toString)
// // fls2.filter(_.contains("instagram"))
//         }
//       }

//     }

//     // files match {

//     //   case Success(files) =>
//     //     println(("\n") + "Success" + ("\n") + files.mkString("\n"))

//     //   case Failure(ex) => ex

//     // }

//   }

// }
