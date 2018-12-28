# python 笔记

## 关于实例中第一行代码  #!/usr/bin/python3 的理解：

分成两种情况：

1. 如果调用python脚本时，使用: python script.py 
#!/usr/bin/python 被忽略，等同于注释。
2. 如果调用python脚本时，使用: ./script.py 
#!/usr/bin/python 指定解释器的路径。


## python文件头

```
#!/usr/bin/env python
# -*- coding: utf-8 -*-

第一行注释是为了告诉Linux/OS X系统，这是一个Python可执行程序，Windows系统会忽略这个注释；

第二行注释是为了告诉Python解释器，按照UTF-8编码读取源代码，否则，你在源代码中写的中文输出可能会有乱码。
```

## 字符串处理

```
元组可以格式化显示，厉害了
>>> '%2d-%02d' % (3, 1)
' 3-01'
```
### 集合类处理
```
# list集合表示有序数组，成员可变，可以嵌套list 表示二维数组

a = ["abc",True,123]
a.insert(index, value)
a.append(value)
a.pop(i)

# truple 元组，初始化之后不可修改



```