package com.example.hello.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraPersistenceComponents
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import com.example.hello.api.KamonlagomreproService
import com.lightbend.lagom.scaladsl.broker.kafka.LagomKafkaComponents
import com.softwaremill.macwire._

class KamonlagomreproLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new KamonlagomreproApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new KamonlagomreproApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[KamonlagomreproService])
}

abstract class KamonlagomreproApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with CassandraPersistenceComponents
    with LagomKafkaComponents
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer = serverFor[KamonlagomreproService](wire[KamonlagomreproServiceImpl])

  // Register the JSON serializer registry
  override lazy val jsonSerializerRegistry = KamonlagomreproSerializerRegistry

  // Register the kamon-lagom-repro persistent entity
  persistentEntityRegistry.register(wire[KamonlagomreproEntity])
}
