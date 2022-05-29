#!/usr/bin/python
# encoding=utf-8
import sys, getopt

def demo_01():
    import sys
    print("====import mode====")
    for i in sys.argv:
        print(i)
    print("\n 环境变量" ,sys.path)



def main(argv):
    '''
    执行命令如下：
    python  e:/xiayule/BaseLearning/coding/python/python01.py -i intput -o output  55 66
    python  e:/xiayule/BaseLearning/coding/python/python01.py --ifile=intput --ofile=output 55 77

    hi:o: 表示可以接受三个参数： -h  后面没有附加参数    -i  后面有附加参数    -o 后面有附加参数

    输出：
    输入的文件为： intput
    输出的文件为： output
    opts [('--ifile', 'intput'), ('--ofile', 'output')]
    args ['55', '77']
    '''
    inputfile = None
    outputfile = None
    try:
        opts, args = getopt.getopt(argv, "hi:o:",["ifile=","ofile="])
    except getopt.GetoptError:
        print ('test.py -i <inputfile> -o <outputfile>')
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print ('test.py -i <inputfile> -o <outputfile>')
            sys.exit()
        elif opt in ("-i", "--ifile"):
            inputfile = arg
        elif opt in ("-o", "--ofile"):
            outputfile = arg
    print ('输入的文件为：', inputfile)
    print ('输出的文件为：', outputfile)
    print("opts", opts)
    print("args", args)

def demo_set():
    # 集合的
    classA = {"Tom","Jim","Jack","Tom"}
    classD = {"Tom"}
    classC = set("abctoa")
    classB = set("Tom")
    print("集合测试")
    print(classA & classB)
    print(classB & classC)
    print(classD & classB)

    print(classD | classC)  
    print(classB | classC) 
    print(classA | classC)

def demo_dic():

    # 字典类型
    dic = {}
    dic["one"] = 1
    dic[2] = 0.1
    print(dic[2])

    dica = {x: x**2 for x in (2, 4, 6)}
    print(dica)

    
if __name__ == "__main__":
    print "hello"
#    main(sys.argv[1:])


