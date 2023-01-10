scalaVersion := "2.13.10"

name := "zio-http-scenarios"
organization := "io.github.scottweaver"
version := "1.0"

Global / cancelable := true 
Global / fork := true

libraryDependencies ++= Seq(
    "io.d11" %% "zhttp" % "1.0.0.0-RC29",
    "dev.zio" %% "zio" % "1.0.15",
)
