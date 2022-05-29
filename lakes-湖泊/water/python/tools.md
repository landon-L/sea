# python工具安装
## window 下使用vscode进行python开发
1. window 下安装python ，设置环境变量。
2. vscode 中安装python扩展程序。  【查看】-【扩展】
3. vscode设置 python路径   "python.pythonPath" = "xxx\\xxx"
4. 创建python 文件  f5, 开始python之旅


## 安装机器学习环境遇到的问题
'''
Registered email address: 1412840154@qq.com
Product key: 55E9-401F-B07C-A741-3802-1C86-9D7C-3976


pip install --upgrade --no-cache-dir https://get.graphlab.com/GraphLab-Create/2.1/1412840154@qq.com/55E9-401F-B07C-A741-3802-1C86-9D7C-3976/GraphLab-Create-License.tar.gz

'''

> python2.x 升级到python3.x，首先确保安装anaconda
python 安装软件有三种方式
1.软件包解压安装
2.pip
3.conda 包管理器
```
打开Anaconda Command Prompt，输入以下命令：

conda update anaconda

conda create -n py3k python=3.3 anaconda (3.3为版本号，可以改成其他版本)

在安装过程中会有一些提示，输入y按回车即可。

安装完成，重新打开Anaconda Command Prompt，输入以下命令：

activate py3k

然后在终端输入：ipython

这时会显示python的版本信息，ipython的版本信息，并启动·交互性命令窗口。

```


## cond 使用命令

> 查看虚拟环境  conda info -e

> 激活虚拟环境  source activate snowflakes 

> 退出当前激活的环境  source deactivate

> 删除环境  conda env remove -n snowflakes

> 拷贝环境  conda create --clone snowflakes --name snowCopy

> 修改conda 数据源
conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free/
conda config --set show_channel_urls yes


## pip 安装软件,使用国内镜像
```


清华：https://pypi.tuna.tsinghua.edu.cn/simple

阿里云：http://mirrors.aliyun.com/pypi/simple/

中国科技大学 https://pypi.mirrors.ustc.edu.cn/simple/

华中理工大学：http://pypi.hustunique.com/

山东理工大学：http://pypi.sdutlinux.org/ 

豆瓣：http://pypi.douban.com/simple/

pip 命令格式：

pip install -i https://pypi.tuna.tsinghua.edu.cn/simple tensorflow=2.0.0-beta0


设为默认:

修改 ~/.config/pip/pip.conf  (没有就创建一个)
 

    mkdir ~/.pip
    vim .pip/pip.conf

修改 index-url至tuna，例如

    [global]
    index-url = https://pypi.tuna.tsinghua.edu.cn/simple
    trusted-host = pypi.tuna.tsinghua.edu.cn



```
  