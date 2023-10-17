package midterm_exam;

import java.net.*;
import java.io.*;
import java.util.*;

public class MidtermExam {
    public static void main(String[] args) {
        //URL u=new URL("file:///D:/Archive/file (c=1)_(d=26).txt");
        String[] formats={"<c=%d>_<d=%d>","<c=%d>_(c=%d)","<c=%d>_<d=%d>","(c=%d)_(d=%d)"};
        int fileCount=0;
        int missingFileCount=0;
        Map<Integer,Integer> map=new HashMap<>();
        int maxNumber=Integer.MIN_VALUE;
        int minNumber=Integer.MAX_VALUE;
        int maxFreqNumber=0;
        int minFreqNumber=0;
        Reader r = null;
        double start=System.currentTimeMillis();
        try {
            int c, d,i;
            for (c = 1; c <=50; c++) {
                for (d = 1; d <=50; d++) {
                    for (i = 0; i < formats.length; i++) {
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
                        System.out.println("c:"+c+" d:"+d);
                        fileCount++;
                        int readChar;
                        StringBuilder numberBuilder=new StringBuilder();
                        while ((readChar=r.read())!= -1) {
                            char ch=(char)readChar;
                            if(ch==32||ch==10){
                                if(numberBuilder.isEmpty()){
                                    continue;
                                }
                                Integer number=Integer.parseInt(numberBuilder.toString());
                                numberBuilder.setLength(0);
                                maxNumber= maxNumber < number ? number : maxNumber; minNumber= minNumber > number ? number : minNumber;
                                if(map.isEmpty()){
                                    maxFreqNumber=number; minFreqNumber=number;
                                    map.put(number,1);
                                    continue;
                                }
                                if(map.containsKey(number)){
                                    map.replace(number,map.get(number)+1);
                                    maxFreqNumber= map.get(maxFreqNumber) < map.get(number) ? number : maxFreqNumber;
                                    minFreqNumber= map.get(minFreqNumber) > map.get(number) ? number : minFreqNumber;
                                }else{
                                    map.put(number,1);
                                }
                            } else{
                                numberBuilder.append(ch);
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

