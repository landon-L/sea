# rockmq 定位问题
1. ssh连接到rockmq服务所在的服务器，切换到rockmq/bin目录
2. 检测指定topic更新时间，用来确定生产者是否生产数据

    sh mqadmin topicStatus -n 192.168.101.28.9876 -t AlarmV3
    然后可以看到last update的时间，以此做一些判断

## sh mqadmin consumerStatus -n 192.168.101.28:9876 -g CaputreRecord
    查看消费者的状态,有几个消费者，ip多少，实例名是什么，方便后面针对clienid进行细节查询

## sh mqadmin consumerStatus -n 192.168.101.28:9876 -g CaputreRecord -i 192.168.23.1@195984
    查看指定客户端消费状态


# 启动rocketMq命令： 
    sh mqnamesrv &
    sh mqbroker -c ../conf/2m-noslave/broker-a.properties &

1. 登录控制台： 首先进入 RocketMQ 工程，进入/RocketMQ/bin   在该目录下有个 mqadmin 脚本 .
 查看帮助：   在 mqadmin 下可以查看有哪些命令  
 a: 查看具体命令的使用 : sh mqadmin    
 b: sh mqadmin help 命令名称  
例如，查看 updateTopic 的使用
sh mqadmin help updateTopic
2. 关闭nameserver和所有的broker:
   进入到bin下： 
   sh mqshutdown namesrv
   sh mqshutdown broker
3. 查看所有消费组group:
   sh mqadmin consumerProgress -n 192.168.23.159:9876
4. 查看指定消费组下的所有topic数据堆积情况：
    sh mqadmin consumerProgress -n 192.168.23.159:9876 -g ywdGroupConsumer
5. 查看所有topic :
     sh mqadmin topicList -n 192.168.23.159:9876
6. 查看topic信息列表详情统计
   sh mqadmin topicstatus -n 192.168.23.159:9876 -t myTopicTest1
7.  新增topic
   sh mqadmin updateTopic –n 10.45.47.168 –c DefaultCluster –t ZTEExample
8. 删除topic
  sh mqadmin deleteTopic –n 10.45.47.168:9876 –c DefaultCluster –t ZTEExample


## mq消费者消费规则
    ConsumeFromWhere consumeFromWhere 
    消费者从那个位置消费，分别为： 
    1. CONSUME_FROM_LAST_OFFSET：第一次启动从队列最后位置消费，后续再启动接着上次消费的进度开始消费 
    2. CONSUME_FROM_FIRST_OFFSET：第一次启动从队列初始位置消费，后续再启动接着上次消费的进度开始消费 
    3. CONSUME_FROM_TIMESTAMP：第一次启动从指定时间点位置消费，后续再启动接着上次消费的进度开始消费 
    以上所说的第一次启动是指从来没有消费过的消费者，如果该消费者消费过，那么会在broker端记录该消费者的消费位置，如果该消费者挂了再启动，那么自动从上次消费的进度开始