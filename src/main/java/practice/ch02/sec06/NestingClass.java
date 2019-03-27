package practice.ch02.sec06;

/*
    2.6.1
    静态嵌套类（static修饰的嵌套类）
 */

import java.util.ArrayList;

public class NestingClass {

    //嵌套类被声明为 private，因此不用操心要将内部类的实例变量改为私有变量
    private static class Item {
        String description;
        int quantity;
        double unitPrice;

        double price(){
            return quantity * unitPrice;
        }
    }

    //嵌套类被声明为 public，需要使用通常的封装机制，外部通过 NestingClass.Item2 就能构建对象
    public static class Item2{
        private String description;
        private int quantity;
        private double unitPrice;

        public double price(){
            return quantity * unitPrice;
        }
    }

    private ArrayList<Item> items = new ArrayList<>();

    private void addItem(String description,int quantity,double unitPrice) {
        Item newItem = new Item();
        newItem.description = description;
        newItem.quantity = quantity;
        newItem.unitPrice = unitPrice;
        items.add(newItem);
    }
}
