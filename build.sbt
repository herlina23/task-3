name := "task-3"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies += "joda-time" % "joda-time" % "2.10.3"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.7"

//libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.25"

lazy val akkaVersion = "2.5.25"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)

libraryDependencies += "com.lightbend.akka" %% "akka-stream-alpakka-amqp" % "0.1"

//libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "0.10.0"
