package practice.ch05.sec01;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
    5.1.1
    异常抛出

    5.1.2
    异常继承层次：所有的异常类都派生自 Throwable 类
    未检查异常（unchecked exception）、已检查异常（checked exception）
    自定义异常类最好提供两个构造函数，一个不带参数，一个带消息字符串参数

    5.1.3
    如果异常之间有联系，可以将异常合并到一个共同的父类
    异常处理的黄金法则：早抛出，后捕获
    当你覆盖一个方法时，它不能抛出比父类方法声明中还要多的已检查异常
    可以用 javadoc 的 @throw 标签来文档化
    如果 lambda 表达式会抛出一个已检查异常，则你只能将它传给一个其方法声明了该异常的函数式接口

    5.1.4
    try-catch，多个 catch 块，最具体的异常类型必须放在前面
    还可以让多种异常共享一个处理器

    5.1.5
    try-with-resources，try(ResourceType res = init)
    AutoCloseable 接口，有一个单独的方法 close() throws Exception
    当你捕获了主要异常时，可以通过调用 getSuppressed() 方法检索得到第二个异常

    5.1.6
    避免在 finally 子句中抛出异常，try 块中的异常会被 finally 中的异常覆盖，而异常抑制机制只适用于 try-with-resources
    finally 子句中不应该包含 return 语句，否则会替换 try 块中的 return 语句

    5.1.7
    TODO:异常重抛和链接

    5.1.8
    TODO:堆栈踪迹（stack trace）
    有时，你被迫捕获了一个异常，并且的确不知如何处理它，那么至少要打印出堆栈踪迹信息，ex.printStackTrace()

    5.1.9
    Objects.requireNonNull() 检测参数是否为空，可结合堆栈踪迹使用
 */

public class ExceptionResolve {

    private static Random generator = new Random();

    public static int randInt (int low, int high) {
        if (low > high)
            throw new IllegalArgumentException(
                    String.format("low should be <= high but low is % and high is %d",low,high)
            );
        return low + (int) (generator.nextDouble() * (high - low + 1));
    }

    public static void main(String[] args) {

        try {
            int i = ExceptionResolve.randInt(10, 5);
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
//        System.out.println(i);

        //该代码隐藏危险，如果任意方法抛出一个异常，out.close() 调用就不会执行
        ArrayList<String> lines = new ArrayList<>();
        try {
            PrintWriter out = new PrintWriter("output.txt");
            for(String line:lines){
                out.println(line.toLowerCase());
            }
            out.close();
        }catch (FileNotFoundException ex){

        }

        //该代码块保证了 out.close() 方法总会被调用
        try(PrintWriter out = new PrintWriter("output.txt")) {
            for(String line:lines){
                out.println(line.toLowerCase());
            }
        }catch (FileNotFoundException ex) {
        }

        try (Scanner in = new Scanner(Paths.get("/usr/share/dict/words"));
             PrintWriter out = new PrintWriter("output.txt")) {
            while (in.hasNext())
                out.println(in.next().toLowerCase());
        }catch (IOException ex) {

            Throwable[] secondaryExceptions = ex.getSuppressed();

        }
    }

}
