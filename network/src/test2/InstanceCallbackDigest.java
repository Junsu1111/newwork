package test2;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class InstanceCallbackDigest implements Runnable{
    //여기서 암호화하는 코드를 작성한 다음에 문자로 바꿔주는거는 인터페이스에서 한다.
    //함수를 호출하면 먼저 파일을 읽고 호출은 여기서 한다. filename을 받아서 받고 인터페이스 객체를 여기서 가지고 있는다.
    //그 다음에 그 다음에 인터페이스에서는 digest를 받아서 작업을 한다.
    //근데 멀티 쓰레드로 작업이 되어야 한다.
    //인터페이스에서 불러서 메인은 따로 둔다. 그니까 digest를 부른 다음부터는 인터페이스 객체를 사용하는 것이다.
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








