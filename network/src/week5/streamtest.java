package week5;

import week2.Inputstream;

import java.io.*;

public class streamtest {
    public static void main(String[] args) {
        try{
            File file=new File("src/week5/test.txt");
            InputStream in=new FileInputStream(file);
            in=new BufferedInputStream(in);
            OutputStream out=new FileOutputStream("testOutput.txt");
            out=new BufferedOutputStream(out);//byte�� �İ� ���� ���۶� ��ǻ�� ���۶��� �ٸ� ���̴�.
            int c;
            byte[] buffer=new byte[3];
            while((c=in.read())!=-1){
//                System.out.println(c);->�ϴ� ���⼭�� �ƽ�Ű ���� ���´�.
//                System.out.println((char)c);->���⼭�� �ƽ�Ű�� �ص��� ���� �ʿ�
//                out.write(c);->�ƽ�Ű�� �ص��Ѱ� ���� ���⼭ �� �� �ִ� ���� stream������ c�� ������ �� ��ü�̴�.
                out.write(c);
            }
            out.flush();
//            while((c=in.read(buffer))!=-1){
//                System.out.println(c);//���⼭�� c�� ������ ��ü�� �ƴ϶� ������ �о����� ��Ÿ���� ������!
//                //�׷��Ƿ� ���۶� ���� ������ �׷��ٴ� ���̴�.
//                out.write((char)c);
//            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
