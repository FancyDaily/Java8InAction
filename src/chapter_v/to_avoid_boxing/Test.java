package chapter_v.to_avoid_boxing;

import chapter_Iv.entity.Dish;
import chapter_Iv.uitl.DishUtils;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: FangyiXu
 * @Date: 2019-06-04 16:28
 */
public class Test {
    public static void main(String[] args) {
        List<Dish> dishes = DishUtils.getDishes();
        //sum、min、max、average
        int sum = dishes.stream()
                .mapToInt(Dish::getCalories).sum();

        System.out.println(sum);

        List<Integer> collect = dishes.stream()
                .mapToInt(Dish::getCalories).boxed().collect(Collectors.toList());
        System.out.println(collect);

        OptionalInt max = dishes.stream()
                .mapToInt(Dish::getCalories).max();
        max.orElse(1);
        System.out.println(max.getAsInt());

        IntStream evenNumbers = IntStream.rangeClosed(1, 100)   //[1,100]
                .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());

        IntStream evenNumbers1 = IntStream.range(1, 100)    //[1,100)
                .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers1.count());
    }
}
