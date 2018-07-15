# lunux 知识点

## ubuntu 下使用root用户

    1. 在终端执行sudo -s，然后输入当前登录的普通用户密码，进入到root用户模式。
    2.  执行gedit /etc/lightdm/lightdm.conf。  或者是user.conf文件
    3.  添加如下两行代码。
        greeter-show-manual-login=true  #  手工录入系统的用户名和密码。
        allow-guest=false  # 不允许guest登录。
    4.  执行sudo passwd root设置root用户密码

## 使用apt-get install 安装软件时候出现被锁定问题

    E: Could not get lock /var/lib/dpkg/lock - open (11: Resource temporarily unavailable)
    E: Unable to lock the administration directory (/var/lib/dpkg/), is another process using it?

    解决办法：
    rm /var/lib/dpkg/lock 文件夹即可