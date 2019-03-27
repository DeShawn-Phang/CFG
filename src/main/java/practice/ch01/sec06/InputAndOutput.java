package practice.ch01.sec06;

/*

    1.6.1
    调用 System.out.println()，输出发送到"标准输出流"，然后显示
    从"标准输入流"读取，相应的 System.in 对象只有读取单个字节的方法
    要读取字符串和数字，构造一个依附到 System.in 的 Scanner
    使用 hasNextLine() hasNext() 等，检查是否有另外的可读取的内容
    要读取密码时，如果不希望输入可见，可以使用 Console 类(只能在控制台使用)
    将密码存储在字符数组中比较安全，因为可以重写数组
    shell 重定向语法 java mypackage.MainClass<input.txt> output.txt 文件的读取、输出

    1.6.2
    print() 方法不会换行，println() 会自动换行
    printf() 限制显示的位数，提供参数
 */

import java.io.Console;
import java.util.Scanner;

public class InputAndOutput {

    public void test(){

        Scanner in = new Scanner(System.in);
        System.out.println("what is your name?");

        //nextLine() 应对输入可能包含空格的情况
        String name = in.nextLine();
        //读取单个单词（空格分隔）
        String firstName = in.next();
        //读取整数
        int age = in.nextInt();

        if(in.hasNextInt()){
            int age2 = in.nextInt();
            System.out.println(age2+"");
        }
        System.out.println(name+firstName+age+"");
    }
    public void consoleTest(){

        Console terminal = System.console();
        if(terminal==null){
            System.out.println("无控制台");
        }else {
            String username = terminal.readLine("User name:");
            char[] passwd = terminal.readPassword("Password:");
            System.out.println(username + " : " + passwd);
        }
    }

    public static void main(String [] args){

        InputAndOutput io = new InputAndOutput();
//        io.test();
        io.consoleTest();
        String name = "KD";
        int age = 5;
        System.out.printf("Hello，%s，you're %d",name,age);

        //用标记符控制格式化输出的显示形式
        System.out.printf("%,+.2f",100000.0/3.0);

        //用String.format() 创建不打印输出的格式化字符串
        String meassage = String.format("Hello,%s",name);
    }
}