package week5;

import java.net.*;
import java.io.*;

public class ReachabiltyTest {
    public static void main(String[] args) {
        try{
            byte[] addr={(byte)202,30,38,108};
            InetAddress address=InetAddress.getByAddress(addr);
            int timeout=3000;
            int ttl=10;
            if(address.isReachable(timeout)){
                System.out.println(address.getHostName()+" CAN BE reached within "+timeout);
            }else{
                System.out.println(address.getHostName()+"CANNOT BE reached within"+timeout);
            }
        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
