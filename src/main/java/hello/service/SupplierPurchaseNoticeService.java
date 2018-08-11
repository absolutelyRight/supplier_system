package hello.service;

import hello.api_model.ServiceResult;
import hello.models.ArchiveEntity;
import hello.models.PurchaseNoticeEntity;
import hello.util.CommonTool;

import java.sql.Date;
import java.util.List;

public class SupplierPurchaseNoticeService {

    public ServiceResult joinedPurchaseNotices(String supplierId){
        final String key="supplier.joinedNotices";
        List<PurchaseNoticeEntity> purchaseNoticeEntityList=PurchaseNoticeEntity.<PurchaseNoticeEntity>query(key).param("id",supplierId).list();
        purchaseNoticeEntityList.forEach(v->{v.setPnCheckUid("");v.setPnPublishUid("");v.setPnSuppierId("");v.setPnUpdateUid("");});
        return new ServiceResult(200,"",purchaseNoticeEntityList);
    }
    public ServiceResult winPurchaseNotices(String supplierId){
        final String key="supplier.winNotices";
        List<PurchaseNoticeEntity> purchaseNoticeEntityList=PurchaseNoticeEntity.<PurchaseNoticeEntity>query(key).param("id",supplierId).list();
        purchaseNoticeEntityList.forEach(v->{v.setPnCheckUid("");v.setPnPublishUid("");v.setPnSuppierId("");v.setPnUpdateUid("");});
        return new ServiceResult(200,"",purchaseNoticeEntityList);
    }
    public ServiceResult joinPurchaseNotices(String supplierId,String purchaseNpticeId){
        ArchiveEntity archiveEntity= new ArchiveEntity();
        archiveEntity.setAId(CommonTool.getIdUUID(ArchiveEntity.class.getName()));
        archiveEntity.setSId(supplierId);
        archiveEntity.setPaId(purchaseNpticeId);
        archiveEntity.setATenderTime(new Date((new java.util.Date()).getTime()));
        archiveEntity.setAIstender(0);
        if (archiveEntity.create()==null)
            return ServiceResult.DB_ERROR;
        return new ServiceResult(200,"",archiveEntity);
    }

}
