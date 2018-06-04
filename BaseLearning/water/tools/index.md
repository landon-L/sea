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

    
