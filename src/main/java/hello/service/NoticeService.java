package hello.service;

import hello.api_model.AbstractNotice;
import hello.api_model.ServiceResult;
import hello.api_model.UserInfo;
import hello.models.NoticeEntity;
import hello.util.CommonTool;

import java.sql.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class NoticeService {
    public ServiceResult addNotice(AbstractNotice abstractNotice, UserInfo userInfo){
        try {
            NoticeEntity noticeEntity=abstractNotice.toSampleNotice();
            noticeEntity.setNId(CommonTool.getIdUUID(NoticeEntity.class.getName()));
            noticeEntity.setNStarttime(new Date(new java.util.Date().getTime()));
            noticeEntity.setNStartuid(userInfo.getUId());
            noticeEntity.setNCheckstatus(0);
            noticeEntity.setNIsdelete(0);
            if(noticeEntity.create()==null)
                return ServiceResult.DB_ERROR;
            System.out.println("addNotice3");
            return new ServiceResult(200,"",noticeEntity);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ServiceResult(404,"日期格式错误",null);
        }

    }
    public ServiceResult examineNotice(AbstractNotice abstractNotice, UserInfo userInfo){
        try {
            NoticeEntity noticeEntity=NoticeEntity.findOrNull(abstractNotice.getId());
            if (noticeEntity==null)
                return ServiceResult.ERROR_RESULT;
            abstractNotice.toSampleNotice(noticeEntity);
            noticeEntity.setNCheckstatus(abstractNotice.getChecksStatus());
            noticeEntity.setNReason(abstractNotice.getReason());
            noticeEntity.setNCheckuid(userInfo.getUId());
            noticeEntity.setNChecktime(new Date(new java.util.Date().getTime()));
            if(noticeEntity.update()==null)
                return ServiceResult.DB_ERROR;
            return new ServiceResult(200,"",noticeEntity);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ServiceResult(404,"日期格式错误",null);
        }

    }
    public ServiceResult fixNotice(AbstractNotice abstractNotice, UserInfo userInfo){
        try {
            NoticeEntity noticeEntity=NoticeEntity.findOrNull(abstractNotice.getId());
            if (noticeEntity==null)
                return ServiceResult.ERROR_RESULT;
            abstractNotice.toSampleNotice(noticeEntity);
            noticeEntity.setNUpdateuid(userInfo.getUId());
            noticeEntity.setNUpdatetime(new Date(new java.util.Date().getTime()));
            if(noticeEntity.update()==null)
                return ServiceResult.DB_ERROR;
            return new ServiceResult(200,"",noticeEntity);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ServiceResult(404,"日期格式错误",null);
        }
    }
    public ServiceResult remove(String noticeId,UserInfo userInfo){
        NoticeEntity noticeEntity= NoticeEntity.findOrNull(noticeId);
        if (noticeEntity!=null){
            noticeEntity.setNDeleteUid(userInfo.getDId());
            noticeEntity.setNIsdelete(1);
            noticeEntity.setNDeleteTime(new Date(new java.util.Date().getTime()));
            noticeEntity.update();
        }
        return ServiceResult.SUCCESS;
    }
    public ServiceResult selfNotics(UserInfo userInfo,Integer status ){
        final String key="GetHisselfNotices";
        Map p=new HashMap();
        p.put("id",userInfo.getUId());
        p.put("status",status.toString());
        return new ServiceResult(200,"",NoticeEntity.<NoticeEntity>query().params(p).list());
    }
    public ServiceResult needList(){
        final String key="GetNeedNotices";
        return new ServiceResult(200,"",NoticeEntity.<NoticeEntity>query().list());
    }
}
