# 这里记载学习hadoop遇到的问题

## datanode 启动失败
> 问题现象
* 格式化namenode后启动dfs,发现datanode无法正常启动。
> 解决方案 
* 删除tmp/dfs/data 文件夹后解决。
> 原因分析--（不一定正确）
* data 文件夹为无效文件，重新格式化namenode节点后，需要重新生产data文件才有效。

## hive 创建表异常
> For direct MetaStore DB connections, we don't support retries at the client level.
> 解决方法：alter database dbname character set latin1;

设置之后：只有xx_database 编码字符集为latin1
  character_set_client     | utf8                       |
| character_set_connection | utf8                       |
| character_set_database   | latin1                     |
| character_set_filesystem | binary                     |
| character_set_results    | utf8                       |
| character_set_server     | utf8                       |
| character_set_system     | utf8 


