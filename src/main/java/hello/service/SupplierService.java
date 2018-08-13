package hello.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hello.api_model.ServiceResult;
import hello.api_model.UserLogin;
import hello.models.SupplierEntity;
import hello.util.CommonTool;
import leap.core.annotation.Bean;
import leap.lang.json.JSON;
import leap.orm.query.PageResult;

@Bean
public class SupplierService {
    private Logger log;

    public SupplierService() {
        log = LoggerFactory.getLogger(this.getClass());
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
    public ServiceResult  SupplierUpdate(SupplierEntity nowSupplier,SupplierEntity futureSupplier){
        final SupplierEntity s1=SupplierEntity.findBy("S_EMAIL", futureSupplier.getSEmail());
        if(s1!=null){
            return new ServiceResult(401,"该邮箱已存在",null);
        }
        futureSupplier.setSId(nowSupplier.getSId());
        futureSupplier.setSStartTime(nowSupplier.getSStartTime());
        futureSupplier.setSCheckStatus(0);
        futureSupplier.update();
        return new ServiceResult(200,"",futureSupplier);
    }
    
    public ServiceResult Remove(String id) {
        SupplierEntity user = SupplierEntity.findOrNull(id);
        //删除某用户，竟然发现为空！
        if (user == null) {
            return ServiceResult.NOT_FOUND;
        }
        //逻辑删除
        user.setSIsdelete(1);
        user.save();
        return ServiceResult.SUCCESS;
    }
    public ServiceResult PageSearch(String type,String name,int index,int pageSize) {
    	String key="supplier.all";
        PageResult<SupplierEntity> page=SupplierEntity.<SupplierEntity>query(key)
        		.param("name", name).param("type", type!=null&&type.matches("{(\\d+,)*\\d+}")?type:null)
        		.pageResult(index, pageSize);
        Map<String,Object> pageObj=new HashMap<>(4);
        pageObj.put("page",page);
        pageObj.put("list",page.list());
        System.out.println(page.list().get(0).toString());
        System.out.println(JSON.encode(page.list().get(0)));
        System.out.println(page.list().get(0).getClass().getName());
//        noticeEntities.forEach(v->{v.setNCheckuid("");v.setNStartuid("");v.setNUpdateuid("");});
        return new ServiceResult(200,"",pageObj);
    }
}
