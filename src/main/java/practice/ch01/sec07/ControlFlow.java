package practice.ch01.sec07;

/*
    1.7.1
    if-else,switch-case(default、break)
    在编译选项中指示编译器警惕 break 遗漏错误，javac -Xlint:fallthrough mypackage/mainClass.java
    在 switch 方法前面加上注解 @SuppressWarning("fallthrough")，给编译器提供信息，解除 switch 警告
    case 可以是 char,byte,short,int 或者其包装类的常量表达式，还可以是字符串，枚举类型值

    1.7.2
    while,do-while,for

    1.7.3
    break 跳出循环，continue 跳到下一个更新声明
    break声明只是跳出直接封闭循环或者switch，如果想跳到另一个封闭声明的结束，则使用带标签的break声明
    continue 声明也可以带标签，可以跳转到标签循环的下一个迭代

    1.7.4
    局部变量的作用域从声明变量处起到封闭块的结束
    在 Java 中，重叠的作用域范围内不允许有同名的局部变量
 */

import java.util.Random;
import java.util.Scanner;

public class ControlFlow {

    public void test(){
        Random generator = new Random();

        //获取 0~9 的随机整数
        int next = generator.nextInt(10);
        int sum=0;
        int target=1000;
        while(sum<target){
            int next1 = generator.nextInt(100);
            sum += next1;
        }

        //TODO:在声明的顶部加标签，但是break声明调到尾部
        outer:
        while(true){
            Scanner input = new Scanner(System.in);
            String inputData = input.next();
            if(inputData.equals("Q")) break;
            while(true){
                sum++;
                break outer;
            }
        }
        //TODO:带标签的break跳转到这里
    }

}