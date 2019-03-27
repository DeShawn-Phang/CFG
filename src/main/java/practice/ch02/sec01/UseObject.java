package practice.ch02.sec01;

/*
    2.1.1
    Accessor（访问器）方法：没改变调用它的对象
    Mutator（更改器）方法：改变了调用它的对象

    2.1.2
    引用（reference）是与实现相关的一种定位对象的方式
    用持有对象引用的变量给另一个变量赋值时，两个引用指向同一个对象，这就实现了共享对象
    如果类没有更改器方法，就不用担心对象被修改，可以大量分发对象的引用
    将对象变量设置为特殊值 null，表示它没有引用任何对象，但这是危险的，更推荐使用可选类型
    没有引用的对象最终会被垃圾回收器（garbage collector）回收内存
 */

import java.time.DayOfWeek;
import java.time.LocalDate;

public class UseObject {

    public void getDate(){
        int year=2001;
        int month=2;
        LocalDate date = LocalDate.of(year,month,1);

        //得到代表下一天的 LocalDate 对象
        date.plusDays(1);

        while(date.getMonthValue()==month){
            date = date.plusDays(1);
        }

        //返回 date 是一周中的第几天
        DayOfWeek weekday = date.getDayOfWeek();

        int value = weekday.getValue();
        int value2 = date.getDayOfWeek().getValue();

        for(int i=1;i<value;i++)
            System.out.print(" ");
    }
}
