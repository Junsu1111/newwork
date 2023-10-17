
package midterm2;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Async {
    HashMap<Integer,Integer> map=new HashMap<>();
    int fileCount=0;
    public static void main(String[] args) {
        double start=System.currentTimeMillis();
        int threadNum=20;
        int allot=2500/threadNum;
        Async mt=new Async();
        ExecutorService service= Executors.newFixedThreadPool(threadNum);
        for(int i=0;i<threadNum;i++){
            service.submit(new ReadRunnable(mt,i*allot,i*allot+allot));
        }
        service.shutdown();
        try {
            service.awaitTermination(50, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int maxFreqNum = 0, maxFreq = 0;
        int minFreqNum = 0, minFreq = Integer.MAX_VALUE;
        int maxNum = Integer.MIN_VALUE, minNum = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : mt.map.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();

            // 최빈값 계산
            if (freq > maxFreq) {
                maxFreqNum = num;
                maxFreq = freq;
            }

            // 최소 빈도값 계산
            if (freq < minFreq) {
                minFreqNum = num;
                minFreq = freq;
            }

            // 최대값 및 최소값 계산
            maxNum = Math.max(maxNum, num);
            minNum = Math.min(minNum, num);
        }
        double end=System.currentTimeMillis();
        // 결과 출력
        System.out.println("max freq num: " + maxFreqNum + ", max freq: " + maxFreq);
        System.out.println("min freq num: " + minFreqNum + ", min freq: " + minFreq);
        System.out.println("max: " + maxNum + ", min: " + minNum);
        System.out.println("missing file: "+(2500- mt.fileCount));
        System.out.println("Run time is "+(end-start)+"ms");
    }
}


class ReadRunnable implements Runnable{
    private String[] formats = {"_c=%d___d=%d_", "_c=%d__(d=%d)", "(c=%d)__d=%d_", "(c=%d)_(d=%d)"};
    private BufferedReader r = null;
    private int start;
    private int end;
    Async mt;

    ReadRunnable(Async mt, int start,int end) {
        this.mt = mt;
        this.start = start;
        this.end=end;
    }
    @Override
    public void run() {
        int c,d;
        for(int i=start;i<end;i++){
            c=i/50+1; d=i%50+1;
            try{
                for(int j=0;j<4;j++){
                    URL url = new URL("file:///D:/Archive/file " + String.format(formats[j], c, d) + ".txt");
                    try {
                        InputStream is = (InputStream) url.getContent();
                        r = new BufferedReader(new InputStreamReader(is));
                    } catch (FileNotFoundException e) {
                        continue;
                    }
                    String line;
                    while((line=r.readLine())!=null){
                        String[] numbers=line.split(" ");
                        for (String num : numbers) {
                            int intNum = Integer.parseInt(num);
                            synchronized (mt) {
                                mt.map.merge(intNum, 1, Integer::sum);
                            }
                        }
                    }
                    synchronized (mt){
                        mt.fileCount++;
                    }
                    r.close();
                    break;
                }
            }catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}















