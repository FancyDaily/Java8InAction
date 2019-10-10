package chapter_vI.collector_of_your_own;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 自定义Collector
 * @Author: FangyiXu
 * @Date: 2019-10-10 16:41
 */
public class MyToListCollector<T> implements Collector<T, List<T>, List<T>> {
    @Override
    public Supplier<List<T>> supplier() {    //供应者
//        return () -> new ArrayList<>();
        return ArrayList::new; //返回一个Supplier(无参函数）
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {   //累加器
//        return (list, item) -> list.add(item);
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {  //连接者，合并者，把流的各个子部分进行合并
        return (a, b) -> {
            a.addAll(b);
            return a;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {    //终结者，对结果容器应用最终的转换
//        return t -> t;
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() { //特性。描述累加器的三种行为。 注意这里的Characteristics是一个枚举 UNORDERED无序的 CONCURRENT多线程的(仅当数据源无序才会进行并行处理) IDENTIFY_FINISH恒等的(可以跳过finisher得到同样的结果，即累加器的最终状态就是我们要的值)
        return Collections.unmodifiableSet(EnumSet.of(
            Characteristics.CONCURRENT, Characteristics.IDENTITY_FINISH
        ));
    }
}
