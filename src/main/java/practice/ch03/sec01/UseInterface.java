package practice.ch03.sec01;

/*
    3.1.1
    接口的所有方法默认为公有方法
    不提供实现的方法称为抽象方法

    3.1.2
    如果一个类只实现接口的某些方法，那么它必须以 abstract 修饰符声明

    3.1.3
    可以将实现接口的类的对象赋值给接口变量
    当子类型T的任何值不要转换就能赋值给父类型S的变量时，S就是T的父类

    3.1.4
    强制类转换（cast）和 instanceof
    cast：从父类转为子类
    只有父类对象本身就是用子类new出来的时候, 才可以在将来被强制转换为子类对象
    只能将一个对象强制转换为它的实际类或者它的父类之一，否则会发生编译时错误或强制类型转换异常
    为了避免异常，可以使用 instanceof

    3.1.5
    一个接口可以继承（extend）另外一个接口

    3.1.6
    一个类可以实现很多接口

    3.1.7
    定义在接口中的任何变量，自动为 public static final，可以通过限定名引用它们
    这不是常用习惯，使用枚举类型描述一组常量集合远比这种方式好
    在接口中，你无法拥有实例变量。接口指定行为，而不是状态。
 */

public class UseInterface {

    public interface IntSequence {

        int var = 1;

        boolean hasNext();
        int next();
        default int go(){
            return 1;
        }
    }

    public static double average(IntSequence seq, int n) {
        int count = 0;
        double sum = 0;
        while(seq.hasNext() && count < n) {
            count ++;
            sum += seq.next();
        }
        return count == 0 ? 0 : sum/count;
    }

    public static void main(String[] args) {
        SquareSequence sequence = new SquareSequence();
        double avg = average(sequence,100);
        System.out.println(avg);

        IntSequence digits = new DigitSequence(1792);
        double avg2 = average(digits,100);

        if(digits instanceof DigitSequence) {
            DigitSequence digits2 = (DigitSequence) digits;
            System.out.println(digits2.rest());
            int i = IntSequence.var;
        }
    }
}