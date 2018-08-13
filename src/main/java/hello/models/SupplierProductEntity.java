package hello.models;

import leap.orm.annotation.Column;
import leap.orm.annotation.Id;
import leap.orm.annotation.Table;
import leap.orm.model.Model;



@Table(name="SUPPLIER_PRODUCT", autoCreate = true)
public class SupplierProductEntity extends Model {
    @Id
    private String SpId;
    @Column
    private String SpTypeName;
    @Column
    private String DParentCode;
    @Column
    private String DLevelCode;

    public String getSpId() {
        return SpId;
    }

    public void setSpId(String spId) {
        SpId = spId;
    }

    public String getSpTypeName() {
        return SpTypeName;
    }

    public void setSpTypeName(String spTypeName) {
        SpTypeName = spTypeName;
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
}
