package practice.ch03.sec07;

/*
    3.7.1
    lambda 表达式的方法体与嵌套代码块有着相同的作用域
    lambda 表达式中的关键字 this 代表的是创建 lambda 表达式的方法的 this 参数

    3.7.2
    lambda 表达式有三个部分
        1、代码块
        2、参数
        3、自由变量 —— 既不是参数变量，也不是代码内部定义的变量
    闭包（closure）—— 带有自由变量值的代码块的技术名词
    重要约束 —— 只能引用那些值不会改变的变量
    错误示例
    for(int i-0;i<n;i++){
        new Thread(()->System.out.println(i)).start();
            // 错误 —— 不能捕获i
    }
    同样的规则适用于被局部内部类捕获的变量

    ******* 注意 *******
    增强的 for 循环中的变量是有效 final 的，因为它的作用域是单个迭代
    for(String arg:args){
        new Thread(()->System.out.println(arg)).start();
            // 可以捕获变量
    }
    区别在于，每一个迭代会创建新的 arg 变量，并且从 args 数组中将下一个值赋给 arg。
    相比之下，上一个例子中变量 i 的作用域是整个循环
    作为"有效final"规则的结果，lambda 表达式不能改变任何捕获的变量

    ******* 注意 *******
    不要依赖编译器去捕获所有并发访问的错误，有些情况下不会产生错误报告

    ******* 注意 *******
    可以通过使用长度为 1 的数组绕过对不适当修改的检查，即使这样是非线程安全的
 */

import javafx.scene.control.Button;

import java.awt.*;

public class LambdaTech {

    public static void repeatMessage(String text, int count){
        Runnable r = () -> {
            for (int i = 0; i < count; i++){
                System.out.println(text);
            }
        };
        new Thread(r).start();
    }

    public static void main(String[] args) {

        repeatMessage("Hello", 1000);

        int[] counter = new int[1];
        Button button = new Button();
        button.setOnAction(event -> counter[0]++);
    }
}
