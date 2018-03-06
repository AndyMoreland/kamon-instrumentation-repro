organization in ThisBuild := "com.example"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.12.4"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test

lazy val `kamon-lagom-repro` = (project in file("."))
  .aggregate(`kamon-lagom-repro-api`, `kamon-lagom-repro-impl`, `kamon-lagom-repro-stream-api`, `kamon-lagom-repro-stream-impl`)

lazy val `kamon-lagom-repro-api` = (project in file("kamon-lagom-repro-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `kamon-lagom-repro-impl` = (project in file("kamon-lagom-repro-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`kamon-lagom-repro-api`)

lazy val `kamon-lagom-repro-stream-api` = (project in file("kamon-lagom-repro-stream-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `kamon-lagom-repro-stream-impl` = (project in file("kamon-lagom-repro-stream-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .dependsOn(`kamon-lagom-repro-stream-api`, `kamon-lagom-repro-api`)
