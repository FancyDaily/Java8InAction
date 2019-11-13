package chapter_vII.fork_Join;

import chapter_vII.utils.Measurer;

import static chapter_vII.fork_Join.ForkJoinSumCalculator.forkJoinSum;

/**
 * @Author: FangyiXu
 * @Date: 2019-10-12 11:25
 */
public class main {
    public static void main(String[] args) {
        int n = 1_00_000_000;
        Long aLong = forkJoinSum(n);
        System.out.println(aLong);

        long l = Measurer.measureFunctionPerf(ForkJoinSumCalculator::forkJoinSum, 10_000_000);
        System.out.println(l);
    }


}
