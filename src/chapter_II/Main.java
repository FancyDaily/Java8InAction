package chapter_II;

import chapter_I.Apple;
import chapter_II.util.MyFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleSupplier;

/**
 * @Author: FangyiXu
 * @Date: 2019-05-27 21:34
 */
public class Main {
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        for(int i=0; i<3; i++) {
            int temp = i;
            Apple apple = new Apple();
            apple.setColor("green");
            if(i==1) {
                apple.setColor("red");
            }
            apple.setPrice(temp + 100);
            temp+=10;
            apple.setWeight(temp * 11);
            apples.add(apple);
        }
        List<Apple> apples1 = AppleUtils.filterApples(apples, new AppleGreenColorPredicate());
        /*for(Apple apple:apples1) {
            System.out.println(apple.getColor());
        }*/

        //1.使用预先定制的策略进行打印
        AppleUtils.prettyPrintApple(apples, new SmartAppleWeightPrintHelper());
        AppleUtils.prettyPrintApple(apples, new AppleWeightPrintHelper());
        //2.使用匿名类，随用随建
        AppleUtils.prettyPrintApple(apples, new ApplePrintHelper() {    //(Anonymous class) can be replaced with lambda
            @Override
            public void doHelp(List<Apple> apples) {
                for(Apple apple:apples) {
                    if("red".equals(apple.getColor())) {
                        System.out.println(apple.getWeight() + "," + apple.getPrice() + "," + apple.getColor());
                    }
                }
            }
        });
        System.out.println("------------3------------");
        //3.使用lambda
        AppleUtils.prettyPrintApple(apples, (List<Apple> lotsOfApples) -> {
            for(Apple apple:lotsOfApples) {
                if("red".equals(apple.getColor())) {
                    System.out.println(apple.getWeight() + "," + apple.getPrice() + "," + apple.getColor());
                }
            }
        });

        System.out.println("------------My filter------------");
        //使用不同的谓词轻松完成不同的过滤实现
        Integer[] numberArray = {0,1,2,3,4,5,6,7,8,9};
        List<Integer> numbers = Arrays.asList(numberArray);
        List<Integer> resultNumbers = MyFilter.filter(numbers, (Integer i) -> i % 3 == 0);
        System.out.println(resultNumbers);
        List<Apple> filter = MyFilter.filter(apples, (Apple a) -> "red".equals(a.getColor()));
        System.out.println(filter);

        //排序
        resultNumbers.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println(resultNumbers);

        apples.sort((Apple appleA, Apple appleB) -> appleA.getWeight() - appleA.getWeight());
        apples.sort((Apple appleA, Apple appleB) -> appleA.getWeight() - appleA.getWeight());
        System.out.println(apples);

        //线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!");
            }
        });

        Thread anotherThread = new Thread(() -> {
            System.out.println("Happy SplatFast!");
        }
        );

        thread.run();
        anotherThread.run();

        DoubleSupplier doubleSupplier = () -> 42;
        System.out.println(doubleSupplier.getAsDouble());

        Thread t1 = new Thread(() -> new ArrayList<>());

    }
}
