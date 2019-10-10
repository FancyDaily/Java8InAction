package chapter_v.fold;

import chapter_Iv.entity.Dish;
import chapter_Iv.uitl.DishUtils;
import chapter_v.utils.NumberUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;

/**
 * @Author: FangyiXu
 * @Date: 2019-06-03 17:01
 */
public class Fold {
    public static void main(String[] args) {
        List<Integer> numbers = NumberUtils.getNumbers();
        //reduce(T, BinaryOperator<T>)
        //参数1为初始值
        Integer result = numbers.stream().reduce(0, (a, b) -> a + b);   //会将初始值也参与到运算(将初始值作为BinaryOperator的第一个参数，并从流获取第一个元素作为另一个BinaryOperator的参数)
        System.out.println(result);

        BinaryOperator<Integer> operator = (a, b) -> a * b;
        Integer reduce = numbers.stream().reduce(1, operator);
        System.out.println(reduce);

        Optional<Integer> theSameResult = numbers.stream().reduce(Integer::sum);
        Integer integer = theSameResult.get();
        System.out.println(integer);

        Integer reduce1 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(reduce1);

        //max
        Integer max1 = numbers.stream().reduce(0, (a, b) -> a > b ? a : b);
        System.out.println(max1);

        Integer max2 = numbers.stream().reduce(0, Integer::max);
        System.out.println(max2);

        //count
        List<Dish> dishes = DishUtils.getDishes();
        Integer reduce2 = dishes.stream()
                .map(d -> 1)
                .reduce(0, (a, b) -> a + b);
        System.out.println(reduce2);

        long count = dishes.stream().count();
        System.out.println(count);

    }
}
