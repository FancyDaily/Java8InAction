package chapter_vI.collector_of_your_own;

import chapter_Iv.entity.Dish;
import chapter_Iv.uitl.DishUtils;
import chapter_v.fold_practice.Trader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @Author: FangyiXu
 * @Date: 2019-10-10 17:25
 */
public class Demo {
    public static void main(String[] args) {
        List<Dish> dishes = DishUtils.getDishes();
        List<Dish> collect = dishes.stream()
                .collect(new MyToListCollector<Dish>());
        System.out.println(collect);

        //对于IDENTITY_FINISH这种收集，有简便的方式(它无法传递Characteristics, 它永远是IDENTIFY_FINISH 和 CONCURRENT的)
        List<Object> collect1 = dishes.stream()
                .collect(
                        ArrayList::new, //supplier
                        List::add,  //accumulator
                        List::addAll    //compiner
                );
        System.out.println(collect);

        int candidate = 47;
        Map<Boolean, List<Integer>> booleanListMap = IntStream.rangeClosed(2, 47).boxed()
                .collect(new MyPartitioningByCollector());

        System.out.println(booleanListMap);

        System.out.println(System.nanoTime());

        //同样直接把实现以lambda的形式传餐(完全的函数式编程！这是一个一次性的实现！可读性差，可重用性差。)
        int n = 34;
        HashMap<Boolean, List<Integer>> booleanListHashMap = IntStream.rangeClosed(2, n).boxed().collect(
                () -> new HashMap<Boolean, List<Integer>>() {{
                    put(true, new ArrayList<>());
                    put(false, new ArrayList<>());
                }},     //supplier
                (HashMap<Boolean, List<Integer>> acc, Integer cand) -> {
                    acc.get(MyPartitioningByCollector.isPrime(acc.get(true), cand))
                            .add(cand);
                },      //accumulator
                (map1, map2) -> {
                    map1.get(true).addAll(map2.get(true));
                    map1.get(false).addAll(map2.get(false));
                }       //combinder
        );
        System.out.println(booleanListHashMap);
    }
}
