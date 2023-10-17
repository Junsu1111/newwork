package threads;

class test3 {
    public static void main(String[] args) {
        Thread t2 = new Thread(new Runnable2());
        Thread t1 = new Thread(new Runnable1());

        t1.setPriority(7);
        t2.start();
        t1.start();
    }
}
class Runnable1 implements Runnable{
    @Override
    public void run() {
        for (int i=0;i<50;i++){
            System.out.println("-");
        }
    }
}
class Runnable2 implements  Runnable{
    @Override
    public void run() {
        for (int i=0;i<50;i++){
            System.out.println("|");
        }
    }
}
