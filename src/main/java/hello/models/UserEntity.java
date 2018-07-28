package hello.models;

import leap.orm.annotation.Column;
import leap.orm.annotation.Id;
import leap.orm.annotation.Table;
import leap.orm.model.Model;

import java.sql.Date;
import java.util.Objects;


@Table("USER")
public class UserEntity extends Model {
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
}