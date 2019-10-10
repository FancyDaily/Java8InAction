package chapter_v.number_of_shares;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: FangyiXu
 * @Date: 2019-06-04 16:45
 */
public class main {
    //勾股数
    public static void main(String[] args) {
//        int[] intArray = {3, 4, 5};
        int no1 = 3;
        //尝试1
        List<int[]> collect1 = IntStream.rangeClosed(0, 100)
                .filter(no2 -> Math.sqrt(no1 * no1 + no2 * no2) % 1 == 0)
                .boxed()
                .map(no2 -> new int[]{no1, no2, (int) Math.sqrt(no1 * no1 + no2 * no2)})
                .collect(Collectors.toList());

        //尝试2
        List<int[]> collect = IntStream.rangeClosed(0, 100)
                .filter(no2 -> Math.sqrt(no1 * no1 + no2 * no2) % 1 == 0)
                .mapToObj(no2 -> new int[]{no1, no2, (int) Math.sqrt(no1 * no1 + no2 * no2)})
                .collect(Collectors.toList());

        //尝试3
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)   //避免出现重复的三元数eg.(3,4,5) 与 (4,3,5)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b ->
                                        new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );

        pythagoreanTriples.limit(5)
                .forEach(n ->
                        System.out.println(n[0] + "," + n[1] + "," + n[2]));

        //最优解(较优解)
        Stream<double[]> stream = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(
                        a -> IntStream.rangeClosed(a, 100)
                                .mapToObj(
                                        b -> new double[]{a, b, Math.sqrt(a * a + b * b)}
                                ).filter(t -> t[2] % 1 == 0)
                );
        stream.limit(5)
                .forEach(n ->
                        System.out.println((int)n[0] + "," + (int)n[1] + "," + (int)n[2]));
    }
}
