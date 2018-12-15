# python语法

## 行与缩进
    python最具特色的就是使用缩进来表示代码块，不需要使用大括号 {} 

    缩进的空格数是可变的，但是同一个代码块的语句必须包含相同的缩进空格数

## 基本数据类型
    Python 中的变量不需要声明。每个变量在使用前都必须赋值，变量赋值以后该变量才会被创建。
    在 Python 中，变量就是变量，它没有类型，我们所说的"类型"是变量所指的内存中对象的类型。
    等号（=）用来给变量赋值。
    等号（=）运算符左边是一个变量名,等号（=）运算符右边是存储在变量中的值。
    
    counter = 100          # 整型变量
    miles   = 1000.0       # 浮点型变量
    name    = "runoob"     # 字符串
## python中数字有四种类型：整数、布尔型、浮点数和复数。
    int (整数), 如 1, 只有一种整数类型 int，表示为长整型，没有 python2 中的 Long。
    bool (布尔), 如 True。
    float (浮点数), 如 1.23、3E-2
    complex (复数), 如 1 + 2j、 1.1 + 2.2j

## 字符串
    python中单引号和双引号使用完全相同。
    使用三引号('''或""")可以指定一个多行字符串。
    转义符 '\'
    反斜杠可以用来转义，使用r可以让反斜杠不发生转义。。 如 r"this is a line with \n" 则\n会显示，并不是换行。
    按字面意义级联字符串，如"this " "is " "string"会被自动转换为this is string。
    字符串可以用 + 运算符连接在一起，用 * 运算符重复。
    Python 中的字符串有两种索引方式，从左往右以 0 开始，从右往左以 -1 开始。
    Python中的字符串不能改变。
    Python 没有单独的字符类型，一个字符就是长度为 1 的字符串。
    字符串的截取的语法格式如下：变量[头下标:尾下标]

## list列表
    1、List写在方括号之间，元素用逗号隔开。
    2、和字符串一样，list可以被索引和切片。
    3、List可以使用+操作符进行拼接。
    4、List中的元素是可以改变的。

## tuple 元组
    1. 元组（tuple）与列表类似，不同之处在于元组的元素不能修改。元组写在小括号 () 里，元素之间用逗号隔开。
    2. 元组中的元素类型也可以不相同

## set集合
    集合（set）是一个无序不重复元素的序列。

    基本功能是进行成员关系测试和删除重复元素。

    可以使用大括号 { } 或者 set() 函数创建集合，注意：创建一个空集合必须用 set() 而不是 { }，因为 { } 是用来创建一个空字典。

    注意：
    classD = {"Tom"}  # set集合只有一个元素
    classC = set("abctoa")   #set集合中有四个元素

## dic 类型
    动态构建dic
    dica = {x: x**2 for x in (2, 4, 6)}

## 数据类型转换
    a = 4.5
    b = int(a)

## 其他
    string, tuples numbers是不可变类型，list,dic是可变类型
    不可变类型做参数类型，不会影响参数本省；可变参数为引用传递，会修改源值。

    在Linux/Unix系统中，你可以在脚本顶部添加以下命令让Python脚本可以像SHELL脚本一样可直接执行
    #! /usr/bin/env python3
    #增加执行权限
    chmod +x hello.py
    ##像shell脚本一样执行
    ./hello.py

    # 可以将python代码编译为java字节码执行
    Python 解释器可不止一种哦，有 CPython、IPython、Jython、PyPy 等

    # python支持逻辑运算符，相当于java中的 && ||  ！
    and or not
    # 成员运算符
    in 、 not in
    # 身份运算符，用来判断是否引用同一个对象
    is、is not
    a = 20
    b = 20 
    a is b    # 结果为true

## 条件控制，循环
    if elif else  每个条件后使用  ”：“，表示条件结束
    没有switch case
    
    #使用下标遍历序列
    for i in range(len(a)):
        print(i,a[i])

    循环语句可以有 else 子句，它在穷尽列表(以for循环)或条件变为 false (以while循环)导致循环终止时被执行,但循环被break终止时不执行。

## 迭代器与生成器
    iter(), next()
    #使用迭代器遍历集合
    it = iter([1,2,3,4])
    for x in it:
        print(x, end="")
    生成器 yield 返回值为迭代器，当运行 next(),从当前位置开始继续执行

## 函数
    def 函数名():
        函数体

    关键字参数：使用关键字参数允许函数调用时参数的顺序与声明时不一致，因为 Python 解释器能够用参数名匹配参数值
    printinfo( age=50, name="runoob" )  #￥ 函数调用时指定
    默认参数：def printinfo( name, age = 35 ):

    不定长参数：加了星号（*）的变量名会存放所有未命名的变量参数。如果在函数调用时没有指定参数，它就是一个空元组
    def functionname([formal_args,] *var_args_tuple ):

    lambda 函数
    lambda [arg1 [,arg2,.....argn]]:expression

    注意：
    Python 中只有模块（module），类（class）以及函数（def、lambda）才会引入新的作用域，其它的代码块（如 if/elif/else/、try/except、for/while等）是不会引入新的作用域的，也就是说这些语句内定义的变量，外部也可以访问

## 全局变量和局部变量
    局部变量可以和全局变量同名，修改局部变量不会影响全局变量，如果在局部作用域想修改全局变量需要使用 gloable 关键字。

    如果要修改嵌套作用域（enclosing 作用域，外层非全局作用域）中的变量则需要 nonlocal 关键字了

    注意：第二个a 没有定义，运行会报错
    a = 10
    def text():
        a = a + 1
        print(a)
    text()

## 数据结构




    
