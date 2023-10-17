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
        File output = new File(input.getName().split("\\.")[0]+".gz");//.�� ������ \\.�� ���� ����ؾ� �Ѵ�.
        System.out.println(output.getName());
        if(output.exists()) {
            System.out.println("������ �̹� �����մϴ�.");
            return;
        }
        try (
                InputStream in =new BufferedInputStream(new FileInputStream(input));
                OutputStream out=new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(output)));
        ){
            int b;
            while((b=in.read())!=-1) out.write(b); //int�� ���� ��ü�� �ƴ϶� ������ �� ���� ����ϴ����� �˷��ִ� ���̴�. ������ ���ۿ� �ְ���
            out.flush();
        }catch (IOException e){ e.printStackTrace(); }


    }
}
