package chapter_vII.fork_Join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 自定义分支/合并 求和类
 *
 * @Author: FangyiXu
 * @Date: 2019-10-12 10:54
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {    //继承RecursiveTask来创建可以用与分支/合并框架的任务

    private final long[] numbers;   //待求和的数组

    private final int start;    //数组的起始位置

    private final int end;  //数据的终止位置

    public static final long THRESHOLD = 10_000;    //粒度，表示此大小为原子大小，不再切分

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if(length <= THRESHOLD) {   //不可再切分
            return computeSequentially();   //顺序计算
        }
        //切分，分为两部分，左边的部分进行fork，右边的部分视其是否为原子考虑是否进行fork(即继续compute)
        ForkJoinSumCalculator leftTask =
                new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();    //创建一个自任务来为前半部分求和
        ForkJoinSumCalculator rightTask =
                new ForkJoinSumCalculator(numbers, start + length/2, end);
        Long rightResult = rightTask.compute(); //同步执行第二个子任务(在同一线程下)
        Long leftResult = leftTask.join();  //读取第一个自任务的结果
        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static Long forkJoinSum(long n) {
        long[] longs = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(longs);
        return new ForkJoinPool().invoke(task); //实例化一个ForkJoinPool线程池来执行这个任务
    }
}
