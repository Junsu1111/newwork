package week5;

import java.net.URLEncoder;

public class AsciiTest {
    public static void main(String[] args) {
        System.out.println((char)32);
        System.out.println((char)43);
        System.out.println(URLEncoder.encode(" "));// �ڹٴ� �� Ư���ؼ� ������ %20���� ���ڵ��ϴ� ���� �ƴ϶� +�� ���ڵ��Ѵ�.
        System.out.println(URLEncoder.encode("+"));
        System.out.println(0x32);
    }
}
