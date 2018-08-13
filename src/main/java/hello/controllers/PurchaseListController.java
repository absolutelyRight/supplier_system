package hello.controllers;

import hello.api_model.AbstractNotice;
import hello.api_model.ServiceResult;
import hello.api_model.UserInfo;
import hello.service.PurchaseListService;
import leap.core.annotation.Inject;
import leap.web.action.ControllerBase;
import leap.web.annotation.Path;

public class PurchaseListController  extends ControllerBase {
    //需求模块
    @Inject
    private PurchaseListService purchaseListService;

    @Path("create")
    public ServiceResult create(AbstractNotice abstractNotice) {
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        return purchaseListService.create(abstractNotice, user);
    }

    @Path("examine")
    public ServiceResult examine(AbstractNotice abstractNotice) {
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        return purchaseListService.examine(abstractNotice, user);
    }

    @Path("remove")
    public ServiceResult remove(String abstractNoticeId) {
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        return purchaseListService.remove(abstractNoticeId, user);
    }
    @Path("status")
    public ServiceResult lookStatus(String status){
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        return purchaseListService.LookStatus(status);
    }

}