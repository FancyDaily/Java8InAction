package chapter_v.fold;

import chapter_v.fold_practice.Trader;
import chapter_v.fold_practice.Transaction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: FangyiXu
 * @Date: 2019-06-04 14:36
 */
public class Practice {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader fancy = new Trader("Fancy", "Seatle");
        Trader ivy = new Trader("Ivy", "Seatle");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(raoul, 2011, 300),
                new Transaction(mario, 2019, 1400),
                new Transaction(fancy, 2019, 1900),
                new Transaction(ivy, 2019, 2200),
                new Transaction(mario, 2011, 900)
        );

        //找出2011年发生的所有交易，安交易额升序
        List<Transaction> collect2011 = transactions.stream().filter(
                trans -> trans.getYear() == 2011
        ).sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(collect2011);
        //交易员都在哪些不同的城市工作过
        List<Trader> traders = Arrays.asList(raoul, mario, fancy, ivy);
        List<String> traderCities = traders.stream()
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(traderCities);

        List<String> traderCities2 = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(traderCities2);

        Set<String> traderCities3 = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(Collectors.toSet());
        System.out.println(traderCities3 + " <- toSet");
        //返回所有来自剑桥的交易员，按姓名排序
        List<Trader> tradersFromCambridge = traders.stream()
                .filter(a -> "Cambridge".equals(a.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(tradersFromCambridge);

        List<Trader> tradersFromCambridge2 = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(tradersFromCambridge2);
        //返回所有交易员的姓名字符串，按字母顺序排序
        List<String> sorted = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted().collect(Collectors.toList());
        System.out.println(sorted);

        String reduce1 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted()
//                .reduce("", (a, b) -> a + "," + b)
                .collect(Collectors.joining(", "))
                ;
        System.out.println(reduce1);
        //有没有交易员在米兰工作的
        boolean trueOrFalse = traders.stream()
                .anyMatch(trader -> "Milan".equals(trader.getCity()));
        System.out.println(trueOrFalse);
        //打印生活在剑桥的交易员的所有交易额
        Integer valueOfCambridge = transactions.stream()
                .filter(a -> "Cambridge".equals(a.getTrader().getCity()))
                .map(Transaction::getValue)
                .reduce(0, Integer::sum);
        System.out.println(valueOfCambridge);
        //所有交易中，最高的交易额是多少
        Optional<Integer> maxOptional = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        if(maxOptional.isPresent()) {
            System.out.println(maxOptional.get());
        }
        //找到交易额最小的交易
        Transaction minValueTransaction = transactions.stream()
                .reduce(new Transaction(mario, 2019, 999999999), (a, b) -> a.getValue() < b.getValue() ? a : b);
        System.out.println(minValueTransaction);

        Optional<Transaction> reduce = transactions.stream().reduce((a, b) -> a.getValue() < b.getValue() ? a : b);
        System.out.println(reduce.get());
        Optional<Trader> sssss = traders.stream().filter(trader -> trader.getCity().equals("sssss")).findAny();
//        Trader trader = sssss.get();  //NoSuchElementException
//        System.out.println(trader);

        Optional<Transaction> min = transactions.stream()
//                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1:t2)
                .min(Comparator.comparing(Transaction::getValue));
        System.out.println(min.get());

    }
}
