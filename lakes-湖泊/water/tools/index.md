# 这里包含各种工具的安装和使用
## plsql的安装、使用，遇到的问题
> 环境变量配置

    变量名：ORACLE_HOME  变量值：E:\PLSQLDeveloper\PLSQL\instantclient_11_2

    变量名：TNS_ADMIN  变量值：E:\PLSQLDeveloper\PLSQL\instantclient_11_2

    变量名：NLS_LANG   变量值：SIMPLIFIED CHINESE_CHINA.ZHS16GBK   --解决中文乱码的问题

> ORA-12154:TNS:无法解析指定的连接标识符

    解决方案1：行首一定不能出现空格
    192.168.101.39 =
    (DESCRIPTION =
    (ADDRESS = (PROTOCOL = TCP)(HOST = 192.168.101.39)(PORT = 1521))
    (CONNECT_DATA =
        (SERVER=DEDICATED)
        (SERVICE_NAME = yunsunv3yt)
    ))

    解决方案2：登录框Database 中直接输入  ip:port/service_name

## mysql window 下安装（非msi）
    1.修改my.ini文件中的basedir ,datadir 路径
        basedir=C:\Program Files\mysql-5.6.40
        datadir=C:\Program Files\mysql-5.6.40\data
    2.以管理员身份运行cmd，或者powershell, 执行mysqld -install
        net start mysql 安装mysql服务
        mysqld -remove 删除服务
        出现1067的错误，则为配置文件修改错误，尽量不要动默认配置，也不要尽信网上教程

    修改用户名密码
    set password for root@localhost = password('123');

    


## redis配置允许远程访问，或者加密码访问
    设置安全模式 no
    protected-mode no
    设置绑定端口为，为允许所有电脑访问的意思
    bind 0.0.0.0
    考虑安全性，可以设置密码
    # requirepass 123456  打开即可

    
