package practice.ch01.sec05;

/*
    1.5.1
    字符串可以包含任何 Unicode 字符，如"Java™"或"Java\u2122"
    使用 StringBuilder 帮助处理繁琐操作

    1.5.2
    取子串，subString()
    通过分隔符分割字符串并提取，split()
    结合正则表达式，如，split("\\s+")

    1.5.3
    比较字符串，equals()，== 比较的是引用
    测试一个对象是否为空时，可以用 ==
    空字符串不等同于 null
    在 null 上调用任何方法都会导致空指针异常，如果不明确处理，程序就会终止
    其他，compareTo(),equalsIgnoreCase()
    Collator 对象，知道关于特定语言的排序规则

    1.5.4
    数字与字符串转换，Integer.toString()，Integer.parseInt()

    1.5.5
    String类API，trim()方法，返回一个删除了前后空白的字符串
    String类是不可变的
    CharSequence 是 String、StringBuilder 和其他字符序列的公共父类

    1.5.6
    现在，Unicode 需要 21 个比特，每个有效的 Unicode 被称为编码点
    在 Java 中，字符串不是 Unicode 字符或者编码点的序列，而是编码单元，UTF-16编码的16比特序列
    TODO:codePointAt() codePointCount() countPoints()
 */

public class SimpleString {

    public static void main(String[] args){
        int age = 19;

        //注意比较下面两个例子
        System.out.println("Next year, you will be " + age + 1);
        System.out.println("Next year, you will be " +(age + 1));

        //通过分隔符连接字符串
        String names = String.join(",","Peter","Paul","Mary");

        String w = "world";

        //这样写，即便 w 为null，也能正常工作
        "word".equals(w);
        //这样写，就可能出现空指针异常
        w.equals("word");

        //指定基数的 toString()
        int n = 42;
        String str = Integer.toString(n,2);
        System.out.println(str);
    }

}