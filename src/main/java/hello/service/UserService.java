package hello.service;


import hello.api_model.UserInfo;
import hello.api_model.UserLogin;
import hello.api_model.ServiceResult;
import hello.models.*;
import hello.util.CommonTool;
import leap.core.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;

import static hello.api_model.ServiceResult.ERROR_RESULT;

@Bean
public class UserService {
    private Logger log;

    public static final String PARAMETER_EMPTY = "请输入正确的格式";
    public static final String USER_NOT_EXIST = "用户不存在";
    public static final String USER_ERROR = "用户名密码错误";
    public static final String USER_AUDIT = "审核中";

    public UserService() {
        log = LoggerFactory.getLogger(this.getClass());
    }

    public ServiceResult userLogin(UserLogin input) {
        final ServiceResult output = new ServiceResult();
        if (input.getUsername() == "" || input.getPassword() == "") {
            output.setCode(404);
            output.setMsg(PARAMETER_EMPTY);
        }
        log.info(input.getUsername());
        //根据用户名，正则匹配用户输入的是邮箱还是手机号还是用户名
        final UserEntity user = UserEntity.findBy(CommonTool.getBingoLoginTableName(input.getUsername()), input.getUsername());

        if (user == null || (user.getUIsdelete() != null && user.getUIsdelete() == 1)) {
            //用户名不存在
            output.setCode(404);
            output.setMsg(USER_NOT_EXIST);
            return output;
        }
        if (!user.getUPassword().equals(input.getPassword())) {
            //用户密码不匹配
            output.setCode(401);
            output.setMsg(USER_ERROR);
            return output;
        }
        if (user.getUIsuse() == null || user.getUIsuse() == 0) {
            output.setCode(403);
            output.setMsg(USER_AUDIT);
        }
        final String key = "userGetRole";
        RoleEntity roleEntity = UserRoleEntity.<RoleEntity>query(key).param("id", user.getUId()).first();
        final String key2 = "userGetDepartment";
        List<DepartmentEntity> departmentEntitys = DepartmentEntity.<DepartmentEntity>query(key2).param("id", user.getUId()).list();
        if (departmentEntitys == null || departmentEntitys.isEmpty()) {
            output.setBusinessObject(UserInfo.change(user, roleEntity.getRType(),""));
        } else {
            output.setBusinessObject(UserInfo.change(user, roleEntity.getRType(), departmentEntitys.get(0).getDId()));
        }
        output.setCode(200);
        output.setMsg("success");

        return output;
    }

    public ServiceResult RemoveUser(String id) {
        UserEntity user = UserEntity.findOrNull(id);
        if (user == null) {
            return ServiceResult.SUCCESS;
        }
        //逻辑删除
        user.setUIsdelete(1);
        user.save();
        return ServiceResult.SUCCESS;
    }

    public ServiceResult GetUserList() {
        List<UserEntity> userlist = UserEntity.all();
        userlist.forEach(v -> v.setUPassword(""));
        return new ServiceResult(200, "", userlist);
    }

    public ServiceResult UpdateUser(UserEntity userEntity) {
        //fixme 怎么更新，值得跟前端商榷下
        UserEntity user = UserEntity.findOrNull(userEntity.getUId());
        if (user == null) {
            return new ServiceResult(404, "所修改的用户不存在", null);
        }
        UserEntity user1 = userEntity.update();
        if (user1 == null)
            return ERROR_RESULT;
        user1.setUPassword("");
        return new ServiceResult(200, "", user1);
    }

    public ServiceResult AddtUser(UserInfo userInfo,String createUserId) {
        final DepartmentEntity departmentEntity=DepartmentEntity.findOrNull(userInfo.getDId());
        if(departmentEntity==null){
            return new ServiceResult(404,"部门不存在",null);
        }
        userInfo.setDepartmentName(departmentEntity.getDName());
        final RoleEntity roleEntity=RoleEntity.findOrNull(userInfo.getRoleId());
        if(roleEntity==null){
            return new ServiceResult(404,"角色不存在",null);
        }
        userInfo.setRoleId(roleEntity.getRId());
        final UserEntity userEntity= UserInfo.change(userInfo);
        userEntity.setUTime(new Date(new java.util.Date().getTime()));
        userEntity.setUId(CommonTool.getIdUUID(UserEntity.class.getName()));
        userEntity.setUIsdelete(0);
        userEntity.setUIsuse(1);
        UserEntity user1 = userEntity.create();
        if (user1 == null)
            return new ServiceResult(404, "添加用户失败", null);
        final UserRoleEntity userRoleEntity=new UserRoleEntity();
        userRoleEntity.setRId(roleEntity.getRId());
        userRoleEntity.setUId(user1.getUId());
        userRoleEntity.setUrTime(new Date(new java.util.Date().getTime()));
        userRoleEntity.setUrUid(createUserId);
        if (userRoleEntity.create()==null)
            return new ServiceResult(404, "添加用户角色失败", null);
        final UserDepartmentEntity userDepartmentEntity=new UserDepartmentEntity();
        userDepartmentEntity.setDId(departmentEntity.getDId());
        userDepartmentEntity.setUdTime(new Date(new java.util.Date().getTime()));
        userDepartmentEntity.setUId(user1.getUId());
        userDepartmentEntity.setUdUid(createUserId);
        if (userRoleEntity.create()==null)
            return new ServiceResult(404, "添加用户部门失败", null);
        userInfo.setUPassword("");
        return new ServiceResult(200, "", userInfo);
    }
}
