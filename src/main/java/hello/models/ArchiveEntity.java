package hello.models;

import java.sql.Date;
import java.util.Objects;

import leap.orm.annotation.Column;
import leap.orm.annotation.Id;
import leap.orm.annotation.Table;
import leap.orm.model.Model;

@Table("ARCHIVE")
public class ArchiveEntity extends Model{
    @Id
    private String AId;
    @Column
    private String SId;
    @Column
    private String PaId;
    @Column
    private Date ATenderTime;
    @Column
    private Integer AIstender;
    @Column
    private Date ADefineTime;
    @Column
    private String AAffix;

    public String getAId() {
        return AId;
    }

    public void setAId(String AId) {
        this.AId = AId;
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

    public Date getATenderTime() {
        return ATenderTime;
    }

    public void setATenderTime(Date ATenderTime) {
        this.ATenderTime = ATenderTime;
    }

    public Integer getAIstender() {
        return AIstender;
    }

    public void setAIstender(Integer AIstender) {
        this.AIstender = AIstender;
    }

    public Date getADefineTime() {
        return ADefineTime;
    }

    public void setADefineTime(Date ADefineTime) {
        this.ADefineTime = ADefineTime;
    }

    public String getAAffix() {
        return AAffix;
    }

    public void setAAffix(String AAffix) {
        this.AAffix = AAffix;
    }
}
