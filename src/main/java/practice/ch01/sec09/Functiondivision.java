package practice.ch01.sec09;

/*
    1.9.1
    静态方法的声明与调用

    1.9.2
    将数组的引用作为参数传递

    1.9.3
    可变参数，也叫变长参数，在类型之后加上 ...，实际上是一个数组
    可变的参数必须是方法的最后一个参数
 */

public class Functiondivision {

    public static double average(double x,double y){
        double sum = x + y;
        return sum/2;
    }

    //可以修改传入的数组
    public static void swap(int[] values,int i,int j){
        int temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }

    //没有修改传入的数组，返回了一个新的数组
    public static int[] firstLast(int[] values){
        if(values.length==0) return new int[0];
        else return new int[]{values[0],values[values.length-1]};
    }

    public static double average(double... values){
        double sum = 0;
        for (double v : values) sum += v;
        return values.length == 0 ? 0 : sum/values.length;
    }

    public static void main(String[] args){
        double a = 1;
        double b = 2;
        double result = average(a,b);

        //可变参数的传值也可以是数组
        double avg = average(3,6,2,7);
        double[] scores = {3,5,2,9};
        double avg2 = average(scores);
    }

}