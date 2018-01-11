package cn.imaq.order.model.dto;

import java.io.Serializable;

public class UserCountDTO implements Serializable {
    private int onlineCount;

    private int loginCount;

    public UserCountDTO(int onlineCount, int loginCount) {
        this.onlineCount = onlineCount;
        this.loginCount = loginCount;
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public int getLoginCount() {
        return loginCount;
    }
}
