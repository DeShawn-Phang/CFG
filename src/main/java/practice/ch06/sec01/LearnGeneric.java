package practice.ch06.sec01;

public class LearnGeneric {
    public static void main(String[] args) {
        //实例化泛型类，其中，类型参数不能用基本类型实例化。可以在构造函数中省略类型参数，这被称为钻石语法
        Entry<String, Integer> entry = new Entry<>("Fred",22);
    }
}