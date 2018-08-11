package hello.controllers;

import hello.api_model.ServiceResult;
import hello.models.SupplierEntity;
import hello.models.SupplierProductEntity;
import leap.web.action.ControllerBase;
import leap.web.annotation.Path;

public class SupplierProductController extends ControllerBase {

    @Path("get_products")
    public ServiceResult getAllProduct(){
       final SupplierEntity  supplierEntity=(SupplierEntity)request().getServletRequest().getSession().getAttribute("supplier");
       if (supplierEntity==null){
           return ServiceResult.NOT_LOGIN;
       }
    }
    @Path("add")
    public ServiceResult addProduct(SupplierProductEntity supplierProductEntity){
        final SupplierEntity  supplierEntity=(SupplierEntity)request().getServletRequest().getSession().getAttribute("supplier");
        if (supplierEntity==null){
            return ServiceResult.NOT_LOGIN;
        }
        //供应商新增产品，若此产品种类未存在，则新增此产品种类再添加；若已存在，则直接插入联系
    }
    @Path("delete")
    public ServiceResult deleteProduct(String productId){
        final SupplierEntity  supplierEntity=(SupplierEntity)request().getServletRequest().getSession().getAttribute("supplier");
        if (supplierEntity==null){
            return ServiceResult.NOT_LOGIN;
        }
    }
    @Path("update")
    public ServiceResult updateProduct(){
        final SupplierEntity  supplierEntity=(SupplierEntity)request().getServletRequest().getSession().getAttribute("supplier");
        if (supplierEntity==null){
            return ServiceResult.NOT_LOGIN;
        }
    }
}
