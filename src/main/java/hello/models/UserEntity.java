package hello.models;

import leap.core.security.UserPrincipal;
import leap.orm.annotation.Column;
import leap.orm.annotation.Id;
import leap.orm.annotation.Table;
import leap.orm.model.Model;

import java.sql.Date;
import java.util.Objects;


@Table(name="USER", autoCreate = true)
public class UserEntity extends Model implements UserPrincipal {
    @Id
    private String UId;
    @Column
    private String UAccount;
    @Column
    private String UPassword;
    @Column
    private String UName;
    @Column
    private String UFounderUid;
    @Column
    private String UPhone;
    @Column
    private String UEmail;
    @Column
    private Integer UIsdelete;
    @Column
    private Integer UIsuse;
    @Column
    private Integer USex;
    @Column
    private Date UTime;

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

    public String getUPassword() {
        return UPassword;
    }

    public void setUPassword(String UPassword) {
        this.UPassword = UPassword;
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

    public Integer getUIsdelete() {
        return UIsdelete;
    }

    public void setUIsdelete(Integer UIsdelete) {
        this.UIsdelete = UIsdelete;
    }

    public Integer getUIsuse() {
        return UIsuse;
    }

    public void setUIsuse(Integer UIsuse) {
        this.UIsuse = UIsuse;
    }

    public Integer getUSex() {
        return USex;
    }

    public void setUSex(Integer USex) {
        this.USex = USex;
    }

    public Date getUTime() {
        return UTime;
    }

    public void setUTime(Date UTime) {
        this.UTime = UTime;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "UId='" + UId + '\'' +
                ", UAccount='" + UAccount + '\'' +
                ", UPassword='" + UPassword + '\'' +
                ", UName='" + UName + '\'' +
                ", UFounderUid='" + UFounderUid + '\'' +
                ", UPhone='" + UPhone + '\'' +
                ", UEmail='" + UEmail + '\'' +
                ", UIsdelete=" + UIsdelete +
                ", UIsuse=" + UIsuse +
                ", USex=" + USex +
                ", UTime=" + UTime +
                '}';
    }

    @Override
    public String getName() {
        return this.UName;
    }

    @Override
    public String getLoginName() {
        return this.UAccount;
    }

    @Override
    public Object getId() {
        return this.UId;
    }
}
