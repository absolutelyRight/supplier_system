package hello.service;

import hello.api_model.ServiceResult;
import hello.models.NoticeEntity;
import hello.models.PurchaseNoticeEntity;

import java.util.List;

public class InformationService {
    public ServiceResult commonNotices() {
        String key="notice.common";
        List<NoticeEntity> noticeEntities=NoticeEntity.<NoticeEntity>query(key).list();
        noticeEntities.forEach(v->{v.setNCheckuid("");v.setNStartuid("");v.setNUpdateuid("");});
        return new ServiceResult(200,"",noticeEntities);
    }

    public ServiceResult purchase() {
        String key="notice.purchase";
        List<NoticeEntity> noticeEntities=NoticeEntity.<NoticeEntity>query(key).list();
        noticeEntities.forEach(v->{v.setNCheckuid("");v.setNStartuid("");v.setNUpdateuid("");});
        return new ServiceResult(200,"",noticeEntities);
    }
    public ServiceResult selectNotices() {
        String key="notice.select";
        List<NoticeEntity> noticeEntities=NoticeEntity.<NoticeEntity>query(key).list();
        noticeEntities.forEach(v->{v.setNCheckuid("");v.setNStartuid("");v.setNUpdateuid("");});
        return new ServiceResult(200,"",noticeEntities);
    }
    public ServiceResult purchaseNotices() {
        String key="purchaseNotice";
        List<PurchaseNoticeEntity> purchaseNoticeEntities=PurchaseNoticeEntity.<PurchaseNoticeEntity>query(key).list();
        purchaseNoticeEntities.forEach(v->{v.setPnCheckUid("");v.setPnPublishUid("");v.setPnSuppierId("");v.setPnUpdateUid("");});
        return new ServiceResult(200,"",purchaseNoticeEntities);
    }
}
