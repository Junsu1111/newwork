package week3_1;

public class CallbackDigestUserInterface {
    public static String toHexString(byte[] bytes){
        StringBuilder hexString=new StringBuilder();
        for(int i=0;i<bytes.length;i++){
            String hex=Integer.toHexString(0xFF&bytes[i]);
            if(hex.length()==1){
                hexString.append("0");
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    public static void receiveDigest(byte[] digest,String name){
        StringBuilder result=new StringBuilder(name);
        result.append(": ");
        result.append(toHexString(digest));
        System.out.println(result);
    }

    public static void main(String[] args) throws InterruptedException{
        for(String filename:args){
            CallbackDigest cb=new CallbackDigest(filename);
            Thread t=new Thread(cb);
            t.start();
        }
    }
}
