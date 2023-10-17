package test2;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetworkInterfaceTest {
    public static void main(String[] args) {
        try {
            NetworkInterface nis=null;
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while(interfaces.hasMoreElements()){
                nis=interfaces.nextElement();
                System.out.println(nis);
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
