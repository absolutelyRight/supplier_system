package hello.controllers;

import hello.api_model.ServiceResult;
import hello.models.SupplierEntity;
import hello.models.SupplierProductEntity;
import hello.service.SupplierProductService;
import leap.core.annotation.Inject;
import leap.web.action.ControllerBase;
import leap.web.annotation.Path;

public class SupplierProductController extends ControllerBase {

    private SupplierProductService supplierProductService;

    public SupplierProductController() {
        this.supplierProductService = new SupplierProductService();
    }

    @Path("get_products")
    public ServiceResult getAllProduct() {
        final SupplierEntity supplierEntity = (SupplierEntity) request().getServletRequest().getSession().getAttribute("supplier");
        if (supplierEntity == null) {
            return ServiceResult.NOT_LOGIN;
        }
        return supplierProductService.getAllProduct(supplierEntity.getSId());
    }

    @Path("add")
    public ServiceResult addProduct(SupplierProductEntity supplierProductEntity) {
        final SupplierEntity supplierEntity = (SupplierEntity) request().getServletRequest().getSession().getAttribute("supplier");
        if (supplierEntity == null) {
            return ServiceResult.NOT_LOGIN;
        }
        //供应商新增产品，若此产品种类未存在，则新增此产品种类再添加；若已存在，则直接插入联系
        return supplierProductService.addProduct(supplierProductEntity, supplierEntity.getSId());
    }

    @Path("delete")
    public ServiceResult deleteProduct(String productId) {
        final SupplierEntity supplierEntity = (SupplierEntity) request().getServletRequest().getSession().getAttribute("supplier");
        if (supplierEntity == null) {
            return ServiceResult.NOT_LOGIN;
        }
        return supplierProductService.deleteSupplierProduct(productId,supplierEntity.getSId());
    }

}
