package test2;

public class InstanceCallbackDigestUserInterface {
    private byte[] digest;
    private String filename;
    public InstanceCallbackDigestUserInterface(String filename){
        this.filename=filename;
    }

    public String getFilename() {
        return filename;
    }

    void calculateCallbackDigest(){
        InstanceCallbackDigest callbackDigest=new InstanceCallbackDigest(this);
        Thread thread=new Thread(callbackDigest);
        thread.start();
    }

    void receiveDigest(byte[] bytes){
        this.digest=digest;
        System.out.println(this);
    }

    String toHexString(byte[] bytes){
        StringBuilder hexString=new StringBuilder();
        for(int i=0;i<bytes.length;i++){
            String hex=Integer.toHexString(0xFF&bytes[i]);
            if(hex.length()==1){ hexString.append("0");}
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @Override
    public String toString() {
        String result=filename+": ";
        if(digest==null){
            result+=" is not available.";
        }else{
            result+=toHexString(digest);
        }
        return result;
    }
}
