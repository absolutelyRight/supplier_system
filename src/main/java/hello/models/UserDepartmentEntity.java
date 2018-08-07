package hello.models;

import leap.orm.annotation.Column;
import leap.orm.annotation.Id;
import leap.orm.annotation.Table;
import leap.orm.model.Model;

import java.sql.Date;


@Table(name = "USER_DEPARTMENT")
public class UserDepartmentEntity extends Model {
    @Id
    private String DId;
    @Id
    private String UId;
    @Id
    private String UdUid;
    @Column
    private Date UdTime;

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

    public String getUdUid() {
        return UdUid;
    }

    public void setUdUid(String udUid) {
        UdUid = udUid;
    }

    public Date getUdTime() {
        return UdTime;
    }

    public void setUdTime(Date udTime) {
        UdTime = udTime;
    }
}
