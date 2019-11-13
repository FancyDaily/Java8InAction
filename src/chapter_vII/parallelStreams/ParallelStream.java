package chapter_vII.parallelStreams;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @Author: FangyiXu
 * @Date: 2019-10-11 15:47
 */
public class ParallelStream {
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)   //无限流
                .limit(n)   //限流
//                .parallel() //并行化
//                .sequential()   //顺序化（要注意，最后执行的paralle() 或 sequential()会决定最终将并行还是顺序执行
                .reduce(0L, Long::sum); //归约
    }

    public static long iterativeSum(long n) {
        long sum = 0;
        for(int i=0; i < n; i++) {
            sum += i;
        }

        return sum;
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)   //无限流 -> 生成的是Integer(存在拆箱成本), iterate本质是顺序的。在此处使用并行，效率更加低下(相比于顺序执行)
                .limit(n)   //限流
                .parallel() //并行化
                .reduce(0L, Long::sum); //归约
    }

    public static long sequentialSumRangeClosed(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }

    public static long parallelSumRangeClosed(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

}
