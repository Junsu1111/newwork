package week2;

import java.util.Random;
import java.util.stream.IntStream;

public class stream3 {
    public static void main(String[] args) {
//        String[] strArr = {"aaa","bb","c","dddd"};
//        System.out.println(Arrays.stream(strArr).mapToInt(str->str.length()).sum());
//        System.out.println(Arrays.asList(strArr).stream().max((s1,s2)->s1.length()-s2.length()).orElse("null"));
//        new Random().ints(1,46).distinct().limit(6).boxed().forEach(System.out::println);
//ints는 무한 스트림 생성
        IntStream.range(1,7).boxed().flatMap(n1->IntStream.range(1,7).mapToObj(n2->new Integer[]{n1,n2})).filter(dice->dice[0]+dice[1]==6).forEach(dice->System.out.println("("+dice[0]+", "+dice[1]+")"));

    }
}
