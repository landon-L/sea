package BaseLearning.InterviewProgramming;

import org.junit.Test;

/**
 * Created by Administrator on 2017/6/27.
 * 1.整数的原码等于补码
 * 2.负数的补码为负数原码取反加1（符号位不变）
 * 也可以理解为：负数的补码为相对应整数取反加1（包括符号位）
 * 3.负数的符号位可以直接参与运算
 *
 */
public class BinaryOperator_7 {
    /**
     * 题目：不用额外的变量交换两个整数的值
     * 总结：异或运算能够使的两个值的信息以一个值的形式保存下来，
     * 再与其中一个值进行异或运算能够得到另一个值的信息。
     * 其实加法操作也是类似原理。
     */
    @Test
    public void changeTwoNumberWithoutOtherVar() {
        int a = 4;
        int b = 3;
        System.out.println("交换前：a=" + a + " and b=" + b);
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("交换后：a=" + a + " and b=" + b);

        //采用位运算的结果
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("异或运算后：a=" + a + " and b=" + b);
    }

    /**
     * 只用位运算实现加减乘除运算
     */
    @Test
    public void onlyBinaryOperator() {
        int a = 11;
        int b = 4;
        int c = (a & b) + (a | b);
        System.out.println(c);
    }

    @Test
    public void testFourOperator() {
        int a = -4;
        int b = 0;
        int res = add(a, b);
        System.out.println("a+b=" + res);
        int c = 9;
        res = multi(a, c);
        System.out.println("a*c= " + res);
    }
    /**
     * 用位运算代替乘法运算
     * 原理：a*b=a*2^0*b0+a*2^1*b1+……+a*2^i*bi+……+a*2^31*b31
     * 每次计算b的最低位和a的乘积，b>>>1,and a<<1;
     * >>>与>>唯一的不同是它无论原来的最左边是什么数，统统都用0填充
     * @param a
     * @param b
     * @return
     */
    public int multi(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res = add(res, a);//这里注意a的值不能被add函数给修改，所以add总定义为final
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    /**
     * 用位运算代替加法运算
     * 原理：a + b
     * a ^ b 得到的结果为a 和 b 无进位相加的结果
     * (a & b) << 1 得到的结果为a 和 b相加，进位得到的值
     * 所以；a + b = (a^b) +(a & b) << 1
     * 采用迭代，直到b为0
     *
     * @param a
     * @param b
     * @return
     */
    public int add(final int a, final int b) {
        int temp_a = a;
        int temp_b = b;
        int sum = 0;
        while(temp_b != 0) {
            sum = temp_a ^ temp_b;
            temp_b = (temp_a & temp_b) << 1;
            temp_a = sum;
        }
        return sum;
    }

    /**
     * 用位运算代替减法运算
     * 原理：a - b = a + (-b)
     * 得到一个数的相反数，取反加1（补码)
     * 思考：b为0是如何处理的？？
     * @param a
     * @param b
     * @return
     */
    public int minus(int a, int b) {
        return add(a,add(~b, 1));
    }

    /**
     * 求a的相反数
     * @param a
     * @return
     */
    public int negative(int a) {
        return add(~a, 1);
    }

}
