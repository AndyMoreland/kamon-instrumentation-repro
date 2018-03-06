package com.example.hellostream.api

import akka.NotUsed
import akka.stream.scaladsl.Source
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}

/**
  * The kamon-lagom-repro stream interface.
  *
  * This describes everything that Lagom needs to know about how to serve and
  * consume the KamonlagomreproStream service.
  */
trait KamonlagomreproStreamService extends Service {

  def stream: ServiceCall[Source[String, NotUsed], Source[String, NotUsed]]

  override final def descriptor = {
    import Service._

    named("kamon-lagom-repro-stream")
      .withCalls(
        namedCall("stream", stream)
      ).withAutoAcl(true)
  }
}

