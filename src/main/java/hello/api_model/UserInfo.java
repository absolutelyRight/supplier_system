package hello.api_model;

import hello.models.UserEntity;

import java.sql.Date;

public class UserInfo {
    private String UId;

    private String UAccount;

    private String UName;

    private String UFounderUid;

    private String UPhone;

    private String UEmail;

    private Integer USex;
    //角色类型
    private Integer Type;

    static public UserInfo change(UserEntity user, Integer type) {
        UserInfo userInfo = new UserInfo();
        if (user.getUId() != null)
            userInfo.setUId(user.getUId());
        if (user.getUAccount()!=null)
            userInfo.setUAccount(user.getUAccount());
        if (user.getUName()!=null)
            userInfo.setUName(user.getUName());
        if (user.getUFounderUid()!=null)
            userInfo.setUFounderUid(user.getUFounderUid());
        if (user.getUPhone()!=null)
            userInfo.setUPhone(user.getUPhone());
        if (user.getUEmail()!=null)
            userInfo.setUPhone(user.getUEmail());
        if (user.getUSex()!=null)
            userInfo.setUSex(user.getUSex());
        userInfo.setType(type);
        return userInfo;
    }

    public String getUId() {
        return UId;
    }

    public void setUId(String UId) {
        this.UId = UId;
    }

    public String getUAccount() {
        return UAccount;
    }

    public void setUAccount(String UAccount) {
        this.UAccount = UAccount;
    }

    public String getUName() {
        return UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public String getUFounderUid() {
        return UFounderUid;
    }

    public void setUFounderUid(String UFounderUid) {
        this.UFounderUid = UFounderUid;
    }

    public String getUPhone() {
        return UPhone;
    }

    public void setUPhone(String UPhone) {
        this.UPhone = UPhone;
    }

    public String getUEmail() {
        return UEmail;
    }

    public void setUEmail(String UEmail) {
        this.UEmail = UEmail;
    }

    public Integer getUSex() {
        return USex;
    }

    public void setUSex(Integer USex) {
        this.USex = USex;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }
}
