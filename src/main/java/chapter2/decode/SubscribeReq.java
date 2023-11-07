package chapter2.decode;

import java.io.Serializable;

/**
 * @author luwu
 * @date 8/7/23 17:24
 */
public class SubscribeReq implements Serializable {

    private String name;

    private int num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "SubscribeReq{" +
                "name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}
