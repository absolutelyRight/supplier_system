package hello.service;

import hello.api_model.ServiceResult;
import hello.models.RoleEntity;
import hello.util.CommonTool;

public class RoleService {
    public ServiceResult getAllRole(){
        return new ServiceResult(200,"",RoleEntity.all());
    }
    public ServiceResult addRole(RoleEntity roleEntity){
        roleEntity.setRId(CommonTool.getIdUUID(RoleEntity.class.getName()));
        if (roleEntity.create()==null)
            return ServiceResult.DB_ERROR;
        return ServiceResult.SUCCESS;
    }
}
