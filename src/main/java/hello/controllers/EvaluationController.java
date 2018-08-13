package hello.controllers;

import hello.api_model.ServiceResult;
import hello.api_model.UserInfo;
import hello.models.EvaluationEntity;
import hello.service.EvaluationService;
import leap.core.annotation.Inject;
import leap.web.action.ControllerBase;
import leap.web.annotation.Path;

public class EvaluationController extends ControllerBase {
    //用于处理评价的控制器
    @Inject
    private EvaluationService evaluationService;
    @Path("create")
    public ServiceResult create(EvaluationEntity evaluationEntity){
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
       return evaluationService.create(evaluationEntity,user);
    }
    @Path("list")
    public ServiceResult list(){
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        return evaluationService.list();
    }
}
