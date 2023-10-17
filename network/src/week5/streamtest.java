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
            out=new BufferedOutputStream(out);//byte로 냐가 쓰는 버퍼랑 컴퓨터 버퍼랑은 다른 것이다.
            int c;
            byte[] buffer=new byte[3];
            while((c=in.read())!=-1){
//                System.out.println(c);->일단 여기서는 아스키 값이 나온다.
//                System.out.println((char)c);->여기서는 아스키를 해독한 것이 너옴
//                out.write(c);->아스키를 해독한게 나옴 여기서 알 수 있는 것은 stream에서도 c는 데이터 그 자체이다.
                out.write(c);
            }
            out.flush();
//            while((c=in.read(buffer))!=-1){
//                System.out.println(c);//여기서는 c가 데이터 자체가 아니라 어디까지 읽었나를 나타내는 정보임!
//                //그러므로 버퍼랑 같이 쓸떄만 그렇다는 것이다.
//                out.write((char)c);
//            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
