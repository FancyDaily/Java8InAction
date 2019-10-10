package chapter_II;

import chapter_I.Apple;

import java.util.List;

/**
 * @Author: FangyiXu
 * @Date: 2019-05-27 21:50
 */
public class AppleWeightPrintHelper implements ApplePrintHelper {
    @Override
    public void doHelp(List<Apple> apples) {
        for(Apple apple:apples) {
            System.out.println(apple.getWeight());
        }
    }
}
