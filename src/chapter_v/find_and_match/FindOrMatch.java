package chapter_v.find_and_match;

import chapter_Iv.entity.Dish;
import chapter_Iv.uitl.DishUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Author: FangyiXu
 * @Date: 2019-06-03 14:10
 */
public class FindOrMatch {
    public static void main(String[] args) {
        List<Dish> menu = DishUtils.getDishes();
        /*
            Match 接受一个 Predicate<T>
         */
        //AnyMatch
        if(menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somehow) vegetarian friendly!");
        }
        //AllMatch
        if(menu.stream().allMatch(dish -> dish.getCalories() > 1500)) {
            System.out.println("All High calories!");
        }
        //NoneMatch
        if(menu.stream().noneMatch(dish -> dish.getType().equals(Dish.Type.FISH))) {
            System.out.println("There is no fish!");
        }

        /*
            Find
         */
        //FindAny
        Optional<Dish> any = menu.stream().findAny();
        Optional<Dish> anyFish = menu.stream().filter(
                dish -> dish.getType().equals(Dish.Type.FISH)
        ).findAny();    //与filter一起使用时，找到第一个符合条件的元素
        
        //isPresent
        boolean present = anyFish.isPresent();
        System.out.println(present);

        //ifPresent
        anyFish.ifPresent(
                System.out::println
        );

        //get
        System.out.println("--------get---------");
        Dish dish = anyFish.get();
        System.out.println(dish);

        Optional<Dish> any1 = menu.stream().filter(
                d -> d.getType().equals(Dish.Type.MEAT)
        ).findAny();
        //orElse
        Dish dish1 = any1.orElse(dish);  //当anyFish.get()为空时,返回fish (相当于默认值)
        System.out.println(dish1);


        //FindOne
        List<Integer> orderedNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> first = orderedNumbers.stream()
                .filter(x -> x * x % 3 == 0)
                .findFirst();   //找到第一个符合条件的元素，以原顺序(由于要考虑到顺序，显然findAny在（使用并行流的前提下）效率更高）
        Integer integer = first.get();
        System.out.println(integer);
    }
}
