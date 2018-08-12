package hello.controllers;

import hello.api_model.AbstractNotice;
import hello.api_model.ServiceResult;
import hello.api_model.UserInfo;
import hello.service.PurchaseNoticeService;
import leap.core.annotation.Inject;
import leap.web.action.ControllerBase;
import leap.web.annotation.Path;
import leap.web.annotation.http.POST;

public class PurchaseNoticeController extends ControllerBase {
    //用于后台处理采购公告的控制器
    //新建
    //审核
    @Inject
    private PurchaseNoticeService purchaseNoticeService;
    @POST("create")
    public ServiceResult create(AbstractNotice abstractNotice){
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        //需求经理不能创建公告
        if (user.getType() == UserInfo.DEMAND)
            return ServiceResult.POWER_ERROR;
        return purchaseNoticeService.addNotice(abstractNotice,user);
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
        return purchaseNoticeService.examineNotice(abstractNotice,user);
    }
    //修改公告
    @POST("update")
    public ServiceResult update(AbstractNotice abstractNotice){
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        return purchaseNoticeService.fixNotice(abstractNotice,user);
    }
    //逻辑删除
    @Path("remove")
    public ServiceResult remove(String noticeId){
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        return purchaseNoticeService.remove(noticeId,user);
    }
    //获得自己发起的公告
    @POST("selfNotics")
    public ServiceResult selfNotics(Integer status){
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        return purchaseNoticeService.selfNotics(user,status);
    }
    //获得所有未审核的公告
    @Path("need")
    public ServiceResult getNotices(){
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        return purchaseNoticeService.needList();
    }
    //查看所有到达截止日期的投标

    //查看投标内所有参与投标的供应商
    @Post("joinedSippliers")
    public ServiceResult joinedSippliers(){

    }
    //选择中标的供应商
}
