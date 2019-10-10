package chapter_vI.collector_of_your_own;

import chapter_Iv.entity.Dish;
import chapter_Iv.uitl.DishUtils;

import java.util.ArrayList;
import java.util.List;

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
    }
}
