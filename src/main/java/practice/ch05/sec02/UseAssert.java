package practice.ch05.sec02;
/*
    5.2.1
    使用断言：断言有两种形式，例，assert x >= 0 : x ;

    5.2.2
    启用和禁用断言
    默认情况下，断言是被禁用的，在运行程序时，加上 -enableassertions 或者 -ea 选项可以启用
    禁用断言：-disableassertions 或者 -da
    针对系统类，需要使用 -enablesystemassertions/-esa
    通过编程的方式控制类加载器的断言状态：
    void ClassLoader.setDefaultAssertionStatus(boolean enabled);

 */
public class UseAssert {
}
