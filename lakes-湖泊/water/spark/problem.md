## 学习spark 遇到的问题
## mysql 字符编码问题 -（未解决）
> com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Specified key was too long; max key length is 767 bytes
> 尝试设置 alter database dbname character set latin1;  不能解决
> 分析，应该是有一个设置不能为utf8()


## 异常：org.apache.kafka.common.protocol.types.SchemaException: Error reading field 'topic_metadata': Error reading array of size 1143664, only 19 bytes available
场景：进行spark和kafka整合时出现异常
分析：因为客户端kakfa的版本为0.10（jar包版本），服务端为0.9（环境变量）
解决办法：修改两边版本一致,小版本也要保持一致  0.10.0.1（客户端）  和0.10.2.2（服务端）就不行




## sparks streaming 本身就可以和flume进行对接，为什么还要走kafka呢？
1.spark streaming 可以通过pull方式进行flume sink中的数据消费，效率不好，还是缓存性能不行？  原因：sparkstreaming 扛不住
2.