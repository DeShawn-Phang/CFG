package practice.ch01.sec01;

/*
    1.1.1
    在程序中，通过操作对象让它们完成工作。
    对象属于某个类，类定义了对象能做什么。
    HelloWorld是类。
    main是方法，也就是在类中声明的函数，是程序运行时调用的第一个方法，是静态的，不依赖于对象运行，无返回值。
    public、private，访问控制符。
    包（package）是一组相关类的集合，便于区分相同类名的多个类。
    System.out，标准输出。
    Java中，所有的一切都必须在类中声名。
    行注释、多行注释、文档注释。

    1.1.2
    javac命令将Java源代码编译为字节码，并将它们保存到类文件（调用javac编译器时，加上文件名，斜杠是路径分隔符，扩展名是.java）。
    java命令启动虚拟机（JVM），虚拟机加载类文件并执行字节码（启动java虚拟机时，加上类名，以点号分隔包名，没有扩展名）。

    1.1.3
    System.out是对象，它是PrintStream类的实例，println、print等是实例方法（运行在对象或者类的实例上）。
    调用实例方法，需要使用点符号。
    在Java中，需要构造绝大多数对象（不像System.out和"Hello World!"对象，它们已经存在，准备好供你使用）。
 */

//  第一个Java程序

import java.util.Random;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello,World!");

        System.out.println("Hello World!".length());

        //在构造对象上调用方法
        new Random().nextInt();

        //在一个对象上调用多个方法，将对象存储在变量中
        Random generator = new Random();
        System.out.println(generator.nextInt());
        System.out.println(generator.nextInt());
    }
}