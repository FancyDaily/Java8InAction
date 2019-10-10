package chapter_II;

/**
 * 匿名类谜题
 *
 * @Author: FangyiXu
 * @Date: 2019-05-28 10:28
 */
public class MeaningOfThis {
    public final int value = 4;
    public void doIt() {
        int value = 6;
        Runnable r = new Runnable() {
            public final int value = 5;
            @Override
            public void run() {
                int value = 10;
//                System.out.println(this.value);   //IDEA is so smart that we have to block this line
            }
        };
        r.run();
    }
    public static void main(String[] args) {
        MeaningOfThis m = new MeaningOfThis();
        m.doIt();   //结果是什么？
    }
}
