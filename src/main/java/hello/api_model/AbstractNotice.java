package hello.api_model;

import hello.models.NoticeEntity;
import hello.models.PurchaseListEntity;
import hello.models.PurchaseNoticeEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AbstractNotice {
    //公告ID
    private String id;
    //标题
    private String title;
    //正文
    private String text;
    //摘要
    private String summary;
    //公告类型
    private Integer type;

    //时间需与前端统一数据格式
    public static final String TIME_FORMAT="yyyy-MM-dd";
    //发布时间
    private String publishtime;
    //审核状态，能不能通过审核
    private  Integer checksStatus;
    //不通过理由
    private String reason;
    //是否删除
    private Integer isDelete;
    //转换成
    public PurchaseListEntity toSamplePurchaseList(){
        PurchaseListEntity purchaseListEntity=new PurchaseListEntity();
        purchaseListEntity.setPlTitle(title);
        purchaseListEntity.setPlText(text);
        purchaseListEntity.setPlType(type);
        return purchaseListEntity;
    }
    public void toSamplePurchaseList(PurchaseListEntity purchaseListEntity){
        purchaseListEntity.setPlTitle(title);
        purchaseListEntity.setPlText(text);
        purchaseListEntity.setPlType(type);
    }
    //转换成最简公告
    public NoticeEntity toSampleNotice() throws ParseException {
        NoticeEntity noticeEntity = new NoticeEntity();
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
        noticeEntity.setNTitle(title);
        noticeEntity.setNText(text);
        noticeEntity.setNType(type);
        noticeEntity.setNSummary(summary);
        if (publishtime != null && publishtime != "")
            noticeEntity.setNPublishtime(new java.sql.Date(format.parse(publishtime).getTime()));
        return noticeEntity;
    }
    public void toSampleNotice(NoticeEntity noticeEntity) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
        noticeEntity.setNTitle(title);
        noticeEntity.setNText(text);
        noticeEntity.setNType(type);
        noticeEntity.setNSummary(summary);
        if (publishtime != null && publishtime != "")
            noticeEntity.setNPublishtime(new java.sql.Date(format.parse(publishtime).getTime()));
    }

    //以下字段用于采购公告
    //截止时间
    private String stopTime;
    //采购类型
    private Integer purchaseType;
    //供应商Id
    private  String supplierIds;
    //中标供应商Id
    private  String selectSupplierId;

    public PurchaseNoticeEntity toSamplePurchase() throws ParseException {
        PurchaseNoticeEntity purchaseNoticeEntity = new PurchaseNoticeEntity();
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
        purchaseNoticeEntity.setPnTitle(title);
        purchaseNoticeEntity.setPnText(text);
        purchaseNoticeEntity.setPnType(type);
        purchaseNoticeEntity.setPnSummary(summary);
        purchaseNoticeEntity.setPnPurchaseType(purchaseType);
        purchaseNoticeEntity.setPnSuppierId(supplierIds);
        purchaseNoticeEntity.setPnSelectSuppierid(selectSupplierId);
        if (publishtime != null && publishtime != "")
            purchaseNoticeEntity.setPnPublishTime(new java.sql.Date(format.parse(publishtime).getTime()));
        if (stopTime!=null&&stopTime!="")
            purchaseNoticeEntity.setPnStopTime(new java.sql.Date(format.parse(stopTime).getTime()));
        return purchaseNoticeEntity;
    }
    public void toSamplePurchase(PurchaseNoticeEntity purchaseNoticeEntity) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
        purchaseNoticeEntity.setPnTitle(title);
        purchaseNoticeEntity.setPnText(text);
        purchaseNoticeEntity.setPnType(type);
        purchaseNoticeEntity.setPnSummary(summary);
        purchaseNoticeEntity.setPnPurchaseType(purchaseType);
        purchaseNoticeEntity.setPnSuppierId(supplierIds);
        purchaseNoticeEntity.setPnSelectSuppierid(selectSupplierId);
        if (publishtime != null && publishtime != "")
            purchaseNoticeEntity.setPnPublishTime(new java.sql.Date(format.parse(publishtime).getTime()));
        if (stopTime!=null&&stopTime!="")
            purchaseNoticeEntity.setPnStopTime(new java.sql.Date(format.parse(stopTime).getTime()));
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public Integer getChecksStatus() {
        return checksStatus;
    }

    public void setChecksStatus(Integer checksStatus) {
        this.checksStatus = checksStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


    public Integer getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(Integer purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getSupplierIds() {
        return supplierIds;
    }

    public void setSupplierIds(String supplierIds) {
        this.supplierIds = supplierIds;
    }

    public String getSelectSupplierId() {
        return selectSupplierId;
    }

    public void setSelectSupplierId(String selectSupplierId) {
        this.selectSupplierId = selectSupplierId;
    }
}
