package test;

public class ThreaJoinTest {
    static long startTime=0;
    public static void main(String[] args) {
        MyThread1 t1=new MyThread1();
        MyThread2 t2=new MyThread2();
        t1.start(); t2.start();
        startTime= System.currentTimeMillis();
        try {
            t1.join(); t2.join();
        }catch (InterruptedException e){}
        System.out.println();
        System.out.println("소요시간:"+(System.currentTimeMillis()-startTime));
    }
}

class MyThread1 extends  Thread{
    @Override
    public void run() {
        for (int i=0;i<300;i++){
            System.out.print("-");
        }
    }
}
class MyThread2 extends Thread{
    @Override
    public void run() {
        for(int i=0;i<300;i++){
            System.out.print("|");
        }
    }
}
