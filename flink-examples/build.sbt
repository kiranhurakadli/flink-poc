name := "flink-examples"

version := "1.0"

scalaVersion := "2.11.12"


libraryDependencies ++= Seq(
  "org.apache.flink" %% "flink-streaming-scala" % "1.3.1")

// https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "0.9.0.0"

// https://mvnrepository.com/artifact/org.apache.flink/flink-connector-kafka-0.10
libraryDependencies += "org.apache.flink" %% "flink-connector-kafka-0.9" % "1.3.1" exclude("org.apache.kafka", "kafka-clients")


// https://mvnrepository.com/artifact/org.slf4j/slf4j-api
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.5"


// https://mvnrepository.com/artifact/org.slf4j/slf4j-log4j12
libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.7.5"

// https://mvnrepository.com/artifact/org.apache.flink/flink-shaded-jackson
libraryDependencies += "org.apache.flink" % "flink-shaded-jackson" % "2.7.9-3.0"
