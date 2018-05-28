# python 笔记

## 关于实例中第一行代码  #!/usr/bin/python3 的理解：

分成两种情况：

1. 如果调用python脚本时，使用: python script.py 
#!/usr/bin/python 被忽略，等同于注释。
2. 如果调用python脚本时，使用: ./script.py 
#!/usr/bin/python 指定解释器的路径。