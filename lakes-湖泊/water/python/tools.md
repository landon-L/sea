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