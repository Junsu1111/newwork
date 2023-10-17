package test;

import java.util.concurrent.*;

public class MultiThreadMaxFinder {
    public static int max(int[] data) throws InterruptedException, ExecutionException{
        if(data.length==1){ return data[0];}
        else if(data.length==0){ throw new IllegalArgumentException();}
        FindMaxTask task1=new FindMaxTask(data,0,data.length/2);
        FindMaxTask task2=new FindMaxTask(data,data.length/2,data.length);

        ExecutorService service= Executors.newFixedThreadPool(2);
        //call�� run�̶�� �����ϰ� service�� Thread��� ��������.
        //�׸��� call�� ���� �����ϹǷ� �߰����� ��ü�� �ʿ��ѵ� �װ� Future��� ��������.

        Future<Integer> future1=service.submit(task1);//�̰� Thread(r)�̶�� ��������.
        Future<Integer> future2=service.submit(task2);

        service.shutdown();
        return Math.max(future1.get(),future2.get());
    }
}