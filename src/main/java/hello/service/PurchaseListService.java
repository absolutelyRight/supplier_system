package hello.service;

import hello.api_model.AbstractNotice;
import hello.api_model.ServiceResult;
import hello.api_model.UserInfo;
import hello.models.PurchaseListEntity;
import hello.util.CommonTool;

import java.sql.Date;

public class PurchaseListService {
    public ServiceResult create(AbstractNotice abstractNotice, UserInfo user){
        PurchaseListEntity purchaseListEntity=abstractNotice.toSamplePurchaseList();
        purchaseListEntity.setPlId(CommonTool.getIdUUID(PurchaseListEntity.class.getName()));
        purchaseListEntity.setPlStartUid(user.getUId());
        purchaseListEntity.setPlStatus(0);
        purchaseListEntity.setPlIsDelete(0);
        if(purchaseListEntity.create()==null)
            return ServiceResult.DB_ERROR;
        return new ServiceResult(200,"",purchaseListEntity);
    }
    public ServiceResult examine (AbstractNotice abstractNotice, UserInfo user){
        PurchaseListEntity purchaseListEntity=PurchaseListEntity.findOrNull(abstractNotice.getId());
        abstractNotice.toSamplePurchaseList(purchaseListEntity);
        purchaseListEntity.setPlCheckUid(user.getUId());
        purchaseListEntity.setPlCheckTime(new Date(new java.util.Date().getTime()));
        purchaseListEntity.setPlStatus(abstractNotice.getChecksStatus());
        purchaseListEntity.setPlReason(abstractNotice.getReason());
        if (purchaseListEntity.update()==null)
            return ServiceResult.DB_ERROR;
        return new ServiceResult(200,"",purchaseListEntity);
    }
    public ServiceResult remove (String abstractNoticeId, UserInfo user){
        PurchaseListEntity purchaseListEntity=PurchaseListEntity.findOrNull(abstractNoticeId);
        if (purchaseListEntity==null)
            return ServiceResult.SUCCESS;
        purchaseListEntity.setPlIsDelete(1);
        purchaseListEntity.setPlDeleteUId(user.getUId());
        if (purchaseListEntity.update()==null)
            return ServiceResult.DB_ERROR;
        return ServiceResult.SUCCESS;
    }
    public ServiceResult LookStatus(String status){
        final String key="PurchaseList.status";
        return new ServiceResult(200,"",PurchaseListEntity.<PurchaseListEntity>query(key).param("status",status).list());
    }
}
