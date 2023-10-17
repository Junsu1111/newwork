package test;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class test1 {
    public static void main(String[] args)  {

        try {
            System.out.println(InetAddress.getByName("www.baconbaconsdfdf.com"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
