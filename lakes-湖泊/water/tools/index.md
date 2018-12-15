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


## navicate premium 12 破解方法
[navicate 破解方法](https://blog.csdn.net/qq_38698632/article/details/80577938)


## ubuntu 下安装chrome的方法
> 下载 wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
> 安装 sudo dpkg -i google-chrome-stable_current_amd64.deb 

## 解决ubuntu 中sublime3，无法使用中文输入法的问题
[参考这个](https://github.com/lyfeyaj/sublime-text-imfix)
> sublime3 激活码，可用
```
----- BEGIN LICENSE -----
sgbteam
Single User License
EA7E-1153259
8891CBB9 F1513E4F 1A3405C1 A865D53F
115F202E 7B91AB2D 0D2A40ED 352B269B
76E84F0B CD69BFC7 59F2DFEF E267328F
215652A3 E88F9D8F 4C38E3BA 5B2DAAE4
969624E7 DC9CD4D5 717FB40C 1B9738CF
20B3C4F1 E917B5B3 87C38D9C ACCE7DD8
5F7EF854 86B9743C FADC04AA FB0DA5C0
F913BE58 42FEA319 F954EFDD AE881E0B
------ END LICENSE ------
```

## ubuntu中无法挂在ntfs分区的问题
> sudo ntfsfix /dev/sda3    sda3 根据情况来替换


## ubuntu 下安装网易云音乐
```
https://blog.csdn.net/sinat_33718563/article/details/80209274
```



