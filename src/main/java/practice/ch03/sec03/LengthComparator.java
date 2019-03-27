package practice.ch03.sec03;

import java.util.Comparator;

class LengthComparator implements Comparator<String> {

    //父类方法不是静态的，重写的时候就不能改成静态的
    @Override
    public int compare(String o1, String o2) {
        return o1.length()-o2.length();
    }

    public static void main(String[] args) {

        //compare不是静态方法，因此需要创建一个实例
        Comparator<String> comp = new LengthComparator();
        if(comp.compare("sss","es")>0) System.out.println("sss大");
    }
}