package week3_1;

import java.util.concurrent.*;
public class MultithreadedMaxFinder {
    public static  int max(int[] data) throws InterruptedException, ExecutionException{
        if(data.length==1){return data[0];}
        else if(data.length==0){throw new IllegalArgumentException();}

        FindMaxTask task1=new FindMaxTask(data,0,data.length/2);
        FindMaxTask task2=new FindMaxTask(data,data.length/2,data.length);

        ExecutorService service=Executors.newFixedThreadPool(2);

        Future<Integer> future1=service.submit(task1);
        Future<Integer> future2=service.submit(task2);

        service.shutdown();

        return Math.max(future1.get(),future2.get());
        //polling에서 처럼 아직 max를 구하는 과정이 안끝났는데 get을 호출하는 문제는 없다.
        //왜냐면 block되어서 끝날 때까지 기다리기 때문이다.
        //Futere.get()은 blocking call임.

    }

    public static void main(String[] args) throws Exception{
        int[] data={100,88,0,30,75,34,22,0,1,78,53,69,389};
        int result=max(data);
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(result);
        System.out.println(data.length/2);
    }



}
