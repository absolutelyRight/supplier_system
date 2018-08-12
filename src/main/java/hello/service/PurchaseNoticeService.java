package hello.service;

import hello.api_model.AbstractNotice;
import hello.api_model.ServiceResult;
import hello.api_model.UserInfo;
import hello.models.*;
import hello.util.CommonTool;

import java.sql.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseNoticeService {
    public ServiceResult addNotice(AbstractNotice abstractNotice, UserInfo userInfo) {
        try {
            PurchaseNoticeEntity purchaseNoticeEntity = abstractNotice.toSamplePurchase();
            purchaseNoticeEntity.setPnId(CommonTool.getIdUUID(PurchaseNoticeEntity.class.getName()));
            purchaseNoticeEntity.setPnStartTime(new Date(new java.util.Date().getTime()));
            purchaseNoticeEntity.setPnPublishUid(userInfo.getUId());
            purchaseNoticeEntity.setPnCheckStatus(0);
            purchaseNoticeEntity.setPnIsdelete(0);
            if (purchaseNoticeEntity.create() == null)
                return ServiceResult.DB_ERROR;
            return new ServiceResult(200, "", purchaseNoticeEntity);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ServiceResult(404, "日期格式错误", null);
        }

    }

    //fixme 审核通过后向所有邀请的供应商发送通知
    public ServiceResult examineNotice(AbstractNotice abstractNotice, UserInfo userInfo) {
        try {
            PurchaseNoticeEntity purchaseNoticeEntity = abstractNotice.toSamplePurchase();
            purchaseNoticeEntity.setPnId(abstractNotice.getId());
            purchaseNoticeEntity.setPnCheckStatus(abstractNotice.getChecksStatus());
            purchaseNoticeEntity.setPnReason(abstractNotice.getReason());
            purchaseNoticeEntity.setPnCheckUid(userInfo.getUId());
            purchaseNoticeEntity.setPnCheckTime(new Date(new java.util.Date().getTime()));
            if (purchaseNoticeEntity.update() == null)
                return ServiceResult.DB_ERROR;
            return new ServiceResult(200, "", purchaseNoticeEntity);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ServiceResult(404, "日期格式错误", null);
        }

    }

    public ServiceResult fixNotice(AbstractNotice abstractNotice, UserInfo userInfo) {
        try {
            PurchaseNoticeEntity purchaseNoticeEntity = abstractNotice.toSamplePurchase();
            purchaseNoticeEntity.setPnId(abstractNotice.getId());
            purchaseNoticeEntity.setPnCheckUid(userInfo.getUId());
            purchaseNoticeEntity.setPnUpdateTime(new Date(new java.util.Date().getTime()));
            if (purchaseNoticeEntity.update() == null)
                return ServiceResult.DB_ERROR;
            return new ServiceResult(200, "", purchaseNoticeEntity);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ServiceResult(404, "日期格式错误", null);
        }
    }

    public ServiceResult remove(String noticeId, UserInfo userInfo) {
        PurchaseNoticeEntity purchaseNoticeEntity = PurchaseNoticeEntity.findOrNull(noticeId);
        if (purchaseNoticeEntity != null) {
            purchaseNoticeEntity.setPnDeleteUid(userInfo.getDId());
            purchaseNoticeEntity.setPnIsdelete(1);
            purchaseNoticeEntity.setPnDeleteTime(new Date(new java.util.Date().getTime()));
            purchaseNoticeEntity.update();
        }
        return ServiceResult.SUCCESS;
    }

    public ServiceResult selfNotics(UserInfo userInfo, Integer status) {
        final String key = "GetHisselfPNotices";
        Map p = new HashMap();
        p.put("id", userInfo.getUId());
        p.put("status", status.toString());
        return new ServiceResult(200, "", PurchaseNoticeEntity.<PurchaseNoticeEntity>query().params(p).list());
    }

    public ServiceResult needList() {
        final String key = "GetNeedPNotices";
        return new ServiceResult(200, "", PurchaseNoticeEntity.<PurchaseNoticeEntity>query().list());
    }

    public ServiceResult listJoinedSuppliers(String purchaseNoticeId) {
        final String key = "listJoinedSuppliers";
        return new ServiceResult(200, "", SupplierEntity.<SupplierEntity>query(key).param("id", purchaseNoticeId).list());
    }

    public ServiceResult selectOneSupplier(String purchaseNoticeId, String supplierId) {
        final PurchaseNoticeEntity p = PurchaseNoticeEntity.findOrNull(purchaseNoticeId);
        if (p == null) {
            return ServiceResult.ERROR_RESULT;
        }
        final SupplierEntity supplierEntity = SupplierEntity.findOrNull(supplierId);
        if (supplierEntity == null) {
            return ServiceResult.ERROR_RESULT;
        }
        p.setPnSelectSuppierid(supplierId);
        PurchaseNoticeEntity p2 = p.update();
        final String key = "listJoinedArchive";
        List<ArchiveEntity> archiveEntities = ArchiveEntity.<ArchiveEntity>query(key).param(purchaseNoticeId).list();
        archiveEntities.forEach(v -> {
            if (v.getSId().equals(supplierId)) v.setAIstender(1);
            else v.setAIstender(0);
            v.update();
        });
        return new ServiceResult(200, "", p2);
    }

    //fixme 日期比较不知道能不能这么写
    public ServiceResult lookup() {
        final String key = "lookup";
        List<PurchaseNoticeEntity> purchaseNoticeEntities = PurchaseNoticeEntity.<PurchaseNoticeEntity>query(key).param("time", new Date(new java.util.Date().getTime()).toString()).list();
        return new ServiceResult(200, "", purchaseNoticeEntities);
    }
}
