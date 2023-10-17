package midterm_exam;

import java.net.*;
import java.io.*;
import java.util.*;

public class MidtermExamReadline {
    public static void main(String[] args) {
        //URL u=new URL("file:///D:/Archive/file (c=1)_(d=26).txt");
        String[] formats={"_c=%d___d=%d_","_c=%d__(d=%d)","(c=%d)__d=%d_","(c=%d)_(d=%d)"};
        int missingFileCount=0;
        Map<Integer,Integer> map=new HashMap<>();
        int maxNumber=Integer.MIN_VALUE;
        int minNumber=Integer.MAX_VALUE;
        int maxFreqNumber=0;
        int minFreqNumber=0;
        BufferedReader r = null;
        double start=System.currentTimeMillis();
        try {
            int c, d,i;
            for (c = 1; c <=50; c++) {
                for (d = 1; d <=50; d++) {
                    for (i = 0; i < 4; i++) {
                        URL url = new URL("file:///D:/Archive/file " + String.format(formats[i],c,d)+".txt");
                        try {
                            InputStream is=(InputStream) url.getContent();
                            r=new BufferedReader(new InputStreamReader(is));
                        } catch (FileNotFoundException e) {
                            if(i==3){
                                missingFileCount++;
                                break;
                            }
                            continue;
                        }
                        String line;
                        while ((line=r.readLine())!=null) {
                            String[] numbers=line.split(" ");
                            for(String number : numbers){
                                Integer num=Integer.parseInt(number);
                                maxNumber= maxNumber < num ? num : maxNumber; minNumber= minNumber > num ? num : minNumber;
                                if(map.containsKey(num)){
                                    map.replace(num,map.get(num)+1);
                                    maxFreqNumber= map.get(maxFreqNumber) < map.get(num) ? num : maxFreqNumber;
                                    minFreqNumber= map.get(minFreqNumber) > map.get(num) ? num : minFreqNumber;
                                }else{
                                    if(map.isEmpty()){ maxFreqNumber=minFreqNumber=num;}
                                    map.put(num,1);
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(r!=null){
                try {
                    r.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Max Number: "+maxNumber);
        System.out.println("Min Number: "+minNumber);
        System.out.println("Max Frequency Number: "+maxFreqNumber+" The Frequency is "+map.get(maxFreqNumber));
        System.out.println("Min Frequency Number: "+minFreqNumber+" The Frequency is "+map.get(minFreqNumber));
        System.out.println("Number of missing files: "+missingFileCount);
        double end=System.currentTimeMillis();
        System.out.println("Run time is "+(end-start));
    }
}
//이걸 멀티로 바꿔야 한다. 파일을 보내는 속도보다 파일을 처리하는 속도가 더 빠르므로 파일을 처리하는 스레드와 파일을 처리하는 스레드를 따로 두어야 한다.
