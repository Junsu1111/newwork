package week5;
import java.io.*;
import java.net.*;
public class ContentTypePreferenceTest {
    public static void main(String[] args) {
        try {
            URL url=new URL("https://cdn.oreillystatic.com/oreilly ");
            Class<?>[] types=new Class[3];
            types[0]=String.class;
            types[1]=Reader.class;
            types[2]=InputStream.class;
            Object o=url.getContent(types);
            System.out.println(o.getClass().getName());
            if(o instanceof String){
                System.out.println(o);
            }else if(o instanceof Reader){
                int c;
                Reader r=(Reader)o;
                while((c=r.read())!=-1){ System.out.print((char)c);}
                r.close();
            }else if(o instanceof InputStream){
                System.out.println("The content type is 'InputStream'");
                int c;
                InputStream in=(InputStream)o;
                while((c=in.read())!=-1){ System.out.write(c);}
                in.close();
            }else{
                System.out.println("Error: unexpected type"+o.getClass());
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
