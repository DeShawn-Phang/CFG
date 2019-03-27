package practice.ch03.sec03;

/*
    3.3.1
    使用 Integer.compare() 避免减法溢出
    用 Double.compare() 比较浮点数
    可以使用 Arrays.sort() 方法对任何 Comparable 对象的数组进行排序，如果对象没有实现该接口，则会抛出异常

    3.3.2
    第二种版本的 Arrays.sort(待排序数组，比较器) 方法，有两个参数

    3.3.3
    Runnable 接口，在一个单独的线程运行特定任务
    还有一个 Callable<T> 接口可以用来定义返回结果为类型 T 的任务

    3.3.4
    UI回调，当用户行为发生时，要回调某些代码
 */

import javafx.scene.control.Button;

import java.util.Arrays;

public class JavaInterface implements Comparable<JavaInterface> {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(JavaInterface o) {
        return 0;
    }

    public int getOtherId(JavaInterface ji){
        return ji.id;
    }

    public static void main(String[] args){
        JavaInterface ji1 = new JavaInterface();
        ji1.setId(5);
        JavaInterface ji2 = new JavaInterface();
        ji2.setId(12);

        //TODO:Java 中，方法能够访问它自己所在类对象的任何私有特性
        System.out.println(ji1.getOtherId(ji2));

        String[] friends = {"Peter","Paul","Mary"};
        //对数组默认按字典排序
        Arrays.sort(friends);
        //对数组按比较器排序
        Arrays.sort(friends,new LengthComparator());

        //run 方法在一个单独的线程中执行，并且当前线程能处理其他工作
        Runnable task = new HelloTask();
        Thread thread = new Thread(task);
        thread.start();

//        Button cancelButton = new Button("Cancel");
//        cancelButton.setOnAction(new CancelAction());
    }
}