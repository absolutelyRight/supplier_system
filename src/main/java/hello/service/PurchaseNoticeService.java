package hello.service;

import hello.api_model.AbstractNotice;
import hello.api_model.ServiceResult;
import hello.api_model.UserInfo;
import hello.models.PurchaseNoticeEntity;
import hello.util.CommonTool;

import java.sql.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class PurchaseNoticeService {
    public ServiceResult addNotice(AbstractNotice abstractNotice, UserInfo userInfo){
        try {
            PurchaseNoticeEntity purchaseNoticeEntity=abstractNotice.toSamplePurchase();
            purchaseNoticeEntity.setPnId(CommonTool.getIdUUID(PurchaseNoticeEntity.class.getName()));
            purchaseNoticeEntity.setPnStartTime(new Date(new java.util.Date().getTime()));
            purchaseNoticeEntity.setPnPublishUid(userInfo.getUId());
            purchaseNoticeEntity.setPnCheckStatus(0);
            purchaseNoticeEntity.setPnIsdelete(0);
            if(purchaseNoticeEntity.create()==null)
                return ServiceResult.DB_ERROR;
            return new ServiceResult(200,"",purchaseNoticeEntity);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ServiceResult(404,"日期格式错误",null);
        }

    }
    public ServiceResult examineNotice(AbstractNotice abstractNotice, UserInfo userInfo){
        try {
            PurchaseNoticeEntity purchaseNoticeEntity=abstractNotice.toSamplePurchase();
            purchaseNoticeEntity.setPnId(abstractNotice.getId());
            purchaseNoticeEntity.setPnCheckStatus(abstractNotice.getChecksStatus());
            purchaseNoticeEntity.setPnReason(abstractNotice.getReason());
            purchaseNoticeEntity.setPnCheckUid(userInfo.getUId());
            purchaseNoticeEntity.setPnCheckTime(new Date(new java.util.Date().getTime()));
            if(purchaseNoticeEntity.update()==null)
                return ServiceResult.DB_ERROR;
            return new ServiceResult(200,"",purchaseNoticeEntity);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ServiceResult(404,"日期格式错误",null);
        }

    }
    public ServiceResult fixNotice(AbstractNotice abstractNotice, UserInfo userInfo){
        try {
            PurchaseNoticeEntity purchaseNoticeEntity=abstractNotice.toSamplePurchase();
            purchaseNoticeEntity.setPnId(abstractNotice.getId());
            purchaseNoticeEntity.setPnCheckUid(userInfo.getUId());
            purchaseNoticeEntity.setPnUpdateTime(new Date(new java.util.Date().getTime()));
            if(purchaseNoticeEntity.update()==null)
                return ServiceResult.DB_ERROR;
            return new ServiceResult(200,"",purchaseNoticeEntity);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ServiceResult(404,"日期格式错误",null);
        }
    }
    public ServiceResult remove(String noticeId,UserInfo userInfo){
        PurchaseNoticeEntity purchaseNoticeEntity= PurchaseNoticeEntity.findOrNull(noticeId);
        if (purchaseNoticeEntity!=null){
            purchaseNoticeEntity.setPnDeleteUid(userInfo.getDId());
            purchaseNoticeEntity.setPnIsdelete(1);
            purchaseNoticeEntity.setPnDeleteTime(new Date(new java.util.Date().getTime()));
            purchaseNoticeEntity.update();
        }
        return ServiceResult.SUCCESS;
    }
    public ServiceResult selfNotics(UserInfo userInfo,Integer status ){
        final String key="GetHisselfPNotices";
        Map p=new HashMap();
        p.put("id",userInfo.getUId());
        p.put("status",status.toString());
        return new ServiceResult(200,"",PurchaseNoticeEntity.<PurchaseNoticeEntity>query().params(p).list());
    }
    public ServiceResult needList(){
        final String key="GetNeedPNotices";
        return new ServiceResult(200,"",PurchaseNoticeEntity.<PurchaseNoticeEntity>query().list());
    }
}
