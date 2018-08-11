package hello.controllers;

import hello.api_model.UserLogin;
import hello.api_model.ServiceResult;
import hello.models.SupplierEntity;
import hello.service.SupplierService;
import leap.core.annotation.Inject;
import leap.web.action.ControllerBase;
import leap.web.annotation.Path;
import leap.web.annotation.http.POST;

public class SupplierController extends ControllerBase {

    @Inject
    private SupplierService supplierService;

    @POST("login")
    public ServiceResult login(UserLogin input) {
        //邮箱，用户ID都可以登陆
        final ServiceResult output = supplierService.SupplierLogin(input);
        if (output.getCode() == 200) {
            request().getServletRequest().getSession().setAttribute("supplier", output.getBusinessObject());
        }
        return output;
    }

    //校验是否存在该用户名,不存在则返回200
    @Path("notexist")
    public ServiceResult notExist(String input) {
        return supplierService.SupplierIsNotExist(input);
    }

    @POST("register")
    public ServiceResult registerSupplier(SupplierEntity supplierEntity) {
        final ServiceResult output=supplierService.SupplierRegister(supplierEntity);
        if (output.getCode()==200){
            request().getServletRequest().getSession().setAttribute("supplier", output.getBusinessObject());
        }
        return output;
    }
//
//    //查询参与过的投标
//    @Path("getjoined")
//    public ServiceResult
//    //参与招标，需有权限管理
//    //被邀请的招标
//    //中过的投标
//    //所供应产品的增删改查，查的话暴露两个接口，一个给登陆后的供应商，一个给后台查
//    //自身信息查询，，每一次修改都会修改状态是否，过审核
}
