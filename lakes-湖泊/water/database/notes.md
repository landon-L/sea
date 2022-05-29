# 这里记录数据库开发过程中遇到的一些问题，及总结

## linux 下启动mysql 服务
* service mysql start


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


## sql语句直接合并一列数据
select name, a.cnt from student left join 
(select count(*) as cnt from student)a
on 1=1

## 升级mysql5.5，5.6.5.7  ---> 8.0，需要修改如下配置
```
多了个cj
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
这里必须设置时区，或者服务端设置默认的时区信息
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/iip?serverTimezone=GMT%2B8

同时引用的jar，也需要升级到8.0系列
```

## mysql 下分组求最大
```
select t.*,.*,g.cnt frt from t_cmm_notice t LEFT JOIN 
(select ntc_typ,count(*) cnt, max(tm_smp) tm_smp from t_cmm_notice group by ntc_typ) g 
on on t.tm_smp_smp =  = g.tm_smp_smp where re g.ntc_typ_typ =  = t.ntc_typ_typ
```
> mysql 中还有一个函数叫做group_concat,可以做组内排序, 当作一个字段输出。否则顺序不保证, 拿到的就不一定是第一个了。配合使用subString_index 函数进行切分，就可以达到取固定字段的目的。

>  尽量设置严格模式，否则聚合得到的字段是随机的。 [参考这里](https://www.cnblogs.com/anstoner/p/6414440.html)
set @@global.sql_mode = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION'
