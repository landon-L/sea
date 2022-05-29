simple-agent.sources = netcat-source
simple-agent.sinks = spark-sink
simple-agent.channels = memory-channel

simple-agent.sources.netcat-source.type = netcat
simple-agent.sources.netcat-source.bind = hadoop000
simple-agent.sources.netcat-source.port = 44444

simple-agent.sinks.spark-sink.type = org.apache.spark.streaming.flume.sink.SparkSink
simple-agent.sinks.spark-sink.hostname = hadoop000
simple-agent.sinks.spark-sink.port = 41414

simple-agent.channels.memory-channel.type = memory

simple-agent.sources.netcat-source.channels = memory-channel
simple-agent.sinks.spark-sink.channel = memory-channel



flume-ng agent --name simple-agent --conf $FLUME_HOME/conf \
--conf-file $FLUME_HOME/conf/flume_pull_streaming.conf \
-Dflume.root.logger=INFO,console

2018,zs
2019,ls
2020,ww


*/1 * * * * * /home/xiayule/develop/python-source/createlog.sh





exec-memory-kafka.sources = exec-source
exec-memory-kafka.sinks = kafka-sink
exec-memory-kafka.channels = memory-channel

exec-memory-kafka.sources.exec-source.type = exec
exec-memory-kafka.sources.exec-source.command = tail -F /home/hadoop/app/python/access.log
exec-memory-kafka.sources.exec-source.shell = /bin/sh -c

exec-memory-kafka.sinks.kafka-sink.type = org.apache.flume.sink.kafka.KafkaSink
exec-memory-kafka.sinks.kafka-sink.topic = exec-memory-kafka
exec-memory-kafka.sinks.kafka-sink.brokerList = hadoop000:9092
exec-memory-kafka.sinks.kafka-sink.batchSize = 20
exec-memory-kafka.sinks.kafka-sink.requiredAcks = 1

exec-memory-kafka.channels.memory-channel.type = memory

exec-memory-kafka.sources.exec-source.channels = memory-channel
exec-memory-kafka.sinks.kafka-sink.channel = memory-channel

启动flume

flume-ng agent --name exec-memory-kafka --conf $FLUME_HOME/conf \
--conf-file $FLUME_HOME/conf/exec-memory-kafka.conf \
-Dflume.root.logger=INFO,console



bin/kafka-topics.sh --create --zookeeper hadoop000:2181 --replication-factor 1 --partitions 1 --topic exec-memory-kafka


kafka消费消息：
bin/kafka-console-consumer.sh --zookeeper hadoop000:2181 --topic exec-memory-kafka --from-beginning 

channel_v3.json