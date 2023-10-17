package threads;

import java.io.*;
import java.security.*;
public class DigestRunnable  implements Runnable{
    private String filename;

    public  DigestRunnable(String filename){
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

//            StringBuilder result=new StringBuilder(filename);
//            result.append(": ");
//            result.append(toHexString(digest));
//            System.out.println(result);



//            System.out.print("filename: ");
//            System.out.println(toHexString(digest));
//            System.out.println();
//            이렇게 하면 멀티 스레드이기 때문에 막 섞여서 출력된다.(넣는 건 왔다갔다하면서 넣어도 되는데 출력은 한번에 해야지)
        }catch (IOException e){
            e.printStackTrace();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }
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
            DigestRunnable dr=new DigestRunnable(filename);
            Thread t=new Thread(dr);
            t.start();
        }
        //굳이 thread의 함수를 사용하지 않는데 상속해서 사용할 필요가 없다.
        // 그래서 그냥 task(처리하는일->run함수 안에 일)과 thread자체의 코드를 분리하자는 것
    }




















}
