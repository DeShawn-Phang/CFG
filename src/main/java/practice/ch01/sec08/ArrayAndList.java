package practice.ch01.sec08;

/*
    1.8.1
    小心数组Index越界
    声明语句尽量写成 String[] names 的形式

    1.8.2
    数组的默认填充：0，false，空引用
    注意长度为0的数组和 null 的区别

    1.8.3
    数组的长度不可变
    使用 ArrayList，其对象内部管理数组，可实现长度可变的数组
    ArrayList是泛型类，即带有类型参数的类
    钻石语法，指编译器会从变量的声明推断出类型参数

    1.8.4
    泛型类不能使用基本类型作为类型参数
    num.get(i)==num.get(j)，比较的是引用，要用equals()方法

    1.8.5
    增强循环

    1.8.6
    通过赋值语句来复制数组，是传递引用，共享数组
    复制数组可使用静态方法，Arrays.copyOf()
    复制 ArrayList 可以通过构造方法传入数组参数
    要在 int[] 和 ArrayList<Integer> 中间转换，需要显示循环或者 IntStream

    1.8.7
    Arrays类和Collections类实现了数组和数组列表的常用算法
    对数组可以使用 parallelSort()方法，将工作分布到多个处理器上运行

    1.8.8
    命令行参数 String[] args，不做演示，自己看例子

    1.8.9
    二维数组是数组的数组，如果不提供初始值，则必须使用 new 操作符并指定行/列数（可以只有行没有列）
    二维数组的每一行长度可以不同
    Arrays.deepToString()
    没有二维的 ArrayList，但是可以自己声明 ArrayList<ArrayList<object>> 类型的变量
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ArrayAndList {

    public void test() {
        ArrayList<Integer> numbers = new ArrayList<>();

        //对象在自动装箱的过程中自动构造
        numbers.add(42);
        //自动拆箱
        int first = numbers.get(0);

        for (int n : numbers) {
            System.out.println(n);
        }

        int[] ns = new int[10];

        ArrayList primes = numbers;
        primes.get(5);
        int[] ms = Arrays.copyOf(ns,ns.length);

        primes = new ArrayList(numbers);
        primes = new ArrayList(Arrays.asList(numbers));

        //TODO:ArrayList 到 数组，向后兼容，需要提供正确类型的数组
        Integer[] s = numbers.toArray(new Integer[0]);

        Arrays.fill(ns,0);
        Collections.fill(numbers,0);
        Arrays.sort(ns);
        Collections.sort(numbers);
        Arrays.toString(ns);
        numbers.toString();

        //自动实现toString
        System.out.println(numbers);

        //反转元素
        Collections.reverse(numbers);
        //随机打乱元素
        Collections.shuffle(numbers);
    }
    public void test2(){
        int n=8;
        int[][] triangle = new int[n][];
        for(int i=0;i<n;i++){
            triangle[i] = new int[i+1];
            triangle[i][0] = 1;
            triangle[i][i] = 1;
            for(int j=1;j<i;j++){
                triangle[i][j] = triangle[i-1][j-1]+triangle[i-1][j];
            }
        }

        //遍历二维数组
        for(int r=0;r<triangle.length;r++){
            for(int c=0;c<triangle[r].length;c++){
                System.out.printf("%4d",triangle[r][c]);
            }
            System.out.println();
        }
        for(int[] row:triangle){
            for(int element:row){
                System.out.printf("%4d",element);
            }
            System.out.println();
        }

        //输出二维数组
        System.out.println(Arrays.deepToString(triangle));
    }
    public static void main(String[] args){
        ArrayAndList arrayAndList = new ArrayAndList();
        arrayAndList.test2();
    }
}