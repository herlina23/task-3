name := "task-3"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.7"

libraryDependencies += "joda-time" % "joda-time" % "2.10.3"

lazy val akkaVersion = "2.5.25"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)

libraryDependencies += "com.lightbend.akka" %% "akka-stream-alpakka-amqp" % "0.1"

libraryDependencies += "com.lightbend.akka" %% "akka-stream-alpakka-file" % "1.1.1"
