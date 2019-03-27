package practice.ch02.sec02;

/*
    2.2.2
    如果方法与类用户无关，则应该将方法声明为 private

    2.2.3
    方法头之后是方法体

    2.2.4
    非静态方法就是实例方法
    调用方法的实例也被称为方法的接受者，也可以被认为是方法的一种参数

    2.2.5
    this的使用
    在某些编程语言中，实例变量以 _name 的方式修饰，Java中这样也合法，但是不常用
    甚至可以将 this 声明为方法（构造方法除外）的参数，但不常用

    2.2.6
    TODO:在 Java 中，你不能写出更新基本类型参数的方法（important！）
    TODO:在 Java 中，所有参数（对象引用以及基本类型值）都是值传递！（important！）
 */

public class RealizeClass {

    //TODO:这里为什么非要我加上 static 啊
    public static class Employee {

        private String name;
        private double salary;

        public Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        public void raiseSalary(double byPercent){
            double raise = salary*byPercent/100;
            salary += raise;
        }

        public void setSalary(double salary){
            this.salary = salary;
        }

        public String getName(){
            return name;
        }

        //如果外部调用 boss.replaceWithZombie(fred),则e会指向fred，然后e又指向一个新的Employee对象，fred不变
        public void replaceWithZombie(Employee e) {
            e = new Employee("",0);
        }

        //此方法无法生效
        public void increaseRandomly(double x){
            double amount = x*2;
            x += amount;
        }
    }

    public static void main(String[] args){
        Employee fred = new Employee("fred",10);
        fred.raiseSalary(5);

        int k = 1;
        fred.increaseRandomly(k);
        System.out.println(k);
    }
}