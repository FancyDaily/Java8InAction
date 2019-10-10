package chapter_Iv;

import chapter_Iv.entity.Dish;
import chapter_Iv.uitl.DishUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: FangyiXu
 * @Date: 2019-05-29 13:59
 */
public class Main {
    public static void main(String[] args) {
        List<Dish> dishes = DishUtils.getDishes();
        Stream<Dish> stream = dishes.stream();
        stream.forEach(System.out::println);
        stream = dishes.stream();
        List<String> dishesAfterFilted = stream.filter(a -> a.getCalories() > 300) //筛选条件
                .map(Dish::getName) //取得名字
                .limit(3)   //取前三条
                .collect(Collectors.toList());  //得到结果集
        System.out.println(dishesAfterFilted);

//        stream.forEach(System.out::println);    //  流已经被操作过 java.lang.IllegalStateException: stream has already been operated upon or closed
        ArrayList<Dish> objects = new ArrayList<>();
        dishes.forEach(a -> objects.add(a));
        List<String> collect = objects.stream().map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(collect);

        //将filter()、map()等称为中间操作，将collect()、forEach()等称为终端操作,可以看到 各中间操作的处理是并行的
        List<String> collect1 = dishes.stream()
                .filter(d -> {
                    System.out.println("fitering" + d.getName());
                    return d.getCalories() > 300;
                })
                .map(d -> {
                    System.out.println("maping" + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(collect1);
    }
}
