package hello.controllers;

import hello.api_model.ServiceResult;
import hello.api_model.UserInfo;
import hello.models.DepartmentEntity;
import hello.service.BingoDepartmentService;
import leap.core.annotation.Inject;
import leap.web.action.ControllerBase;
import leap.web.annotation.Path;

public class BingoDepartmentController  extends ControllerBase {
    @Inject
    BingoDepartmentService bingoDepartmentService;

    public BingoDepartmentController() {
        this.bingoDepartmentService = new BingoDepartmentService();
    }

    //添加部门
    @Path("add")
    public ServiceResult add(DepartmentEntity departmentEntity){
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        //自己可以修改自己的个人信息。管理员与部门经理可以创建他人信息
        if (user.getType() == UserInfo.ROOT || user.getType() == UserInfo.MANAGER)
            return bingoDepartmentService.addDepartment(departmentEntity);
        else
            return ServiceResult.POWER_ERROR;
    }
    //列出部门
    @Path("list")
    public ServiceResult listDepartment(){
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        //除了需求经理之外都可以看到有哪些部门
        if (user.getType()!=UserInfo.DEMAND)
            return bingoDepartmentService.listDepartment();
        else
            return ServiceResult.POWER_ERROR;
    }
}
