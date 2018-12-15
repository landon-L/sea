# 这里记录数据库开发过程中遇到的一些问题，及总结

## mysqlserver 8.0 安装步骤，解压包安装
* 配置mysql 环境变量  /xxx/bin
* mysqld --install
* 以管理员身份运行cmd  mysqld --initialize --console   在目录下生成data目录,此时控制台会输出 随机生成的root用户密码：xxx
* 启动服务：net start mysql
* 登陆  mysql -u root -p mima
* 修改用户名密码：ALTER user 'root'@'localhost' IDENTIFIED BY 'Cliu123#'


## ora-28000 账户被锁定

![Image text](https://raw.githubusercontent.com/onepeoplerainyday/javabasepractice/master/imge/ora_28000%E8%B4%A6%E5%8F%B7%E8%A2%AB%E9%94%81%E5%AE%9A.png)

## mysql常用命令
> 查看mysql编码格式  show variables like 'character_set_database'
> linux 启动mysql   sudo service mysqld start/stop/restart
> 查看一个表的所有字段 SHOW  Full FIELDS FROM iip.iip_task  

> select table_name,table_comment from information_schema.tables where                  table_schema = 'iip' 查看一个schemal下的所有表

## oracle下常用命令
```
    select dbms_random.value(0,5) from dual   oracle中用来生成随机数
    指定范围内的整数：
    select trunc(dbms_random.value(0,100)) from dual;
    生成随机字符串
    select substr(cast(dbms_random.value as varchar2(38)),3,20) from dual;
    更新oracle中数据表的指定列为随机数
    update DLMIS.TOLL_COMPANY_RELATION  set distance = dbms_random.value(0,5)
```


## mysql 8密码验证方式导致的navicat连接服务出错

    解决办法：ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';

## window 下修改mysql数据库的编码方式为UTF8

    编辑my.ini文件添加如下两项，重启服务。dos下查看编码格式：show variables like "%char%"
    [mysqld]
    character-set-server=utf8

    [client]
    default-character-set=utf8

    net stop mysql
    net start mysql

    bin/mysqld --install  安装mysql服务到window,这里可以指定配置文件

    bin/mysqld --remove 移除mysql服务
    bin/mysqld --initialize  初始化，这是后要清空data目录

    ···
    [client]
    port=3306
    default-character-set=utf8

    [mysqld]
    port=3306
    character-set-server=utf8
    collation-server=utf8_general_ci
    basedir=D:\mysql-5.7.19-winx64   注意这里不能使用%MYSQL_HOME%来引入位置
    datadir=D:\mysql-5.7.19-winx64\data

    [WinMySQLAdmin]
    D:\mysql-5.7.19-winx64\bin\mysqld.exe
    ```


## 更新mysql数据库中的指定字段的值

> 去除掉多余的空格   UPDATE com_person SET  id = REPLACE(id, ' ', '') ,personno = replace(personno, ' ', '')



## 乐观锁和悲观锁
> 悲观锁：假定会发生并发冲突，屏蔽一切可能违反数据完整性的操作。[1]      悲观锁假定其他用户企图访问或者改变你正在访问、更改的对象的概率是很高的，因此在悲观锁的环境中，在你开始改变此对象之前就将该对象锁住，并且直到你提交了所作的更改之后才释放锁。悲观的缺陷是不论是页锁还是行锁，加锁的时间可能会很长，这样可能会长时间的限制其他用户的访问，也就是说悲观锁的并发访问性不好。

> 乐观锁：假设不会发生并发冲突，只在提交操作时检查是否违反数据完整性。[1] 乐观锁不能解决脏读的问题。    乐观锁则认为其他用户企图改变你正在更改的对象的概率是很小的，因此乐观锁直到你准备提交所作的更改时才将对象锁住，当你读取以及改变该对象时并不加锁。可见乐观锁加锁的时间要比悲观锁短，乐观锁可以用较大的锁粒度获得较好的并发访问性能。但是如果第二个用户恰好在第一个用户提交更改之前读取了该对象，那么当他完成了自己的更改进行提交时，数据库就会发现该对象已经变化了，这样，第二个用户不得不重新读取该对象并作出更改。这说明在乐观锁环境中，会增加并发用户读取对象的次数。
> interbase和大多数关系数据库一样，采用的是乐观锁，而且读锁是共享的，写锁是排他的。可以在一个读锁上再放置读锁，但不能再放置写锁；你不能在写锁上再放置任何锁。锁是目前解决多用户并发访问的有效手段。  
乐观锁和悲观锁主要应对，丢失更新和脏读的问题。、

如何解决脏读的问题呢？
a用户读取temp值为8，加锁。
b用户读取temp值为8，加锁。
a释放锁，b释放锁，然后b修改数据库中的值。此时a读取的temp仍然为8

只能保证加锁期间数据的正确性？？？  a在更新数据库时会检查数据的版本，如果过时则重新获取数据。

> 乐观锁应用
1. 使用自增长的整数表示数据版本号。更新时检查版本号是否一致，比如数据库中数据版本为6，更新提交时version=6+1,使用该version值(=7)与数据库version+1(=7)作比较，如果相等，则可以更新，如果不等则有可能其他程序已更新该记录，所以返回错误。

2. 使用时间戳来实现.

注：对于以上两种方式,Hibernate自带实现方式：在使用乐观锁的字段前加annotation: @Version, Hibernate在更新时自动校验该字段。

> 悲观锁应用
需要使用数据库的锁机制，比如SQL SERVER 的TABLOCKX（排它表锁） 此选项被选中时，SQL  Server  将在整个表上置排它锁直至该命令或事务结束。这将防止其他进程读取或修改表中的数据。

总结：在实际生产环境里边,如果并发量不大且不允许脏读，可以使用悲观锁解决并发问题；但如果系统的并发非常大的话,悲观锁定会带来非常大的性能问题,所以我们就要选择乐观锁定的方法.