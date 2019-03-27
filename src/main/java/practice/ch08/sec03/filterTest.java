package practice.ch08.sec03;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
    filter 的参数是一个 Predicate<T> 对象，也就是说从 T 到 boolean 的函数
    使用 map 方法，并传递给它一个执行转化的函数，如下将所有单词转换成小写
    当你使用 map 方法时，函数将作用于每一个元素
 */

public class filterTest {

    List<String> words = Arrays.asList("SSSSSSSSSSSSSSSS");
    Stream<String> longWords = words.stream().filter(w->w.length()>12);
    Stream<String> lowercaseWords = words.stream().map(String::toLowerCase);
    Stream<String> firstLetters = words.stream().map(s->s.substring(0,1));

}