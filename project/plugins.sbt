// The Lagom plugin
addSbtPlugin("com.lightbend.lagom" % "lagom-sbt-plugin" % "1.4.1")
// Needed for importing the project into Eclipse
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.2.4")

// for aspectj
addSbtPlugin("io.kamon" % "sbt-aspectj-runner" % "1.1.0")

// to bundle aspectj w/ docker images
addSbtPlugin("com.lightbend.sbt" % "sbt-javaagent" % "0.1.4")

// add aspectj for lagom dev mode
addSbtPlugin("io.kamon" % "sbt-aspectj-runner-play-2.6" % "1.1.0")
