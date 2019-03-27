package practice.ch03.sec06;

import java.awt.*;

// 你应该使用 @FunctionalInterface 注解标记函数式接口
// 1、编译器会检查被注解实体是一个带有单个抽象方法的接口
// 2、javadoc页面会包含该接口是函数式接口的声明

@FunctionalInterface
public interface PixelFunction {
    Color apply(int x, int y);
}