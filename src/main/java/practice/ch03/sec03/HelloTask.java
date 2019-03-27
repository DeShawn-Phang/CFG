package practice.ch03.sec03;

public class HelloTask implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Hello,World!");
        }
    }
}