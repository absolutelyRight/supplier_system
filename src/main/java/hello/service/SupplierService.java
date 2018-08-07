package hello.service;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import hello.api_model.SupplierOutput;
import hello.models.SupplierEntity;
import leap.core.annotation.Bean;
import leap.orm.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Bean
public class SupplierService {
    private Logger log;

    public SupplierService() {
        log = LoggerFactory.getLogger(this.getClass());
    }
    //fixme 可能需要分页
    public List<SupplierOutput> SuppliersList(){
        Query<SupplierOutput> querylist=SupplierEntity.dao().createNamedQuery("supplier.all",SupplierOutput.class);
        List<SupplierOutput> list=querylist.list();
        list.forEach(v -> v.setSPassword(""));
        return list;
    }
}
