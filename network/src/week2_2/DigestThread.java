package week2_2;

import java.io.*;
import java.security.*;
public class DigestThread  extends Thread{
    //멀티 스레드 코드
//    private String filename;
//
//    public  DigestThread(String filename){
//        this.filename=filename;
//    }
//
//    @Override
//    public void run() {
//        try{
//            FileInputStream in=new FileInputStream(filename);
//            MessageDigest sha=MessageDigest.getInstance("SHA-256");
//            DigestInputStream din =new DigestInputStream(in,sha);
//            while(din.read()!=-1);
//            din.close();
//            byte[] digest=sha.digest();
//
//            StringBuilder result=new StringBuilder(filename);
//            result.append(": ");
//            result.append(toHexString(digest));
//            System.out.println(result);
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }catch (NoSuchAlgorithmException e){
//            e.printStackTrace();
//        }
//    }

    //싱글스레드 코드
private String filename;

    public  DigestThread(String filename){
        this.filename=filename;
    }

    @Override
    public void run() {
        try{
            FileInputStream in=new FileInputStream(filename);
            MessageDigest sha=MessageDigest.getInstance("SHA-256");
            DigestInputStream din =new DigestInputStream(in,sha);
            while(din.read()!=-1);
            din.close();
            byte[] digest=sha.digest();

            StringBuilder result=new StringBuilder(filename);
            result.append(": ");
            result.append(toHexString(digest));
            System.out.println(result);

        }catch (IOException e){
            e.printStackTrace();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }
    //싱글 스레드로 만들어보기
    public static String toHexString(byte[] bytes){
        StringBuilder hexString =new StringBuilder();
        for(int i=0;i<bytes.length;i++){
            String hex=Integer.toHexString(0xFF&bytes[i]);
            if(hex.length()==1){
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }


    public static void main(String[] args) {
        String[] filenames={"src/test1.txt","src/test2.txt","src/test3.txt"};
        for(String filename : filenames){
            Thread t=new DigestThread(filename);
            t.start();
            //Thread이기 때문에 넣어준 순서되로 출력되는 것이 아니라 먼저 처리되는 것부터 출력한다. 당연히 암호화이니까 같은 문자라고 같은 암호가 출려되지 않는다.
        }
    }




















}
