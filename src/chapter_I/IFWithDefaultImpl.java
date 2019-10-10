package chapter_I;

import interfaceWithDefaultMethod.IFWithDefault;

/**
 * @Author: FangyiXu
 * @Date: 2019-05-27 20:46
 */
public class IFWithDefaultImpl implements IFWithDefault {

    @Override
    public void talk() {
        sing(); //不必实现接口中的default方法
        System.out.println("Talk like Cai XuKung!");
    }

    /*@Override
    public void sing() {
        System.out.println("Season in the sun!");
    }*/

    public static void main(String[] args) {
        new IFWithDefaultImpl().talk();
    }
}
