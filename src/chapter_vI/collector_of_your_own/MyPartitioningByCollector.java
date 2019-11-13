package chapter_vI.collector_of_your_own;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * 自定义分区收集器(质数分区收集器)
 * @Author: FangyiXu
 * @Date: 2019-10-10 17:40
 */
public class MyPartitioningByCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {   //供应者
        return () -> new HashMap<Boolean, List<Integer>>() {{
            put(true, new ArrayList<>());
            put(false, new ArrayList<>());
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() { //累加器(最重要)
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {    //为了更清楚，写出了参数的类型
            acc.get(isPrime(acc.get(true), candidate)).add(candidate);  //根据筛选结果加入到true或false的列表
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                Characteristics.IDENTITY_FINISH
        ));
    }

    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for(A item: list) {
            if(!p.test(item)) {         //如果不满足谓词推断，返回子列表
                return list.subList(0, i);
            }
            i++;
        }
        return list;    //返回完整列表
    }

    /**
     * 求是否为质数
     * @param primes
     * @param candidate
     * @return
     */
    public static boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return takeWhile(primes, i -> i <= candidateRoot)
                .stream()
                .noneMatch(p -> candidate % p == 0);
    }


    public static void main(String[] args) {
        //题外话：以下这种写法实际是借助了匿名内部类和实例代码块
        HashMap<Boolean, List<Integer>> hashMap = new HashMap<Boolean, List<Integer>>() {{
            put(true, new ArrayList<>());
            put(false, new ArrayList<>());
        }};

        HashMap<String, Object> hashMap1 = new HashMap<String, Object>() {{
            ArrayList<Object> objects = new ArrayList<>();
            objects.add("is a pig");
            put("zangsan", objects);
        }};

        System.out.println(hashMap1);

        ArrayList<String> strings = new ArrayList<String>() {
            {
                add("123");
            }
        };
        System.out.println(strings);

        HashSet<Object> objects = new HashSet<>();
        objects.add(13);
        System.out.println(objects);
    }
}
