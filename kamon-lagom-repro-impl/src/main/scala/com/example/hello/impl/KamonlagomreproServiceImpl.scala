package com.example.hello.impl

import com.example.hello.api.KamonlagomreproService
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry
import kamon.Kamon
import kamon.context.Key
import org.slf4j.LoggerFactory

import scala.concurrent.{ExecutionContext, Future}

/**
  * Implementation of the KamonlagomreproService.
  */
class KamonlagomreproServiceImpl(
    persistentEntityRegistry: PersistentEntityRegistry)(implicit executionContext: ExecutionContext)
    extends KamonlagomreproService {

  private lazy val logger =
    LoggerFactory.getLogger(classOf[KamonlagomreproServiceImpl])

  private val TRACE_KEY = Key.broadcast("trace_key", "default value")

  override def hello(id: String) = ServiceCall { _ =>
    Kamon.withContextKey(TRACE_KEY, "non default value") {
      logger.info("Inside withContextKey")
      logger.info("Outside future: " + Kamon.currentContext().get(TRACE_KEY))
      Future {
        logger.info("Entering future")
        val traceKeyValue = Kamon.currentContext().get(TRACE_KEY)
        logger.info("In future: " + traceKeyValue)
        traceKeyValue
      }
    }
  }
}
