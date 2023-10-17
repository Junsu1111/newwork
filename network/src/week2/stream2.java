package week2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class stream2 {
    public static void main(String[] args) {
        Trader kyu = new Trader("Kyu", "Seoul");
        Trader ming = new Trader("Ming", "Gyeonggi");
        Trader hyuk = new Trader("Hyuk", "Incheon");
        Trader hyan = new Trader("Hyuk", "Seoul");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(kyu, 2019, 30000),
                new Transaction(kyu, 2020, 12000),
                new Transaction(ming, 2020, 40000),
                new Transaction(ming, 2020, 7100),
                new Transaction(hyan, 2019, 5900),
                new Transaction(hyuk, 2018, 6300)
        );
//        transactions.stream()
//                .filter(transaction->transaction.getYear()==2020)
//                .sorted((t1,t2)->t1.getValue()-t2.getValue())
//                .map(transaction->transaction.getTrader().getName()).forEach(System.out::println);
//        transactions.stream().map(transaction->transaction.getTrader().getCity()).distinct().forEach(System.out::println);
//        transactions.stream().filter(transaction->transaction.getTrader().getCity().equals("Seoul")).sorted((t1,t2)->t1.getTrader().getName().compareTo(t2.getTrader().getName())).map(t->t.getTrader().getName()).forEach(System.out::println);
//        transactions.stream().map(transaction -> transaction.getTrader().getName()).sorted().forEach(System.out::println);
//        System.out.println(transactions.stream().filter(transaction -> transaction.getTrader().getName().equals("Busan")).findAny().isEmpty());
//        transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Seoul")).forEach(
//                t->{
//                    System.out.print(t.getTrader().getName()+" ");
//                    System.out.print(t.getYear()+" ");
//                    System.out.print(t.getValue()+" ");
//                    System.out.println();
//        });



    }

}
class Trader {
    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    private String name;
    private String city;

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }
}

class Transaction {
    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    private Trader trader;
    private int year;
    private int value;
}