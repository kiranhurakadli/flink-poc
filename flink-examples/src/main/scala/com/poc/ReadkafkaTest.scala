package com.poc


import java.util.Properties
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.util.serialization.SimpleStringSchema
import org.slf4j.LoggerFactory

object ReadkafkaTest {

  def main(args: Array[String]): Unit = {

    val log = LoggerFactory.getLogger(classOf[App])
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val prop = new Properties
    prop.setProperty("bootstrap.servers", "localhost:9092")
    prop.setProperty("group.id", "testing-abs")
    val source = Utils.getKafkaSource[String]("09", new SimpleStringSchema(), "test", prop)

    val stream = env.addSource(source)



    stream.print()

     env.execute()
  }
}
