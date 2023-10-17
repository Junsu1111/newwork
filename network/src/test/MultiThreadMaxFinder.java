package test;

import java.util.concurrent.*;

public class MultiThreadMaxFinder {
    public static int max(int[] data) throws InterruptedException, ExecutionException{
        if(data.length==1){ return data[0];}
        else if(data.length==0){ throw new IllegalArgumentException();}
        FindMaxTask task1=new FindMaxTask(data,0,data.length/2);
        FindMaxTask task2=new FindMaxTask(data,data.length/2,data.length);

        ExecutorService service= Executors.newFixedThreadPool(2);
        //call은 run이라고 생각하고 service는 Thread라고 생각하자.
        //그리고 call은 값을 리턴하므로 추가적인 객체가 필요한데 그게 Future라고 생각하자.

        Future<Integer> future1=service.submit(task1);//이거 Thread(r)이라고 생각하자.
        Future<Integer> future2=service.submit(task2);

        service.shutdown();
        return Math.max(future1.get(),future2.get());
    }
}
