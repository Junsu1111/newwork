package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class InstanceCallbackDigest implements Runnable{
    private String filename;
    private InstanceCallbackDigestUserInterface callback;
    public InstanceCallbackDigest(String filename, InstanceCallbackDigestUserInterface callback){
        this.filename=filename;
        this.callback=callback;
    }

    @Override
    public void run() {
        File file=new File(filename);
        try {
            FileInputStream in=new FileInputStream(file);
            MessageDigest sha = MessageDigest.getInstance("SHA_256");
            DigestInputStream din=new DigestInputStream(in,sha);
            while(din.read()!=-1);
            byte[] digest=sha.digest();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
//암호화 시킨다음에 화면에 출력하는 코드를 작성하시오. 콜백함수로