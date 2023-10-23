package com.gz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.gz.common.ContentService;
import com.gz.common.ProductService;
import com.gz.common.model.*;
import com.gz.utils.JSONUtil;
import com.gz.utils.Response;
import com.gz.utils.StringUtil;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.jfinal.json.Json;
import com.jfinal.plugin.activerecord.Page;

import java.util.*;

/**
 * Created by gongzhen on 2018/6/9.
 */
@RestController
public class ProductController {
    public ResponseEntity<Map<String, Object>> getProductPage(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize){
        Page<Product> productPage= ProductService.getService().getProductPage(pageNum,pageSize,null);
        return new ResponseEntity<>(Response.responseJson(0,"成功",productPage), HttpStatus.OK);
    }
    public void getProduct(){
        Product product=ProductService.getService().getProduct(getParaToInt("id",0));
        renderJson(Response.responseJson(0,"成功",product));
    }
    public void getProductList(){
        List<Product> productList=ProductService.getService().getProductList();
        renderJson(Response.responseJson(0,"成功",productList));
    }
    public void getAttribute(){
        renderJson(Response.responseJson(0,"成功",ProductService.getService().getAttribute()));
    }

    public void updateAttribute(){
        Attribute attribute=getBean(Attribute.class,"");
        if(attribute.getId()!=null){
            attribute.update();
        }else{
            attribute.save();
        }
        renderJson(Response.responseJson(0,"成功",attribute));
    }
    public void delAttribute(){
        Attribute attribute=ProductService.getService().getAttribute(getParaToInt("id",0));
        if(attribute!=null){
            attribute.delete();
            renderJson(Response.responseJson(0,"成功"));
        }else{
            renderJson(Response.responseJson(1,"参数错误"));
        }

    }
    public void addAttributeValue(){
        AttributeValue attributeValue=getBean(AttributeValue.class,"");
        if(attributeValue.getId()!=null){
            attributeValue.update();
        }else{
            attributeValue.save();
        }
        renderJson(Response.responseJson(0,"成功",attributeValue));

    }
    public void delAttributeValue(){
        AttributeValue attributeValue=ProductService.getService().getAttributeValue(getParaToInt("id",0));
        if(attributeValue!=null){
            attributeValue.delete();
            renderJson(Response.responseJson(0,"成功"));
        }else{
            renderJson(Response.responseJson(1,"参数错误"));
        }

    }
    public void getCategory(){
        renderJson(Response.responseJson(0,"成功",ProductService.getService().getCategoryList()));
    }
    public void getCategorys(){
        renderJson(Response.responseJson(0,"成功",ProductService.getService().getCategorys()));
    }
    public void updateCategory(){
        Category category=getBean(Category.class,"");
        if(category.getId()!=null){
            category.update();
        }else{
            category.save();
        }
        renderJson(Response.responseJson(0,"成功",category));
    }
    public void sortCategory(){
        int id=getParaToInt("id",0);
        int sort_num=getParaToInt("sort_num",0);
        Category category= ProductService.getService().getCategory(id);
        if(category==null){
            renderJson(Response.responseJson(1,"参数错误"));
        }else{
            category.setSortNum(sort_num);
            category.update();
            renderJson(Response.responseJson(0,"排序成功"));
        }
    }
    public void delCategory(){
        Category category=ProductService.getService().getCategory(getParaToInt("id",0));
        if(category!=null){
            category.delete();
            renderJson(Response.responseJson(0,"成功",category));
        }else{
            renderJson(Response.responseJson(1,"参数错误",category));
        }

    }
    public void updateProduct(){
        Product product=getBean(Product.class,"");
        product.setModifyDate(new Date());
        if(product.getId()==null){
            product.setCreateDate(new Date());
            product.save();
        }else{
            product.update();
        }
        renderJson(Response.responseJson(0,"成功",product));
    }

    /**
     * 保存规商品格值组
     */
    public void saveSpecification(){
        JSONArray specifications= JSON.parseArray(getPara("specifications"));
        String ids[]=new String[specifications.size()];
        int productId=0;
        float minPrice=Float.MAX_VALUE;
        for(int i=0;i<specifications.size();i++){
            JSONObject jsonObject= (JSONObject)specifications.get(i);
            ProductAttributeValue productAttributeValue= ProductService.getService().getProductAttributeValueBySKU(jsonObject.getString("SKU"),jsonObject.getInteger("productId"));
            if(productAttributeValue==null){
                productAttributeValue=new ProductAttributeValue();
                productAttributeValue.setAttributeValues(jsonObject.getString("attributeValues"));
                productAttributeValue.setSKU(jsonObject.getString("SKU"));
                productAttributeValue.setProductId(jsonObject.getInteger("productId"));
            }
            productAttributeValue.setStock(jsonObject.getInteger("stock")==null?0:jsonObject.getInteger("stock"));
            productAttributeValue.setPrice(jsonObject.getFloat("price")==null?0:jsonObject.getFloat("price"));
            productAttributeValue.setThumbnail(jsonObject.getString("thumbnail"));
            if(productAttributeValue.getId()==null){
                productAttributeValue.save();
            }else{
                productAttributeValue.update();
            }
            ids[i]=productAttributeValue.getId().toString();
            productId=productAttributeValue.getProductId();
            if(minPrice>productAttributeValue.getPrice()&&productAttributeValue.getPrice()>0){
                minPrice=productAttributeValue.getPrice();
            }
        }
        Product product=ProductService.getService().getProduct(productId);
        product.setPrice(minPrice);
        product.update();
        ProductService.getService().delProductAttributeValueBySKU(ids,productId);
        renderJson(Response.responseJson(0,"成功"));
    }
    /**
     * 获取商品格值组
     */
    public void getSpecification(){
        int productId=getParaToInt("productId",0);
        Map<String,Object> result=  ProductService.getService().getSpecification(productId);
        renderJson(Response.responseJson(0,"成功",result));
    }

