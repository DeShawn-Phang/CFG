package practice.ch04.sec02;

import practice.ch04.sec01.Employee;

import java.util.Objects;

/*
    4.2.1
    打印数组，Arrays.toString()，打印多维数组，Arrays.deepToString()

    4.2.2
    equals()，由于重载了 Object 类的方法，因此需要转换类型才能查看它的实例变量
    在转换类型之前，先要用 getClass 方法，或者 instanceof 操作进行类型检查
    对于 double 类型，如果担心 NaN 或无穷大，可以使用 Double.equals
    可以先调用父类的 equals() 方法，再做后续检查
    对于在父类中就能固定的 equals() 方法，在父类中用 final 修饰

    4.2.3
    TODO:下面这点很重要
    如果你重定义了 equals() 方法，则也需要重定义 hashCode 方法，以兼容 equals 方法。
    如果不这么做，则当用户将你的类插入哈希集合或者 HashMap 时，它们可能会丢失
    Objects.hash() 会计算每个参数的哈希码，并组合，是空指针安全的
    数组类型的哈希，先用静态方法 Array.hashCode() 计算数组元素哈希，再传结果给 Object.hash()

    4.2.4
    clone()，克隆一个与原对象状态相同的不同对象，是浅拷贝，要小心引用
    clone 方法被声明为 protected 如果想让类用户克隆实例，就要覆盖它
 */

public class EventualObj implements Cloneable {

    @Override
    public String toString() {
        return getClass().getName();
    }

    @Override
    public EventualObj clone() throws CloneNotSupportedException {
        return (EventualObj)super.clone();
    }

    public static void main(String[] args) {
        EventualObj obj = new EventualObj();
        obj.toString();

        //对象 obj 为null不安全
        obj.equals(null);
        //对象 obj 为null也安全
        Objects.equals(obj,null);

        obj.hashCode();

        obj.getClass();

        try {
            EventualObj obj1 = obj.clone();
        }catch (CloneNotSupportedException e){

        }

        //obj.finalize(); TODO:自动调用

        //obj.wait(); TODO：不懂

//        obj.notify();
//        obj.notifyAll();

    }
}