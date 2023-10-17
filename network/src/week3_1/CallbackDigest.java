package week3_1;

import java.io.*;
import java.security.*;
public class CallbackDigest implements  Runnable{
    private String filename;

    public  CallbackDigest(String filename){
        this.filename=filename;
    }

    @Override
    public void run() {
        try {
            FileInputStream in=new FileInputStream(filename);
            MessageDigest sha=MessageDigest.getInstance("SHA_256");
            DigestInputStream din=new DigestInputStream(in,sha);
            while(din.read()!=-1);
            din.close();
            byte[] digest=sha.digest();
            CallbackDigestUserInterface.receiveDigest(digest,filename);
        }catch (IOException e){
            e.printStackTrace();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }
}
