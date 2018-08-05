package hello.controllers;

import hello.api_model.ManagerInput;
import hello.api_model.ServiceResult;
import hello.api_model.SupplierOutput;
import hello.models.AffixEntity;
import hello.models.UserEntity;
import hello.service.FileService;
import hello.service.SupplierService;
import hello.service.UserService;
import leap.core.annotation.Inject;
import leap.web.action.ControllerBase;
import leap.web.annotation.Path;
import leap.web.annotation.http.POST;
import leap.web.download.FileDownload;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static hello.api_model.ServiceResult.ERROR_RESULT;

public class BingoController extends ControllerBase {

    @Inject
    private UserService userService;
    @Inject
    private FileService fileService;
    @Inject
    private SupplierService supplierService;

    @POST("login")
    public ServiceResult login(ManagerInput input) {

        ServiceResult output = userService.ManagerLogin(input);
        //fixme 不知道这种方式有是没有用？？？
        if (output.getCode() == 200) {
            request().getServletRequest().getSession().setAttribute("user", output.getBusinessObject());
        }
        return output;
    }

    @Path("suppliersList")
    public List<SupplierOutput> getsuppliersList() {
        //返回供应商信息接口
        return supplierService.SuppliersList();
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
