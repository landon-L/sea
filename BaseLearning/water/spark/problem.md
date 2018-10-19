## 学习spark 遇到的问题
## mysql 字符编码问题 -（未解决）
> com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Specified key was too long; max key length is 767 bytes
> 尝试设置 alter database dbname character set latin1;  不能解决
> 分析，应该是有一个设置不能为utf8()