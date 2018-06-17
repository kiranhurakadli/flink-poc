package com.poc



import java.util.Properties

import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer09, FlinkKafkaConsumerBase}
import org.apache.flink.streaming.util.serialization.DeserializationSchema

/**
  *  Utils .....
  */
object Utils {


  def getKafkaSource[T](version: String, schema: DeserializationSchema[T], topic: String, prop: Properties): FlinkKafkaConsumerBase[T] = {
    require(version != null || !version.isEmpty, "version cannot be null or empty!")
    require(schema != null, "schema cannot be null ")
    require(version != null || !version.isEmpty, "version cannot be null or empty!")
    require(topic != null || !topic.isEmpty, "topic cannot be null or empty!")
    version match {
      case "09" => new FlinkKafkaConsumer09[T](topic, schema, prop)
      case  _=> throw  new IllegalArgumentException(s"Unsupported version $version")
    }
  }
}
