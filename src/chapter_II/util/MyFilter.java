package chapter_II.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用列表过滤器
 *
 * @Author: FangyiXu
 * @Date: 2019-05-28 10:41
 */
public class MyFilter {
    public interface Predicate<T> {
        boolean test(T t);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for(T e:list) {
            if(predicate.test(e)) {
                result.add(e);
            }
        }
        return result;
    }
}
