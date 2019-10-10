package chapter_v.filter_and_map;

import chapter_Iv.entity.Dish;
import chapter_Iv.uitl.DishUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: FangyiXu
 * @Date: 2019-05-29 16:25
 */
public class FilterAndMap {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 1, 3, 2, 5, 6);
        List<Integer> collect = list.stream().filter(a -> a % 2 == 0)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);
        List<String> strings = Arrays.asList("java8", "lambda", "in", "action");
        List<Integer> collect1 = strings.stream().filter(a -> a.length() > 2)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(collect1);


        List<Dish> dishes = DishUtils.getDishes();
        Stream<String> transfered = dishes.stream().map(Dish::getName); //完成了映射
        List<Integer> collect2 = transfered.map(String::length).collect(Collectors.toList());//获取菜名的长度
        System.out.println(collect2);

        //得到的collect3实际是两个数组,并且元素本身不同，调用distinct之后未发生任何变化
        List<String> vocabularies = Arrays.asList("Hello", "World");
        Stream<String[]> stream = vocabularies.stream()
                .map(word -> word.split(""));
        List<String[]> collect3 = stream.distinct().collect(Collectors.toList());
        collect3.forEach(a -> Arrays.stream(a).forEach(System.out::println));

        //使用Arrays.stream()把一个String[]转换成Stream<String>
        List<Stream<String>> collect4 = vocabularies.stream()
                .map(vocabulary -> vocabulary.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect4);

        //问题终于解决！使用flatMap可将将Stream<String[]>扁平化为一个流Stream<String>
        Stream<String> stringStream = vocabularies.stream().map(w -> w.split(""))
                .flatMap(Arrays::stream);//将各个生成流(Stream<String[]>)扁平化为一个流(Stream<String>)
        List<String> collect5 = stringStream.distinct()
                .collect(Collectors.toList());

        System.out.println(collect5);
        
        //Test1: 给定【1，2，3，4，5】得到 【1，4，9，16，25】
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> integerStream = numbers.stream()
                .map(number -> number * number);
        List<Integer> collect6 = integerStream.collect(Collectors.toList());
        System.out.println(collect6);
        //Test2: 给定[1,2,3] 与 【3，4】返回所有无序数对
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4);
        Stream<Integer[]> stream1 = list1.stream()
                .flatMap(a -> list2.stream()
                        .map(b -> new Integer[] {a,b}
                        ));
        List<Integer[]> collect7 = stream1.collect(Collectors.toList());
        System.out.println(collect7.size());

        //观察相比map(),flatMap()做了什么
        Stream<Integer[]> stream2 = list2.stream()
                .map(b -> new Integer[]{1, b});

        Stream<Stream<Integer[]>> streamStream = list1.stream()
                .map(a -> stream2);

        Stream<Integer[]> stream3 = list1.stream()
                .flatMap(a -> stream2); //扁平化

        //观察每一步发生了什么
        //1.split()将这个String[]中的每一个元素都拆解成String[] -> Stream<String[]>
        Stream<String[]> stringArrayStream = vocabularies.stream()
                .map(word -> word.split(""));
        //2.Arrays.stream()将String[]形成一个Stream<String>,由map得到一个Stream<Stream<String>>
        Stream<Stream<String>> streamStringStream = stringArrayStream.map(Arrays::stream);

        //2.1Arrays.stream()将String[]形成一个Stream<String>,由faltMap得到一个Stream<String>,对比map可以知道扁平化做了什么
//        Stream<String> aStringStream = stringArrayStream.flatMap(Arrays::stream);

        Stream<String> nameStream = dishes.stream()
                .map(Dish::getName);
        Stream<Integer> lengthStream = nameStream.map(String::length);

        List<Integer> list3 = Arrays.asList(1,2,3);
        List<Integer> list4 = Arrays.asList(3, 4);
        Stream<Integer[]> stream4 = list3.stream()
                .flatMap(a ->
                        list4.stream().map(b -> new Integer[]{a, b}))
                .filter(array -> {
                    int result = 1;
                    for (Integer integer : array) {
                        result *= integer;
                    }
                    return result % 3 == 0;
                });
        List<Integer[]> collect8 = stream4.collect(Collectors.toList());
        for (Integer[] integers : collect8) {
                System.out.println("shuzu");
            for (Integer integer : integers) {
                System.out.println(integer);
            }
            
        }

        //        System.out.println(collect8);ystem.out.println("=================");
        //        collect8.stream().forEach(a -> Arrays.stream(a).forEach(System.out::


        List<Integer[]> bestCollect = list3.stream()
                .flatMap(
                        a -> list4.stream()
                                .filter(b -> (a + b) % 3 == 0)
                                .map(ele -> new Integer[]{a, ele})
                ).collect(Collectors.toList());
        System.out.println("-----------spliter-------------");
        bestCollect.forEach(a -> Arrays.asList(a).forEach(System.out::println));


    }
}
