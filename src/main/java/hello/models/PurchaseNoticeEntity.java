package hello.models;

import leap.orm.annotation.Column;
import leap.orm.annotation.Id;
import leap.orm.annotation.Table;
import leap.orm.model.Model;

import java.sql.Date;


@Table(name="PURCHASE_NOTICE", autoCreate = true)
public class PurchaseNoticeEntity extends Model {
    @Id
    private String PnId;
    @Column
    private String PnTitle;
    @Column
    private String PnText;
    @Column
    private String PnPublishUid;
    @Column
    private String PnCheckUid;
    @Column
    private String PnAffix;
    @Column
    private String PnSuppierId;
    @Column
    private String PnSelectSuppierid;
    @Column
    private String PnUpdateUid;
    @Column
    private String PnReason;
    @Column
    private String PnUid;
    @Column
    private String PnSummary;
    @Column
    private String PnDeleteUid;
    @Column
    private Integer PnIsuse;
    @Column
    private Integer PnPurchaseType;
    @Column
    private Integer PnIsdelete;
    @Column
    private Integer PnCheckStatus;
    @Column
    private Integer PnType;
    @Column
    private Date PnCheckTime;
    @Column
    private Date PnUpdateTime;
    @Column
    private Date PnSelectTime;
    @Column
    private Date PnDeleteTime;
    @Column
    private Date PnStopTime;
    @Column
    private Date PnStartTime;
    @Column
    private Date PnPublishTime;

    public String getPnSummary() {
        return PnSummary;
    }

    public void setPnSummary(String pnSummary) {
        PnSummary = pnSummary;
    }

    public String getPnId() {
        return PnId;
    }

    public void setPnId(String pnId) {
        PnId = pnId;
    }

    public String getPnTitle() {
        return PnTitle;
    }

    public void setPnTitle(String pnTitle) {
        PnTitle = pnTitle;
    }

    public String getPnText() {
        return PnText;
    }

    public void setPnText(String pnText) {
        PnText = pnText;
    }

    public String getPnPublishUid() {
        return PnPublishUid;
    }

    public void setPnPublishUid(String pnPublishUid) {
        PnPublishUid = pnPublishUid;
    }

    public String getPnCheckUid() {
        return PnCheckUid;
    }

    public void setPnCheckUid(String pnCheckUid) {
        PnCheckUid = pnCheckUid;
    }

    public String getPnAffix() {
        return PnAffix;
    }

    public void setPnAffix(String pnAffix) {
        PnAffix = pnAffix;
    }

    public String getPnSuppierId() {
        return PnSuppierId;
    }

    public void setPnSuppierId(String pnSuppierId) {
        PnSuppierId = pnSuppierId;
    }

    public String getPnSelectSuppierid() {
        return PnSelectSuppierid;
    }

    public void setPnSelectSuppierid(String pnSelectSuppierid) {
        PnSelectSuppierid = pnSelectSuppierid;
    }

    public String getPnUpdateUid() {
        return PnUpdateUid;
    }

    public void setPnUpdateUid(String pnUpdateUid) {
        PnUpdateUid = pnUpdateUid;
    }

    public String getPnReason() {
        return PnReason;
    }

    public void setPnReason(String pnReason) {
        PnReason = pnReason;
    }

    public String getPnUid() {
        return PnUid;
    }

    public void setPnUid(String pnUid) {
        PnUid = pnUid;
    }

    public String getPnDeleteUid() {
        return PnDeleteUid;
    }

    public void setPnDeleteUid(String pnDeleteUid) {
        PnDeleteUid = pnDeleteUid;
    }

    public Integer getPnIsuse() {
        return PnIsuse;
    }

    public void setPnIsuse(Integer pnIsuse) {
        PnIsuse = pnIsuse;
    }

    public Integer getPnPurchaseType() {
        return PnPurchaseType;
    }

    public void setPnPurchaseType(Integer pnPurchaseType) {
        PnPurchaseType = pnPurchaseType;
    }

    public Integer getPnIsdelete() {
        return PnIsdelete;
    }

    public void setPnIsdelete(Integer pnIsdelete) {
        PnIsdelete = pnIsdelete;
    }

    public Integer getPnCheckStatus() {
        return PnCheckStatus;
    }

    public void setPnCheckStatus(Integer pnCheckStatus) {
        PnCheckStatus = pnCheckStatus;
    }

    public Integer getPnType() {
        return PnType;
    }

    public void setPnType(Integer pnType) {
        PnType = pnType;
    }

    public Date getPnCheckTime() {
        return PnCheckTime;
    }

    public void setPnCheckTime(Date pnCheckTime) {
        PnCheckTime = pnCheckTime;
    }

    public Date getPnUpdateTime() {
        return PnUpdateTime;
    }

    public void setPnUpdateTime(Date pnUpdateTime) {
        PnUpdateTime = pnUpdateTime;
    }

    public Date getPnSelectTime() {
        return PnSelectTime;
    }

    public void setPnSelectTime(Date pnSelectTime) {
        PnSelectTime = pnSelectTime;
    }

    public Date getPnDeleteTime() {
        return PnDeleteTime;
    }

    public void setPnDeleteTime(Date pnDeleteTime) {
        PnDeleteTime = pnDeleteTime;
    }

    public Date getPnStopTime() {
        return PnStopTime;
    }

    public void setPnStopTime(Date pnStopTime) {
        PnStopTime = pnStopTime;
    }

    public Date getPnStartTime() {
        return PnStartTime;
    }

    public void setPnStartTime(Date pnStartTime) {
        PnStartTime = pnStartTime;
    }

    public Date getPnPublishTime() {
        return PnPublishTime;
    }

    public void setPnPublishTime(Date pnPublishTime) {
        PnPublishTime = pnPublishTime;
    }
}
