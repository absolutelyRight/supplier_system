package hello.service;

import hello.api_model.ServiceResult;
import hello.api_model.UserInfo;
import hello.models.EvaluationEntity;
import hello.util.CommonTool;

import java.sql.Date;

public class EvaluationService {

    public ServiceResult create(EvaluationEntity evaluationEntity, UserInfo userInfo){
        evaluationEntity.setEId(CommonTool.getIdUUID(EvaluationEntity.class.getName()));
        evaluationEntity.setUId(userInfo.getUId());
        evaluationEntity.setETime(new Date(new java.util.Date().getTime()));
        if(evaluationEntity.create()==null){
            return ServiceResult.ERROR_RESULT;
        }
        return new ServiceResult(200,"",evaluationEntity);
    }
    public ServiceResult list(){
        return new ServiceResult(200,"",EvaluationEntity.all());
    }
}