    public void getPromotionList(){
        List<Promotion> promotionList=ProductService.getService().getPromotionList();
        renderJson(Response.responseJson(0,"成功",promotionList));
    }
    public void getPromotion(){
        Promotion promotion=Promotion.dao.findById(getParaToInt("id",0));
        renderJson(Response.responseJson(0,"成功",promotion));
    }
    public void savePromotion(){
        Promotion promotion=getBean(Promotion.class,"");
        if(promotion.getId()!=null){
            promotion.update();
        }else{
            promotion.setCreatDate(new Date());
            promotion.save();
        }
        renderJson(Response.responseJson(0,"成功",promotion));
    }
    public void delPromotion(){
        Promotion promotion=Promotion.dao.findById(getParaToInt("id",0));
        promotion.delete();
        renderJson(Response.responseJson(0,"删除成功"));
    }
    public void getProductNoPromotionList(){
        Promotion promotion=Promotion.dao.findById(getParaToInt("promotionId",0));
        if(promotion==null){
            renderJson(Response.responseJson(1,"promotion参数错误"));
        }else{
            List<Product> productList=ProductService.getService().getProductNoPromotionList(promotion);
            renderJson(Response.responseJson(0,"请求成功",productList));
        }
    }
    public void getProductPromotionList(){
        int promotionId=getParaToInt("promotionId",0);
        List<ProductPromotion> productPromotionList=ProductService.getService().getProductPromotionList(promotionId);
        renderJson(Response.responseJson(0,"保存成功",productPromotionList));
    }
    public void saveDiscountPromotion(){
        String data=getPara("data");
        int promotionId=getParaToInt("promotionId",0);
        if(StringUtil.isEmpty(data)){
            renderJson(Response.responseJson(1,"promotion参数错误"));
        }else{
            JSONArray jsonArray=JSON.parseArray(data);
            for(int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                ProductPromotion productPromotion=ProductService.getService().getProductPromotion(jsonObject.getInteger("productId"),promotionId);
                if(productPromotion!=null){
                    productPromotion.setDiscount(jsonObject.getFloat("discount"));
                    productPromotion.setReduceMoney(jsonObject.getFloat("reduceMoney"));
                    productPromotion.setLastPrice(jsonObject.getFloat("lastPrice"));
                    productPromotion.update();
                }else if(jsonObject.getFloat("lastPrice")!=null){
                    productPromotion=new ProductPromotion();
                    productPromotion.setDiscount(jsonObject.getFloat("discount"));
                    productPromotion.setReduceMoney(jsonObject.getFloat("reduceMoney"));
                    productPromotion.setLastPrice(jsonObject.getFloat("lastPrice"));
                    productPromotion.setProductId(jsonObject.getInteger("productId"));
                    productPromotion.setPromotionId(promotionId);
                    productPromotion.save();
                }
            }
            renderJson(Response.responseJson(0,"保存成功"));
        }
    }
    public void saveReducePromotion(){
        String data=getPara("data");
        int promotionId=getParaToInt("promotionId",0);
        if(StringUtil.isEmpty(data)){
            renderJson(Response.responseJson(1,"promotion参数错误"));
        }else{
            JSONArray jsonArray=JSON.parseArray(data);
            for(int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                ProductPromotion productPromotion=ProductService.getService().getProductPromotion(jsonObject.getInteger("productId"),promotionId);
              if(productPromotion==null){
                    productPromotion=new ProductPromotion();
                    productPromotion.setProductId(jsonObject.getInteger("productId"));
                    productPromotion.setPromotionId(promotionId);
                    productPromotion.save();
                }
            }
            renderJson(Response.responseJson(0,"保存成功"));
        }
    }
    public void delDiscountPromotion(){
        int promotionId=getParaToInt("promotionId",0);
        int productId=getParaToInt("productId",0);
        if(promotionId==0||productId==0){
            renderJson(Response.responseJson(1,"promotion参数错误"));
        }else{
            ProductPromotion productPromotion=ProductService.getService().getProductPromotion(productId,promotionId);
            if(productPromotion!=null)
                productPromotion.delete();
            renderJson(Response.responseJson(0,"取消成功"));
        }
    }
    public void getCouponList(){
        List<Coupon> couponList=ProductService.getService().getCouponList();
        renderJson(Response.responseJson(0,"成功",couponList));
    }
    public void saveCoupon(){
       Coupon coupon=getBean(Coupon.class,"");
        if(coupon.getId()!=null){
            coupon.update();
        }else{
            coupon.save();
        }
        renderJson(Response.responseJson(0,"成功",coupon));
    }
    public void delCoupon(){
        Coupon coupon=Coupon.dao.findById(getParaToInt("id",0));
        coupon.delete();
        renderJson(Response.responseJson(0,"删除成功"));
    }
    public void getAdList(){
        List<Ad> adList=ProductService.getService().getAdList();
        renderJson(Response.responseJson(0,"成功",adList));
    }
    public void saveAd(){
        Ad ad=getBean(Ad.class,"");
        if(ad.getId()==null){
            ad.save();
        }else{
            ad.setSortNum(getParaToInt("sort_num"));
            ad.update();
        }
        renderJson(Response.responseJson(0,"成功",ad));
    }
    public void delAd(){
        Ad ad=Ad.dao.findById(getParaToInt("id",0));
        ad.delete();
        renderJson(Response.responseJson(0,"删除成功"));
    }
}
