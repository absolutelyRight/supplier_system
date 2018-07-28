package hello.models;

import java.util.Objects;

import leap.orm.annotation.Column;
import leap.orm.annotation.Id;
import leap.orm.annotation.Table;
import leap.orm.model.Model;
import java.sql.Date;


@Table("EVALUATION")
public class EvaluationEntity extends Model {
    @Id
    private String EId;
    @Column
    private String SId;
    @Column
    private String PaId;
    @Column
    private String UId;
    @Column
    private Double ETotal;
    @Column
    private String EText;
    @Column
    private Date ETime;
    @Column
    private String EAffix;

    public String getEId() {
        return EId;
    }

    public void setEId(String EId) {
        this.EId = EId;
    }

    public String getSId() {
        return SId;
    }

    public void setSId(String SId) {
        this.SId = SId;
    }

    public String getPaId() {
        return PaId;
    }

    public void setPaId(String paId) {
        PaId = paId;
    }

    public String getUId() {
        return UId;
    }

    public void setUId(String UId) {
        this.UId = UId;
    }

    public Double getETotal() {
        return ETotal;
    }

    public void setETotal(Double ETotal) {
        this.ETotal = ETotal;
    }

    public String getEText() {
        return EText;
    }

    public void setEText(String EText) {
        this.EText = EText;
    }

    public Date getETime() {
        return ETime;
    }

    public void setETime(Date ETime) {
        this.ETime = ETime;
    }

    public String getEAffix() {
        return EAffix;
    }

    public void setEAffix(String EAffix) {
        this.EAffix = EAffix;
    }
}
