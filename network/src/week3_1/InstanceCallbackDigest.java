package week3_1;

import java.io.*;
import java.security.*;

public class InstanceCallbackDigest implements Runnable {

    private String filename;
    private InstanceCallbackDigestUserInterface callback;

    public InstanceCallbackDigest(String filename, InstanceCallbackDigestUserInterface callback) {
        this.filename = filename;
        this.callback = callback;
    }


    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA_256");
            DigestInputStream din = new DigestInputStream(in, sha);
            while (din.read() != -1) ;
            din.close();
            byte[] digest = sha.digest();
            callback.receiveDigest(digest);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
