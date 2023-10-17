package test;

import com.sun.jdi.IntegerType;


import javax.swing.*;

public class InstanceCallbackDigestUserInterface {
    private String filename;
    private byte[] digest;

    public InstanceCallbackDigestUserInterface(String filename){
        this.filename=filename;
    }

    void receiveDigest(byte[] digest){
        this.digest=digest;
        System.out.println(this);
    }

    void calculateDigest(){
        InstanceCallbackDigest callback=new InstanceCallbackDigest(filename,this);
        Thread thread=new Thread(callback);
        thread.start();
    }

    String toHexString(byte[] bytes){
        StringBuilder hexString =new StringBuilder();
        for (int i=0;i<bytes.length;i++){
            String hex= Integer.toHexString(0xFF & bytes[i]);
            if(hex.length()==1){ hexString.append("0");}
            hexString.append(hex);
        }
        return hexString.toString();
    }


    @Override
    public String toString() {
        String result=filename+":";
        if(digest!=null)
            result+=toHexString(digest);
        else
            result+="not available";
        return result;
    }
}
