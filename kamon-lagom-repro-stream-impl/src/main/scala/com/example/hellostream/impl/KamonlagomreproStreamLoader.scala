package com.example.hellostream.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import com.example.hellostream.api.KamonlagomreproStreamService
import com.example.hello.api.KamonlagomreproService
import com.softwaremill.macwire._

class KamonlagomreproStreamLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new KamonlagomreproStreamApplication(context) {
      override def serviceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new KamonlagomreproStreamApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[KamonlagomreproStreamService])
}

abstract class KamonlagomreproStreamApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer = serverFor[KamonlagomreproStreamService](wire[KamonlagomreproStreamServiceImpl])

  // Bind the KamonlagomreproService client
  lazy val kamonlagomreproService = serviceClient.implement[KamonlagomreproService]
}
