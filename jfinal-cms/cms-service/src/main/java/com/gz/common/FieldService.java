package com.gz.common;



import com.gz.common.model.Field;
import com.jfinal.plugin.activerecord.Db;

import java.io.File;
import java.util.List;

/**
 * Created by gongzhen on 2016/12/2.
 */
public class FieldService {
    private static FieldService service;

    private FieldService() {
    }

    public static FieldService getService() {
        if (service == null) {
            service = new FieldService();
        }
        return service;
    }
    public List<Field> getFieldsByLmID(Integer lanmuId){
        if(lanmuId==null) {
            lanmuId=0;
        }
        return Field.dao.find("select * from tb_field where   lanmu_id=? order by sort_num asc", lanmuId);
    }
    public List<Field> getFieldsByLmIDEditor(Integer lanmuId){
        if(lanmuId!=null) {
            return Field.dao.find("select * from tb_field where  lanmu_option!=0  and lanmu_id=? order by sort_num asc", lanmuId);
        }
        return null;
    }
    public int delFieldsByLmId(Integer lanmuId){
        return Db.delete("delete from tb_field where lanmu_id=?",lanmuId);
    }
}
