# lunux 知识点

## ubuntu 下使用root用户

    1. 在终端执行sudo -s，然后输入当前登录的普通用户密码，进入到root用户模式。
    2.  执行gedit /etc/lightdm/lightdm.conf。  或者是user.conf文件
    3.  添加如下两行代码。
        greeter-show-manual-login=true  #  手工录入系统的用户名和密码。
        allow-guest=false  # 不允许guest登录。
    4.  执行sudo passwd root设置root用户密码   (后面测试只有这一步也可以)

## 使用apt-get install 安装软件时候出现被锁定问题

    E: Could not get lock /var/lib/dpkg/lock - open (11: Resource temporarily unavailable)
    E: Unable to lock the administration directory (/var/lib/dpkg/), is another process using it?

    解决办法：
    rm /var/lib/dpkg/lock 文件夹即可

## 修改制定用户密码
    passwd hadoop 

## 为制定用户修改文件权限
    chown -u+w /etc/sudores
    rwx 分别为读、写、执行

## 切换用户
    su hadoop  和  su -hadoop区别在于 是否切换shell环境，su - 为真实切换用户，su 只是获取制定用户权限。

## 增加删除用户
    新建删除hadoop，包括/home文件下的用户目录
    useradd -m hadoop
    userdel -r hadoop   （- f 强制删除）

    新建删除hadoop用户，不包含/home下的用户目录
    useradd hadoop
    userdel hadoop

    将一个用户添加到用户组中，千万不能直接用： 
    usermod -G groupA 
 
    这样做会使你离开其他用户组，仅仅做为 这个用户组 groupA 的成员。 
    应该用 加上 -a 选项： 
    usermod -a -G groupA user

    查看用户所属的组
    id hadoop

## vim下进行粘贴复制操作

    使用normal模式下的  v命令，进入visual模式，v+ j/k/h/l   进行文本选中

    对于选中的文本进行如下按键：

    （1.1）d   ------ 剪切操作

    （1.2）y   -------复制操作

    （1.3）p   -------粘贴操作

    （1.4）^  --------选中当前行，光标位置到行首（或者使用键盘的HOME键）

    （1.5）$  --------选中当前行，光标位置到行尾（或者使用键盘的END键）

    (1.6) u -------- 回退上一布，可多次回退


## linux 下定时任务crontab 的使用
> [详情参考]{}  注意cron 为5 位长度

## ubuntu下如何扩展分区，如下两种实现方式
1. 调整分区大小
> 使用图形化工具Gparted,  sudo gparted
这里可以看到系统的分区情况， 挂载点为  “/"为系统盘所在目录 ，linux 系统磁盘格式一般为ext3/ext4 ,还会有一个swap分区，用来缓存数据，加快系统的运行速度。
右键》调整/移动分区大小
之前的空余空间：距离前一个盘符的空间大小
新的大小：当前盘符的大小
之后的空余空间：距离下一个盘符的空间大小

可以实现：不相邻的两个分区合并，需要多次调整相邻的分区，类似冒泡排序多次移动即可。但是系统分区不能成功和非系统分区合并（未成功，理论上可以）
2. 指定 挂载路径到特定的盘符下
https://blog.csdn.net/wr132/article/details/75916692
https://help.ubuntu.com/community/Partitioning/Home/Moving 英文版

主要步骤：

创建新的分区
查询新分区的UUID
修改fstab将新的分区挂载到/media/home目录
使用rsync命令将/home目录下的所有数据转移到/media/home下，其实是移动到了新的盘符下，当下次使用该盘符时候数据就已经有了。
验证是否复制成功
将/home目录改名为/old_home
修改fstab将新的分区挂载到/home
删除旧的/old_home目录
