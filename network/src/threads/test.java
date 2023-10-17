package threads;


class test {
    public static void main(String[] args) {
        Runnable r=new Thread_ex1();
        Thread t=new Thread(r,"³»±â¹Îµç thread");
        t.start();
        for (int i=0;i<100;i++){
            System.out.println(i);
        }
    }
}
class Thread_ex1 implements Runnable{
    @Override
    public void run() {
        threadName();
        throwError();
    }
    public void threadName(){
        for (int i=0;i<10;i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
    public void throwError(){
        try {
            throw new Exception();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
