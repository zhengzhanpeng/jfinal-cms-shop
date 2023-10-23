package com.gz.controller;

import com.gz.common.*;
import com.gz.common.model.*;
import com.gz.utils.Response;
import com.gz.utils.StringUtil;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gongzhen on 2018/6/2.
 */
@RestController
public class IndexController {
    public ResponseEntity<Map<String, Object>> login(@RequestParam String account, @RequestParam String password){
        if(StringUtil.isEmpty(account)){
            return new ResponseEntity<>(Response.responseJson(1,"请输入用户名"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtil.isEmpty(password)){
            return new ResponseEntity<>(Response.responseJson(1,"请输入密码"), HttpStatus.BAD_REQUEST);
        }

        User user=UserService.getService().getUserByAccount(account);
        if(user!=null&&user.getPassword().equals(password)){
            setSessionAttr("user",user);
            user.setPassword("");
            user.put("role",UserService.getService().getRole(user.getId()));
            renderJson(Response.responseJson(0,"登录成功",user));
            return;
        }else{
            renderJson(Response.responseJson(3,"用户名或密码错误"));
            return;
        }
    }

    public void getMenu(){
        renderJson(Response.responseJson(0,"成功",LanmuService.getService().getMenu(1)));
    }
    public void getAllLanmu(){

        renderJson(Response.responseJson(0,"成功",LanmuService.getService().getAllLanmu(1)));
    }
    public void getLanmu(){
        int lanmuId=getParaToInt("lanmuId",-1);
        renderJson(Response.responseJson(0,"成功",LanmuService.getService().getLanmu(lanmuId)));
    }
    public void saveLanmu(){
        boolean flag=false;
        Lanmu lanmu=getModel(Lanmu.class,"");
        System.out.println(lanmu.getName());
        System.out.println(lanmu.getId());
        if(!checkRepeat("name",lanmu.getName(),lanmu.getId()!=null?lanmu.getId():0)){
			         return new ResponseEntity<>(Response.responseJson(1,"栏目名已存在",lanmu), HttpStatus.BAD_REQUEST);
			     }/*else if(!checkRepeat("route",lanmu.getRoute(),lanmu.getId()!=null?lanmu.getId():0)){
			data.put("msg","路由重复");
			return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
		}*/
        if(lanmu.getId()==null){
            Lanmu upLamu =Lanmu.dao.findById(lanmu.getUpLevelid());
            if(lanmu.getLanmuType()==null&&lanmu.getUpLevelid()==0){
                lanmu.setLanmuType(0);
            }else if(lanmu.getLanmuType()==null&&lanmu.getUpLevelid()!=0){
                lanmu.setLanmuType(1);
            }
            if(lanmu.getIsSysterm()!=null){
                lanmu.setIsSysterm(upLamu.getIsSysterm());
            }
            flag= lanmu.save();
            List<User> users= UserService.getService().getListByRole("administrator");
            for(User user:users){
                RoleLanmu roleLanmu=new RoleLanmu();
                roleLanmu.setLanmuId(lanmu.getId());
                roleLanmu.setRoleId(user.getId());
                roleLanmu.save();
            }
        }else
        {
            flag= lanmu.update();
        }
        lanmu=lanmu.findById(lanmu.getId());
        if(lanmu.getInt("is_single")!=2&&FieldService.getService().getFieldsByLmID(lanmu.getId()).size()<=0){
            List<Field> fields= FieldService.getService().getFieldsByLmID(1);
            for (Field field:fields){
                field.setId(null);
                field.setLanmuId(lanmu.getId());
                flag= field.save();
            }
        }else if(lanmu.getInt("is_single")==2&&FieldService.getService().getFieldsByLmID(lanmu.getId()).size()>0)
        {
            FieldService.getService().delFieldsByLmId(lanmu.getId());
        }
        if(flag==true) {
            return new ResponseEntity<>(Response.responseJson(0,"保存成功",lanmu), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Response.responseJson(1,"保存失败"), HttpStatus.BAD_REQUEST);
        }

    }
    public boolean checkRepeat(String key,String value,int lanmuId){
        List<Lanmu> result = Lanmu.dao.find("select * from tb_lanmu where " + key + " =?", value);
        if(result==null||result.size()<=0||(result.size()<2&&result.get(0).getId()==lanmuId)){
            return true;
        }else{
            return false;
        }
    }

    public void updateField(){
        Map<String,Object> data = new HashMap<>();
        Field field=getModel(Field.class,"");
        field.update();
        data.put("code",0);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    public void delLanmu(){
        Map<String,Object> data=new HashMap<>();
        data.put("code",1);
        Integer LanmuId=getParaToInt("lanmuId");
        if(LanmuId!=null){
            data.put("code",0);
            Lanmu.dao.deleteById(LanmuId);
        }else{
            data.put("msg","参数不能为空");
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    public void lanmuSort(){
        Map<String,Object> data=new HashMap<>();
        data.put("code",1);
        Integer ids[]=getParaValuesToInt("ids");
        Integer sort_nums[]=getParaValuesToInt("sort_nums");
        if(ids!=null&&ids.length>0){
            for(int i=0;i<ids.length;i++){
                Lanmu bean=Lanmu.dao.findById(ids[i]);
                if(sort_nums.length>i&&sort_nums[i]!=null){
                    bean.setSortNum(sort_nums[i]);
                    bean.update();
                }
            }
            data.put("code",0);
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    public void fieldSort(){
        Map<String,Object> data=new HashMap<>();
        data.put("code",1);
        Integer ids[]=getParaValuesToInt("ids");
        Integer sort_nums[]=getParaValuesToInt("sort_nums");
        if(ids!=null&&ids.length>0){
            for(int i=0;i<ids.length;i++){
                Field bean=Field.dao.findById(ids[i]);
                if(sort_nums.length>i&&sort_nums[i]!=null){
                    bean.setSortNum(sort_nums[i]);
                    bean.update();
                }
            }
            data.put("code",0);
        }
        renderJson(data);
    }
    /**
     * 所有字段
     */
    public void getField(){
        return new ResponseEntity<>(Response.responseJson(0,"请求成功",FieldService.getService().getFieldsByLmID(lanmuId)), HttpStatus.OK);
    }

    /**
     * 可编辑的字段
     */
    public void getFieldsByLmIDEditor(){
        renderJson(Response.responseJson(0,"请求成功",FieldService.getService().getFieldsByLmIDEditor(getParaToInt("lanmuId",-1))));

    }

    public void getContent(){
        int lanmuId=getParaToInt("lanmuId",0);
        Lanmu lanmu= LanmuService.getService().getLanmu(lanmuId);
        Map<String,Object> data=new HashMap<>();
        data.put("code",1);
        if(lanmu.getIsSingle()==1){
            Content content= ContentService.getService().getOneContent(lanmuId);
            List<Attachment> pictures = AttachmentService.getService().getList(content.getId());
            for (Attachment attachment : pictures) {
                attachment.setUrl(Constant.FILE_PATH + attachment.getUrl());
            }
            if(!StringUtil.isEmpty(content.getThumbnail()))
            content.put("thumbnail_temp", Constant.FILE_PATH + content.getThumbnail());
            content.put("pictures", pictures);
            data.put("content",content);
        }else{
            List<Content> contents=ContentService.getService().getContentList(lanmuId);
            for(Content content:contents){
                List<Attachment> pictures = AttachmentService.getService().getList(content.getId());
                for (Attachment attachment : pictures) {
                    attachment.setUrl(Constant.FILE_PATH + attachment.getUrl());
                }
                content.put("pictures", pictures);
                if(!StringUtil.isEmpty(content.getThumbnail()))
                content.put("thumbnail_temp", Constant.FILE_PATH + content.getThumbnail());
            }
            data.put("contents",contents);
        }
        data.put("lanmu",lanmu);
        return new ResponseEntity<>(Response.responseJson(0,"请求成功",data), HttpStatus.OK);
    }
    public void getOneContent(){
        int lanmuId=getParaToInt("lanmuId",0);
        int id=getParaToInt("id",0);
        Lanmu lanmu= LanmuService.getService().getLanmu(lanmuId);
        Map<String,Object> data=new HashMap<>();
        Content content=ContentService.getService().getContent(id);
        if(content!=null) {
            List<Attachment> pictures = AttachmentService.getService().getList(content.getId());
            for (Attachment attachment : pictures) {
                attachment.setUrl(Constant.FILE_PATH + attachment.getUrl());
            }
            content.put("pictures", pictures);
            if(!StringUtil.isEmpty(content.getThumbnail()))
                content.put("thumbnail_temp", Constant.FILE_PATH + content.getThumbnail());
            data.put("content", content);
        }
        data.put("lanmu",lanmu);
        renderJson(Response.responseJson(0,"请求成功",data));
    }
    public void updateContent(){
        System.out.println(getPara("pictures[0][id]"));
        Map<String,Object> data=new HashMap<>();
        data.put("code",1);
        int lanmuId=getParaToInt("lanmuId",0);
        Lanmu lanmu= LanmuService.getService().getLanmu(lanmuId);
        if(lanmuId==0){
            return new ResponseEntity<>(data, HttpStatus.OK);
            return;
        }
        Content content=getBean(Content.class,"",false);
        content.setUpdatetime(new Date());
        if(content.getId()==null||content.getId()==0){
            content.setLanmuId(lanmuId);
            content.setCreatetime(new Date());
            content.save();
        }else{
            content.update();
        }
        int i=0;
        while (true){
            int id=getParaToInt("pictures["+i+"][id]",0);
            i++;
            if(id==0){
                break;
            }
            ContentAttachment contentAttachment= AttachmentService.getService().getContentAttachment(content.getId(),id);
            if(contentAttachment==null){
                contentAttachment=new ContentAttachment();
                contentAttachment.setAttachmentId(id);
                contentAttachment.setContentId(content.getId());
                contentAttachment.save();
            }
        }
        data.put("content",content);
        data.put("code",0);
        renderJson(data);
    }
    public void sortContent(){
     int id=getParaToInt("id",0);
        int sort_num=getParaToInt("sort_num",0);
        Content content=ContentService.getService().getContent(id);
        if(content==null){
            renderJson(Response.responseJson(1,"参数错误"));
        }else{
            content.setSortNum(sort_num);
            content.update();
            return new ResponseEntity<>(Response.responseJson(0,"排序成功"), HttpStatus.OK);
        }
    }
    public void delContent(){
        Integer[] ids=getParaValuesToInt("id[]");
        if(ids!=null&&ids.length>0){
            for(int id:ids) {
                Content.dao.deleteById(id);
            }
            return new ResponseEntity<>(Response.responseJson(0,"删除成功"), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Response.responseJson(1,"所选记录为空"), HttpStatus.BAD_REQUEST);
        }
    }
    public void delAttachment(){
        int attachmentId=getParaToInt("attachmentId",0);
        int contentId=getParaToInt("contentId",0);
        ContentAttachment contentAttachment=AttachmentService.getService().getContentAttachment(contentId,attachmentId);
        if(contentAttachment!=null){
            contentAttachment.delete();
            return new ResponseEntity<>(Response.responseJson(0,"删除成功"), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Response.responseJson(1,"此文件不存在"), HttpStatus.BAD_REQUEST);
        }
    }
}
