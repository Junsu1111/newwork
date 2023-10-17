package test;

import java.util.Arrays;

public class ThreadJoinTest2 {
    public static void main(String[] args) {
        double[] array=new double[1000];
        for (int i=0;i<array.length;i++){
            array[i]=Math.random();
        }
        SortRunnable r=new SortRunnable(array);
        Thread t=new Thread(r);
        t.start();
        try {
            t.join();
            System.out.println(array[0]);
            System.out.println(array[array.length/2]);
            System.out.println(array[array.length-1]);
        }catch (InterruptedException e){}
    }
}

class SortRunnable implements Runnable{
    double[] array;

    public SortRunnable(double[] array){
        this.array=array;
    }

    @Override
    public void run() {
        array=Arrays.stream(array).sorted().toArray();
//        array=Arrays.stream(array).sorted(Comparator.reverseOrder()).toArray();
    }
}



