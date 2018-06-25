package com.gz.common;

import com.gz.common.model.Content;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

/**
 * Created by gongzhen on 2016/12/5.
 */
public class ContentService {
    private static ContentService service;

    private ContentService() {
    }

    public static ContentService getService() {
        if (service == null) {
            service = new ContentService();
        }
        return service;
    }
   public Page<Content> getPages(int pageNum, int pageSize, int lanmuId){
           return Content.dao.paginate(pageNum, pageSize, "select *", " from tb_content where  lanmu_id=? and gzDelete =0 order by sort_num desc,id desc", lanmuId);
   }
    public Page<Content> getPages(int pageNum, int pageSize, String where){
        return Content.dao.paginate(pageNum, pageSize, "select *", " from tb_content where  "+where+" gzDelete =0 order by sort_num desc,id desc");
    }
    public Content getOneContent(int lanmuId){
        return Content.dao.findFirst("select * from tb_content where  lanmu_id=?", lanmuId);
    }
    public Content getContent(int id){
        return Content.dao.findFirst("select * from tb_content where  id=?", id);
    }
    public List<Content> getContentList(int lanmuId){
        return Content.dao.find("select * from tb_content where  lanmu_id=? order by sort_num desc,id desc", lanmuId);
    }
}
