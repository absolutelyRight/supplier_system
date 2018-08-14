package hello.controllers;


import hello.api_model.AbstractNotice;
import hello.api_model.ServiceResult;
import hello.api_model.UserInfo;
import hello.service.NoticeService;
import leap.core.annotation.Inject;
import leap.web.action.ControllerBase;
import leap.web.annotation.Path;
import leap.web.annotation.http.POST;

public class NoticeController extends ControllerBase {
    //用于处理公告的控制器
    @Inject
    private NoticeService noticeService;

    public NoticeController() {
        this.noticeService =new NoticeService();
    }

    @POST("create")
    public ServiceResult create(AbstractNotice abstractNotice){
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        //需求经理不能创建公告
        if (user.getType() == UserInfo.DEMAND)
            return ServiceResult.POWER_ERROR;
        System.out.println(abstractNotice.toString());
        if (noticeService==null){
            return ServiceResult.ERROR_RESULT;
        }
        return noticeService.addNotice(abstractNotice,user);
    }
    //审核
    @POST("examine")
    public ServiceResult examine(AbstractNotice abstractNotice){
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        //只有经理或者系统管理员才有权限审核
        if (user.getType() != UserInfo.ROOT||user.getType() !=UserInfo.MANAGER )
            return ServiceResult.POWER_ERROR;
        return noticeService.examineNotice(abstractNotice,user);
    }
    //修改公告
    @POST("update")
    public ServiceResult update(AbstractNotice abstractNotice){
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        return noticeService.fixNotice(abstractNotice,user);
    }
    //逻辑删除
    @Path("remove")
    public ServiceResult remove(String noticeId){
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        return noticeService.remove(noticeId,user);
    }
    //获得自己发起的公告
    @POST("selfNotics")
    public ServiceResult selfNotics(Integer status){
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        return noticeService.selfNotics(user,status);
    }
    //获得所有未审核的公告
    @Path("need")
    public ServiceResult getNotices(){
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        return noticeService.needList();
    }
    //上传附件
}
