package practice.ch03.sec06;

import java.awt.*;
import java.awt.image.BufferedImage;

/*
    3.6.1
    延迟执行代码的原因：
        1、在另一个单独的线程中运行代码
        2、多次运行代码
        3、在算法的恰当时刻运行代码（如，排序中的比较操作）
        4、在某些情况发生时运行代码（按钮被单击、数据到达等）
        5、只有在需要时才运行代码
    要接受lambda 表达式，我们需要选择函数式接口，比如 Runnable

    3.6.2
    "名义类型"——在 Java 中，你需要使用诸如 Comparator<String> 这样的函数式接口来达到声明函数的目的
    如果我们想接受没有特定语义的"任意函数"，可以使用泛函数类型
    TODO:不是太理解

    3.6.3

 */
public class UseLambda {

    public static void repeat1(int n, Runnable action) {
        for (int i = 0; i < n; i++) action.run();
    }

    // 改进的 repeat 方法，传入了一个自定义的函数式接口
    public static void repeat(int n, IntConsumer action) {
        for (int i = 0; i < n; i++) action.accept(i);
    }

    // 调用改进的 repeat 方法
    public void test(){
        repeat(10,i->System.out.println("Countdown:" + (9-i)));
    }

    public BufferedImage createImage(int width, int height, PixelFunction f) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = f.apply(x, y);
                image.setRGB(x, y, ((Color) color).getRGB());
            }
        }
        return image;
    }

    public void test2(){
        BufferedImage frenchFlag = createImage(150, 100 ,(x, y)->x<50?Color.BLUE:x<100?Color.WHITE:Color.RED);
    }

    public static void main(String[] args) {

        repeat1(10, ()->System.out.println("hi"));

        UseLambda useLambda = new UseLambda();
        useLambda.test();
        useLambda.test2();
    }
}