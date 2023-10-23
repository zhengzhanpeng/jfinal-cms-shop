package com.gz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gz.common.ProductService;
import com.gz.common.model.*;
import com.gz.utils.Response;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.jfinal.plugin.activerecord.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gongzhen on 2018/6/2.
 */
@RestController
public class IndexController {
    public String index(){
            return "index";
    }
    public String getAdList(){
        List<Ad> adList= ProductService.getService().getAdList();
        return Response.responseJson(0,"成功",adList);
    }
    public String getProductPage(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize){
        Page<Product> productPage= ProductService.getService().getProductPage(pageNum,pageSize,null);
        return Response.responseJson(0,"成功",productPage);
    }
    public String getCategorys(){
        return Response.responseJson(0,"成功",ProductService.getService().getCategoryList());
    }
    public String getProduct(@RequestParam(defaultValue = "0") int id){
        Product product=ProductService.getService().getProduct(id);
        return Response.responseJson(0,"成功",product);
    }
    /**
     * 获取商品格值组
     */
    public String getSpecification(@RequestParam(defaultValue = "0") int productId){
        Map<String,Object> result=  ProductService.getService().getSpecification(productId);
        return Response.responseJson(0,"成功",result);
    }


}
