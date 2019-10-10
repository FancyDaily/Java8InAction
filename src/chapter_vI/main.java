package chapter_vI;

import chapter_Iv.entity.Dish;
import chapter_Iv.uitl.DishUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: FangyiXu
 * @Date: 2019-06-05 13:44
 */
public class main {
    public static void main(String[] args) {
        /*
        collect(Collector)
         */
        //预定义收集器
        //counting (一般直接用stream.count)
        List<Dish> dishes = DishUtils.getDishes();
        Long count = dishes.stream()
                .collect(Collectors.counting());
        System.out.println(count);
        //最大最小 maxBy minBy
        Optional<Dish> collect = dishes.stream().collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)));
        if(collect.isPresent()) {
            System.out.println(collect.get());
        }

        //求和 summingInt、summingDouble
        Integer sum = dishes.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(sum);

        //平均数 averagingInt..
        //...

        //统计 summarizingInt
        IntSummaryStatistics statistics = dishes.stream().collect(Collectors.summarizingInt(Dish::getCalories));

        String joining = dishes.stream().map(Dish::toString).collect(Collectors.joining(","));
        System.out.println(joining);

        String collect1 = dishes.stream().map(Dish::getName).collect(Collectors.joining(","));
        System.out.println(collect1);

        Integer collect2 = dishes.stream().collect(Collectors.reducing(0, Dish::getCalories, (a, b) -> a + b));
        System.out.println(collect2);

        //reducing(BinaryOperator<T>)   =>  BinaryOperator<T> 相当于 Functional<T,T,T>
        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        Integer collect3 = dishes.stream().collect(Collectors.reducing(0,   //初始值
                Dish::getCalories,  //转换函数
                Integer::sum)); //累计函数

        int sum1 = dishes.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(sum1);

        //分组
        //groupingBy(Function<T>)
        Map<Dish.Type, List<Dish>> collect4 = dishes.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(collect4);

        Map<Integer, List<Dish>> collect5 = dishes.stream().collect(Collectors.groupingBy(Dish::getCalories));
        List<Dish> dishes1 = collect5.get(13131312);
        System.out.println(dishes1==null);
    }
}
