package interfaceWithDefaultMethod;

/**
 * @Author: FangyiXu
 * @Date: 2019-05-27 20:44
 */
public interface IFWithDefault {

    void talk();

    default void sing() {
        System.out.println("Sing like Londa Rousy!");
    }


}
