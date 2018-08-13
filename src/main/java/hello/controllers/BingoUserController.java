package hello.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import hello.api_model.ServiceResult;
import hello.api_model.UserInfo;
import hello.api_model.UserLogin;
import hello.models.AffixEntity;
import hello.models.UserEntity;
import hello.service.FileService;
import hello.service.SupplierService;
import hello.service.UserService;
import leap.core.annotation.Inject;
import leap.web.action.ControllerBase;
import leap.web.annotation.Path;
import leap.web.annotation.http.GET;
import leap.web.annotation.http.POST;
import leap.web.download.FileDownload;


@Path("bingouser")
public class BingoUserController extends ControllerBase {

    @Inject
    private UserService userService;
    @Inject
    private FileService fileService;
    @Inject
    private SupplierService supplierService;

    @POST("login")
    public ServiceResult login(UserLogin input) {

        ServiceResult output = userService.userLogin(input);
        if (output.getCode() == 200) {
            request().getServletRequest().getSession().setAttribute("user", output.getBusinessObject());
        }
        return output;
    }

    //测试用
    @GET
    public UserInfo getUser() {
        return (UserInfo) request().getServletRequest().getSession().getAttribute("user");
    }

    //获得所有用户信息，测试用
    @Path("user/list")
    public ServiceResult getUserList() {
        return userService.GetUserList();
    }

    @Path("user/edit")
    public ServiceResult editUser(UserEntity userEntity) {
        //修改个人信息
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        //自己可以修改自己的个人信息。管理员与部门经理可以创建与修改他人信息
        if (userEntity.getUId().equals(user.getUId()) || user.getType() == 10 || user.getType() == 8)
            return userService.UpdateUser(userEntity);
        else
            return ServiceResult.POWER_ERROR;
    }

    @Path("user/add")
    public ServiceResult addUser(UserEntity userEntity) {
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        //自己可以修改自己的个人信息。管理员与部门经理可以创建他人信息
        if ( user.getType() == 10 || user.getType() == 8)
            return userService.UpdateUser(userEntity);
        else
            return ServiceResult.POWER_ERROR;

    }

    @Path("user/remove")
    public ServiceResult removeUser(String id) {
        /*final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        //自己可以修改自己的个人信息。管理员与部门经理可以创建他人信息
        if (user.getType() == 10 || user.getType() == 8)
            return userService.UpdateUser(userEntity);
        else
            return ServiceResult.POWER_ERROR;*/
        return userService.RemoveUser(id);
    }

    @Path("user/batchremove")
    public ServiceResult batchRemoveUser(String[] ids) {
        //fixme 权限检查，是否拥有修改此用户的权利
        for (String id : ids) {
            userService.RemoveUser(id);
        }
        return new ServiceResult();
    }

    @Path("download")
    public FileDownload downloadFile(String affix) throws Exception {
        return fileService.DownloadFile(affix);
    }

    @Path("upload")
    public ServiceResult upload() {
        ServiceResult serviceResult = new ServiceResult();

        try {
            // 上传文件
            HttpServletRequest request = request().getServletRequest();
            List<AffixEntity> list = fileService.uploadFiles(request);
            if (list.isEmpty()) {
                serviceResult.setCode(404);
                serviceResult.setMsg("上传失败！");
            } else {
                serviceResult.setMsg("上传成功！");
                serviceResult.setBusinessObject(list);
            }

        } catch (Exception ex) {
            String message = ex.getMessage();
            serviceResult.setCode(404);
            serviceResult.setMsg(message);
            return serviceResult;
        }
        return serviceResult;
    }

}
