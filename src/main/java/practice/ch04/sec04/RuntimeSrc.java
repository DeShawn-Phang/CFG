package practice.ch04.sec04;

/*
    4.4.1
    Class.forName 方法主要用于构造那些可能在编译时还不被知晓的类的Class对象

    4.4.2
    Class 类的一个有用服务 ——> 定位应用程序可能需要的资源，比如配置文件或图片

    4.4.3
    类加载器 ——> 负责加载字节流，并且在虚拟机中将它们转化成一个类或者接口
    虚拟机根据需要加载类文件，从类的 main 方法开始
    当执行 Java 程序时，至少会涉及三个类加载器
    bootstrap 类加载器（根加载器）会加载 Java 类库，是虚拟机的一部分
    扩展类加载器从 jre/lib/ext 目录中加载 "标准库扩展" 部分
    系统类加载器加载应用程序类，它定位 classpath 中目录和 JAR 文件的类
    扩展类和系统类加载器都是在 Java 中实现的。它们都是 URLClassLoader 类的实例
    通过创建自己的 URLClassLoader 实例，可以从 classpath 以外的目录或者 JAR 文件中加载类
    TODO: 不太懂

    4.4.4
    上下文类加载器
    TODO: 看不懂，跳过
 */

import java.io.InputStream;
import java.util.Scanner;

public class RuntimeSrc extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    // 如果将资源和类文件放在同一位置，则可以像这样给文件打开一个输入流，目录可是相对路径也可以是绝对路径
    public void openInputStream(){
        // 下面的代码会在 RuntimeSrc 类所在包的根目录下找到 config.txt 文件
        InputStream stream = RuntimeSrc.class.getResourceAsStream("config.txt");
        Scanner in = new Scanner(stream);
    }

    public static void main(String[] args) {
        String s = "abc";
        Object obj = s;

        // 书上说这样不行，实际上却可以
        Class c2 = obj.getClass();

        Class<?> c3 = obj.getClass();

        // 一旦有了 Class 对象，就可以查出类名称
        System.out.println("This object is an instance of " + c2.getName());
        System.out.println("This object is an instance of " + c3.getName());

        // 通过静态方法来获取一个 Class 对象
        String className = "java.util.Scanner.class";
        // Class.forName 方法及其他使用了反射机制的方法，当发生错误时会抛出检查异常
        try {
            Class<?> c4 = Class.forName(className);
        } catch (ReflectiveOperationException e){
            // 可以给主调方法打上会抛出 ReflectiveOperationException 异常的标签
            // 或者根据编译器提示打
        }

        Class<?> c1 = java.util.Scanner.class;
        Class<?> c12 = String[].class;
        Class<?> c13 = Runnable.class;
        Class<?> c14 = int.class;
        Class<?> c15 = void.class;

        // 如果为数组产生 Class 对象，就得用下面的打印结果
        System.out.println(c12.getName());

        // 这样是常规的结果
        System.out.println(c12.getCanonicalName());
    }
}