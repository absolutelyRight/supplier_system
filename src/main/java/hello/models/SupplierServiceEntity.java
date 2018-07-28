package hello.models;

import leap.orm.annotation.Column;
import leap.orm.annotation.Id;
import leap.orm.annotation.ManyToOne;
import leap.orm.annotation.Table;
import leap.orm.model.Model;
import java.util.Objects;

@Table("SUPPLIER_SERVICE")
public class SupplierServiceEntity extends Model{
    @Id
    //@ManyToOne(SupplierEntity.class)
    private String SId;
    @Id
   // @ManyToOne(SupplierProductEntity.class)
    private String SpId;

    public String getSId() {
        return SId;
    }

    public void setSId(String SId) {
        this.SId = SId;
    }

    public String getSpId() {
        return SpId;
    }

    public void setSpId(String spId) {
        SpId = spId;
    }
}
