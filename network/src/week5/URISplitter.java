package week5;
import java.net.*;
public class URISplitter {
    public static void main(String[] args) {
        String uriString="http://www.cafeaulait.org/books/javaio2/index.html?isbn=0596527500#toc";
        try {
            URI uri=new URI(uriString);
            System.out.println("The URI is "+uri);
            if(uri.isOpaque()){
                System.out.println("URI is not hierarchical");
            }else{
                System.out.println("URI is hierarchical");
                System.out.println("The scheme is "+ uri.getScheme());
                System.out.println("This is not parsed url. Try to get host: "+uri.getHost());
                System.out.println("This is not parsed uri. Try to get userInfo: "+uri.getUserInfo());
                System.out.println("This is not parsed uri. Try to get Port number: "+uri.getPort());
                try{
                    uri=uri.parseServerAuthority();
                    System.out.println("This is parsed uri. The host is "+ uri.getHost());
                    System.out.println("The user info is "+ uri.getUserInfo());
                    System.out.println("The port is "+ uri.getPort());
                }catch (URISyntaxException e){
                    System.out.println("The authority is "+ uri.getAuthority());
                }
                System.out.println("The path is "+ uri.getPath());
                System.out.println("The query string is "+ uri.getQuery());
                System.out.println("The fragment ID is "+ uri.getFragment());
            }
        }catch (URISyntaxException e){
            System.err.println(uriString+" does not seem to be a URI.");
        }
        System.out.println();
    }
}
