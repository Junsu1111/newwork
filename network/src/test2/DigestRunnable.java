package test2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Callable;

public class DigestRunnable implements Runnable {
    private String filename;
    private static byte[] digest;

    public DigestRunnable(String filename){
        this.filename=filename;
    }

    public static byte[] getDigest(){
        return digest;
    }
    @Override
    public void run() {
        File file=new File(filename);
        try {
            FileInputStream fis=new FileInputStream(file);
            MessageDigest sha=MessageDigest.getInstance("SHA-256");
            DigestInputStream dis=new DigestInputStream(fis,sha);
            int c;
            while((c=dis.read())!=-1);
            digest= sha.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filename="SourceView.txt";
        DigestRunnable dr=new DigestRunnable(filename);
        Thread t=new Thread(dr);
        t.start();
        try { t.join();} catch (InterruptedException e) { e.printStackTrace();}
        byte[] d=DigestRunnable.getDigest();
        for(byte b : d){
            System.out.print(b);
        }
    }
}
