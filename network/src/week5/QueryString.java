package week5;

import java.beans.Encoder;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.StringTokenizer;

public class QueryString {
    private StringBuilder query=new StringBuilder();

    public StringBuilder getQuery(){
        return query;
    }

    @Override
    public String toString() {
        return getQuery().toString();
    }

    public synchronized void add(String name, String value){
        query.append("&");
        encode(name,value);
    }

    private synchronized void encode(String name, String value){
        try {
            query.append(URLEncoder.encode(name,"UTF-8"));
            query.append("=");
            query.append(URLEncoder.encode(value,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Broken VM does not support UTF-8");//�Լ� �ȿ��� ���̴� private�Լ��̴ϱ� ����ó���� �ƴ϶� ������ ������ ����
        }
    }

    public static void main(String[] args) {
        QueryString qs=new QueryString();
        qs.add("hl","en");
        qs.add("as_q","java");
        qs.add("as_epq","I/O");
        String url="https://www.google.com/search?"+qs;
        System.out.println(url);
        System.out.println(URLDecoder.decode(url));
    }

}
//��ü�� static�� �ƴϿ��� �Լ��� static�̸� �̷��� �ҷ��� �� �ִ�.