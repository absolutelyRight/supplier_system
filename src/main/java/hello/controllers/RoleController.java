package hello.controllers;

import hello.api_model.ServiceResult;
import hello.api_model.UserInfo;
import hello.models.RoleEntity;
import hello.service.RoleService;
import leap.core.annotation.Inject;
import leap.web.action.ControllerBase;
import leap.web.annotation.Path;

public class RoleController extends ControllerBase {
    @Inject
    private RoleService roleService;
    @Path("list")
    public ServiceResult getAllRole(){
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        return roleService.getAllRole();
    }
    //只有最高权限管理员才有新角色功能
    @Path("add")
    public ServiceResult addRole(RoleEntity roleEntity){
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        if(user.getType()!=10){
            return ServiceResult.POWER_ERROR;
        }
        return roleService.addRole(roleEntity);
    }
}
