package chapter_vI.groupingBy;

import chapter_Iv.entity.Dish;
import chapter_Iv.uitl.DishUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: FangyiXu
 * @Date: 2019-10-10 11:08
 */
public class Test {
    public static void main(String[] args) {
        //partitioningBy
        List<Dish> dishes = DishUtils.getDishes();
        Map<Boolean, Map<Boolean, List<Dish>>> result1 = dishes.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.partitioningBy(d -> d.getCalories() > 500)));
        System.out.println(result1);

        //counting
        Map<Boolean, Long> result2 = dishes.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.counting()));
        System.out.println(result2);

        //质数与非质数的例子
        int n = 27;
        Map<Boolean, List<Integer>> booleanListMap = IntStream.rangeClosed(2, n)
                .boxed()
                .collect(Collectors.partitioningBy(a -> isPrime(a, true)));
        System.out.println(booleanListMap);
    }

    private static boolean isPrime(int candidate) {
        return IntStream.range(2, candidate)
                .noneMatch(i -> candidate % i == 0);    //没有任何2到他本身的数被整除，说明是质数
    }

    public static boolean isPrime(int candidate, boolean isBetter) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return isBetter? IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0):isPrime(candidate);
    }
}
