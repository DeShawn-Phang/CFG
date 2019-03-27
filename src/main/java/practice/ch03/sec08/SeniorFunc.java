package practice.ch03.sec08;

import java.util.Comparator;

/*
    3.8.1
    产生定制函数，将定制函数传递给带有函数式接口的方法

    3.8.2
    Comparator 接口有个默认方法 reversed，产生给定 comparator 的反序

    3.8.3
    TODO:不想看啦
 */
public class SeniorFunc {

    public static Comparator<String> comparaInDirection(int direction){
        return (x, y)-> direction * x.compareTo(y);
    }

    public static Comparator<String> reverse(Comparator<String> comp){
        return (x, y)-> comp.compare(x, y);
    }

    public static void test(){
        reverse(String :: compareToIgnoreCase);
    }
}
