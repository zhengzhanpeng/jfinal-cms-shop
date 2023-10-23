package com.gz.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gz.common.model.*;
import com.gz.utils.JSONUtil;
import com.gz.utils.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gongzhen on 2018/6/2.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductAttributeRepository productAttributeRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public Page<Product> getProductPage(int pageNum,int pageSize,String condition){
        if(StringUtil.isEmpty(condition)){
            condition="1=1";
        }
        Page<Product> productPage= productRepository.findAll(PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "sortNum", "id")));
        for(Product product:productPage.getContent()){
            product.setCategory(getCategory(product.getProductCategoryId()).getName());
            product.setProductAttribute(getProductAttribute(product.getId()));
            System.out.println(product.getParameterValue());
/*
            product.setProductParameterValue(JSON.parseArray("[{\"key\":\"重量\",\"value\":\"300g\"}]"));
*/
            product.put("pictures",AttachmentService.getService().getProductImgList(product.getId()));
            product.put("thumbnail_temp",Constant.FILE_PATH+product.getThumbnail());
        }
        return productPage;
    }
    public List<Product> getProductList() {
        List<Product> productList= productRepository.findByIsMarketableTrueOrderBySortNumDescIdDesc();
        for(Product product:productList){
            product.setThumbnailTemp(Constant.FILE_PATH+product.getThumbnail());
        }
        return productList;
    }
    public Product getProduct(Integer id) {
        Product product=productRepository.findById(id).orElse(null);
        if(product!=null){
            product.setCategory(getCategory(product.getProductCategoryId()).getName());
            product.setProductAttribute(getProductAttribute(product.getId()));
            System.out.println(product.getParameterValue());
/*
            product.setProductParameterValue(JSON.parseArray("[{\"key\":\"重量\",\"value\":\"300g\"}]"));
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
        Query query = entityManager.createNativeQuery(sql, Product.class);
        List<Product> productList = query.getResultList();
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
        return productRepository.findByProductCategoryId(category).size();
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
        String notInIds = Stream.of(ids).map(String::valueOf).collect(Collectors.joining(","));
        Query query = entityManager.createNativeQuery("delete from tb_product_attribute_value where productId=?1 and id not in ("+notInIds+")");
        query.setParameter(1, productId);
        return query.executeUpdate();
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

public Map<String, Object>  getSpecification(int productId){
    List<Attribute> attributeList= ProductService.getService().getAttribute();
    List<ProductAttributeValue> productAttributeValueList= getProductAttributeValuesBySKU(productId);
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
    return map;
}
}
