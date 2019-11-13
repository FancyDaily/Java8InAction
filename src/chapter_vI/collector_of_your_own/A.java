package chapter_vI.collector_of_your_own;

/**
 * 题外话：内部类实例化时，整个外部类和内部类的初始化顺序
 * @Author: FangyiXu
 * @Date: 2019-10-11 10:48
 */
public class A {
    int att;
    static int staAtt;
    B b = new B();

    static {
        staAtt = 9999;
        System.out.println("静态代码块");
    }

    {
        att = 666;
        System.out.println("代码块");
    }

    public A() {
        System.out.println("att=" + att);
        System.out.println("无餐");
    }

    public A(int att) {
        this.att = att;
        System.out.println("att=" + att);
        System.out.println("有参!");
    }

    class B {
        int b = 111;

        public B() {
            System.out.println("B构造");
        }

        {
            System.out.println(b);
            System.out.println("B 代码块");
        }
    }

    public static void main(String[] args) {
        A b = new A();
    }
}
