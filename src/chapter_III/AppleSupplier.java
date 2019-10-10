package chapter_III;

import chapter_I.Apple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author: FangyiXu
 * @Date: 2019-05-28 16:44
 */
public class AppleSupplier {
    static Map<Integer, Function<Integer, Apple>> priceFunctionMap = new HashMap<>();
    static Map<Integer, Supplier<Apple>> supplierMap = new HashMap<>();
    //当引用两个参数的构造函数可以使用BiFunction<T,U,R>,当(被引用的构造函数)超过两个参数时，只能自己去定义@FunctionalInterface了

    //Map的作用是根据价格来选择何种构造函数引用方式
    static {
        //场景：有单价一百元和两百元的苹果,100元的不到一斤，免费赠送，200元的无限供应，按需提供。
        supplierMap.put(100, Apple::new);   //使用的是Supplier<T> 函数式接口,这里Apple::new相当于 () -> new Apple()
        priceFunctionMap.put(200, Apple::new);  //使用的是Function<T,R> 函数式接口,这里Apple::new相当于 (weight) -> new Apple(weight)

    }

    public static List<Apple> supplyAll(List<Integer> list, Function<Integer, Apple> function) {
        List<Apple> apples = new ArrayList<>();
        for(Integer weight:list) {
            apples.add(function.apply(weight));
        }
        return apples;
    }

    public static Apple giveMeApple(Integer price, Integer weight) {
        Supplier<Apple> appleSupplier = supplierMap.get(price);
        Function<Integer, Apple> integerAppleFunction = priceFunctionMap.get(price);
        Apple apple = null;
        if(integerAppleFunction!=null) {
            apple = integerAppleFunction.apply(weight);
            apple.setPrice(price);
        } else if(appleSupplier!=null) {
            if(weight >= 1) {
                System.out.println("对不起，没有足够的货！");
                return null;
            }
            apple = appleSupplier.get();
            apple.setPrice(price);
            apple.setWeight(1);
        } else {
            System.out.println("对不起,没有该价格的货！");
        }
        return apple;
    }
}
