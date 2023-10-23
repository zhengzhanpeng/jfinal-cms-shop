package com.gz.controller;

import com.gz.common.AttachmentService;
import com.gz.common.Constant;
import com.gz.common.model.Attachment;
import com.gz.utils.FileUtil;
import com.gz.utils.Response;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;

import java.util.Date;
import java.util.Map;

/**
 * Created by gongzhen on 2018/6/6.
 */
@RestController
public class FileToolController {
    public ResponseEntity<Map<String, Object>> upload(@RequestParam("file") MultipartFile file) {
        if(file.isEmpty()){
            return new ResponseEntity<>(Response.responseJson(1,"没有发现文件"), HttpStatus.BAD_REQUEST);
            return;

        }
        Attachment attachment= AttachmentService.getService().getAttachmentByFile(file);
        Map<String,Object> result= FileUtil.upload(file);
        if((int)result.get("code")==0){
            attachment.setUrl(result.get("url").toString());
            attachment.save();
            result.put("attachment",attachment);
            result.put("url", Constant.FILE_PATH+attachment.getUrl());
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<Page<Attachment>> getAttachmentList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "1") int pageSize){
        Page<Attachment> attachmentPage= AttachmentService.getService().getAttachmentPage(pageNum, pageSize);
        return new ResponseEntity<>(Response.responseJson(1,"请求成功",attachmentPage), HttpStatus.OK);
    }
    public ResponseEntity<Map<String, Object>> delAttachment(@RequestParam int id){
        Attachment.dao.deleteById(id);
        return new ResponseEntity<>(Response.responseJson(1,"删除成功"), HttpStatus.OK);
    }
}
