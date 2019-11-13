package chapter_vII;

import chapter_vII.parallelStreams.ParallelStream;
import chapter_vII.utils.Measurer;

import static chapter_vII.parallelStreams.ParallelStream.sequentialSum;

/**
 * @Author: FangyiXu
 * @Date: 2019-06-05 13:44
 */
public class main {
    public static void main(String[] args) {
        long l = sequentialSum(100);
        System.out.println(l);

        System.out.println(Runtime.getRuntime().availableProcessors()); //获取处理器数量
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12"); //注意！！！修改全局的并行流的线程池大小！不建议修改！！

        int n = 10_000_000;
        System.out.println("Sequential sum done in: " +
                Measurer.measureFunctionPerf(ParallelStream::sequentialSum, n));    //output: 101 97 99 97 ...

        System.out.println("Iterative sum done in: " +
                Measurer.measureFunctionPerf(ParallelStream::iterativeSum, n));     //output: 4 4 4 4

        System.out.println("ParalelSum sum done in: " +
                Measurer.measureFunctionPerf(ParallelStream::parallelSum, n));      //output: 124 241 289 125 ...

        System.out.println("SequentialSum using rangeClosed sum done in: " +
                Measurer.measureFunctionPerf(ParallelStream::sequentialSumRangeClosed, n));    //output: 101 97 99 97 ...

        System.out.println("ParallelSumRangeClosed using rangeClosed sum done in: " +
                Measurer.measureFunctionPerf(ParallelStream::parallelSumRangeClosed, n));    //output: 101 97 99 97 ...
    }


}
