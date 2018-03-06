package com.example.hellostream.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.example.hellostream.api.KamonlagomreproStreamService
import com.example.hello.api.KamonlagomreproService

import scala.concurrent.Future

/**
  * Implementation of the KamonlagomreproStreamService.
  */
class KamonlagomreproStreamServiceImpl(kamonlagomreproService: KamonlagomreproService) extends KamonlagomreproStreamService {
  def stream = ServiceCall { hellos =>
    Future.successful(hellos.mapAsync(8)(kamonlagomreproService.hello(_).invoke()))
  }
}
