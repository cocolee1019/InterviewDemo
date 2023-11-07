package chapter2.decode;

import java.io.Serializable;

/**
 * @author luwu
 * @date 8/7/23 17:25
 */
public class SubscribeResp implements Serializable {

    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "SubscribeResp{" +
                "nickName='" + nickName + '\'' +
                '}';
    }
}
