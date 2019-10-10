package chapter_II;

import chapter_I.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: FangyiXu
 * @Date: 2019-05-27 21:29
 */
public class AppleUtils {
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate) {
        List<Apple> list = new ArrayList<>();
        for(Apple one:inventory) {
            if(predicate.test(one)) {
                list.add(one);
            }
        }
        return list;
    }
    public static void prettyPrintApple(List<Apple> inventory, ApplePrintHelper applePrintHelper) {
        applePrintHelper.doHelp(inventory);
    }

    public void test() {
        Apple[] apples = {new Apple(1950)};
        List<Apple> appleList = Arrays.asList(apples);
        appleList.sort(Comparator.comparing(Apple::getWeight));
    }
}
