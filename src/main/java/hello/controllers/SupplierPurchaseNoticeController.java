package hello.controllers;

import hello.api_model.ServiceResult;
import hello.models.AffixEntity;
import hello.models.ArchiveAffixEntity;
import hello.models.SupplierEntity;
import hello.service.FileService;
import hello.service.SupplierPurchaseNoticeService;
import leap.core.annotation.Inject;
import leap.web.action.ControllerBase;
import leap.web.annotation.Path;

import java.util.List;

public class SupplierPurchaseNoticeController extends ControllerBase {
    private SupplierPurchaseNoticeService supplierPurchaseNoticeService;
    private FileService fileService;

    public SupplierPurchaseNoticeController() {
        this.supplierPurchaseNoticeService = new SupplierPurchaseNoticeService();
        this.fileService = new FileService();
    }

    //查看参与过的投标
    @Path("joined")
    public ServiceResult joinedPurchaseNotices() {
        final SupplierEntity supplierEntity = (SupplierEntity) request().getServletRequest().getSession().getAttribute("supplier");
        if (supplierEntity == null) {
            return ServiceResult.NOT_LOGIN;
        }
        return supplierPurchaseNoticeService.joinedPurchaseNotices(supplierEntity.getSId());
    }

    //查看已中的投标
    @Path("win")
    public ServiceResult winPurchaseNotices() {
        final SupplierEntity supplierEntity = (SupplierEntity) request().getServletRequest().getSession().getAttribute("supplier");
        if (supplierEntity == null) {
            return ServiceResult.NOT_LOGIN;
        }
        return supplierPurchaseNoticeService.winPurchaseNotices(supplierEntity.getSId());
    }
    //参与投标
    @Path("join")
    public ServiceResult createArchive( String purchaseNoticeId) {
        final SupplierEntity supplierEntity = (SupplierEntity) request().getServletRequest().getSession().getAttribute("supplier");
        if (supplierEntity == null) {
            return ServiceResult.NOT_LOGIN;
        }
        //权限检查
        if (supplierEntity.getSCheckStatus()!=2){
            return new ServiceResult(405, "暂时无法参与投标", "");
        }
        return supplierPurchaseNoticeService.joinPurchaseNotices(supplierEntity.getSId(),purchaseNoticeId);
    }
    //上传投标附件,需要输入投标id
    @Path("uploadfile")
    public ServiceResult uploadfile(String ArchiveId){
        final SupplierEntity supplierEntity = (SupplierEntity) request().getServletRequest().getSession().getAttribute("supplier");
        if (supplierEntity == null) {
            return ServiceResult.NOT_LOGIN;
        }
        //权限检查
        if (supplierEntity.getSCheckStatus()!=2){
            return new ServiceResult(405, "暂时无法上传", "");
        } try {
            List<AffixEntity> affixEntities = fileService.uploadFiles(request().getServletRequest());
            for (AffixEntity affix : affixEntities) {
                ArchiveAffixEntity archiveAffixEntity = new ArchiveAffixEntity();
                archiveAffixEntity.setAffixId(affix.getId());
                archiveAffixEntity.setArchive(ArchiveId);
                archiveAffixEntity.create();
            }
        } catch (Exception e) {
            //日志记录
            e.printStackTrace();
            return new ServiceResult(500, "上传失败", null);
        }
        return ServiceResult.SUCCESS;
    }
}