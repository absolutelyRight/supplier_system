package hello.controllers;

import hello.api_model.UserInfo;
import hello.api_model.UserLogin;
import hello.api_model.ServiceResult;
import hello.models.AffixEntity;
import hello.service.FileService;
import hello.service.SupplierService;
import hello.service.UserService;
import leap.core.annotation.Inject;
import leap.web.action.ControllerBase;
import leap.web.annotation.Path;
import leap.web.annotation.http.GET;
import leap.web.annotation.http.POST;
import leap.web.download.FileDownload;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


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

    //fixme 获得所有用户信息
    @Path("list")
    public ServiceResult getUserList() {
        return userService.GetUserList();
    }

    //fixme 暂时不能修改个人信息,修改个人需要三个表
//    @Path("edit")
//    public ServiceResult editUser(UserEntity userEntity) {
//        //修改个人信息
//        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
//        if (user == null) {
//            return ServiceResult.NOT_LOGIN;
//        }
//        //自己可以修改自己的个人信息。管理员与部门经理可以创建与修改他人信息
//        if (userEntity.getUId().equals(user.getUId()) || user.getType() == UserInfo.ROOT || user.getType() == UserInfo.MANAGER)
//            return userService.UpdateUser(userEntity);
//        else
//            return ServiceResult.POWER_ERROR;
//    }

    //新增用户，需传入用户信息，部门ID，角色ID
    @Path("add")
    public ServiceResult addUser(UserInfo userInfo) {
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        //管理员与部门经理可以创建他人信息
        if (user.getType() == UserInfo.ROOT || user.getType() == UserInfo.MANAGER)
            return userService.AddtUser(userInfo,user.getUId());
        else
            return ServiceResult.POWER_ERROR;

    }

    @Path("remove")
    public ServiceResult removeUser(String id) {
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        //自己可以修改自己的个人信息。管理员与部门经理可以创建他人信息
        if (user.getType() == UserInfo.ROOT || user.getType() == UserInfo.MANAGER)
            return userService.RemoveUser(id);
        else
            return ServiceResult.POWER_ERROR;
    }

    @Path("batchremove")
    public ServiceResult batchRemoveUser(String[] ids) {
        final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
        if (user == null) {
            return ServiceResult.NOT_LOGIN;
        }
        //fixme 部门经理没有删除管理员的权限
        if (user.getType() == UserInfo.ROOT || user.getType() == UserInfo.MANAGER) {
            boolean b = false;
            for (String id : ids) {
                final ServiceResult serviceResult = userService.RemoveUser(id);
                if (serviceResult.getCode() != 200)
                    b = true;
            }
            if (b)
                return new ServiceResult(400, "有部分未删除成功", null);
            return ServiceResult.SUCCESS;
        } else
            return ServiceResult.POWER_ERROR;
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
