name := """rummy-scala"""

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.specs2" %% "specs2-core" % "3.7" % "test"

scalacOptions in Test ++= Seq("-Yrangepos")

fork in run := true
