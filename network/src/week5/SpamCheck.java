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


    private static boolean isSpammer(String hostName){//�޼��� �������� ���Ÿ� private�� �Լ��� ����ϸ� ��. �״ϱ� �Լ� ������ ���Ǵ� �Լ��� ���̴�.
        try {
            InetAddress address=InetAddress.getByName(hostName);
            byte[] quad=address.getAddress();
            String query=BLACKHOLE;
            for(byte octet : quad){
                int unsignedByte=octet<0 ? octet+256 : octet;
                query=unsignedByte+"."+query;
            }
            System.out.println(query);
            InetAddress.getByName(query);//������ �߻��ϴ��� Ȯ����.
            return true;
        }catch (UnknownHostException e){
            return false;
        }
    }
}
