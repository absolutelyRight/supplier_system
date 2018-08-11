import hello.models.RoleEntity;
import hello.models.SupplierEntity;
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
    }

}
