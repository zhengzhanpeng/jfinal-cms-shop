package com.gz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gz.common.ProductService;
import com.gz.common.model.*;
import com.gz.utils.Response;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gongzhen on 2018/6/2.
 */
public class IndexController extends Controller{
    public void index(){
            renderJson("index");
    }
    public void getAdList(){
        List<Ad> adList= ProductService.getService().getAdList();
        renderJson(Response.responseJson(0,"成功",adList));
    }
    public void getProductPage(){
        int pageNum=getParaToInt("pageNum",1);
        int pageSize=getParaToInt("pageSize",10);
        Page<Product> productPage= ProductService.getService().getProductPage(pageNum,pageSize,null);
        renderJson(Response.responseJson(0,"成功",productPage));
    }
    public void getCategorys(){
        renderJson(Response.responseJson(0,"成功",ProductService.getService().getCategoryList()));
    }
    public void getProduct(){
        Product product=ProductService.getService().getProduct(getParaToInt("id",0));
        renderJson(Response.responseJson(0,"成功",product));
    }
    /**
     * 获取商品格值组
     */
    public void getSpecification(){
        int productId=getParaToInt("productId",0);
        List<Attribute> attributeList= ProductService.getService().getAttribute();
        List<ProductAttributeValue> productAttributeValueList= ProductService.getService().getProductAttributeValuesBySKU(productId);
        List<Attribute> checkedAttributes=new ArrayList<>();
        for(Attribute attribute:attributeList){
            List<AttributeValue> attributeValueList=attribute.get("attributeValues");
            boolean flag=false;
            for(AttributeValue attributeValue:attributeValueList) {
                for (ProductAttributeValue productAttributeValue : productAttributeValueList) {
                    JSONArray jsonArray = productAttributeValue.get("attributeValues");
                    for(int i=0;i<jsonArray.size();i++){
                        JSONObject jsonObject= (JSONObject)jsonArray.get(i);
                        if(jsonObject.getIntValue("id")==attributeValue.getId().intValue()){
                            attributeValue.put("checked",true);
                            flag=true;
                        }
                    }
                }
            }
            if(flag){
                checkedAttributes.add(attribute);
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("checkedAttributes",checkedAttributes);
        map.put("specifications",productAttributeValueList);
        renderJson(Response.responseJson(0,"成功",map));
    }
}
