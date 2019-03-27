package practice.ch01.sec03;

/*
    1.3.1
    强类型语言，要求变量的使用要严格符合定义，所有变量都必须先定义后使用。

    1.3.2
    变量名中的符号$是专门用在自动产生的名称中，平时不应该使用
    拉丁字母，π，也是有效的名称
    大小写敏感

    1.3.3
    在首次需要变量的前一刻声明变量是一种好习惯

    1.3.4
    常量的名称使用大写字母
    可以在方法外，使用 static 声明常量
    要在其他类中使用常量，要在前面加上类名
    System 类中声明的常量，public static final PrintStream out，也就是我们常用的 System.out
 */

import java.util.Random;

public class Variable {

    int total = 0, count;
    Random generator = new Random();

    public static final int DAYS_PER_WEEK = 7;

    public static final int MONDAY = 0;
    public static final int TUESDAY = 1;

    //一组相关的常量，可以被定义为枚举类型
    enum Weekday{MON,TUE,WED,THU,FRI,SAT,SUN};
    Weekday startDay = Weekday.MON;

    public static void main(String[]arg){

        final int DAYS_PER_WEEK = 7;

        //延迟 final 变量的初始化是允许的
        final int DAYS_IN_FEBRUARY;
        if(true){
            DAYS_IN_FEBRUARY = 29;
        }else{
            DAYS_IN_FEBRUARY = 28;
        }

    }

}
