package com.example.hello.impl

import com.example.hello.api
import com.example.hello.api.{KamonlagomreproService}
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.broker.TopicProducer
import com.lightbend.lagom.scaladsl.persistence.{EventStreamElement, PersistentEntityRegistry}

/**
  * Implementation of the KamonlagomreproService.
  */
class KamonlagomreproServiceImpl(persistentEntityRegistry: PersistentEntityRegistry) extends KamonlagomreproService {

  private val TRACE_KEY = Key

  override def hello(id: String) = ServiceCall { _ =>
    Kamon.
  }
}
