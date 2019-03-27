package practice.ch04.sec01;

/*
    4.1.1
    父类和子类

    4.1.2
    定义和继承方法

    4.1.3
    方法覆盖
    子类不能访问父类的私有实例变量
    与 this 不同，super 不是对象的引用，而是绕过动态查找方法，并调用特定方法的指令
    从技术上讲，重载方法，协变返回类型是允许的，但是改变参数是不允许的，子类方法的可见性至少和父类一样
    通过给计划要覆盖的父类方法打上@Override注释，可以避免类型错误

    4.1.4
    子类的构造

    4.1.5
    将一个子类对象赋给父类变量是合法的，调用方法时优先调用子类的方法，这个过程被称作动态方法查找

    4.1.6
    父类的变量可以引用子类的对象，但是与此同时带来麻烦，变量只能调用父类中存在的方法
    此时，可以用 instanceof 操作符判断，再将父类引用转为子类引用

    4.1.7
    将类中的一个方法声明为 final，子类将不可以覆盖它
    将类声明为 final，就能阻止其他人继承该类

    4.1.8
    一个类可以定义没有实现的方法，强迫子类去实现方法，这样的方法以及其所属类被称为抽象方法和类
    通常将非常通用的类设计为抽象类
    抽象类可以拥有非抽象方法
    抽象类可以被另一个抽象类继承
    抽象类不能构造实例，但可以用抽象类的引用指向其子类的对象

    4.1.9
    protected，允许子类访问父类的实例变量
    TODO:protected 域应该谨慎使用

    4.1.10
    匿名子类
    invite( new ArrayList<String>() {{ add("Harry");add("Sally");}} );
    外面的大括号创建匿名子类，里面的大括号是初始化块
    不推荐使用这种小技巧，效率较低

    4.1.11
    "class win"原则

    4.1.12
    带 super 的方法表达式
 */

import java.util.ArrayList;

public class Manager extends Employee{

    //field and overriding methods
    private double bonus;

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public double getSalary() {
        return super.getSalary() + bonus;
    }

    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<String>(100) {
            @Override
            public void add(int index, String element) {
                super.add(index, element);
                System.out.printf("Adding %s at %d\n",element,index);
            }
        };
        names.add(0,"Harry");
    }
}