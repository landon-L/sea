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


# range(5) 可以直接生成要给序列list
[0, 1, 2, 3, 4]

要创建一个set，可以提供一个list作为输入集合：python2 和python3中有区别
s = {1,3,5}
可以对set进行集合运算

set和dict的唯一区别仅在于没有存储对应的value，但是，set的原理和dict一样，所以，同样不可以放入可变对象，因为无法判断两个可变对象是否相等，也就无法保证set内部“不会有重复元素”。试试把list放入set，看看是否会报错。
```