package hello.controllers;

import hello.api_model.ServiceResult;
import hello.models.SupplierEntity;
import hello.service.InformationService;
import leap.core.annotation.Inject;
import leap.web.action.ControllerBase;
import leap.web.annotation.Path;

public class GatewayNoticeController extends ControllerBase {
    //本控制器用于返回门户界面的公告信息
    @Inject
    private InformationService informationService;
    //用于返回目前普通公告信息
    @Path("common")
    public ServiceResult common() {
        return informationService.commonNotices();
    }
    //返回目前招标信息
    @Path("purchase")
    public ServiceResult purchase(){
        return informationService.purchase();
    }
    //返回目前中标信息
    @Path("select")
    public ServiceResult select(){
        return informationService.selectNotices();
    }
    //返回采购公告，登陆才能看
    @Path("purchaseNotices")
    public ServiceResult purchaseNotices(){
        final SupplierEntity supplierEntity = (SupplierEntity) request().getServletRequest().getSession().getAttribute("supplier");
        if (supplierEntity == null) {
            return ServiceResult.NOT_LOGIN;
        }
        return informationService.purchaseNotices();
    }

}
