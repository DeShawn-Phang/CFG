package practice.ch02.sec04;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/*
    2.4.1
    静态变量属于类

    2.4.2
    静态常量相当普遍，如 Math.PI，System.out
    注意：还是有个 setOut 方法将 System.out 设置到不同的流上。
    该方法是本地方法，不是在Java中实现的，它能绕过Java语言的访问控制机制。

    2.4.3
    静态初始块：用于完成额外的静态变量的初始化工作

    2.4.4
    静态方法是指可以不用运行在对象上的方法，如 Math.pow()
    不能在静态方法中访问实例变量，但可以在静态方法中访问本类的静态变量

    2.4.5
    工厂方法：返回一个类的新实例的静态方法
    例如，NumberFormat 类使用工厂方法产生各种格式的 formatter 对象
    工厂方法也能返回共享对象
 */
public class StaticVar {

    private static final ArrayList<Integer> expirationYear = new ArrayList<>();

    //静态初始块
    static {
        //将下一个20年添加到数组列表
        int year = LocalDate.now().getYear();
        for(int i =year;i<=year+20;i++){
            expirationYear.add(i);
        }
    }

    //提供静态方法，获取指定范围内的随机数
    public static int nextInt(Random generator,int low,int high){
        return low+generator.nextInt(high-low+1);
    }

    public void factoryMethod(){
        //返回不同的实例对象
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        NumberFormat percentFormatter = NumberFormat.getPercentInstance();
        double x = 0.1;
        System.out.println(currencyFormatter.format(x));
        System.out.println(percentFormatter.format(x));
        //返回一个共享的对象
        Collections.emptyList();
    }
}