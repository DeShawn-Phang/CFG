package practice.ch04.sec03;

/*
    4.3.1
    枚举方法：valueOf、values、ordinal

    4.3.2
    每个枚举类型实例保证只被构造一次
    枚举类型的构造函数总是私有的

    4.3.3
    实例的实现体，见 Operation

    4.3.4
    由于枚举常量在静态成员之前构建，所以你不能在构造函数里引用任何静态成员
    解决办法是在一个静态初始化块中进行初始化工作
    一旦构造完常量，静态变量的初始化和静态初始化块按照一般的从上而下的方式执行
    ******* 注意 *******
    枚举类型可以嵌套在一个类中。这样嵌套的枚举类属于隐式的静态嵌套类————也就是，它们的方法不能引用外部类的实例变量

    4.3.5
    在 switch 语句中使用枚举常量
 */

public enum Size {

    //1
    SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRS_LARGE("XL");

    private String abbreviation;

    //2，有 2 才能有 1 的初始化
    Size(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}