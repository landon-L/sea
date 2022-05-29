# 这里是es的学习笔记

# 启动es
su lidongyang
./bin/elasticsearch -d




## ssh免密登录远端服务器
```
ssh-keygen -t rsa
ssh-copy-id ldz@192.168.0.1
```

## 增加elasearch 用户和相关目录
```

groupadd  elasearchs增加用户组。

useradd  elasearch –g elasearchs 增加用户到用户组。

chown –R elasearch elasticsearch-6.3.2 增加用户组权限。

创建ES数据文件和日志文件，直接在root 用户根目录创建即可

   mkdir /data 创建文件夹data

   mkdir /logs 创建文件夹logs

   chown –R elasearch /data给用户组增加权限

   chown –R elasearch /logs 给用户组增加权限

   su – elasearch 切换用户

```

##  max file descriptors [4096] for elasticsearch process is too low, increase to at least [65536] 意思是说你的进程不够用了
```

解决方案： 切到root 用户：进入到security目录下的limits.conf；执行命令 vim /etc/security/limits.conf 在文件的末尾添加下面的参数值：

* soft nofile 65536
* hard nofile 131072
* soft nproc 2048
* hard nproc 4096

前面的*符号必须带上，后面有空格，然后重新启动就可以了。执行完成后可以使用命令 ulimit -n 查看进程数
```

## max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]  需要修改系统变量的最大值了
```

解决方案：切换到root用户修改配置sysctl.conf  增加配置值： vm.max_map_count=655360

执行命令 sysctl -p   这样就可以了，然后重新启动ES服务 就可以了

```

## es中的四个主要概念
1. 索引
2. type
3. documents
4. fields


## es 命令
```
查看所有的索引
GET /_cat/indices?v


指定id进行put操作
PUT /customer/_doc/2?pretty
{
  "name": "Jane Doe"
}

不指定id进行post操作
POST /customer/_doc?pretty
{
  "name": "Jane Doe"
}


批量处理
POST /customer/_bulk?pretty
{"index":{"_id":"1"}}
{"name": "John Doe" }
{"index":{"_id":"2"}}
{"name": "Jane Doe" }



导入json文件到es中

curl -H "Content-Type: application/json" -XPOST "localhost:9200/bank/_bulk?pretty&refresh" --data-binary "@accounts.json"
curl "localhost:9200/_cat/indices?v"、

```

因为：http请求有8中，别的存在的意义是什么
get,post,put,head,delete,put,option,trace,connect