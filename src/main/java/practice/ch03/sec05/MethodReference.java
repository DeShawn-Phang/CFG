package practice.ch03.sec05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/*
    3.5.1
    方法引用
    操作符 :: 将方法名称与类或对象名称分隔开
    三种使用方式
        1、类::实例方法 —> 第一个参数作为方法的接收者，其他参数也传给该方法
        2、类::静态方法 -> 所有的参数传递给静态方法
        3、对象::实例方 -> 在给定的对象上调用方法，并传递参数
    可以在方法引用中捕获 this 参数，如 this::equals 等同于 x -> this.equals(x)

    3.5.2
    构造函数引用的方法名都是 new
    可以用来绕过 Java 中的一个限制：在 Java 中，无法构造一个泛型数组，比如 Stream.toArray 返回 Object 数组

 */
public class MethodReference {

    public static void main(String[] args) {

        String [] strings = {"aa","vv"};
        Arrays.sort(strings, (x,y)->x.compareToIgnoreCase(y));
        Arrays.sort(strings, String :: compareToIgnoreCase);

        ArrayList<String> ss = new ArrayList<>();
        ss.add("ok");
        Stream<String> stream = ss.stream();
        // 只能返回 Object 数组
        Object[] words = stream.toArray();
        // 用构造函数引用绕过限制
        String[] words2 = stream.toArray(String[]::new);
    }
}