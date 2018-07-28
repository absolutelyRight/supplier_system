package hello.models;

import leap.orm.annotation.Column;
import leap.orm.annotation.Id;
import leap.orm.annotation.Table;
import leap.orm.model.Model;



@Table("role")
public class RoleEntity extends Model {
    @Id
    private String RId;
    @Column
    private String RName;
    @Column
    private Integer RType;

    public String getRId() {
        return RId;
    }

    public void setRId(String RId) {
        this.RId = RId;
    }

    public String getRName() {
        return RName;
    }

    public void setRName(String RName) {
        this.RName = RName;
    }

    public Integer getRType() {
        return RType;
    }

    public void setRType(Integer RType) {
        this.RType = RType;
    }
}
