package com.poc

import java.util.Properties
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.util.serialization.SimpleStringSchema
import org.slf4j.LoggerFactory

object WordCountExample {

  def main(args: Array[String]): Unit = {

    val log = LoggerFactory.getLogger(classOf[App])
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val prop = new Properties
    prop.setProperty("bootstrap.servers", "localhost:9092")
    prop.setProperty("group.id", "testing-abs")
    val source = Utils.getKafkaSource[String]("09", new SimpleStringSchema(), "test", prop)


    val stream = env.addSource(source)
         val st = stream
           .flatMap(x=>x.split("\\W+")) // split by space
           .map(x=>(x,1))    // map to  word ==> word ,1
           .keyBy(0)   // group by word
           .sum(1)   // sum ->value

    st.print()

    env.execute()
  }
}
