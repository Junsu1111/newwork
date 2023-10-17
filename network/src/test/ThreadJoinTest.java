package test;

public class ThreadJoinTest {
    public static void main(String[] args) {
        Thread t=new Thread(){
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                    System.out.println("MyThread ended");
                    Thread.sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        t.start();
//        try {
//            t.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println("Main Thread ended");
    }
}
