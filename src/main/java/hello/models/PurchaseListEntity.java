package hello.models;

import leap.orm.annotation.Column;
import leap.orm.annotation.Id;
import leap.orm.annotation.Table;
import leap.orm.model.Model;

import java.sql.Date;


@Table(name = "PURCHASE_LIST")
public class PurchaseListEntity extends Model {
    @Id
    private String PlId;
    @Column
    private String PnId;
    @Column
    private String PlTitle;
    @Column
    private String PlText;
    @Column
    private String PlAffix;
    @Column
    private String PlStartUid;
    @Column
    private String PlCheckUid;
    @Column
    private String PlReason;
    @Column
    private Integer PlStatus;
    @Column
    private Integer PlType;
    @Column
    private Date PlCheckTime;
    @Column
    private Date PlStartTime;

    public String getPlId() {
        return PlId;
    }

    public void setPlId(String plId) {
        PlId = plId;
    }

    public String getPnId() {
        return PnId;
    }

    public void setPnId(String pnId) {
        PnId = pnId;
    }

    public String getPlTitle() {
        return PlTitle;
    }

    public void setPlTitle(String plTitle) {
        PlTitle = plTitle;
    }

    public String getPlText() {
        return PlText;
    }

    public void setPlText(String plText) {
        PlText = plText;
    }

    public String getPlAffix() {
        return PlAffix;
    }

    public void setPlAffix(String plAffix) {
        PlAffix = plAffix;
    }

    public String getPlStartUid() {
        return PlStartUid;
    }

    public void setPlStartUid(String plStartUid) {
        PlStartUid = plStartUid;
    }

    public String getPlCheckUid() {
        return PlCheckUid;
    }

    public void setPlCheckUid(String plCheckUid) {
        PlCheckUid = plCheckUid;
    }

    public String getPlReason() {
        return PlReason;
    }

    public void setPlReason(String plReason) {
        PlReason = plReason;
    }

    public Integer getPlStatus() {
        return PlStatus;
    }

    public void setPlStatus(Integer plStatus) {
        PlStatus = plStatus;
    }

    public Integer getPlType() {
        return PlType;
    }

    public void setPlType(Integer plType) {
        PlType = plType;
    }

    public Date getPlCheckTime() {
        return PlCheckTime;
    }

    public void setPlCheckTime(Date plCheckTime) {
        PlCheckTime = plCheckTime;
    }

    public Date getPlStartTime() {
        return PlStartTime;
    }

    public void setPlStartTime(Date plStartTime) {
        PlStartTime = plStartTime;
    }
}
