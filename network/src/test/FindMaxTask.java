package test;

import java.util.concurrent.Callable;

public class FindMaxTask implements Callable {
    private int data[];
    private int start;
    private int end;
    public FindMaxTask(int[] data, int start,int end){
        this.start=start;
        this.end=end;
    }

    @Override
    public Object call() throws Exception {
        int max=Integer.MIN_VALUE;
        for(int i=start;i<end;i++){
            if(data[i]>max){ max=data[i];}
        }
        return max;
    }
}
