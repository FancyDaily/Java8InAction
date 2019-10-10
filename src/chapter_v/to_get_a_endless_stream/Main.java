package chapter_v.to_get_a_endless_stream;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: FangyiXu
 * @Date: 2019-06-04 17:53
 */
public class Main {
    public static void main(String[] args) {
        //iterate(UnaryOperator<T>)
       /* Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);*/
        //斐波那契元组序列
        Stream.iterate(new int[] {0,1}, a -> new int[] {a[1] , a[0] + a[1]})
                .limit(10)
                .forEach(a -> System.out.println("(" + a[0] + "," + a[1] + ")"));

        Stream.iterate(new int[] {0,1}, a -> new int[] {a[1] , a[0] + a[1]})
                .limit(10)
                .map(a -> a[0])
                .forEach(System.out::println);

        //generate(supplier<T>)
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        //以下这种状况使用的供应源是有状态的，在并行中使用有状态的供应源是不安全的
        IntSupplier s = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldCurrnet = this.previous;
                int nextValue = this.previous + this.current;   //预计下一个值
                this.previous = this.current;   //推进
                this.current = nextValue;   //构建下一个元素
                return oldCurrnet;
            }
        };
        IntStream.generate(s)
        .limit(10)
        .forEach(System.out::println);
    }
}
