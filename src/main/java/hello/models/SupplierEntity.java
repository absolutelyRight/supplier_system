package hello.models;

import leap.orm.annotation.Column;
import leap.orm.annotation.Id;
import leap.orm.annotation.Table;
import leap.orm.model.Model;

import java.sql.Date;


@Table(name="SUPPLIER", autoCreate = true)
public class SupplierEntity extends Model {
    @Id
    private String SId;
    @Column
    private String SEmail;
    @Column
    private String SBankName;
    @Column
    private String SBankCredit;
    @Column
    private String SFax;
    @Column
    private String SReason;
    @Column
    private String SAddress;
    @Column
    private String SProduct;
    @Column
    private String SAbility;
    @Column
    private String SContact;
    @Column
    private String SUrl;
    @Column
    private String SClient;
    @Column
    private String SDeputy;
    @Column
    private String SShortName;
    @Column
    private String SSocialCreditCode;
    @Column
    private String SFullName;
    @Column
    private String SPassword;
    @Column
    private String SBankAccount;
    @Column
    private String SAccountName;
    @Column
    private String SAffix;
    private Date SCheckTime;
    @Column
    private Date SStartTime;
    @Column
    private Date SRegistTime;
    @Column
    private Date SFoundDate;
    @Column
    private Double SMoney;
    @Column
    private Double SRealmoney;
    @Column
    private Double SAvgIncome;
    @Column
    private Integer SPhone;
    @Column
    private Integer SCheckStatus;
    @Column
    private Integer SIsdelete;

    public String getSId() {
        return SId;
    }

    public void setSId(String SId) {
        this.SId = SId;
    }

    public String getSEmail() {
        return SEmail;
    }

    public void setSEmail(String SEmail) {
        this.SEmail = SEmail;
    }

    public String getSBankName() {
        return SBankName;
    }

    public void setSBankName(String SBankName) {
        this.SBankName = SBankName;
    }

    public String getSBankCredit() {
        return SBankCredit;
    }

    public void setSBankCredit(String SBankCredit) {
        this.SBankCredit = SBankCredit;
    }

    public String getSFax() {
        return SFax;
    }

    public void setSFax(String SFax) {
        this.SFax = SFax;
    }

    public String getSReason() {
        return SReason;
    }

    public void setSReason(String SReason) {
        this.SReason = SReason;
    }

    public String getSAddress() {
        return SAddress;
    }

    public void setSAddress(String SAddress) {
        this.SAddress = SAddress;
    }

    public String getSProduct() {
        return SProduct;
    }

    public void setSProduct(String SProduct) {
        this.SProduct = SProduct;
    }

    public String getSAbility() {
        return SAbility;
    }

    public void setSAbility(String SAbility) {
        this.SAbility = SAbility;
    }

    public String getSContact() {
        return SContact;
    }

    public void setSContact(String SContact) {
        this.SContact = SContact;
    }

    public String getSUrl() {
        return SUrl;
    }

    public void setSUrl(String SUrl) {
        this.SUrl = SUrl;
    }

    public String getSClient() {
        return SClient;
    }

    public void setSClient(String SClient) {
        this.SClient = SClient;
    }

    public String getSDeputy() {
        return SDeputy;
    }

    public void setSDeputy(String SDeputy) {
        this.SDeputy = SDeputy;
    }

    public String getSShortName() {
        return SShortName;
    }

    public void setSShortName(String SShortName) {
        this.SShortName = SShortName;
    }

    public String getSSocialCreditCode() {
        return SSocialCreditCode;
    }

    public void setSSocialCreditCode(String SSocialCreditCode) {
        this.SSocialCreditCode = SSocialCreditCode;
    }

    public String getSFullName() {
        return SFullName;
    }

    public void setSFullName(String SFullName) {
        this.SFullName = SFullName;
    }

    public String getSPassword() {
        return SPassword;
    }

    public void setSPassword(String SPassword) {
        this.SPassword = SPassword;
    }

    public String getSBankAccount() {
        return SBankAccount;
    }

    public void setSBankAccount(String SBankAccount) {
        this.SBankAccount = SBankAccount;
    }

    public String getSAccountName() {
        return SAccountName;
    }

    public void setSAccountName(String SAccountName) {
        this.SAccountName = SAccountName;
    }

    public String getSAffix() {
        return SAffix;
    }

    public void setSAffix(String SAffix) {
        this.SAffix = SAffix;
    }

    public Date getSCheckTime() {
        return SCheckTime;
    }

    public void setSCheckTime(Date SCheckTime) {
        this.SCheckTime = SCheckTime;
    }

    public Date getSStartTime() {
        return SStartTime;
    }

    public void setSStartTime(Date SStartTime) {
        this.SStartTime = SStartTime;
    }

    public Date getSRegistTime() {
        return SRegistTime;
    }

    public void setSRegistTime(Date SRegistTime) {
        this.SRegistTime = SRegistTime;
    }

    public Date getSFoundDate() {
        return SFoundDate;
    }

    public void setSFoundDate(Date SFoundDate) {
        this.SFoundDate = SFoundDate;
    }

    public Double getSMoney() {
        return SMoney;
    }

    public void setSMoney(Double SMoney) {
        this.SMoney = SMoney;
    }

    public Double getSRealmoney() {
        return SRealmoney;
    }

    public void setSRealmoney(Double SRealmoney) {
        this.SRealmoney = SRealmoney;
    }

    public Double getSAvgIncome() {
        return SAvgIncome;
    }

    public void setSAvgIncome(Double SAvgIncome) {
        this.SAvgIncome = SAvgIncome;
    }

    public Integer getSPhone() {
        return SPhone;
    }

    public void setSPhone(Integer SPhone) {
        this.SPhone = SPhone;
    }

    public Integer getSCheckStatus() {
        return SCheckStatus;
    }

    public void setSCheckStatus(Integer SCheckStatus) {
        this.SCheckStatus = SCheckStatus;
    }

    public Integer getSIsdelete() {
        return SIsdelete;
    }

    public void setSIsdelete(Integer SIsdelete) {
        this.SIsdelete = SIsdelete;
    }
}
