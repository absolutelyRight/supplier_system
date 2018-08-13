package hello.models;

import java.util.Objects;
import leap.orm.annotation.Column;
import leap.orm.annotation.Id;
import leap.orm.annotation.Table;
import leap.orm.model.Model;


@Table(name="DEPARTMENT", autoCreate = true)
public class DepartmentEntity extends Model{
    @Id
    private String DId;
    @Column
    private String DFullname;
    @Column
    private String DName;
    @Column
    private String DParentCode;
    @Column
    private String DLevelCode;
    @Column
    private Integer DIspublic;

    public String getDId() {
        return DId;
    }

    public void setDId(String DId) {
        this.DId = DId;
    }

    public String getDFullname() {
        return DFullname;
    }

    public void setDFullname(String DFullname) {
        this.DFullname = DFullname;
    }

    public String getDName() {
        return DName;
    }

    public void setDName(String DName) {
        this.DName = DName;
    }

    public String getDParentCode() {
        return DParentCode;
    }

    public void setDParentCode(String DParentCode) {
        this.DParentCode = DParentCode;
    }

    public String getDLevelCode() {
        return DLevelCode;
    }

    public void setDLevelCode(String DLevelCode) {
        this.DLevelCode = DLevelCode;
    }

    public Integer getDIspublic() {
        return DIspublic;
    }

    public void setDIspublic(Integer DIspublic) {
        this.DIspublic = DIspublic;
    }
}
