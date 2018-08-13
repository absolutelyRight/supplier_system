package hello.controllers;

import hello.api_model.UserLogin;
import hello.api_model.ServiceResult;
import hello.models.SupplierEntity;
import hello.service.SupplierService;
import leap.core.annotation.Inject;
import leap.web.action.ControllerBase;
import leap.web.annotation.Path;
import leap.web.annotation.PathParam;
import leap.web.annotation.http.DELETE;
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
    @POST("update")
    public ServiceResult updateSupplier(SupplierEntity supplier){
        final SupplierEntity supplierEntity = (SupplierEntity) request().getServletRequest().getSession().getAttribute("supplier");
        if (supplierEntity == null) {
            return ServiceResult.NOT_LOGIN;
        }
        ServiceResult serviceResult=supplierService.SupplierUpdate(supplierEntity,supplier);
        if (serviceResult.getCode()==200){
            request().getServletRequest().getSession().setAttribute("supplier",serviceResult.getBusinessObject());
        }
        return serviceResult;
    }
    
    @POST("add")
    public ServiceResult addSupplier(@PathParam String id){
    	return supplierService.Remove(id);
    }
    @POST("page/{index}")
    public ServiceResult listSupplierPage(@PathParam int index,String type,String name){
    	return supplierService.PageSearch(type, name, index, 5);
    }
    @DELETE("{id}")
    public ServiceResult removeSupplier(@PathParam String id){
    	return supplierService.Remove(id);
    }
}
