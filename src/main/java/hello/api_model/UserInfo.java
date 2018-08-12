package hello.api_model;

import hello.models.UserEntity;

import java.sql.Date;

public class UserInfo {
    private String UId;

    private String UAccount;

    private String UName;

    private String UPassword;

    private String UFounderUid;

    private String UPhone;

    private String UEmail;

    private Integer USex;
    //角色ID，创建用户时从前端传入
    private String RoleId;
    //角色等级，由后端判断，不需要前端传入
    private Integer Type;
    //部门Id，由前端传入
    private String DId;
    //部门名字,用于返回前端
    private String DepartmentName;
    static public final Integer ROOT = 10;
    static public final Integer MANAGER = 8;
    static public final Integer BUYER = 4;
    static public final Integer DEMAND = 2;

    static public UserInfo change(UserEntity user, Integer type, String Did) {
        //为安全不把密码填充入
        UserInfo userInfo = new UserInfo();
        if (user.getUId() != null)
            userInfo.setUId(user.getUId());
        if (user.getUAccount() != null)
            userInfo.setUAccount(user.getUAccount());
        if (user.getUName() != null)
            userInfo.setUName(user.getUName());
        if (user.getUFounderUid() != null)
            userInfo.setUFounderUid(user.getUFounderUid());
        if (user.getUPhone() != null)
            userInfo.setUPhone(user.getUPhone());
        if (user.getUEmail() != null)
            userInfo.setUPhone(user.getUEmail());
        if (user.getUSex() != null)
            userInfo.setUSex(user.getUSex());
        userInfo.setType(type);
        userInfo.setDId(Did);
        return userInfo;
    }

    static public UserEntity change(UserInfo user) {
        //需要把密码填充入
        UserEntity UserEntity = new UserEntity();
        UserEntity.setUId(user.getUId());
        UserEntity.setUAccount(user.getUAccount());
        UserEntity.setUName(user.getUName());
        UserEntity.setUFounderUid(user.getUFounderUid());
        UserEntity.setUPhone(user.getUPhone());
        UserEntity.setUPhone(user.getUEmail());
        UserEntity.setUSex(user.getUSex());
        UserEntity.setUPassword(user.getUPassword());
        return UserEntity;
    }

    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String roleId) {
        RoleId = roleId;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getUPassword() {
        return UPassword;
    }

    public void setUPassword(String UPassword) {
        this.UPassword = UPassword;
    }

    public String getDId() {
        return DId;
    }

    public void setDId(String DId) {
        this.DId = DId;
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
