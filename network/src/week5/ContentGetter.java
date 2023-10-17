package week5;
import java.io.*;
import java.net.*;
public class ContentGetter {
    public static void main(String[] args) {
        InputStream in=null;
        try {
            URL url=new URL("https://www.oreilly.com");
            Object o=url.getContent();
            in=(InputStream)o;
            int c;
            while((c=in.read())!=-1){
                System.out.print((char)c);
            }
            System.out.println("I got a "+o.getClass().getName());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                in.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
