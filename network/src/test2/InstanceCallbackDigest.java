package test2;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class InstanceCallbackDigest implements Runnable{
    //���⼭ ��ȣȭ�ϴ� �ڵ带 �ۼ��� ������ ���ڷ� �ٲ��ִ°Ŵ� �������̽����� �Ѵ�.
    //�Լ��� ȣ���ϸ� ���� ������ �а� ȣ���� ���⼭ �Ѵ�. filename�� �޾Ƽ� �ް� �������̽� ��ü�� ���⼭ ������ �ִ´�.
    //�� ������ �� ������ �������̽������� digest�� �޾Ƽ� �۾��� �Ѵ�.
    //�ٵ� ��Ƽ ������� �۾��� �Ǿ�� �Ѵ�.
    //�������̽����� �ҷ��� ������ ���� �д�. �״ϱ� digest�� �θ� �������ʹ� �������̽� ��ü�� ����ϴ� ���̴�.
    private InstanceCallbackDigestUserInterface callback;
    public InstanceCallbackDigest(InstanceCallbackDigestUserInterface callback){
        this.callback=callback;
    }

    @Override
    public void run() {
        try {
            FileInputStream in=new FileInputStream(callback.getFilename());
            MessageDigest sha=MessageDigest.getInstance("SHA_256");
            DigestInputStream din=new DigestInputStream(in,sha);
            while(din.read()!=-1);
            byte[] digest=sha.digest();
            callback.receiveDigest(digest);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }
}








