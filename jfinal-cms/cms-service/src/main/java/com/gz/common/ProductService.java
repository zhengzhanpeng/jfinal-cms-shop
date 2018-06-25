package com.gz.common;

import com.alibaba.fastjson.JSONArray;
import com.gz.common.model.*;
import com.gz.utils.JSONUtil;
import com.gz.utils.StringUtil;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

/**
 * Created by gongzhen on 2018/6/2.
 */
public class ProductService {
    private static ProductService service;

    private ProductService() {
    }

    public static ProductService getService() {
        if (service == null) {
            service = new ProductService();
        }
        return service;
    }
    public Page<Product> getProductPage(int pageNum,int pageSize,String condition){
        if(StringUtil.isEmpty(condition)){
            condition="1=1";
        }
        Page<Product> productPage=Product.dao.paginate(pageNum,pageSize,"select * ","from tb_product where "+ condition+" order by sort_num desc,id desc");
        for(Product product:productPage.getList()){
            product.put("category",getCategory(product.getProductCategoryId()).getName());
            product.put("productAttribute",getProductAttribute(product.getId()));
            System.out.println(product.getParameterValue());
/*
            product.put("productParameterValue", JSONUtil.parseToJsonArray("[{\"key\":\"重量\",\"value\":\"300g\"}]"));
*/
            product.put("pictures",AttachmentService.getService().getProductImgList(product.getId()));
            product.put("thumbnail_temp",Constant.FILE_PATH+product.getThumbnail());
        }
        return productPage;
    }
    public List<Product> getProductList() {
        List<Product> productList= Product.dao.find("select * from tb_product where isMarketable=1 order by sort_num desc,id desc");
        for(Product product:productList){
            product.put("thumbnail_temp",Constant.FILE_PATH+product.getThumbnail());
        }
        return productList;
    }
    public Product getProduct(Integer id) {
        Product product=Product.dao.findById(id);
        if(product!=null){
            product.put("category",getCategory(product.getProductCategoryId()).getName());
            product.put("productAttribute",getProductAttribute(product.getId()));
            System.out.println(product.getParameterValue());
/*
            product.put("productParameterValue", JSONUtil.parseToJsonArray("[{\"key\":\"重量\",\"value\":\"300g\"}]"));
*/
            product.put("pictures",AttachmentService.getService().getProductImgList(product.getId()));
            product.put("thumbnail_temp",Constant.FILE_PATH+product.getThumbnail());
        }
        return product;
    }

    public List<Product> getProductNoPromotionList(Promotion promotion) {
        String sql="SELECT *,tb_product.id as productId from tb_product where isMarketable=1 and id not in\n" +
                "(SELECT productId from tb_product_promotion WHERE promotionId in\n" +
                "(SELECT id from tb_promotion where   (startDate>='"+promotion.getEndDate()+"' and endDate<='"+promotion.getEndDate()+"')or (startDate>='"+promotion.getStartDate()+"' and endDate>='"+promotion.getStartDate()+"') and type="+promotion.getType()+"))";
        System.out.println(sql);
        List<Product> productList=Product.dao.find(sql);
        for (Product product:productList){
            if(product!=null){
                product.put("thumbnail_temp",Constant.FILE_PATH+product.getThumbnail());
            }
        }
        return productList;
    }

    public Category getCategory(int categoryId) {
        return Category.dao.findById(categoryId);
    }
    /**
     *返回完整类目信息，包括商品数量
     * @param
     * @return List<Category>
     */
    public List<Category> getCategoryList() {
        List<Category> categoryList= Category.dao.find("SELECT * from  tb_category order by sort_num desc,id desc");
        for(Category category:categoryList){
            category.put("count",getCategoryProductCount(category.getId()));
            category.put("icon_temp",Constant.FILE_PATH+category.getIcon());
        }
        return categoryList;
    }


    public int getCategoryProductCount(int category){
        return Product.dao.find("SELECT id from tb_product WHERE productCategoryId=?",category).size();
    }

