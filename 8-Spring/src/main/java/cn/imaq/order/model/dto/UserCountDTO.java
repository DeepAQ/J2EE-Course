package cn.imaq.order.model.dto;

public class UserCountDTO {
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
