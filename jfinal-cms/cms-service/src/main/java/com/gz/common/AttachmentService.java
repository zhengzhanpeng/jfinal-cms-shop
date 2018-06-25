package com.gz.common;


import com.gz.common.model.Attachment;
import com.gz.common.model.ContentAttachment;
import com.gz.utils.FileUtil;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;

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
        attachment.save();
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
    public List<Attachment> getProductImgList(int productId){
        return Attachment.dao.find("SELECT tb_attachment.* FROM tb_attachment RIGHT  JOIN tb_product_attachment on tb_product_attachment.attachmentId=tb_attachment.id  WHERE productId=?",productId);
    }
    public ContentAttachment getContentAttachment(int contentId,int attachment){
        return  ContentAttachment.dao.findFirst("SELECT * from tb_content_attachment WHERE contentId=? and attachmentId=?",contentId,attachment);
    }

    public Page<Attachment> getAttachmentPage(int pageNum, int pageSize) {
        Page<Attachment> attachmentPage= Attachment.dao.paginate(pageNum,pageSize,"select * ","from tb_attachment order by id desc");
        for(Attachment attachment:attachmentPage.getList()){
            attachment.put("thumbnail_temp",Constant.FILE_PATH+attachment.getUrl());
        }
        return  attachmentPage;
    }
}
