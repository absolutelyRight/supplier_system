package hello.controllers;

import hello.api_model.ServiceResult;
import hello.models.AffixEntity;
import hello.models.SupplierEntity;
import hello.models.UserAffixs;
import hello.service.FileService;
import leap.core.annotation.Inject;
import leap.web.action.ControllerBase;
import leap.web.annotation.Path;
import leap.web.download.FileDownload;

import java.util.List;

@Path("supplierfile")
public class SupplierFileController extends ControllerBase {
    @Inject
    FileService fileService;

    //fixme 上传文件的检查
    @Path("upload")
    public ServiceResult uploadFile() {
        final SupplierEntity supplierEntity = (SupplierEntity) request().getServletRequest().getSession().getAttribute("supplier");
        if (supplierEntity == null) {
            return ServiceResult.NOT_LOGIN;
        }
        try {
            List<AffixEntity> affixEntities = fileService.uploadFiles(request().getServletRequest());
            for (AffixEntity affix : affixEntities) {
                UserAffixs userAffixs = new UserAffixs();
                userAffixs.setAffixId(affix.getId());
                userAffixs.setUserId(supplierEntity.getSId());
                userAffixs.create();
            }

        } catch (Exception e) {
            //日志记录
            e.printStackTrace();
            return new ServiceResult(500, "上传失败", null);
        }
        return new ServiceResult(200, "上传成功", null);
    }

    //fixme 记录下谁下载过了几遍。塞在ServiceResult有木有用？
    @Path("download")
    public ServiceResult downloadFile(String affixid) {
        final SupplierEntity supplierEntity = (SupplierEntity) request().getServletRequest().getSession().getAttribute("supplier");
        if (supplierEntity == null) {
            return ServiceResult.NOT_LOGIN;
        }
        if (supplierEntity.getSCheckStatus() != 2) {
            return new ServiceResult(405, "暂时无法下载", "");
        }
        FileDownload fileDownload;
        try {
            fileDownload = fileService.DownloadFile(affixid);
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResult(500, "下载失败", null);
        }
        return new ServiceResult(200, "", fileDownload);
    }
}
