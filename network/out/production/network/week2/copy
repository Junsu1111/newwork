package week2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Inputstream {
    public static void main(String[] args) {
//        int inchar=0;
//        try {
//            inchar=System.in.read();
//            //read는 설정 바이트 단위를 아스키 코드로 읽어서 int로 리턴한다.
//            System.out.println(inchar);
//            System.out.write(inchar);
//            //write는 내가 입력해준 문자 그대로 출력해준다.
//            System.out.flush();
//        } catch (IOException e) {
//
//        }
//        System.out.println(48=='0');
//        //int와 char을 비교하는 것은 가능하다.

//        FileInputStream fis=null;
//        try {
//            fis=new FileInputStream("src/week2/Inputstream.java");
//            int i=0;
//            while((i=fis.read())!=-1){
//                System.out.write(i);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                fis.close();
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try {
            fis=new FileInputStream("src/week2/Inputstream.java");
            fos=new FileOutputStream("src/week2/copy");
            int i=0;
            byte[] buffer=new byte[512];
            while((i=fis.read(buffer))!=-1){ //read는 버퍼에 담긴 개수를 리턴하고 파일이 끝나면 -1을 리턴한다.
                fos.write(buffer,0,i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
