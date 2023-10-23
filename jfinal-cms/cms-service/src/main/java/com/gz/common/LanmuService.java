package com.gz.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gz.common.model.Lanmu;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gongzhen on 2017/7/12.
 */
@Service
public class LanmuService {

    @Autowired
    private LanmuRepository lanmuRepository;

    public List<Lanmu> getAllLanmuList(){
        return lanmuRepository.findByIsSystermNotOrderBySortNumAsc(1);
    }
    public List<Lanmu> getLanmuList(int roleId){
        return lanmuRepository.findByRoleIdOrderBySortNumAsc(roleId);
    }
    public  List<Map<String,Object> >  getLanmuMenus(int roleId){
        List<Lanmu> lList= Lanmu.dao.find("SELECT * FROM tb_lanmu where id in(SELECT lanmu_id from tb_role_lanmu where role_id=?) order by sort_num asc",roleId);
        List<Map<String,Object> > rows=new ArrayList<>();
        for(Lanmu lanmu:lList) {
            Map<String,Object> row=new HashMap<>();
            row.put("title",lanmu.getName());
            row.put("icon",lanmu.getFontIcon());
            row.put("index",lanmu.getRoute());
            rows.add(row);
        }
        return rows;
    }
    public List<Lanmu> getAllLanmu(int roleId){
        List<Lanmu> lanmus=new ArrayList<>();
        List<Lanmu> lanmu_groups=Lanmu.dao.find("SELECT * FROM tb_lanmu where id in (SELECT lanmu_id from tb_role_lanmu where up_levelId=0 and role_id=?) order by sort_num desc,id asc",roleId);
        for(Lanmu lanmu1:lanmu_groups){
            lanmus.add(lanmu1);
            List<Lanmu> lanmus_1_level=getLowLanmus(lanmu1.getId(),roleId);
            List<Map<String,Object>> first_llanmus=new ArrayList<>();
            if(lanmus_1_level!=null&&lanmus_1_level.size()>0){
                for(Lanmu lanmu2:lanmus_1_level){
                    lanmus.add(lanmu2);
                    List<Lanmu> secound_llanmus=getLowLanmus(lanmu2.getId(),0);
                    lanmus.addAll(secound_llanmus);
                }
            }
        }
        return lanmus;
    };
    public List<Lanmu> getLowLanmus(int lanmuid,int roleId){
        List<Lanmu> lanmu_groups=lanmuRepository.findByRoleIdAndIsSystermNotAndUpLevelIdOrderBySortNumDescIdAsc(roleId, 1, lanmuid);
        return lanmu_groups;
    }
    public List<Map<String, Object>> getMenu(int roleId){
        List<Map<String,Object>> menus=new ArrayList<>();
        List<Lanmu> lanmu_groups=lanmuRepository.findByRoleIdAndUpLevelIdOrderBySortNumDescIdAsc(roleId, 0);
        for(Lanmu lanmu1:lanmu_groups){
            Map<String,Object> temp1=new HashMap<>();
            temp1.put("title",lanmu1.getName());
            temp1.put("icon",lanmu1.getFontIcon()!=null?lanmu1.getFontIcon():"");
            temp1.put("index",lanmu1.getRoute());
            temp1.put("lanmuId",lanmu1.getId());
            List<Lanmu> lanmus_1_level=getLowLanmus(lanmu1.getId(),roleId    );
            List<Map<String,Object>> first_llanmus=new ArrayList<>();
            if(lanmus_1_level!=null&&lanmus_1_level.size()>0){
                for(Lanmu lanmu2:lanmus_1_level){
                    Map<String,Object> temp2=new HashMap<>();
                    temp2.put("title",lanmu2.getName());
                    temp2.put("icon",lanmu2.getFontIcon()!=null?lanmu2.getFontIcon():"");
                    temp2.put("index",lanmu2.getRoute());
                    temp2.put("lanmuId",lanmu2.getId());
                    first_llanmus.add(temp2);
                }
            }
            if(first_llanmus.size()>0)
            temp1.put("subs",first_llanmus);
            menus.add(temp1);
        }
        return menus;
    }

    public Lanmu getLanmu(Integer lanmuId) {
        return lanmuRepository.findById(lanmuId).orElse(null);
    }
}
