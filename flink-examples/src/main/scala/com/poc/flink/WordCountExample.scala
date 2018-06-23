package com.poc.flink

import java.util.Properties
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.util.serialization.SimpleStringSchema
import org.slf4j.LoggerFactory

object WordCountExample {

  def main(args: Array[String]): Unit = {

    val KAFKA_CONSUMER_GROUP_ID = "test"
    val KAFKA_TOPIC = "test"
    val KAFKA_SERVERS = "localhost:9092"

    val log = LoggerFactory.getLogger(classOf[App])
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val prop = new Properties
    prop.setProperty("bootstrap.servers",KAFKA_SERVERS) // local kafka server.
    prop.setProperty("group.id", KAFKA_CONSUMER_GROUP_ID) //consumer group id..
    val source = Utils.getKafkaSource[String]("09", new SimpleStringSchema(), KAFKA_TOPIC, prop)

    val stream = env.addSource(source)
         val st = stream
           .flatMap(x=>x.split("\\W+")) // split by space
           .map(x=>(x,1))    // map to  word ==> word ,1
           .keyBy(0)   // group by word
           .sum(1)   // sum -> value

    st.print()

    env.execute()
  }
}
