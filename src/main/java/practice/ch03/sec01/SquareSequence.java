package practice.ch03.sec01;

public class SquareSequence implements UseInterface.IntSequence {
    private int i;

    //因为接口要求 public 级别访问，所以这里的方法必须声明为 public
    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public int next() {
        i++;
        return i*i;
    }
}