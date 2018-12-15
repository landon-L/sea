# spark学习笔记

## spark SQL  -- Spark SQL is a Spark module for structured data processing
> 官方文档地址 [spark SQL](http://spark.apache.org/docs/latest/sql-programming-guide.html)

### hive 底层依赖
* hive on spark -- hive社区
* hive on tez   
* hive on mapreduce  即正常的hive
* spark sql -spark 社区


### sql On hadoop
* Hive
    sql ==> mapreduce
    metastore: 元数据
    sql:database、table、 view
* impala
    cloudera: cdh,cm
* presto
    facebook 贡献
    京东使用
* spark sql

### Spark SQL 总结
* 能够访问hive,json parquet文件数据
* 提供了SQL API,DataFrame 和 Dataset的api

### Spark SQL 架构图

### 开发
* sqlContext  通过脚本传递参数，用来制定文件路径，可以直接解析json文件
* hiveContext  --jars 引入mysql的jar包
* sessionContext
* spark-sql  可以直接用来读取hive数据

### hive  平滑过渡到 spark sql (1-2即可)
* spark 配置文件下加入hive-site.xml  ,其中包括mysql的一些配置
* 执行spark-shell  或spark-sql  引入mysql的jar包即可
* explain extend sql 语句  查看执行计划

spark.sql("show tables).show  来进行sql查询，基于hive，其实就是操作sparkSession 在进行表的查询  

### thriftserver  /beeline 使用   --区别与spark-sql 访问hive数据的另一种方式，有监控页，可以查询具体的执行过程。直接显示结果，有点类似客户端和服务器的关系。同样的hive也有 hiveServer2 beline
* 启动thriftserver :默认端口10000 ，可以修改
* 启动beline beeline -u jdbc:hive2://localhost:10000 -n hadoop
* 区别：spark-shell/spark-sql 相当于启动启动了一个applicatin,多个shell就多个application。但是通过thriftServer的方式，只启动一个，多个客户端之间可以进行数据共享，正式开发较常用。

* 引入hive.jdbc  jar 进行开发

* 使用hive-jdbc 来操作hive表
```
Class.forName("org.apache.hive.jdbc.HiveDriver")
val conn = DriverManager.getConnection("jdbc:hive2://hadoop001:14000", "hadoop","")
val pstmt = conn.prepareStatement("select empno,ename,sal from emp")
val rs = pstmt.executeQuery()
while(rs.next()){
    println("" + rs.getInt("empno")
    "" + rs.getString("ename")
    "" + rs.getDouble("sal))

}

这段代码和spark没有任何关联，完全是使用scala来操作hive
```
 
### DataFrame 和RDD对比
* DataFrame灵活性更高，针对表进行操作
* RDD java/scala  ===> jvm   python ====>python runtime
* dataFrame     java/scala/python ===> Logic Plan(逻辑执行计划)


### rdd 转换 DataFrame 两种方式
> (Interoperating with RDDs)[http://spark.apache.org/docs/2.2.0/sql-programming-guide.html#inferring-the-schema-using-reflection]



### DataFrame   show()有各种重载方法  

### DataFrame 转换DataSet   通过as()函数

### 操作数据总共三种方式
* sql 
* DataFrame 
> val videoAccessTopNDF = accessDF.filter($"day" === "20180925" && $"cmsType" === "video")
          .groupBy("day", "cmsId").agg(count("cmsId").as("times")).orderBy($"times".desc)

* DataSet  编译的时候就知道有哪些列了，更高级一些



### 外部数据源
* [外部数据源官网](https://spark-packages.org/)
* 默认未parquet 类型数据
× 设置driver -必须
* 可以把hive和mysql中的表进行链接操作，通过sparkSql



### 使用github上开源库
* git clone https://github.com/wzhe06/ipdatabase.git
* 编译下载的项目  mvn clean package -DskipTests
* 安装到本地仓库 mvn install:install-file -Dfile=/usr/ipdatabase.jar 
-DgroupId=com.ldy -DartifactId=ipdatabase -Dversion=1.0 -Dpackaging=jar


###  数据清洗

* 控制文件输出的大小  rdd的coalesce(1) 函数，可以控制分区输出文件个数
 

### 读取文件
* json格式文件可直接读取  val peopleDF = spark.read.json(path)

* parquet 格式数据（列式存储）  val parquetFileDF = spark.read.parquet("people.parquet")

### 写出文件
*  parquet  分区写出  accessDF.coalesce(1).write.format("parquet").mode(SaveMode.Overwrite)
          .partitionBy("day").save("/Users/rocky/data/imooc/clean2")


## 调优点
1. 控制文件输出的大小： coalesce(1)
2. 分区字段的数据类型调整：spark.sql.sources.partitionColumnTypeInference.enabled = false
所有的分区类型都以String 类型存储


### 可视化开发框架
* echarts
* highcharts
* D3.js
* HUE
* Zeppeline


