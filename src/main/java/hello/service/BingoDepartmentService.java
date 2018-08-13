package hello.service;

import hello.api_model.ServiceResult;
import hello.models.DepartmentEntity;
import hello.util.CommonTool;

import java.util.List;

public class BingoDepartmentService {

    public ServiceResult addDepartment(DepartmentEntity departmentEntity){
        departmentEntity.setDId(CommonTool.getIdUUID(DepartmentEntity.class.getName()));
        if(departmentEntity.create()==null)
            return ServiceResult.DB_ERROR;
        return new ServiceResult(200,"",departmentEntity);
    }
    public ServiceResult listDepartment(){
        final String key="DepartmentList";
        List<DepartmentEntity> departmentEntitys=DepartmentEntity.<DepartmentEntity>query(key).list();
        return new ServiceResult(200,"",departmentEntitys);
    }
}
