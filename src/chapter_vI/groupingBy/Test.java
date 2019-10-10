package chapter_vI.groupingBy;

import chapter_Iv.entity.Dish;
import chapter_Iv.uitl.DishUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    }
}
