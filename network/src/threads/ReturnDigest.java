package threads;

import java.io.*;
import java.security.*;
public class ReturnDigest extends  Thread{
    private String filename;
    private byte[] digest;

    public ReturnDigest(String filename){
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
            digest=sha.digest();
        }catch (IOException e){
            e.printStackTrace();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public static String toHexString(byte[] bytes){
        StringBuilder hexString =new StringBuilder();
        for(int i=0;i<bytes.length;i++){
            String hex=Integer.toHexString(0xFF&bytes[i]);
            if(i==1)
                System.out.println(hex);
            if(hex.length()==1){
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public byte[] getDigest(){
        return digest;
    }

    public static void main(String[] args) {
        String[] filenames={"src/threads/test1.txt","src/threads/test2.txt","src/threads/test3.txt"};
        for(String filename :filenames) {
            ReturnDigest dr=new ReturnDigest(filename);
            dr.start();

            StringBuilder result=new StringBuilder(filename);
            result.append(": ");
            byte[] digest=dr.getDigest();
            result.append(toHexString(digest));
            System.out.println(result);
            //멀티스레드여서 아직 get하기 전에 접근해서 nullpointer exception이 뜸.
            //polling을 해도 안된다면 while문이 너무 빠르게 돌아서 cpu를 독점해서 계속 null로 반복문을 도는 것이다.
            //때문에 while문 안에 의미없는 코드를 넣어주면 while문이 느려져서 된다.
        }
    }
}
