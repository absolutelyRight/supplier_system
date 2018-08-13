package hello.models;

import leap.orm.annotation.Column;
import leap.orm.annotation.Id;
import leap.orm.annotation.Table;
import leap.orm.model.Model;

import java.sql.Date;


@Table(name="NOTICE", autoCreate = true)
public class NoticeEntity extends Model {
    @Id
    private String NId;
    @Column
    private String NTitle;
    @Column
    private String NText;
    @Column
    private String NAffix;
    @Column
    private String NSummary;
    @Column
    private Date NStarttime;
    @Column
    private Date NUpdatetime;
    @Column
    private Integer NType;
    @Column
    private Date NPublishtime;
    @Column
    private String NStartuid;
    @Column
    private String NCheckuid;
    @Column
    private Integer NCheckstatus;
    @Column
    private Integer NIsdelete;
    @Column
    private String NUpdateuid;
    @Column
    private Date NChecktime;
    @Column
    private String NReason;
    @Column
    private String NUid;
    @Column
    private String NDeleteUid;
    @Column
    private Date NDeleteTime;

    public String getNId() {
        return NId;
    }

    public void setNId(String NId) {
        this.NId = NId;
    }

    public String getNTitle() {
        return NTitle;
    }

    public void setNTitle(String NTitle) {
        this.NTitle = NTitle;
    }

    public String getNText() {
        return NText;
    }

    public void setNText(String NText) {
        this.NText = NText;
    }

    public String getNAffix() {
        return NAffix;
    }

    public void setNAffix(String NAffix) {
        this.NAffix = NAffix;
    }

    public Date getNStarttime() {
        return NStarttime;
    }

    public void setNStarttime(Date NStarttime) {
        this.NStarttime = NStarttime;
    }

    public Date getNUpdatetime() {
        return NUpdatetime;
    }

    public void setNUpdatetime(Date NUpdatetime) {
        this.NUpdatetime = NUpdatetime;
    }

    public Integer getNType() {
        return NType;
    }

    public void setNType(Integer NType) {
        this.NType = NType;
    }

    public Date getNPublishtime() {
        return NPublishtime;
    }

    public void setNPublishtime(Date NPublishtime) {
        this.NPublishtime = NPublishtime;
    }

    public String getNStartuid() {
        return NStartuid;
    }

    public void setNStartuid(String NStartuid) {
        this.NStartuid = NStartuid;
    }

    public String getNCheckuid() {
        return NCheckuid;
    }

    public void setNCheckuid(String NCheckuid) {
        this.NCheckuid = NCheckuid;
    }

    public Integer getNCheckstatus() {
        return NCheckstatus;
    }

    public void setNCheckstatus(Integer NCheckstatus) {
        this.NCheckstatus = NCheckstatus;
    }

    public Integer getNIsdelete() {
        return NIsdelete;
    }

    public void setNIsdelete(Integer NIsdelete) {
        this.NIsdelete = NIsdelete;
    }

    public String getNUpdateuid() {
        return NUpdateuid;
    }

    public void setNUpdateuid(String NUpdateuid) {
        this.NUpdateuid = NUpdateuid;
    }

    public Date getNChecktime() {
        return NChecktime;
    }

    public void setNChecktime(Date NChecktime) {
        this.NChecktime = NChecktime;
    }

    public String getNReason() {
        return NReason;
    }

    public void setNReason(String NReason) {
        this.NReason = NReason;
    }

    public String getNUid() {
        return NUid;
    }

    public void setNUid(String NUid) {
        this.NUid = NUid;
    }

    public String getNDeleteUid() {
        return NDeleteUid;
    }

    public void setNDeleteUid(String NDeleteUid) {
        this.NDeleteUid = NDeleteUid;
    }

    public Date getNDeleteTime() {
        return NDeleteTime;
    }

    public void setNDeleteTime(Date NDeleteTime) {
        this.NDeleteTime = NDeleteTime;
    }

    public String getNSummary() {
        return NSummary;
    }

    public void setNSummary(String NSummary) {
        this.NSummary = NSummary;
    }
}
