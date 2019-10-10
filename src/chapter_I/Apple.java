package chapter_I;

/**
 * @Author: FangyiXu
 * @Date: 2019-05-27 17:45
 */
public class Apple {
    private int weight;

    private int price;

    private String color;

    public int getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", price=" + price +
                ", color='" + color + '\'' +
                '}';
    }

    public Apple(int weight) {
        this.weight = weight;
    }

    public Apple() {
    }
}
