package chapter_II;

import chapter_I.Apple;

/**
 * @Author: FangyiXu
 * @Date: 2019-05-27 21:28
 */
public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
