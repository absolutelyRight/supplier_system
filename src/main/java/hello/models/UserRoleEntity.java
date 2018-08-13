package hello.models;


import leap.orm.annotation.Column;
import leap.orm.annotation.Id;
import leap.orm.annotation.Table;
import leap.orm.model.Model;

import java.sql.Date;


@Table(name="USER_ROLE", autoCreate = true)
public class UserRoleEntity extends Model {
    @Id
    private String UId;
    @Id
    private String RId;
    @Id
    private String UrUid;
    @Column
    private Date UrTime;

    public String getUId() {
        return UId;
    }

    public void setUId(String UId) {
        this.UId = UId;
    }

    public String getRId() {
        return RId;
    }

    public void setRId(String RId) {
        this.RId = RId;
    }

    public String getUrUid() {
        return UrUid;
    }

    public void setUrUid(String urUid) {
        UrUid = urUid;
    }

    public Date getUrTime() {
        return UrTime;
    }

    public void setUrTime(Date urTime) {
        UrTime = urTime;
    }
}
