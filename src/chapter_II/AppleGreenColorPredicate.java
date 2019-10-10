package chapter_II;

import chapter_I.Apple;

/**
 * @Author: FangyiXu
 * @Date: 2019-05-27 21:29
 */
public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
