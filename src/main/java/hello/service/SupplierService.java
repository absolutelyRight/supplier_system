package hello.service;

import hello.api_model.UserLogin;
import hello.api_model.ServiceResult;
import hello.models.SupplierEntity;
import hello.util.CommonTool;
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
    //fixme 可能需要分页 暂时返回供应商
    public List<SupplierEntity> SuppliersList(){
        Query<SupplierEntity> querylist=SupplierEntity.dao().createNamedQuery("supplier.all",SupplierEntity.class);
        List<SupplierEntity> list=querylist.list();
        list.forEach(v -> v.setSPassword(""));
        return list;
    }

    public ServiceResult SupplierLogin(UserLogin input){
        log.info("SupplierLogin");
        final ServiceResult output = new ServiceResult();
        if (input.getUsername() == "" || input.getPassword() == ""||!CommonTool.isEmil(input.getUsername())) {
            output.setCode(404);
            output.setMsg(UserService.PARAMETER_EMPTY);
        }
        log.info(input.getUsername());
        //只允许邮箱登陆
        final SupplierEntity supplier = SupplierEntity.findBy("S_EMAIL", input.getUsername());

        if (supplier == null || (supplier.getSIsdelete() != null && supplier.getSIsdelete() == 1)) {
            //用户名不存在
            output.setCode(404);
            output.setMsg(UserService.USER_NOT_EXIST);
            return output;
        }
        if (!supplier.getSPassword().equals(input.getPassword())) {
            //用户密码不匹配
            output.setCode(401);
            output.setMsg(UserService.USER_ERROR);
            return output;
        }
        supplier.setSPassword("");
        output.setBusinessObject(supplier);
        return output;
    }

    public ServiceResult SupplierRegister(SupplierEntity supplier){
        final ServiceResult result=SupplierIsNotExist(supplier.getSEmail());
        if (result.getCode()==200){
            supplier.setSId(CommonTool.getIdUUID(SupplierEntity.class.getName()));
            supplier.setSCheckStatus(0);

            final SupplierEntity supplierEntity=supplier.save();
            if(supplierEntity!=null){
                result.setBusinessObject(supplierEntity);
            }else {
                result.setCode(500);
                result.setMsg("未保存成功");
            }
        }
        return result;
    }
    public ServiceResult SupplierIsNotExist(String input){
        log.info("SupplierIsExist");
        final ServiceResult output = new ServiceResult();
        if (input == ""||!CommonTool.isEmil(input)) {
            output.setCode(404);
            output.setMsg(UserService.PARAMETER_EMPTY);
        }
        SupplierEntity supplierEntity=SupplierEntity.findBy("S_EMAIL", input);
        if (supplierEntity==null){
            output.setCode(200);
            output.setMsg("该用户不存在");
        }else {
            output.setCode(401);
            output.setMsg("该用户已存在");
        }
        return output;

    }
}
