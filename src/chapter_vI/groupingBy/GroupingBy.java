package chapter_vI.groupingBy;

import chapter_Iv.entity.Dish;
import chapter_Iv.uitl.DishUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: FangyiXu
 * @Date: 2019-08-07 17:05
 */
public class GroupingBy {

    public static void main(String[] args) {
        //1.多级分组
        List<Dish> dishes = DishUtils.getDishes();
        //将groupingBy作为groupingBy的第二个收集器
        Map<Dish.Type, Map<String, List<Dish>>> collect = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
                    Integer calories = dish.getCalories();
                    if (calories <= 400)
//                        return CaloricLevel.LOW;
                        return "level1";
                    else if (calories > 400 && calories <= 600)
//                        return CaloricLevel.MEDIEUM;
                        return "level2";
                    else
//                        return CaloricLevel.HIGH;
                        return "level3";
                })));
        System.out.println(collect);

        //将counting作为groupingBy的第二个收集器
        Map<Dish.Type, Long> collect1 = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println(collect1);

        //将counting作为groupingBy的第二个收集器groupingBy的第二个收集器
        Map<Dish.Type, Map<String, Long>> collect3 = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
                    Integer calories = dish.getCalories();
                    if (calories <= 400)
//                        return CaloricLevel.LOW;
                        return "level1";
                    else if (calories > 400 && calories <= 600)
//                        return CaloricLevel.MEDIEUM;
                        return "level2";
                    else
//                        return CaloricLevel.HIGH;
                        return "level3";
                }, Collectors.counting())));
        System.out.println(collect3);


        //maxBy
        Map<Dish.Type, Optional<Dish>> collect2 = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType,   //按类型分组
                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)))); //获取属性：Calories 最大的dish (每一组
        System.out.println(collect2);   //得到一个值为Optional类型的map

        Map<Dish.Type, Dish> collect4 = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType,   //按类型分组
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println(collect4); //得到Optional.get()的返回值

        //summingInt
        dishes.stream()
                .collect(Collectors.groupingBy(Dish::getType,   //按类型分组
                        Collectors.summingInt(Dish::getCalories))); //获取属性：Calories 的总和 (每一组
        System.out.println(collect2);   //得到一个值为Optional类型的map

        //partitioningBy(Predicate, Collectors) 分区
        Map<Boolean, List<Dish>> partitionedDishes = dishes.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian));    //按照条件的布尔值结果，将符合条件与不符合条件的分成true、false两组
        System.out.println(partitionedDishes);
        List<Dish> dishes1 = partitionedDishes.get(true);
        System.out.println(dishes1);

        List<Dish> dishes2 = dishes.stream()
                .filter(Dish::isVegetarian).collect(Collectors.toList());   //dishes1的另一种解法

        Map<Boolean, Map<Dish.Type, List<Dish>>> collect5 = dishes.stream()
                .collect(Collectors.partitioningBy(a -> a.getCalories() > 1, Collectors.groupingBy(Dish::getType)));    //通过有两个参数的重载方法，对结构继续构建，得到一个二级map
        System.out.println(collect5);

        Map<Boolean, Dish> booleanDishMap = dishes.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparing(Dish::getCalories)),
                        Optional::get
                )));
        System.out.println(booleanDishMap); //素食中热量最高的菜肴

    }
}
