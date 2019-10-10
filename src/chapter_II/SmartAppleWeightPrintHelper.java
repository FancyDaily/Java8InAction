package chapter_II;

import chapter_I.Apple;

import java.util.List;

/**
 * @Author: FangyiXu
 * @Date: 2019-05-27 21:52
 */
public class SmartAppleWeightPrintHelper implements ApplePrintHelper {
    @Override
    public void doHelp(List<Apple> apples) {
        int line = 150;
        for(Apple apple:apples) {
            String weighOrLight = "light";
            if(apple.getWeight() > line) {
                weighOrLight = "heavy";
            }
            System.out.println(apple.getWeight() + ". And it is absolutely " + weighOrLight);
        }
    }
}
