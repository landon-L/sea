# rockmq 定位问题
1. ssh连接到rockmq服务所在的服务器，切换到rockmq/bin目录
2. 检测指定topic更新时间，用来确定生产者是否生产数据

    sh mqadmin topicStatus -n 192.168.101.28.9876 -t AlarmV3
    然后可以看到last update的时间，以此做一些判断