package chapter_I;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: FangyiXu
 * @Date: 2019-05-27 16:40
 */
public class Main {
    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        Apple apple = new Apple();
        apple.setPrice(1);
        apple.setWeight(30);
        inventory.add(apple);
        apple = new Apple();
        apple.setPrice(3);
        apple.setWeight(40);
        inventory.add(apple);

        List<Apple> collect = inventory.parallelStream()
                .filter((Apple a) -> a.getWeight() > 29)
                .collect(Collectors.toList());
        collect.forEach((b) -> System.out.println(b.getPrice()));
        System.out.println(".......");
        List<Apple> heavyOnes = inventory.stream()
                .filter((Apple a) -> a.getWeight() > 31)
                .collect(Collectors.toList());
        heavyOnes.forEach((a)->System.out.println(a.getPrice()));

    }
}
