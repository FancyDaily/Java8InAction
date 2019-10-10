package chapter_III;

import chapter_I.Apple;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/**
 * @Author: FangyiXu
 * @Date: 2019-05-28 11:56
 */
public class Main {

    public static void process(Runnable r) {
        r.run();
    }

    public static void main(String[] args) {
        //下列皆可达成
        Runnable r0 = new Runnable() {  //传统的方式
            @Override
            public void run() {
                System.out.println("Hello World 0");
            }
        };
        process(r0);

        Runnable r1 = () -> System.out.println("Hello World 1"); //将一个lambda表达式赋值给一个函数式接口
        process(r1);


        process(() -> System.out.println("Hello World 2")); //参数是一个函数式接口(则使用lambda表达式)

        String path = "/Users/xufangyi/Downloads/码农的必修课第一期03.mp4";
        try {
            FileUtils.Functional functional= (BufferedReader br) -> br.readLine();  //读取一行的函数
            FileUtils.Functional functional1 = (BufferedReader br) -> br.readLine() + br.readLine();    //读取两行的函数
            String oneLine = FileUtils.processFile(functional, path);
            String twoLines = FileUtils.processFile(functional1, path);
            System.out.println(oneLine);
            System.out.println(twoLines);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //方法引用
        List<Apple> apples = new ArrayList<>();
        for(int i=0; i<3; i++) {
            int temp = 999 - i * 100;
            Apple apple = new Apple();
            apple.setColor("green");
            if(i==1) {
                apple.setColor("red");
            }
            apple.setPrice(temp - 100);
            temp+=10;
            apple.setWeight(temp - 11);
            if(i==2) {
                apple.setWeight(1080);
            }
            apples.add(apple);
        }
        ArrayList<Apple> originApples = new ArrayList<>(apples);
        System.out.println("Original Apples:" + originApples);
        List<Apple> newList = apples;
        System.out.println(newList + "new List");
        apples.sort(Comparator.comparingInt(Apple::getWeight)); //方法引用本身就等同于lambda表达式
        System.out.println(apples);

        newList.sort((Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight());
        System.out.println(newList);

        Consumer<Object> objectConsumer = (Object s) -> System.out.println(s);  //提示可以被方法引用取代
        Consumer<Object> consumer = System.out::println;

        print("James", objectConsumer);
        print(apples,objectConsumer);
        print(newList, consumer);


        List<String> strings = Arrays.asList("B", "d", "A", "c");
//        List<String> tempStrings = strings;   //将引用赋值给tempString 改变原集合会影响到现有集合
        List<String> tempStrings = new ArrayList<>(strings);
        System.out.println(strings);
        strings.sort((s1,s2) -> s1.compareToIgnoreCase(s2));
        System.out.println(strings);

        System.out.println(tempStrings);
        tempStrings.sort(String::compareToIgnoreCase);
        System.out.println(tempStrings);


        //构造函数的引用
        List<Integer> list = Arrays.asList(7, 3, 4, 9);
        List<Apple> apples1 = AppleSupplier.supplyAll(list, Apple::new);
        System.out.println(apples1);


        //有趣的应用
        System.out.println("...............");
        Apple apple = AppleSupplier.giveMeApple(100, 1);
        System.out.println(apple);

        Function<String, Integer> function = s -> Integer.parseInt(s);
        BiPredicate<List<String>, String> biPredicate = (aList, string) -> aList.contains(string);

        Function<String, Integer> function1 = Integer::parseInt;
        BiPredicate<List<String>, String> biPredicate1 = List::contains;
        List<Apple> currentApples = new ArrayList<>(apples);

//        apples.sort(Comparator.comparing(Apple::getColor));
        //比较器复合
        // 当要表示颜色正序重量倒序时 TODO (当在比较器链中使用预置的Comprator中的reversed() 方法表示某条件倒序，应把该条件前置)
        originApples.sort(Comparator.comparing(Apple::getColor)   //错误的结果:[Apple{weight=898, price=799, color='red'}, Apple{weight=998, price=899, color='green'}, Apple{weight=1080, price=699, color='green'}]
                .thenComparing(Apple::getWeight).reversed()
        );

        System.out.println(originApples);

        originApples.sort(Comparator.comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor)
        );
        System.out.println(originApples);   //正确的结果

       /* currentApples.sort(
                (a1, a2) -> a2.getColor().compareToIgnoreCase(a1.getColor())
        );
        System.out.println(currentApples);

        List<String> asList = Arrays.asList("z", "v", "a", "b");
        asList.sort(String::compareToIgnoreCase);
*/

       //谓词复合
        Predicate<Apple> isRed = (a) -> "red".equals(a.getColor());
        Predicate<Apple> isNotRed = isRed.negate();
        Predicate<Apple> isGreen = a -> "greem".equals(a.getColor());
        Predicate<Apple> isRedOrGreen = isRed.or(isGreen);
        Predicate<Apple> isRedAndHeavy = isRed.and(a -> a.getWeight() > 150);
        Predicate<Apple> isRedAndHeavyOrGreen = isRedAndHeavy.or(isGreen);

        //函数复合
        //ToIntFunction<Integer> f = x -> x + 1;
        Function<Integer,Integer> f = x -> x + 2;
        Function<Integer,Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        Integer result = h.apply(1);
        System.out.println(result);
        Function<String, String> addHeader = Main::addHeader;
    }

    static String addHeader(String text) {
        text = text==null?"":text;
        String header = "Fancy Daily:";
        return header + text;
    }

    static <T> void print(T t,Consumer<T> consumer) {
        consumer.accept(t);
    }

}

