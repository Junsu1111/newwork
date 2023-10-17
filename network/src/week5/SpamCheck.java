package week5;
import java.net.*;
public class SpamCheck {
    public static final String BLACKHOLE="zen.spanhaus.org";
    public static void main(String[] args) {
        String[] hosts={"www.konkuk.ac.kr","www.baconbaconbaocn.com"};
        for(String host : hosts){
            if(!isSpammer(host)){ System.out.println(host+" is a known spammer.");}
            else { System.out.println(host+" appears legitimate.");}
        }
    }


    private static boolean isSpammer(String hostName){//메서드 내에서만 쓸거면 private로 함수를 사용하면 됨. 그니까 함수 내에서 사용되는 함수인 것이다.
        try {
            InetAddress address=InetAddress.getByName(hostName);
            byte[] quad=address.getAddress();
            String query=BLACKHOLE;
            for(byte octet : quad){
                int unsignedByte=octet<0 ? octet+256 : octet;
                query=unsignedByte+"."+query;
            }
            System.out.println(query);
            InetAddress.getByName(query);//예러가 발생하는지 확인함.
            return true;
        }catch (UnknownHostException e){
            return false;
        }
    }
}
