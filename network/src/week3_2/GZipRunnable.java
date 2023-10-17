package week3_2;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class GZipRunnable implements Runnable{
    private final File input;
    public GZipRunnable(File input){
        this.input=input;
    }

    @Override
    public void run() {
        File output = new File(input.getName().split("\\.")[0]+".gz");//.은 예약어여서 \\.과 같이 사용해야 한다.
        System.out.println(output.getName());
        if(output.exists()) {
            System.out.println("파일이 이미 존재합니다.");
            return;
        }
        try (
                InputStream in =new BufferedInputStream(new FileInputStream(input));
                OutputStream out=new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(output)));
        ){
            int b;
            while((b=in.read())!=-1) out.write(b); //int는 정보 자체가 아니라 어디까지 한 번에 써야하는지를 알려주는 것이다. 정보는 버퍼에 있겠지
            out.flush();
        }catch (IOException e){ e.printStackTrace(); }


    }
}
