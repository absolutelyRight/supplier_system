import hello.models.RoleEntity;
import hello.models.SupplierEntity;
import hello.models.SupplierProductEntity;
import hello.models.SupplierServiceEntity;
import hello.service.SupplierService;
import leap.core.junit.AppTestBase;
import leap.orm.query.Query;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestOrm extends AppTestBase {
    @Test
    public void testROLE() {
        RoleEntity r = RoleEntity.findOrNull("1233488e93");
        if (null == r) {
            r = new RoleEntity();
            r.setRId("1233488e93");
            r.setRName("fang1");
            r.setRType(0);
            r.create();
        }
    }

    @Test
    public void testSql() {
//        Query<SupplierOutput> querylist=SupplierEntity.dao().createNamedQuery("supplier.all",SupplierOutput.class);
//        List<SupplierOutput> list=querylist.list();
//        list.forEach(v->v.setSPassword(""));
//        list.forEach(v->System.out.println(v.toString()));
//        Query<SupplierProductEntity> slist=SupplierProductEntity.<SupplierProductEntity>query("select SUPPLIER_PRODUCT.* from SUPPLIER_PRODUCT,SUPPLIER_SERVICE where SUPPLIER_SERVICE.s_id = 1 and SUPPLIER_SERVICE.sp_id=SUPPLIER_PRODUCT.sp_id");
//        for(SupplierProductEntity s:slist.list()){
//            System.out.println(s.getSpId());
//        }
//        Map map = new HashMap();
//        map.put("id", 1);
//        Query<SupplierProductEntity> slist=SupplierProductEntity.<SupplierProductEntity>query("supplier.products").param("id","1");
//        for(SupplierProductEntity s:slist.list()){
//            System.out.println(s.getSpId());
//        }
        SupplierServiceEntity supplierlist=SupplierServiceEntity.findBy("SP_ID","2");
System.out.println(supplierlist.getSId());
    }

}
