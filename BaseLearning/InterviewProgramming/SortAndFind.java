package BaseLearning.InterviewProgramming;

import org.junit.Test;
import sun.security.util.Length;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.RandomAccess;

/**
 * Created by Administrator on 2017/7/5.
 */
public class SortAndFind {
    @Test
    public void quickSortTest() {
        Random random = new Random();
        //定义一个数组
        int arr[] = new int[20];
        for (int i = 0; i< 20; i++) {
            arr[i] = random.nextInt(100);
        }
        //调用排序算法
        quickSortRecursion(arr, arr.length, 0, arr.length - 1);
        //quickSortNonRecursion(arr, arr.length, 0, arr.length);
        showArray(arr, arr.length);
    }

    //采用递归的快速排序
    public void quickSortRecursion(int[] arr, int len, int start, int end) {
        if (start > end) {
            return;
        }
        int index = partition(arr, len, start, end);
        if (index > start) {
            quickSortRecursion(arr, len, start, index -1);
        }
        if (index < end) {
            quickSortRecursion(arr, len, index + 1, end);
        }
    }

    private void showArray(int [] arr, int len) {
        System.out.println("arr elements is ");
        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 采用位运算交换两个值
     * @param a 数组下标
     * @param b 数组下标
     */
    private void swap(int []arr, int a, int b) {
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }
    /**
     * 占坑式，且基线元素位置可随意指定
     * 以基础比较位为中心，小的排在左边，大的排在右边
     * @param arr
     * @param start
     * @param end
     * @param len
     * @return 返回已排序元素的下标
     */
    private int partition(int[] arr, int len, int start, int end) {
        //异常情况返回-1
        if (arr == null || len <= 1 || start < 0 || end > len) {
            return -1;
        }
        //以第一个元素作为被排序的元素,这里可以随便指定基线元素位置
        int index = RandomInRange(start, end);
        int temp = arr[index];
        //基线元素首先和第一个元素交换
        swap(arr, index, start);

        while (start < end) {
            //1.找到右边小于基线元素的下标，然后填坑,填坑注意只有满足start<end才可以
            while ((arr[end] > temp) && (end > start)) {
                --end;
            }
            if (start < end) {
                arr[start++] = arr[end];
            }
            //2.找到左边大于基线元素的下标，然后和end做交换
            while ((arr[start] < temp) && (start < end)) {
                start++;
            }
            if (start < end) {
                arr[end--] = arr[start];
            }
        }
        //需要把坑填上，因为坑里面是原有元素的一个副本
        arr[start] = temp;
        return start;
    }

    /**
     * 生成500内，一个范围内的随机数
     * @param start
     * @param end
     * @return
     */
    private int RandomInRange(int start, int end) {
        Random random = new Random(500);
        return (random.nextInt() % start) + start;
    }

    /**
     * 交换式，且基线元素可随意指定
     * 交换式，但是不指定基线元素，以最后一个元素作为基线元素，则直接注释掉swap(arr, index, end);即可
     * @param arr
     * @param len
     * @param start
     * @param end
     * @return
     */
    private int partitionExchange(int[] arr, int len, int start, int end) {
        //异常情况返回-1
        if (arr == null || len <= 1 || start < 0 || end > len) {
            return -1;
        }
        //以第一个元素作为被排序的元素,这里可以随便指定基线元素位置
        int index = RandomInRange(start, end);
        //交换式的关键在于，一开始就固定基线元素的位置
        swap(arr, index, end);
        //从左到右开始扫描，用来标记第一个小于基线元素的值
        int small = start -1;
        for (index = start; index < end; index++) {
            if (arr[index] < arr[end]) {
                ++small;
                if (small < index) {
                    swap(arr, small, index);
                }
            }
        }
        ++small;
        swap(arr, small, end);
        return small;
    }

    /**
     *
     * @param arr
     * @param len
     * @param start
     * @param end
     * @return
     */
    private int partitionExchangeNonAssign(int[] arr, int len, int start, int end) {
        //异常情况返回-1
        if (arr == null || len <= 1 || start < 0 || end > len) {
            return -1;
        }
        //以第一个元素作为被排序的元素,这里可以随便指定基线元素位置
        int index = RandomInRange(start, end);
        //交换式的关键在于，一开始就固定基线元素的位置
        swap(arr, index, end);
        //从左到右开始扫描，用来标记第一个小于基线元素的值
        int small = start -1;
        for (index = start; index < end; index++) {
            if (arr[index] < arr[end]) {
                ++small;
                if (small < index) {
                    swap(arr, small, index);
                }
            }
        }
        ++small;
        swap(arr, small, end);
        return small;
    }

    /**
     *采用栈来模拟
     *快速排序的非递归方法
     */
    public void quickSortNonRecursion(int[] arr, int len, int start, int end) {
        LinkedList<Integer> stack = new LinkedList<Integer>();  //用栈模拟
        if(start < end) {
            stack.push(end);
            stack.push(start);
            while(!stack.isEmpty()) {
                int l = stack.pop();
                int r = stack.pop();
                int index = partition(arr,len, l, r);
                if(l < index - 1) {
                    stack.push(index-1);
                    stack.push(l);
                }
                if(r > index + 1) {
                    stack.push(r);
                    stack.push(index+1);
                }
            }
        }
    }


    @Test
    public void binarySearchTest() {
        int arr[] = {2,3,6,8,9,10};
        int res = binarySearch(arr, arr.length, 0, 5,8);
        if (res == -1) {
            System.out.println("没有找到指定value");
        }
        else {
            System.out.println("指定元素下标为:" + res);
        }

    }

    /**
     * 需要考虑的事情，是否用递归
     * 返回值为元素的下标即可
     * @param arr
     * @param len
     * @param value
     * @return
     */
    private int binarySearch(int arr[], int len, int start, int end, int value) {
        if (arr == null || start > end || start < 0 || end >= len) {
            return -1;//没有找到
        }
        //递归
        int middle = (start + end) / 2 ;
        if (arr[middle] == value) {
            return middle;
        } else if (arr[middle] > value) {
            return binarySearch(arr, len, start, middle - 1, value);
        } else {
            return binarySearch(arr, len, middle + 1, end, value);
        }
    }
}
