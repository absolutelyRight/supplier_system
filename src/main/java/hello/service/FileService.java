package hello.service;

import hello.models.AffixEntity;
import hello.util.CommonTool;
import leap.core.annotation.Bean;
import leap.web.download.FileDownload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Bean
public class FileService {
    private Logger log;
    //FIXME 根据情况不同，设置根路径
    public static final String ROOT_PATH = "/Users/ermin/IdeaProjects/supplier_system/data";

    public FileService() {
        log = LoggerFactory.getLogger(this.getClass());
        try {
            File rootDir=new File(ROOT_PATH);
            if(!rootDir.exists()){
                rootDir.mkdir();
            }else {
                if(!rootDir.isDirectory()){
                    rootDir.delete();
                    rootDir.mkdir();
                }
            }
        }catch (Exception e){
            log.error("创建文件根目录失败" );
        }

    }

    //下载文件
    public FileDownload DownloadFile(String affixId) throws Exception {
        AffixEntity affixEntity = AffixEntity.findOrNull(affixId);
        if (affixEntity == null) {
            log.error("数据库记录不存在：" + affixId);
            throw new Exception("数据库记录不存在");
        }
        File file = new File(affixEntity.getPath());
        if (!file.exists())
            throw new Exception("附件不存在");
        FileDownload download = new FileDownload(file);
        return download;
    }

    //上传文件
    public List<AffixEntity> uploadFiles(HttpServletRequest request) throws Exception {
        List<AffixEntity> list = new ArrayList<AffixEntity>();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        List<FileItem> items = upload.parseRequest(request);
        Iterator<FileItem> itr = items.iterator();
        while (itr.hasNext()) {
            FileItem item = (FileItem) itr.next();
            if (!item.isFormField() && item.getName() != null && !item.getName().equals("")) {
                // 上传文件
                AffixEntity fileForm = this.uploadFile(item);
                list.add(fileForm);
            }
        }

        return list;
    }

    public AffixEntity uploadFile(FileItem item) throws Exception {
        try {
            // item.getName()返回上传文件在客户端的完整路径名称
            AffixEntity affixEntity = new AffixEntity();
            affixEntity.setId(CommonTool.getIdUUID(AffixEntity.class.getName()));
            Path path = Paths.get(ROOT_PATH, affixEntity.getId(), item.getName());
            FileUtils.forceMkdir(path.getParent().toFile());
            item.write(path.toFile());
            affixEntity.setPath(path.toAbsolutePath().toString());
            affixEntity.create();
            return affixEntity;
        } catch (Exception e) {
            log.error("保存失败");
        }
        return null;

    }
}
