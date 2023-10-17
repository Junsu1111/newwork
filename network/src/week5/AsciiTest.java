package week5;

import java.net.URLEncoder;

public class AsciiTest {
    public static void main(String[] args) {
        System.out.println((char)32);
        System.out.println((char)43);
        System.out.println(URLEncoder.encode(" "));// 자바는 좀 특이해서 공백을 %20으로 인코딩하는 것이 아니라 +로 인코딩한다.
        System.out.println(URLEncoder.encode("+"));
        System.out.println(0x32);
    }
}
