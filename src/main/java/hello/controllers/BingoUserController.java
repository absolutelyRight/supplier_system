package hello.controllers;

import hello.api_model.UserLogin;
import hello.api_model.ServiceResult;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static hello.api_model.ServiceResult.ERROR_RESULT;


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

        ServiceResult output = userService.ManagerLogin(input);
        if (output.getCode() == 200) {
            request().getServletRequest().getSession().setAttribute("user", output.getBusinessObject());
        }
        return output;
    }
    //测试用
    @GET
    public UserEntity getUser(){
        return (UserEntity)request().getServletRequest().getSession().getAttribute("user");
    }

    @Path("suppliersList")
    public ServiceResult getsuppliersList() {
        //返回供应商信息接口
        return new ServiceResult(200,"",supplierService.SuppliersList());
    }

    @Path("user/list")
    public ServiceResult getUserList(){
        return userService.GetUserList();
    }

    @Path("user/edit")
    public ServiceResult editUser(UserEntity userEntity){
        //fixme 权限检查，是否拥有修改此用户的权利
        return userService.UpdateUser(userEntity);
    }

    @Path("user/add")
    public ServiceResult addUser(UserEntity userEntity){
        //fixme 权限检查，是否拥有修改此用户的权利
        return userService.UpdateUser(userEntity);
    }

    @Path("user/remove")
    public ServiceResult removeUser(String id){
        //fixme 权限检查，是否拥有修改此用户的权利
        if(id ==null||id=="")
            return ERROR_RESULT;
        return userService.RemoveUser(id);
    }

    @Path("user/batchremove")
    public ServiceResult batchRemoveUser(String[] ids){
        //fixme 权限检查，是否拥有修改此用户的权利
        for (String id:ids){
            userService.RemoveUser(id);
        }
        return new ServiceResult();
    }

    @Path("download")
    public FileDownload downloadFile(String affix) throws Exception {
        return fileService.DownloadFile(affix);
    }

    @Path("upload")
    public ServiceResult upload(){
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
