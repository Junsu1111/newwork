package midterm_exam;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MidtermExamMultiThread {
    int fileCount=0; //static이라고 객체도 생성안하고 쓸 수 있는 건 아니다. 객체가 static이 아니잖아.
    Map<Integer,Integer> map=new HashMap<>();
    int maxNumber=Integer.MIN_VALUE;
    int minNumber=Integer.MAX_VALUE;
    int maxFreqNumber=0;
    int minFreqNumber=0;
    int start=1;



    public static void main(String[] args) {
        double start=System.currentTimeMillis();
        MidtermExamMultiThread mt=new MidtermExamMultiThread();
        ExecutorService service= Executors.newFixedThreadPool(25);
        for(int i=0;i<25;i++){
            service.submit(new FileRunnable(mt,i*2+1));//static이 아닌 변수는 static안에서 참조 될 수 없다. static의 목적에서 벗어남.
        }
        service.shutdown();
        try {
            service.awaitTermination(50, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double end=System.currentTimeMillis();
        System.out.println("Max Number: "+mt.maxNumber);
        System.out.println("Min Number: "+mt.minNumber);
        System.out.println("Max Frequency Number: "+mt.maxFreqNumber+" The Frequency is "+mt.map.get(mt.maxFreqNumber));
        System.out.println("Min Frequency Number: "+mt.minFreqNumber+" The Frequency is "+mt.map.get(mt.minFreqNumber));
        System.out.println("Number of missing files: "+(2500-mt.fileCount));
        System.out.println("Run time is "+(end-start)+"ms");

    }

}

class FileRunnable implements Runnable {
    private String[] formats = {"_c=%d___d=%d_", "_c=%d__(d=%d)", "(c=%d)__d=%d_", "(c=%d)_(d=%d)"};
    private BufferedReader r = null;
    private int start;
    MidtermExamMultiThread mt;

    FileRunnable(MidtermExamMultiThread mt, int start) {
        this.mt = mt;
        this.start = start;
    }

    @Override
    public void run() {
        for (int c = start; c < start + 2; c++) {
            for (int d = 1; d <= 50; d++) {
                try {
                    for (int i = 0; i < 4; i++) {
                        URL url = new URL("file:///D:/Archive/file " + String.format(formats[i], c, d) + ".txt");
                        try {
                            InputStream is = (InputStream) url.getContent();
                            r = new BufferedReader(new InputStreamReader(is));
                        } catch (FileNotFoundException e) {
                            continue;
                        }
                        String line;
                        synchronized (mt) {
                            while ((line = r.readLine()) != null) {
                                String[] numbers = line.split(" ");
                                for(String number : numbers){
                                    Integer num=Integer.parseInt(number);
                                    mt.maxNumber= mt.maxNumber < num ? num : mt.maxNumber; mt.minNumber= mt.minNumber > num ? num : mt.minNumber;
                                    if(mt.map.containsKey(num)){
                                        mt.map.replace(num,mt.map.get(num)+1);
                                        mt.maxFreqNumber= mt.map.get(mt.maxFreqNumber) < mt.map.get(num) ? num : mt.maxFreqNumber;
                                        mt.minFreqNumber= mt.map.get(mt.minFreqNumber) > mt.map.get(num) ? num : mt.minFreqNumber;
                                    }else{
                                        if(mt.map.isEmpty()){ mt.maxFreqNumber=mt.minFreqNumber=num;}
                                        mt.map.put(num,1);
                                    }
                                }
                            }
                            mt.fileCount++;
                        }
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (r != null) {
                        try {
                            r.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
//이걸 멀티로 바꿔야 한다. 파일을 보내는 속도보다 파일을 처리하는 속도가 더 빠르므로 파일을 처리하는 스레드와 파일을 처리하는 스레드를 따로 두어야 한다.
