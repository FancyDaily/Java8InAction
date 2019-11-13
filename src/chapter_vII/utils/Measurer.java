package chapter_vII.utils;

import java.util.function.Function;

/**
 * 度量类
 *
 * @Author: FangyiXu
 * @Date: 2019-10-11 15:42
 */
public class Measurer {
    public static long measureFunctionPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest)
                fastest = duration;
            System.out.println("Result: " + sum + " ,Done in: " + fastest + "ms");
        }
        return fastest;
    }
}