    /**
     * 返回简单类目，商品编辑选择
     * @return
     */
    public List<Category> getCategorys() {
        return Category.dao.find("SELECT * from  tb_category order by level asc, sort_num desc,id desc");
    }
    public List<Attribute> getAttribute() {
        List<Attribute> attributeList= Attribute.dao.find("SELECT * from tb_attribute");
        for(Attribute attribute:attributeList){
            attribute.put("attributeValues",getAttributeValueList(attribute.getId()));
        }
        return attributeList;
    }
    public List<AttributeValue> getAttributeValueList(int attributeId) {
        return AttributeValue.dao.find("SELECT * from tb_attribute_value where attributeId=?",attributeId);
    }
    public Attribute getAttribute(int id) {
        return Attribute.dao.findById(id);
    }
    public AttributeValue getAttributeValue(int id) {
        return AttributeValue.dao.findById(id);
    }
    public List<ProductAttribute> getProductAttribute(int productId){
        return ProductAttribute.dao.find("SELECT tb_product_attribute.*,name from (tb_product_attribute  LEFT JOIN tb_attribute_value  on tb_attribute_value.id =tb_product_attribute.attributeValueId) LEFT JOIN tb_attribute on tb_attribute.id=tb_attribute_value.attributeId where productId=?",productId);
    }
    public ProductAttributeValue getProductAttributeValueBySKU(String SKU,Integer productId){
        return ProductAttributeValue.dao.findFirst("SELECT * from tb_product_attribute_value where SKU=? and productId=?",SKU,productId);
    }

    public int delProductAttributeValueBySKU(String[] ids,Integer productId){
        return Db.update("delete from tb_product_attribute_value where productId=? and id not in ("+StrKit.join(ids,",")+")",productId);
    }
    public List<ProductAttributeValue> getProductAttributeValuesBySKU(Integer productId){
        List<ProductAttributeValue> productAttributeValueList= ProductAttributeValue.dao.find("SELECT * from tb_product_attribute_value where productId=?",productId);
        for(ProductAttributeValue productAttributeValue:productAttributeValueList){
            productAttributeValue.put("attributeValues",JSONArray.parseArray(productAttributeValue.getAttributeValues()));
            if(!StringUtil.isEmpty(productAttributeValue.getThumbnail()))
            productAttributeValue.put("thumbnail_temp",Constant.FILE_PATH+productAttributeValue.getThumbnail());
        }
        return productAttributeValueList;
    }

    public List<Promotion> getPromotionList() {
        return Promotion.dao.find("SELECT * from tb_promotion ORDER BY id DESC");
    }
    public ProductPromotion getProductPromotion(int productId,int promotionId) {
        return ProductPromotion.dao.findFirst("SELECT * from tb_product_promotion where productId=? and promotionId=?",productId,promotionId);
    }
    public List<ProductPromotion> getProductPromotionList(int promotionId){
        List<ProductPromotion> productPromotionList= ProductPromotion.dao.find("SELECT * from tb_product right JOIN tb_product_promotion on tb_product.id=tb_product_promotion.productId where tb_product_promotion.promotionId=?",promotionId);
        for(ProductPromotion productPromotion:productPromotionList){
            productPromotion.put("thumbnail_temp",Constant.FILE_PATH+productPromotion.getStr("thumbnail"));
        }
        return productPromotionList;
    }
    public List<Coupon> getCouponList(){
        List<Coupon> couponList=Coupon.dao.find("select * from tb_coupon");
        return couponList;
    }

    public List<Ad> getAdList() {
        List<Ad> adList=Ad.dao.find("select * from tb_ad");
        for(Ad ad:adList){
            ad.put("thumbnail_temp",Constant.FILE_PATH+ad.getThumbnail());
            if(ad.getProductId()!=null)
                ad.put("product",ProductService.getService().getProduct(ad.getProductId()));
        }
        return adList;
    }


}
