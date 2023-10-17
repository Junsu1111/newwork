package week3_2;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GZipAllFiles {
    public final static int THREAD_COUNT=4;

    public static void main(String[] args) {
        String[] filenames={"src/week3_2/test1.txt","src/week3_2/test2.txt","src/week3_2/test3.txt"};

        ExecutorService pool= Executors.newFixedThreadPool(THREAD_COUNT);
        for (String filename : filenames){
            File f=new File(filename);
            System.out.println(f.exists());
            if(!f.exists()){
                System.out.println("파일이 존재하지 않습니다.");
                continue;
            }
            if(f.isDirectory()){
                File[] files=f.listFiles();
                for (int i=0;i<files.length;i++){
                    if(!files[i].isDirectory()){
                        Runnable task=new GZipRunnable(files[i]);
                        pool.submit(task);
                    }
                }
            }else{
                Runnable task=new GZipRunnable(f);
                pool.submit(task);
            }
        }
        pool.shutdown();
    }
}
