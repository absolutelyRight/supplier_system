package hello.service;


import hello.api_model.UserInfo;
import hello.api_model.UserLogin;
import hello.api_model.ServiceResult;
import hello.models.RoleEntity;
import hello.models.UserEntity;
import hello.models.UserRoleEntity;
import hello.util.CommonTool;
import leap.core.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        //清除用户敏感信息并返回前端
        final String key="getrole";
        RoleEntity roleEntity=UserRoleEntity.<RoleEntity>query(key).param("id",user.getUId()).first();
        output.setCode(200);
        output.setMsg("success");
        output.setBusinessObject(UserInfo.change(user,roleEntity.getRType()));
        return output;
    }

    public ServiceResult RemoveUser(String id) {
        UserEntity user = UserEntity.findOrNull(id);
        //删除某用户，竟然发现为空！
        if (user == null) {
            return new ServiceResult();
        }
        //逻辑删除
        user.setUIsdelete(1);
        user.save();
        return new ServiceResult();
    }

    public ServiceResult GetUserList() {
        List<UserEntity>userlist=UserEntity.all();
        userlist.forEach(v->v.setUPassword(""));
        return new ServiceResult(200,"",userlist);
    }

    public ServiceResult UpdateUser(UserEntity userEntity){
        //fixme 怎么更新，值得跟前端商榷下
        UserEntity user=UserEntity.findOrNull(userEntity.getUId());
        if(user==null){
            return new ServiceResult(404,"所修改的用户不存在",null);
        }
        UserEntity user1=userEntity.update();
        if (user1==null)
            return ERROR_RESULT;
        user1.setUPassword("");
        return new ServiceResult(200,"",user1);
    }
    public ServiceResult InsertUser(UserEntity userEntity){
        UserEntity user=UserEntity.findOrNull(userEntity.getUId());
        if(user!=null){
            return new ServiceResult(404,"所需添加用户已存在",null);
        }
        UserEntity user1=userEntity.create();
        if (user1==null)
            return new ServiceResult(404,"添加用户失败",null);
        return new ServiceResult(200,"",null);
    }
}
