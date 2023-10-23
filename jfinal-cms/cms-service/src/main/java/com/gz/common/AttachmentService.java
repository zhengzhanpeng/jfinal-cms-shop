package com.gz.common;

import com.gz.common.model.Attachment;
import com.gz.common.model.ContentAttachment;
import com.gz.utils.FileUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AttachmentService {
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private ContentAttachmentRepository contentAttachmentRepository;

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
        attachmentRepository.save(attachment);
        return attachment;
    }

    /**
     * 通过文件获取附件对象
     * @param file
     * @return UploadFile file
     */
    public Attachment getAttachmentByFile(MultipartFile file){
        Attachment attachment=new Attachment();
        attachment.setCreated(new Date());
        attachment.setSuffix(FileUtil.getFileExtension(file.getOriginalFilename()));
        attachment.setMimeType(file.getContentType());
        attachment.setName(FileUtil.getSuffixName(file.getOriginalFilename()));
        attachment.setSize(file.getSize());
        return attachment;
    }
    public List<Attachment> getList(int contentId){
        return attachmentRepository.findByContentId(contentId);
    }
    public List<Attachment> getProductImgList(int productId){
        return attachmentRepository.findByProductId(productId);
    }
    public ContentAttachment getContentAttachment(int contentId,int attachment){
        return  contentAttachmentRepository.findByContentIdAndAttachmentId(contentId, attachment);
    }

    public Page<Attachment> getAttachmentPage(int pageNum, int pageSize) {
        Page<Attachment> attachmentPage= attachmentRepository.findAll(PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "id")));
        for(Attachment attachment:attachmentPage.getContent()){
            attachment.setThumbnailTemp(Constant.FILE_PATH+attachment.getUrl());
        }
        return  attachmentPage;
    }
}
