package com.gz.controller;

import com.gz.common.AttachmentService;
import com.gz.common.Constant;
import com.gz.common.model.Attachment;
import com.gz.utils.FileUtil;
import com.gz.utils.Response;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;

import java.util.Date;
import java.util.Map;

/**
 * Created by gongzhen on 2018/6/6.
 */
public class FileToolController extends Controller{
    public void upload( ) {
        UploadFile file=getFile("file", "\\temp");
        if(file==null){
            renderJson(Response.responseJson(1,"没有发现文件"));
            return;

        }
        Attachment attachment= AttachmentService.getService().getAttachmentByFile(file);
        Map<String,Object> result= FileUtil.upload(file);
        if((int)result.get("code")==0){
            attachment.setUrl(result.get("url").toString());
            attachment.save();
            result.put("attachment",attachment);
            result.put("url", Constant.FILE_PATH+attachment.getUrl());
            renderJson(result);
        }else{
            renderJson(result);
        }
    }
    public void getAttachmentList(){
        Page<Attachment> attachmentPage= AttachmentService.getService().getAttachmentPage(getParaToInt("pageNum",1),getParaToInt("pageSize",1));
        renderJson(Response.responseJson(1,"请求成功",attachmentPage));
    }
    public void delAttachment(){
        int id=getParaToInt("id",0);
        Attachment.dao.deleteById(id);
        renderJson(Response.responseJson(1,"删除成功"));
    }
}
