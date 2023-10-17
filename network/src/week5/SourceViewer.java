package week5;
import java.io.*;
import java.net.*;
public class SourceViewer {
    public static void main(String[] args) {
        InputStream in=null;
        try {
            URL url=new URL("https://www.konkuk.ac.kr");
            in=url.openStream();
            OutputStream out=new FileOutputStream("SourceView.txt");
            out=new BufferedOutputStream(out);
            in=new BufferedInputStream(in);
            int c;
            byte[] buffer=new byte[1024];
            while((c=in.read(buffer))!=-1){
                out.write(buffer);
            }
            out.flush();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if(in!=null){
                    in.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
