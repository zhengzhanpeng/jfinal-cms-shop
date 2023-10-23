package com.gz.common;


import com.gz.common.model.Attachment;
import com.gz.common.model.ContentAttachment;
import com.gz.utils.FileUtil;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class AttachmentService {
import java.util.Date;
import java.util.List;

/**
 * Created by gongzhen on 2016/12/1.
 */
public class AttachmentService {
    private static AttachmentService service;

    private AttachmentService() {
    }

    public static AttachmentService getService() {
        if (service == null) {
            service = new AttachmentService();
        }
        return service;
    }
    public Attachment save(String name, String url, String mini_type , String suffix, Integer content_id, double size){
        Attachment attachment=new Attachment();
        attachment.setName(name);
        attachment.setUrl(url);
        attachment.setMimeType(mini_type);
        attachment.setSuffix(suffix);
        import org.springframework.stereotype.Service;
        import org.springframework.beans.factory.annotation.Autowired;
        @Service
        public class AttachmentService{
        
            @Autowired
            private AttachmentRepository attachmentRepository;
        
            public Attachment save(String name, String url, String mini_type , String suffix, Integer content_id, double size){
                Attachment attachment=new Attachment();
                attachment.setName(name);
                attachment.setUrl(url);
                attachment.setMimeType(mini_type);
                attachment.setSuffix(suffix);
                attachmentRepository.save(attachment);
                return attachment;
            }

    /**
     * 通过文件获取附件对象
     * @param file
     * @return UploadFile file
     */
    public Attachment getAttachmentByFile(UploadFile file){
        Attachment attachment=new Attachment();
        attachment.setCreated(new Date());
        attachment.setSuffix(FileUtil.getFileExtension(file.getFileName()));
        attachment.setMimeType(file.getContentType());
        attachment.setName(FileUtil.getSuffixName(file.getFileName()));
        attachment.setSize(file.getFile().getTotalSpace());
        return attachment;
    }
    public List<Attachment> getList(int contentId){
        return Attachment.dao.find("SELECT tb_attachment.* FROM tb_attachment RIGHT  JOIN tb_content_attachment on tb_content_attachment.attachmentId=tb_attachment.id  WHERE contentId=?",contentId);
    }
    public List<Attachment> getList(int contentId){
        return attachmentRepository.findByContentId(contentId);
    }
    public List<Attachment> getProductImgList(int productId){
        return attachmentRepository.findByProductId(productId);
    }
    public ContentAttachment getContentAttachment(int contentId,int attachment){
        return  contentAttachmentRepository.findByContentIdAndAttachmentId(contentId,attachment);
    }

    public Page<Attachment> getAttachmentPage(int pageNum, int pageSize) {
        Page<Attachment> attachmentPage= attachmentRepository.findAll(PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "id")));
        for(Attachment attachment:attachmentPage.getContent()){
            attachment.put("thumbnail_temp",Constant.FILE_PATH+attachment.getUrl());
        }
        return  attachmentPage;
    }
}
