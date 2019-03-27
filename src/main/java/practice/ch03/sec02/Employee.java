package practice.ch03.sec02;

public abstract class Employee implements Person, Identified {

    @Override
    public String getName() {
        return null;
    }

    //super 关键字让你可以调用父类型的方法
//    public int getId() {
//        return Identified.super.getId();
//    }

    public int getId() {
        return 0;
    }
}