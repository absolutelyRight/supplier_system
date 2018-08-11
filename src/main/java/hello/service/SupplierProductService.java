package hello.service;

import hello.api_model.ServiceResult;
import hello.models.SupplierProductEntity;
import hello.models.SupplierServiceEntity;
import hello.util.CommonTool;

import java.util.List;

public class SupplierProductService {
    //fixme 需要排序吗？
    public ServiceResult getAllProduct(String SuopplierId) {
        String key = "supplier.products";
        List<SupplierProductEntity> supplierProductEntityList = SupplierProductEntity.<SupplierProductEntity>query(key).param("id",SuopplierId).list();
        return new ServiceResult(200,"",supplierProductEntityList);
    }

    public ServiceResult addProduct(SupplierProductEntity supplierProductEntity, String SuopplierId) {
        //fixme 可能会遇到转码的问题，之后建议用产品的ID
//        if
//        SupplierProductEntity supplierProduct=SupplierProductEntity.findOrNull(supplierProductEntity.getSpId());
        SupplierProductEntity supplierProduct = SupplierProductEntity.findBy("SP_TYPE_NAME", supplierProductEntity.getSpTypeName());
        if (supplierProduct != null) {
            SupplierServiceEntity sps = new SupplierServiceEntity();
            sps.setSId(SuopplierId);
            sps.setSpId(supplierProduct.getSpId());
            if (sps.create() == null) {
                return ServiceResult.DB_ERROR;
            }
            return ServiceResult.SUCCESS;
        }
        supplierProductEntity.setSpId(CommonTool.getIdUUID(SupplierProductEntity.class.getName()));
        SupplierProductEntity spe = supplierProductEntity.create();
        if (spe == null)
            return ServiceResult.DB_ERROR;
        return ServiceResult.SUCCESS;
    }

    //删除供应商与产品之间的联系
    public ServiceResult deleteSupplierProduct(String productId, String supplierId) {
        SupplierServiceEntity supplierServiceEntity = new SupplierServiceEntity();
        supplierServiceEntity.setSId(supplierId);
        supplierServiceEntity.setSpId(productId);
        supplierServiceEntity.delete();
        return ServiceResult.SUCCESS;
    }
}