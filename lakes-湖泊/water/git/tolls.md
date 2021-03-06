# git使用过程中的知识点
## git撤销本地修改与回退版本

    即放弃对本地已修改但尚未提交的文件的修改，还原其到未修改前的状态。 
    注意： 已 add/ commit 的文件不适用个方法，应该用本文提到的第二种方法。

    命令如下：

    git checkout .      # 撤销对所有已修改但未提交的文件的修改，但不包括新增的文件
    git checkout [filename]     # 撤销对指定文件的修改，[filename]为文件名

## 回退项目版本

    使用 git reset 回退项目版本
    可以回退到任意已经提交过的版本。已 add / commit 但未 push 的文件也适用。

    命令如下：

    git reset --hard [commit-hashcode]  
    # [commit-hashcode]是某个 commit 的哈希值，可以用 git log 查看
    因此一般用法是先用 git log 查看具体commit的哈希值，然后 reset 到那个版本

注意：
以上两个命令都不会对新增文件起作用。因为新增的文件是还未加到 git 的记录里面的，即属于未被 tracked 的状态，所以撤销修改和回退均对其不影响。我们直接手动删除文件就行了。

## toroise git 执行  clean up 和 revert 即可，如果有目录删除想回退，则需要手动建立对应文件夹。

## 拉取指定分支代码
```
clone码云代码，https不行就尝试ssh
git clone -b feature/yunsun-app git@gitee.com:egova/accident-police.git
https方式：
https://gitee.com/egova/accident-police.git
码云账号密码：
chendebao1985@163.com hwits888
```

## mvn 安装jar到本地仓库
```
mvn安装jar包到本地仓库：
mvn install:install-file -DgroupId=rt -DartifactId=rt -Dversion=1.8 -Dpackaging=jar -Dfile=rt.jar
```


## git 配置免密登录
    本地生成ssh，添加到github 网站中的ssh-key中，表明服务器接受这台机器的请求
    git config --global user.name "xiayule"
    git config --global user.email "1412840154@qq.com"

    添加公钥后执行如下命令，检测是否可以正常登录到github
    ssh -T git@github.com
    如果报一下错误：
    sign_and_send_pubkey: signing failed: agent refused operation

    这个时候我们只要执行下
    eval "$(ssh-agent -s)"
    ssh-add

    此时可以正常使用git，但是还不能免密登录。继续如下操作
    vs code中需要制定git.path的路径  ubuntu 系统默认为 /usr/bin,可以通过 whereis git 来查看该路径
     git config --global credential.helper store

    git config --list 查看所有配置
