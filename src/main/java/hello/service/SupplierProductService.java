package hello.service;

import hello.api_model.ServiceResult;
import hello.models.SupplierProductEntity;
import hello.models.SupplierServiceEntity;

public class SupplierProductService {
    public ServiceResult getAllProduct(String SuopplierId){
        return ServiceResult.ERROR_RESULT;
    }
    public ServiceResult addProduct(SupplierProductEntity supplierProductEntity,String SuopplierId){
        SupplierProductEntity supplierProduct=SupplierProductEntity.findBy("SP_TYPE_NAME",supplierProductEntity.getSpTypeName());
        if(supplierProduct!=null){
            SupplierServiceEntity sp=new SupplierServiceEntity();

    }
}
