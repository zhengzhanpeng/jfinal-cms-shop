package com.gz.common;



import com.gz.common.model.Field;
import com.jfinal.plugin.activerecord.Db;

import java.io.File;
import java.util.List;

/**
 * Created by gongzhen on 2016/12/2.
 */
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class FieldService {

    @Autowired
    private FieldRepository fieldRepository;
    public List<Field> getFieldsByLmID(Integer lanmuId){
        if(lanmuId==null) {
            lanmuId=0;
        }
        return fieldRepository.findByLanmuIdOrderBySortNumAsc(lanmuId);
    }
    public List<Field> getFieldsByLmIDEditor(Integer lanmuId){
        if(lanmuId!=null) {
            return fieldRepository.findByLanmuOptionNotAndLanmuIdOrderBySortNumAsc(0, lanmuId);
        }
        return null;
    }
    public void delFieldsByLmId(Integer lanmuId){
        fieldRepository.deleteByLanmuId(lanmuId);
    }
}
