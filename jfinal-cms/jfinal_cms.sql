/*
Navicat MySQL Data Transfer

Source Server         : 阿里云121.42.239.58
Source Server Version : 50722
Source Host           : 121.42.239.58:3306
Source Database       : jfinal_cms

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-06-25 16:55:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_ad
-- ----------------------------
DROP TABLE IF EXISTS `tb_ad`;
CREATE TABLE `tb_ad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productId` int(11) DEFAULT NULL,
  `thumbnail` varchar(255) NOT NULL,
  `status` smallint(6) NOT NULL DEFAULT '0',
  `sort_num` int(11) NOT NULL DEFAULT '1',
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `productId` (`productId`),
  CONSTRAINT `tb_ad_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `tb_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_ad
-- ----------------------------
INSERT INTO `tb_ad` VALUES ('3', '1', 'upload/img/u/2018/06/19/OF6C.jpg', '0', '4', '618电商节');

-- ----------------------------
-- Table structure for tb_attachment
-- ----------------------------
DROP TABLE IF EXISTS `tb_attachment`;
CREATE TABLE `tb_attachment` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `name` text COMMENT '文件名',
  `user_id` int(20) DEFAULT NULL COMMENT '上传附件的用户ID',
  `url` varchar(512) NOT NULL COMMENT '路径',
  `mime_type` varchar(128) DEFAULT NULL COMMENT 'mime',
  `suffix` varchar(32) DEFAULT NULL COMMENT '附件的后缀',
  `created` datetime DEFAULT NULL COMMENT '上传时间',
  `size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `created` (`created`),
  KEY `suffix` (`suffix`),
  KEY `mime_type` (`mime_type`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8mb4 COMMENT='附件表，用于保存用户上传的附件内容。';

-- ----------------------------
-- Records of tb_attachment
-- ----------------------------
INSERT INTO `tb_attachment` VALUES ('90', '77ed36f4b18679ce54d4cebda306117e.jpg', null, 'upload/img/u/2018/06/19/IDKX.jpg', 'image/jpeg', 'jpg', '2018-06-19 16:54:17', '123501297664');
INSERT INTO `tb_attachment` VALUES ('91', 'd87a209e48adf15bcba95f8da1844881.png', null, 'upload/img/u/2018/06/19/QV7X.png', 'image/png', 'png', '2018-06-19 17:29:23', '123501297664');
INSERT INTO `tb_attachment` VALUES ('92', '802dfba448d932fbdcf1064316665a03.jpg', null, 'upload/img/u/2018/06/19/RMKX.jpg', 'image/jpeg', 'jpg', '2018-06-19 17:29:37', '123501297664');
INSERT INTO `tb_attachment` VALUES ('93', '654e497b4ed3e95a66fcd0cd2b3b4f1b.jpg', null, 'upload/img/u/2018/06/19/8H2U.jpg', 'image/jpeg', 'jpg', '2018-06-19 18:54:18', '123501297664');
INSERT INTO `tb_attachment` VALUES ('97', 'c5f874e190973b243e05d4ce156d2c4c.jpg', null, 'upload/img/u/2018/06/19/8BAZ.jpg', 'image/jpeg', 'jpg', '2018-06-19 19:00:23', '123501297664');
INSERT INTO `tb_attachment` VALUES ('100', '149da869932cb185b2827924e529462d.jpg', null, 'upload/img/u/2018/06/19/OF6C.jpg', 'image/jpeg', 'jpg', '2018-06-19 20:17:18', '123501297664');
INSERT INTO `tb_attachment` VALUES ('102', 'e0936f4db7bd69639239b48f14d61a60.jpg', null, 'upload/img/u/2018/06/19/N4ZT.jpg', 'image/jpeg', 'jpg', '2018-06-19 20:20:46', '123501297664');
INSERT INTO `tb_attachment` VALUES ('103', '2c92f0cb8c4dcc2aefe9b28cf0ec4701.jpg', null, 'upload/img/u/2018/06/19/ESKF.jpg', 'image/jpeg', 'jpg', '2018-06-19 21:03:37', '123501297664');
INSERT INTO `tb_attachment` VALUES ('104', '2bb055ab3186e99a03b614553fd031bf.jpg', null, 'upload/img/u/2018/06/19/7PF4.jpg', 'image/jpeg', 'jpg', '2018-06-19 21:08:12', '123501297664');
INSERT INTO `tb_attachment` VALUES ('105', '802dfba448d932fbdcf1064316665a03.jpg', null, 'upload/img/u/2018/06/19/KSD3.jpg', 'image/jpeg', 'jpg', '2018-06-19 21:23:26', '123501297664');
INSERT INTO `tb_attachment` VALUES ('106', 'c7fca039799d08aa6760a4f77dac5545.jpg', null, 'upload/img/u/2018/06/19/UXHP.jpg', 'image/jpeg', 'jpg', '2018-06-19 21:44:42', '123501297664');
INSERT INTO `tb_attachment` VALUES ('107', 'a7afe86ef81e62728edad6a5de8bde74.jpg', null, 'upload/img/u/2018/06/19/BTRW.jpg', 'image/jpeg', 'jpg', '2018-06-19 21:50:38', '123501297664');
INSERT INTO `tb_attachment` VALUES ('108', '7baa1297a05e9c857ab2a907a2e23905.jpg', null, 'upload/img/u/2018/06/19/OGD6.jpg', 'image/jpeg', 'jpg', '2018-06-19 22:02:57', '123501297664');
INSERT INTO `tb_attachment` VALUES ('109', '45fa47bfaa7ffa355eaa760e06ec52c5.jpg', null, 'upload/img/u/2018/06/20/S4P5.jpg', 'image/jpeg', 'jpg', '2018-06-20 17:16:44', '123501297664');
INSERT INTO `tb_attachment` VALUES ('110', '77ed36f4b18679ce54d4cebda306117e.jpg', null, 'upload/img/u/2018/06/20/RN24.jpg', 'image/jpeg', 'jpg', '2018-06-20 17:20:25', '123501297664');
INSERT INTO `tb_attachment` VALUES ('111', '509621b17d1c416cb9cc75998afd6049.jpg', null, 'upload/img/u/2018/06/20/3ZF2.jpg', 'image/jpeg', 'jpg', '2018-06-20 17:21:18', '123501297664');
INSERT INTO `tb_attachment` VALUES ('112', '9e02a875670f10fc0407846a04af687a.jpg', null, 'upload/img/u/2018/06/20/HSJ4.jpg', 'image/jpeg', 'jpg', '2018-06-20 17:22:02', '123501297664');
INSERT INTO `tb_attachment` VALUES ('113', 'd82522f2784ea105b3f33bbf2f20acef.jpg', null, 'upload/img/u/2018/06/20/H8XF.jpg', 'image/jpeg', 'jpg', '2018-06-20 17:24:21', '123501297664');
INSERT INTO `tb_attachment` VALUES ('114', '45b0770383a77411deae6fea9b1ff40c.jpg', null, 'upload/img/u/2018/06/21/6Q7A.jpg', 'image/jpeg', 'jpg', '2018-06-21 17:00:26', '123501297664');
INSERT INTO `tb_attachment` VALUES ('115', 'c38cc10c2ef43cb47447143afb40f911.jpg', null, 'upload/img/u/2018/06/21/3DZG.jpg', 'image/jpeg', 'jpg', '2018-06-21 17:01:24', '123501297664');
INSERT INTO `tb_attachment` VALUES ('116', '187027a9a5415550375e03174f44b101.jpg', null, 'upload/img/u/2018/06/21/I64X.jpg', 'image/jpeg', 'jpg', '2018-06-21 17:06:02', '123501297664');
INSERT INTO `tb_attachment` VALUES ('117', 'd6e16e12d31ca5eaaf18ef8c8c6a3d82.png', null, 'upload/img/u/2018/06/21/4NO6.png', 'image/png', 'png', '2018-06-21 22:05:48', '123501297664');

-- ----------------------------
-- Table structure for tb_attribute
-- ----------------------------
DROP TABLE IF EXISTS `tb_attribute`;
CREATE TABLE `tb_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '参数名称，如颜色、型号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_attribute
-- ----------------------------
INSERT INTO `tb_attribute` VALUES ('2', '颜色分类');
INSERT INTO `tb_attribute` VALUES ('6', '尺码');
INSERT INTO `tb_attribute` VALUES ('8', '套餐');
INSERT INTO `tb_attribute` VALUES ('9', '机身颜色');
INSERT INTO `tb_attribute` VALUES ('10', '网络类型');
INSERT INTO `tb_attribute` VALUES ('11', '存储容量');
INSERT INTO `tb_attribute` VALUES ('12', '尺寸');

-- ----------------------------
-- Table structure for tb_attribute_value
-- ----------------------------
DROP TABLE IF EXISTS `tb_attribute_value`;
CREATE TABLE `tb_attribute_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attributeId` int(11) NOT NULL,
  `value` varchar(30) NOT NULL COMMENT '规格值',
  PRIMARY KEY (`id`),
  KEY `attributeId` (`attributeId`),
  CONSTRAINT `tb_attribute_value_ibfk_1` FOREIGN KEY (`attributeId`) REFERENCES `tb_attribute` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_attribute_value
-- ----------------------------
INSERT INTO `tb_attribute_value` VALUES ('2', '2', '白色');
INSERT INTO `tb_attribute_value` VALUES ('5', '2', '黄色');
INSERT INTO `tb_attribute_value` VALUES ('6', '2', '黑色');
INSERT INTO `tb_attribute_value` VALUES ('7', '2', '蓝色');
INSERT INTO `tb_attribute_value` VALUES ('9', '2', '红色');
INSERT INTO `tb_attribute_value` VALUES ('10', '2', '灰色');
INSERT INTO `tb_attribute_value` VALUES ('11', '2', '棕色');
INSERT INTO `tb_attribute_value` VALUES ('13', '6', 'S');
INSERT INTO `tb_attribute_value` VALUES ('14', '6', 'M');
INSERT INTO `tb_attribute_value` VALUES ('15', '6', 'L');
INSERT INTO `tb_attribute_value` VALUES ('16', '6', 'XL');
INSERT INTO `tb_attribute_value` VALUES ('17', '6', 'XXL');
INSERT INTO `tb_attribute_value` VALUES ('19', '8', '官方标配');
INSERT INTO `tb_attribute_value` VALUES ('23', '9', '宝石蓝');
INSERT INTO `tb_attribute_value` VALUES ('24', '9', '亮黑色');
INSERT INTO `tb_attribute_value` VALUES ('25', '9', '渐变极光色');
INSERT INTO `tb_attribute_value` VALUES ('26', '9', '渐变樱粉金');
INSERT INTO `tb_attribute_value` VALUES ('27', '10', '4G全网通');
INSERT INTO `tb_attribute_value` VALUES ('28', '11', '64GB');
INSERT INTO `tb_attribute_value` VALUES ('29', '11', '128GB');
INSERT INTO `tb_attribute_value` VALUES ('30', '11', '256GB');
INSERT INTO `tb_attribute_value` VALUES ('31', '12', '36');
INSERT INTO `tb_attribute_value` VALUES ('33', '12', '38');
INSERT INTO `tb_attribute_value` VALUES ('35', '12', '40');
INSERT INTO `tb_attribute_value` VALUES ('36', '12', '42');
INSERT INTO `tb_attribute_value` VALUES ('37', '2', '紫色');

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL COMMENT '类目名称',
  `sort_num` int(11) NOT NULL DEFAULT '1',
  `icon` varchar(255) DEFAULT NULL,
  `uplevelId` int(11) NOT NULL COMMENT '上级类目ID',
  `level` smallint(255) NOT NULL DEFAULT '1' COMMENT '1一级类目，2二级类目',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES ('3', '男装', '3', 'upload/img/u/2018/06/20/RN24.jpg', '0', '1');
INSERT INTO `tb_category` VALUES ('7', '女装', '5', 'upload/img/u/2018/06/20/S4P5.jpg', '0', '1');
INSERT INTO `tb_category` VALUES ('8', '电脑', '1', 'upload/img/u/2018/06/20/3ZF2.jpg', '0', '1');
INSERT INTO `tb_category` VALUES ('9', '手机', '1', 'upload/img/u/2018/06/20/HSJ4.jpg', '0', '1');
INSERT INTO `tb_category` VALUES ('10', '连衣裙', '1', 'upload/img/u/2018/06/20/H8XF.jpg', '7', '2');
INSERT INTO `tb_category` VALUES ('11', '短裤', '1', 'upload/img/u/2018/06/21/6Q7A.jpg', '7', '2');
INSERT INTO `tb_category` VALUES ('12', '文胸', '1', 'upload/img/u/2018/06/21/3DZG.jpg', '7', '2');
INSERT INTO `tb_category` VALUES ('13', '衬衫', '1', 'upload/img/u/2018/06/21/I64X.jpg', '7', '2');

-- ----------------------------
-- Table structure for tb_content
-- ----------------------------
DROP TABLE IF EXISTS `tb_content`;
CREATE TABLE `tb_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT '',
  `author` varchar(200) DEFAULT '' COMMENT '作者',
  `description` varchar(1000) DEFAULT '' COMMENT '描述',
  `column4` varchar(255) DEFAULT NULL,
  `column2` varchar(255) DEFAULT '',
  `column3` varchar(255) DEFAULT NULL,
  `column1` varchar(255) DEFAULT '',
  `content` text,
  `out_link` varchar(200) DEFAULT '' COMMENT '外链',
  `picture` varchar(200) DEFAULT '' COMMENT '内页图',
  `updatetime` datetime NOT NULL COMMENT '修改时间',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `clickcount` int(11) NOT NULL DEFAULT '0' COMMENT '阅读量',
  `commentcount` int(11) NOT NULL DEFAULT '0' COMMENT '评论数',
  `thumbnail` varchar(200) DEFAULT '' COMMENT '缩略图',
  `lanmu_id` int(11) NOT NULL COMMENT '栏目外键',
  `sort_num` int(11) NOT NULL DEFAULT '1' COMMENT '排序字段',
  `status` smallint(4) NOT NULL DEFAULT '0' COMMENT '状态',
  `gzDelete` smallint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `check_state` smallint(4) NOT NULL DEFAULT '0' COMMENT '0审核通过',
  PRIMARY KEY (`id`),
  KEY `lanmu_id` (`lanmu_id`),
  CONSTRAINT `tb_content_ibfk_1` FOREIGN KEY (`lanmu_id`) REFERENCES `tb_lanmu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_content
-- ----------------------------
INSERT INTO `tb_content` VALUES ('20', '2', '2', '', null, '', null, '', null, '', '', '2018-06-10 22:23:13', '2018-06-10 22:23:13', '0', '0', '', '30', '1', '0', '0', '0');

-- ----------------------------
-- Table structure for tb_content_attachment
-- ----------------------------
DROP TABLE IF EXISTS `tb_content_attachment`;
CREATE TABLE `tb_content_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attachmentId` int(11) NOT NULL,
  `contentId` int(11) NOT NULL,
  `sort_num` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `contentId` (`contentId`),
  KEY `attachmentId` (`attachmentId`),
  CONSTRAINT `tb_content_attachment_ibfk_1` FOREIGN KEY (`contentId`) REFERENCES `tb_content` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_content_attachment_ibfk_2` FOREIGN KEY (`attachmentId`) REFERENCES `tb_attachment` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_content_attachment
-- ----------------------------

-- ----------------------------
-- Table structure for tb_coupon
-- ----------------------------
DROP TABLE IF EXISTS `tb_coupon`;
CREATE TABLE `tb_coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `money` int(11) NOT NULL,
  `leastSpendMoney` int(11) NOT NULL,
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '0未使用1已使用',
  `period` int(11) NOT NULL COMMENT '有效期',
  `promotionId` int(11) DEFAULT NULL,
  `name` varchar(14) NOT NULL COMMENT '优惠券名称',
  PRIMARY KEY (`id`),
  KEY `promotionId` (`promotionId`),
  CONSTRAINT `tb_coupon_ibfk_1` FOREIGN KEY (`promotionId`) REFERENCES `tb_promotion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_coupon
-- ----------------------------
INSERT INTO `tb_coupon` VALUES ('2', '5', '105', '\0', '7', null, '优惠券');

-- ----------------------------
-- Table structure for tb_field
-- ----------------------------
DROP TABLE IF EXISTS `tb_field`;
CREATE TABLE `tb_field` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `field_zh` varchar(20) NOT NULL DEFAULT '' COMMENT '字段中文名',
  `field_en` varchar(20) NOT NULL DEFAULT '' COMMENT '字段英文名',
  `field_type` varchar(20) NOT NULL DEFAULT '' COMMENT '字段类型',
  `filed_length` smallint(5) NOT NULL DEFAULT '50' COMMENT '字段长度',
  `is_editor` smallint(1) NOT NULL DEFAULT '0' COMMENT '是否可修改',
  `lanmu_id` int(11) NOT NULL COMMENT '栏目id',
  `lanmu_option` smallint(2) NOT NULL DEFAULT '1' COMMENT '0禁用1选题2必填',
  `field_show` smallint(6) NOT NULL DEFAULT '1' COMMENT '是否可见0不可见1可见',
  `sort_num` int(11) NOT NULL DEFAULT '0' COMMENT '排序字段',
  PRIMARY KEY (`id`),
  KEY `lanmu_id` (`lanmu_id`),
  CONSTRAINT `lanmu_id` FOREIGN KEY (`lanmu_id`) REFERENCES `tb_lanmu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_field
-- ----------------------------
INSERT INTO `tb_field` VALUES ('1', 'id', 'id', 'int', '11', '0', '1', '1', '1', '2');
INSERT INTO `tb_field` VALUES ('2', '内容', 'content', 'varchar', '5000', '0', '1', '1', '0', '15');
INSERT INTO `tb_field` VALUES ('3', '标题', 'title', 'varchar', '200', '1', '1', '1', '1', '4');
INSERT INTO `tb_field` VALUES ('4', '作者', 'author', 'varchar', '100', '1', '1', '1', '1', '5');
INSERT INTO `tb_field` VALUES ('5', '描述', 'description', 'varchar', '1000', '0', '1', '1', '1', '6');
INSERT INTO `tb_field` VALUES ('6', '内页图', 'picture', 'varchar', '100', '0', '1', '1', '0', '7');
INSERT INTO `tb_field` VALUES ('7', '缩略图', 'thumbnail', 'varchar', '100', '1', '1', '1', '1', '8');
INSERT INTO `tb_field` VALUES ('8', '创建时间', 'createtime', 'datatime', '11', '1', '1', '1', '0', '9');
INSERT INTO `tb_field` VALUES ('9', '更新时间', 'updatetime', 'datatime', '11', '0', '1', '1', '0', '10');
INSERT INTO `tb_field` VALUES ('10', '外链', 'out_link', 'varchar', '400', '0', '1', '1', '0', '11');
INSERT INTO `tb_field` VALUES ('11', '阅读量', 'clickcount', 'int', '11', '2', '1', '1', '0', '12');
INSERT INTO `tb_field` VALUES ('12', '状态', 'status', 'smallint', '4', '0', '1', '1', '0', '13');
INSERT INTO `tb_field` VALUES ('13', '审核状态', 'check_state', 'smallint', '4', '0', '1', '1', '0', '14');
INSERT INTO `tb_field` VALUES ('14', '评论量', 'commentcount', 'int', '11', '1', '1', '1', '0', '15');
INSERT INTO `tb_field` VALUES ('15', '是否删除', 'gzDelete', 'smallint', '4', '1', '1', '1', '0', '16');
INSERT INTO `tb_field` VALUES ('16', '栏目id', 'lanmu_id', 'int', '11', '1', '1', '1', '0', '17');
INSERT INTO `tb_field` VALUES ('17', '字段1', 'column1', 'varchar', '255', '0', '1', '0', '0', '18');
INSERT INTO `tb_field` VALUES ('18', '字段2', 'column2', 'varchar', '255', '0', '1', '0', '0', '19');
INSERT INTO `tb_field` VALUES ('19', '字段3', 'column3', 'varchar', '255', '0', '1', '0', '0', '20');
INSERT INTO `tb_field` VALUES ('20', '字段4', 'column4', 'varchar', '255', '0', '1', '0', '0', '21');
INSERT INTO `tb_field` VALUES ('21', 'id', 'id', 'int', '11', '0', '29', '1', '1', '2');
INSERT INTO `tb_field` VALUES ('22', '标题', 'title', 'varchar', '200', '1', '29', '1', '1', '4');
INSERT INTO `tb_field` VALUES ('23', '作者', 'author', 'varchar', '100', '1', '29', '1', '1', '5');
INSERT INTO `tb_field` VALUES ('24', '描述', 'description', 'varchar', '1000', '1', '29', '0', '0', '6');
INSERT INTO `tb_field` VALUES ('25', '内页图', 'picture', 'varchar', '100', '0', '29', '1', '0', '7');
INSERT INTO `tb_field` VALUES ('26', '缩略图', 'thumbnail', 'varchar', '100', '1', '29', '1', '1', '8');
INSERT INTO `tb_field` VALUES ('27', '创建时间', 'createtime', 'datatime', '11', '0', '29', '1', '0', '18');
INSERT INTO `tb_field` VALUES ('28', '更新时间', 'updatetime', 'datatime', '11', '0', '29', '1', '0', '10');
INSERT INTO `tb_field` VALUES ('29', '外链', 'out_link', 'varchar', '400', '0', '29', '1', '0', '11');
INSERT INTO `tb_field` VALUES ('30', '阅读量', 'clickcount', 'int', '11', '0', '29', '1', '0', '12');
INSERT INTO `tb_field` VALUES ('31', '状态', 'status', 'smallint', '4', '0', '29', '1', '0', '13');
INSERT INTO `tb_field` VALUES ('32', '审核状态', 'check_state', 'smallint', '4', '1', '29', '1', '0', '14');
INSERT INTO `tb_field` VALUES ('33', '内容', 'content', 'varchar', '5000', '0', '29', '1', '0', '15');
INSERT INTO `tb_field` VALUES ('34', '评论量', 'commentcount', 'int', '11', '0', '29', '1', '0', '15');
INSERT INTO `tb_field` VALUES ('35', '是否删除', 'gzDelete', 'smallint', '4', '0', '29', '1', '0', '16');
INSERT INTO `tb_field` VALUES ('36', '栏目id', 'lanmu_id', 'int', '11', '0', '29', '1', '0', '17');
INSERT INTO `tb_field` VALUES ('37', '字段1', 'column1', 'varchar', '255', '1', '29', '0', '0', '18');
INSERT INTO `tb_field` VALUES ('38', '字段2', 'column2', 'varchar', '255', '1', '29', '0', '0', '19');
INSERT INTO `tb_field` VALUES ('39', '字段3', 'column3', 'varchar', '255', '1', '29', '0', '0', '20');
INSERT INTO `tb_field` VALUES ('40', '字段4', 'column4', 'varchar', '255', '1', '29', '0', '0', '21');
INSERT INTO `tb_field` VALUES ('41', 'id', 'id', 'int', '11', '0', '30', '1', '1', '1');
INSERT INTO `tb_field` VALUES ('42', '标题', 'title', 'varchar', '200', '1', '30', '2', '1', '2');
INSERT INTO `tb_field` VALUES ('43', '作者', 'author', 'varchar', '100', '1', '30', '2', '1', '5');
INSERT INTO `tb_field` VALUES ('44', '描述', 'description', 'varchar', '1000', '1', '30', '1', '0', '6');
INSERT INTO `tb_field` VALUES ('45', '内页图', 'picture', 'varchar', '100', '0', '30', '0', '0', '7');
INSERT INTO `tb_field` VALUES ('46', '缩略图', 'thumbnail', 'varchar', '100', '1', '30', '1', '1', '8');
INSERT INTO `tb_field` VALUES ('47', '创建时间', 'createtime', 'datatime', '11', '0', '30', '1', '0', '9');
INSERT INTO `tb_field` VALUES ('48', '更新时间', 'updatetime', 'datatime', '11', '0', '30', '1', '0', '10');
INSERT INTO `tb_field` VALUES ('49', '外链', 'out_link', 'varchar', '400', '0', '30', '0', '1', '11');
INSERT INTO `tb_field` VALUES ('50', '阅读量', 'clickcount', 'int', '11', '0', '30', '1', '0', '12');
INSERT INTO `tb_field` VALUES ('51', '状态', 'status', 'smallint', '4', '0', '30', '1', '0', '13');
INSERT INTO `tb_field` VALUES ('52', '审核状态', 'check_state', 'smallint', '4', '1', '30', '1', '0', '14');
INSERT INTO `tb_field` VALUES ('53', '内容', 'content', 'varchar', '5000', '0', '30', '1', '0', '15');
INSERT INTO `tb_field` VALUES ('54', '评论量', 'commentcount', 'int', '11', '0', '30', '1', '0', '15');
INSERT INTO `tb_field` VALUES ('55', '是否删除', 'gzDelete', 'smallint', '4', '0', '30', '1', '0', '16');
INSERT INTO `tb_field` VALUES ('56', '栏目id', 'lanmu_id', 'int', '11', '0', '30', '1', '1', '17');
INSERT INTO `tb_field` VALUES ('57', '字段1', 'column1', 'varchar', '255', '1', '30', '0', '0', '18');
INSERT INTO `tb_field` VALUES ('58', '字段2', 'column2', 'varchar', '255', '1', '30', '0', '0', '19');
INSERT INTO `tb_field` VALUES ('59', '字段3', 'column3', 'varchar', '255', '1', '30', '0', '0', '20');
INSERT INTO `tb_field` VALUES ('60', '字段4', 'column4', 'varchar', '255', '1', '30', '0', '0', '21');

-- ----------------------------
-- Table structure for tb_lanmu
-- ----------------------------
DROP TABLE IF EXISTS `tb_lanmu`;
CREATE TABLE `tb_lanmu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '栏目名',
  `level` int(11) NOT NULL DEFAULT '0' COMMENT '0为栏目标题1为子栏目',
  `up_levelId` int(11) NOT NULL DEFAULT '0' COMMENT '上级Id',
  `route` varchar(255) NOT NULL DEFAULT '' COMMENT '路由',
  `sort_num` int(11) NOT NULL DEFAULT '10' COMMENT '排序字段',
  `is_single` smallint(4) NOT NULL DEFAULT '0' COMMENT '是否为单网页0否1是2空白',
  `is_systerm` smallint(4) NOT NULL DEFAULT '0' COMMENT '是否为系通默认记录，0否1是',
  `data_count` int(11) NOT NULL DEFAULT '0',
  `font_icon` varchar(50) DEFAULT '' COMMENT '图标',
  `lanmu_type` smallint(2) NOT NULL DEFAULT '0' COMMENT '0栏目组1子栏目',
  `description` varchar(500) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_lanmu
-- ----------------------------
INSERT INTO `tb_lanmu` VALUES ('1', '', '0', '0', '', '0', '0', '0', '0', ' ', '0', '');
INSERT INTO `tb_lanmu` VALUES ('25', '用户管理', '0', '0', 'userManage', '3', '2', '0', '0', ' fa fa-user', '0', null);
INSERT INTO `tb_lanmu` VALUES ('26', '栏目管理', '0', '0', 'lanmu', '1', '2', '1', '0', ' fa fa-bars', '0', null);
INSERT INTO `tb_lanmu` VALUES ('27', '管理员', '0', '0', 'adminUser', '2', '2', '1', '0', 'fa fa-user-secret', '0', null);
INSERT INTO `tb_lanmu` VALUES ('28', '文章列表', '0', '0', 'content', '970', '2', '0', '0', 'fa fa-newspaper-o', '0', null);
INSERT INTO `tb_lanmu` VALUES ('29', '今日快报', '0', '28', 'content', '50', '0', '0', '0', null, '1', null);
INSERT INTO `tb_lanmu` VALUES ('30', '美妆推荐', '0', '28', 'content', '100', '0', '0', '0', null, '1', null);
INSERT INTO `tb_lanmu` VALUES ('31', '系统首页', '0', '0', 'dashboard', '1000', '2', '0', '0', 'fa fa-home', '0', null);
INSERT INTO `tb_lanmu` VALUES ('32', '系统设置', '0', '0', 'content', '990', '2', '0', '0', 'fa fa-cog', '1', null);
INSERT INTO `tb_lanmu` VALUES ('35', '商品管理', '0', '0', 'goods', '980', '2', '0', '0', 'fa fa-shopping-basket', '0', null);
INSERT INTO `tb_lanmu` VALUES ('36', '商品类目', '0', '35', 'category', '50', '2', '0', '0', null, '1', null);
INSERT INTO `tb_lanmu` VALUES ('37', '商品规格', '0', '35', 'attribute', '50', '2', '0', '0', null, '1', null);
INSERT INTO `tb_lanmu` VALUES ('38', '商品列表', '0', '35', 'goodslist', '100', '2', '0', '0', null, '1', null);
INSERT INTO `tb_lanmu` VALUES ('39', '会员管理', '0', '25', 'user', '10', '2', '0', '0', '', '1', null);
INSERT INTO `tb_lanmu` VALUES ('40', '收货地址', '0', '25', 'address', '10', '2', '0', '0', null, '1', null);
INSERT INTO `tb_lanmu` VALUES ('41', '推广管理', '0', '0', 'spread', '10', '2', '0', '0', 'fa fa-users', '0', null);
INSERT INTO `tb_lanmu` VALUES ('42', '广告管理', '0', '41', 'ad', '10', '2', '0', '0', '', '1', '');
INSERT INTO `tb_lanmu` VALUES ('43', '促销', '0', '41', 'promotion', '10', '2', '0', '0', '', '1', null);
INSERT INTO `tb_lanmu` VALUES ('44', '素材管理', '0', '0', 'attachment', '10', '2', '0', '0', 'fa fa-files-o', '0', null);
INSERT INTO `tb_lanmu` VALUES ('45', '订单管理', '0', '35', 'order', '80', '2', '0', '0', null, '1', null);

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `modifyDate` datetime NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `caption` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `thumbnail` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '缩略图',
  `introduction` text CHARACTER SET utf8,
  `productCategoryId` int(20) NOT NULL,
  `sn` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '商品编号',
  `sales` int(20) NOT NULL DEFAULT '0' COMMENT '销量',
  `isMarketable` bit(1) DEFAULT b'1' COMMENT '是否上架',
  `price` float(21,2) NOT NULL COMMENT '现价',
  `marketPrice` float(21,2) DEFAULT '0.00' COMMENT '原价、市场价',
  `stock` int(11) NOT NULL DEFAULT '0' COMMENT '库存',
  `parameterValue` varchar(1000) DEFAULT '[]' COMMENT 'json字符串，其他属性',
  `sort_num` int(11) NOT NULL DEFAULT '1',
  `isRecommend` smallint(6) NOT NULL DEFAULT '0' COMMENT '1推荐',
  PRIMARY KEY (`id`),
  KEY `productCategoryId` (`productCategoryId`),
  CONSTRAINT `tb_product_ibfk_1` FOREIGN KEY (`productCategoryId`) REFERENCES `tb_category` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES ('1', '2018-06-10 20:01:33', '2018-06-22 22:31:25', '连衣裙女夏2018新款 小清新韩版女装系带宽松显瘦刺绣 中长款裙子', '夏季新品', 'upload/img/u/2018/06/19/N4ZT.jpg', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<p><img class=\"img-ks-lazyload\" style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"https://img.alicdn.com/imgextra/i2/874022290/TB2_eIKppXXXXX8XFXXXXXXXXXX_!!874022290.jpg\" align=\"absmiddle\"><img class=\"img-ks-lazyload\" style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"https://img.alicdn.com/imgextra/i1/874022290/TB2YmNQqQCWBuNjy0FaXXXUlXXa_!!874022290.jpg\" align=\"absmiddle\"><img class=\"img-ks-lazyload\" style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"https://img.alicdn.com/imgextra/i1/874022290/TB2EwtqqFuWBuNjSszbXXcS7FXa_!!874022290.jpg\" align=\"absmiddle\"><img class=\"img-ks-lazyload\" style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"https://img.alicdn.com/imgextra/i4/874022290/TB2wT4OqL5TBuNjSspmXXaDRVXa_!!874022290.jpg\" align=\"absmiddle\"><img class=\"img-ks-lazyload\" style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"https://img.alicdn.com/imgextra/i3/874022290/TB2r.kJinXYBeNkHFrdXXciuVXa_!!874022290.jpg\" align=\"absmiddle\"><img class=\"img-ks-lazyload\" style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"https://img.alicdn.com/imgextra/i2/874022290/TB2k.03qUR1BeNjy0FmXXb0wVXa_!!874022290.jpg\" align=\"absmiddle\"></p>\n<p style=\"text-align: center;\"><img id=\"desc-module-2\" class=\"desc_anchor img-ks-lazyload\" src=\"https://assets.alicdn.com/kissy/1.0.0/build/imglazyload/spaceball.gif\"><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i1/874022290/TB2hFDJppXXXXbDXXXXXXXXXXXX_!!874022290.jpg\" alt=\"04面料解析2_02.jpg\"></p>\n<table style=\"margin-left: auto; margin-right: auto;\" border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n<tbody>\n<tr>\n<td bgcolor=\"#f7f7f7\" width=\"111\" height=\"50\"><strong>名称</strong></td>\n<td align=\"left\" bgcolor=\"#f7f7f7\" height=\"50\">QZ2307连衣裙</td>\n</tr>\n<tr>\n<td height=\"50\"><strong>货号</strong></td>\n<td align=\"left\" height=\"50\">SF18A2307</td>\n</tr>\n<tr>\n<td bgcolor=\"#f7f7f7\" height=\"50\"><strong>尺码</strong></td>\n<td align=\"left\" bgcolor=\"#f7f7f7\" height=\"50\">S M L XL XXL XXXL</td>\n</tr>\n<tr>\n<td height=\"50\"><strong>颜色</strong></td>\n<td align=\"left\" height=\"50\">船坞蓝色</td>\n</tr>\n<tr>\n<td bgcolor=\"#f7f7f7\" height=\"50\"><strong>成分</strong></td>\n<td align=\"left\" bgcolor=\"#f7f7f7\" height=\"50\">\n<p>面料：亚麻55% 棉45%</p>\n<p>里料：聚酯纤维95.5% 氨纶4.5%</p>\n</td>\n</tr>\n<tr>\n<td bgcolor=\"#ffffff\" height=\"50\"><strong>季节</strong></td>\n<td align=\"left\" bgcolor=\"#ffffff\" height=\"50\">夏季</td>\n</tr>\n<tr>\n<td bgcolor=\"#f7f7f7\" height=\"50\"><strong>说明</strong></td>\n<td align=\"left\" bgcolor=\"#f7f7f7\">双层</td>\n</tr>\n</tbody>\n</table>\n<table style=\"margin-left: auto; margin-right: auto;\" border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n<tbody>\n<tr>\n<td><strong>面料弹性</strong></td>\n<td bgcolor=\"#d7d7d7\">无弹力</td>\n<td bgcolor=\"#ffffff\">轻微弹力</td>\n<td>高弹力</td>\n</tr>\n<tr>\n<td><strong>厚薄指数</strong></td>\n<td>厚款</td>\n<td bgcolor=\"#d6d6d6\">厚薄适中</td>\n<td>薄款</td>\n</tr>\n<tr>\n<td><strong>版型</strong></td>\n<td>紧身</td>\n<td bgcolor=\"#ffffff\">修身</td>\n<td bgcolor=\"#d6d6d6\">宽松</td>\n</tr>\n<tr>\n<td bgcolor=\"#FFFFFF\"><strong>长度</strong></td>\n<td bgcolor=\"#FFFFFF\">短款</td>\n<td bgcolor=\"#d6d6d6\">长度适中</td>\n<td bgcolor=\"#FFFFFF\">长款</td>\n</tr>\n</tbody>\n</table>\n<table style=\"margin-left: auto; margin-right: auto;\" border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n<tbody>\n<tr>\n<td><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i3/874022290/TB2M_YGppXXXXbuXXXXXXXXXXXX_!!874022290.jpg\" alt=\"\"><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i1/874022290/TB2_2bGppXXXXcaXXXXXXXXXXXX_!!874022290.jpg\" alt=\"04面料解析2_02.jpg\">\n<table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n<tbody>\n<tr bgcolor=\"#484848\">\n<td width=\"13%\"><span style=\"color: #ffffff;\">尺码（单位cm）</span></td>\n<td width=\"11%\"><span style=\"color: #ffffff;\">肩宽</span></td>\n<td width=\"11%\"><span style=\"color: #ffffff;\">胸围</span></td>\n<td width=\"9%\"><span style=\"color: #ffffff;\">腰围</span></td>\n<td width=\"10%\"><span style=\"color: #ffffff;\">裙长</span></td>\n<td width=\"10%\"><span style=\"color: #ffffff;\">袖长</span></td>\n<td width=\"10%\"><span style=\"color: #ffffff;\">下摆围</span></td>\n<td width=\"26%\"><span style=\"color: #ffffff;\">建议体重</span></td>\n</tr>\n<tr>\n<td>S</td>\n<td>35</td>\n<td>84</td>\n<td>74</td>\n<td>90</td>\n<td>17</td>\n<td>-</td>\n<td>80-95(斤)</td>\n</tr>\n<tr>\n<td>M</td>\n<td>36</td>\n<td><span style=\"color: #404040;\">88</span></td>\n<td>78</td>\n<td>91</td>\n<td>18</td>\n<td>-</td>\n<td>96-103(斤)</td>\n</tr>\n<tr>\n<td>L</td>\n<td>37</td>\n<td><span style=\"color: #404040;\">92</span></td>\n<td>82</td>\n<td>92</td>\n<td>19</td>\n<td>-</td>\n<td>104-113(斤)</td>\n</tr>\n<tr>\n<td>XL</td>\n<td>38</td>\n<td>96</td>\n<td>86</td>\n<td>93</td>\n<td>20</td>\n<td>-</td>\n<td>114-125(斤)</td>\n</tr>\n<tr>\n<td>XXL</td>\n<td>39</td>\n<td><span style=\"color: #404040;\">100</span></td>\n<td>90</td>\n<td>94</td>\n<td>21</td>\n<td>-</td>\n<td>126-135(斤)</td>\n</tr>\n<tr>\n<td>XXXL</td>\n<td>40</td>\n<td><span style=\"color: #404040;\">104</span></td>\n<td>94</td>\n<td>95</td>\n<td>22</td>\n<td>-</td>\n<td>136-145(斤)</td>\n</tr>\n<tr>\n<td colspan=\"8\">温馨提示：因测量方式不同，若有1-2cm误差皆属合理范围。建议体重仅供参考，请亲们结合自身习惯选择合适尺码。</td>\n</tr>\n</tbody>\n</table>\n<img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i3/874022290/TB21nbvppXXXXauXpXXXXXXXXXX_!!874022290.jpg\" alt=\"04面料解析2_02.jpg\">\n<table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n<tbody>\n<tr>\n<td><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i1/874022290/TB2OXDzppXXXXX7XpXXXXXXXXXX_!!874022290.jpg\" alt=\"\"></td>\n<td>\n<table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n<tbody>\n<tr>\n<td align=\"center\" width=\"30\">1.</td>\n<td>肩宽（平铺后左肩端点到右肩端点的距离）</td>\n</tr>\n<tr>\n<td align=\"center\">2.</td>\n<td>胸围/2（平铺腋下直量）</td>\n</tr>\n<tr>\n<td align=\"center\">3.</td>\n<td>袖长（肩端点至袖口直量）</td>\n</tr>\n<tr>\n<td align=\"center\">4.</td>\n<td>衣长（肩颈点至下摆底端的垂直长度）</td>\n</tr>\n<tr>\n<td align=\"center\">5.</td>\n<td>下摆围/2（下摆低点处左侧到右侧直量，大摆弯量）</td>\n</tr>\n<tr>\n<td align=\"center\">&nbsp;</td>\n<td>&nbsp;</td>\n</tr>\n</tbody>\n</table>\n</td>\n</tr>\n</tbody>\n</table>\n<img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i3/874022290/TB2NJPPppXXXXb_XXXXXXXXXXXX_!!874022290.jpg\" alt=\"04面料解析2_02.jpg\">\n<table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n<tbody>\n<tr bgcolor=\"#484848\">\n<td><span style=\"color: #ffffff;\">试穿人</span></td>\n<td><span style=\"color: #ffffff;\">身高/体重</span></td>\n<td><span style=\"color: #ffffff;\">三围</span></td>\n<td><span style=\"color: #ffffff;\">平时尺码</span></td>\n<td><span style=\"color: #ffffff;\">试穿尺码</span></td>\n<td><span style=\"color: #ffffff;\">试穿感受</span></td>\n</tr>\n<tr>\n<td>梦雨</td>\n<td>155/45</td>\n<td>76/62/78</td>\n<td>S(偏瘦)</td>\n<td>S</td>\n<td>腰围合体，长度刚好</td>\n</tr>\n<tr>\n<td>丽君</td>\n<td>160/50</td>\n<td>84/70/94</td>\n<td>M(正常)</td>\n<td>M</td>\n<td>腰围合体，版型合体</td>\n</tr>\n<tr>\n<td>平平</td>\n<td>165/55</td>\n<td>85/72/95</td>\n<td>L(正常)</td>\n<td>L</td>\n<td>版型显瘦，腰围合体</td>\n</tr>\n<tr>\n<td>小琳</td>\n<td>170/63</td>\n<td>94/73/98</td>\n<td>XL(正常)</td>\n<td>XL</td>\n<td>版型显瘦，穿着合适</td>\n</tr>\n</tbody>\n</table>\n</td>\n</tr>\n</tbody>\n</table>\n<p style=\"text-align: center;\"><img id=\"desc-module-3\" class=\"desc_anchor img-ks-lazyload\" src=\"https://assets.alicdn.com/kissy/1.0.0/build/imglazyload/spaceball.gif\"></p>\n<p><img class=\"img-ks-lazyload\" style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"https://img.alicdn.com/imgextra/i3/874022290/TB2VRpZfDmWBKNjSZFBXXXxUFXa_!!874022290.jpg\" align=\"absmiddle\"><img class=\"img-ks-lazyload\" style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"https://img.alicdn.com/imgextra/i3/874022290/TB2can.faQoBKNjSZJnXXaw9VXa_!!874022290.jpg\" align=\"absmiddle\"><img class=\"img-ks-lazyload\" style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"https://img.alicdn.com/imgextra/i4/874022290/TB2OypoqH1YBuNjSszeXXablFXa_!!874022290.jpg\" align=\"absmiddle\"><img class=\"img-ks-lazyload\" style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"https://img.alicdn.com/imgextra/i4/874022290/TB2iCUligKTBuNkSne1XXaJoXXa_!!874022290.jpg\" align=\"absmiddle\"><img class=\"img-ks-lazyload\" style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"https://img.alicdn.com/imgextra/i4/874022290/TB2Y4JBfrArBKNjSZFLXXc_dVXa_!!874022290.jpg\" align=\"absmiddle\" data-spm-anchor-id=\"a220o.1000855.0.i10.72f27b17f9B99q\"></p>\n</body>\n</html>', '7', '4675696', '0', '\0', '79.00', '270.00', '100', '[]', '1', '0');
INSERT INTO `tb_product` VALUES ('2', '2018-06-10 20:02:21', '2018-06-20 18:22:30', 'Apple iPad 平板电脑 9.7英寸（32G WLAN版/A9 芯片/Retina显示屏/Touch ID技术 MPGT2CH/A）金色', null, 'upload/img/u/2018/06/19/XAM9.jpg', null, '8', '4675696', '0', '\0', '1998.00', '2688.00', '100', '[]', '1', '0');
INSERT INTO `tb_product` VALUES ('3', '2018-06-19 21:04:14', '2018-06-20 18:08:41', '颜域品牌女装2018夏季装新款时尚简约v字领口性感蕾丝镂空连衣裙', null, 'upload/img/u/2018/06/19/ESKF.jpg', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<div id=\"attributes\" class=\"attributes\">\n<div id=\"J_AttrList\" class=\"attributes-list\">\n<ul id=\"J_AttrUL\" data-spm-anchor-id=\"a220o.1000855.0.i12.e22612b6KTVBDB\">\n<li title=\"&nbsp;S&nbsp;M&nbsp;L&nbsp;XL&nbsp;XXL\">尺码:&nbsp;S&nbsp;M&nbsp;L&nbsp;XL&nbsp;XXL</li>\n</ul>\n</div>\n</div>\n<div id=\"mall-banner\">\n<div data-spm=\"1998132255\"><a href=\"https://pages.tmall.com/wow/a/act/19577/upr?wh_weex=true&amp;wh_biz=tm&amp;wh_pid=industry-150977&amp;acm=lb-zebra-163061-948625.1003.4.3619870&amp;scm=1003.4.lb-zebra-163061-948625.OTHER_75_3619870\" target=\"_blank\" rel=\"noopener\"><img src=\"https://img.alicdn.com/tfs/TB1FUy5whGYBuNjy0FnXXX5lpXa-790-120.jpg\" width=\"790\" height=\"120\"></a></div>\n<div id=\"J_DescTMS1\">&nbsp;</div>\n</div>\n<div id=\"J_TmpActBanner\">&nbsp;</div>\n<div id=\"J_DcTopRightWrap\">\n<div id=\"J_DcTopRight\" class=\"J_DcAsyn tb-shop\">\n<div id=\"shop16353064114\" class=\"J_TModule\" data-widgetid=\"16353064114\" data-componentid=\"5003\" data-spm=\"110.0.5003-16353064114\" data-title=\"自定义内容区\">\n<div class=\"skin-box tb-module tshop-pbsm tshop-pbsm-shop-self-defined\"><s class=\"skin-box-tp\"></s>\n<div class=\"skin-box-bd clear-fix\">\n<div class=\"w790\">\n<table border=\"0\" width=\"790\" cellspacing=\"0\" cellpadding=\"0\">\n<tbody>\n<tr>\n<td><a href=\"https://yanyu.tmall.com/index.htm?scene=taobao_shop\" target=\"_blank\" rel=\"noopener\"><img class=\"img-ks-lazyload\" src=\"https://gdp.alicdn.com/imgextra/i2/1020209367/TB2HGWXbKUXBuNjt_XBXXXeDXXa_!!1020209367.jpg\" usemap=\"#Map650755\" width=\"790\" height=\"528\" border=\"0\"></a></td>\n</tr>\n<tr>\n<td><img class=\"img-ks-lazyload\" src=\"https://gdp.alicdn.com/imgextra/i4/1020209367/TB27Ru4yL5TBuNjSspcXXbnGFXa_!!1020209367.jpg\" alt=\"\" usemap=\"#Map6564755\" width=\"790\" height=\"449\" border=\"0\"></td>\n</tr>\n</tbody>\n</table>\n</div>\n<map name=\"Map6564755\"> \n<area coords=\"530,116,787,418\" href=\"https://yanyu.tmall.com/search.htm?spm=a1z10.4-b-s.w15669549-17361818459.4.53bb7450oihOMm&amp;orderType=defaultSort&amp;viewType=grid&amp;catId=616041970&amp;keyword=&amp;lowPrice=&amp;highPrice=&amp;scene=taobao_shop\" target=\"_blank\">\n \n<area coords=\"270,115,526,417\" href=\"https://yanyu.tmall.com/p/xia27bo.htm?scene=taobao_shop\" target=\"_blank\">\n \n<area coords=\"6,116,261,416\" href=\"https://content.tmall.com/wow/pegasus/subject/809/1020209367/9858719?wh_actSignId=34886358&amp;wh_sellerId=1020209367&amp;wh_shopId=73152092&amp;wh_saleSite=1&amp;id=9858719&amp;wh_weex=true&amp;scene=taobao_shop\" target=\"_blank\">\n \n<area coords=\"3,10,264,103\" href=\"https://taoquan.taobao.com/coupon/unify_apply.htm?sellerId=1020209367&amp;activityId=c0f92aef98bb4eb2934b4d685985ae29&amp;scene=taobao_shop\" target=\"_blank\">\n \n<area coords=\"526,9,788,103\" href=\"https://taoquan.taobao.com/coupon/unify_apply.htm?sellerId=1020209367&amp;activityId=0eb2f22cda9942e0a88f017784034df7&amp;scene=taobao_shop\" target=\"_blank\">\n \n<area coords=\"265,10,525,102\" href=\"https://taoquan.taobao.com/coupon/unify_apply.htm?sellerId=1020209367&amp;activityId=ebeb32339c1b461d8a64fe1b65556c24&amp;scene=taobao_shop\" target=\"_blank\">\n </map></div>\n<s class=\"skin-box-bt\"></s></div>\n</div>\n<div id=\"shop17141350476\" class=\"J_TModule\" data-widgetid=\"17141350476\" data-componentid=\"5003\" data-spm=\"110.0.5003-17141350476\" data-title=\"自定义内容区\">\n<div class=\"skin-box tb-module tshop-pbsm tshop-pbsm-shop-self-defined\"><s class=\"skin-box-tp\"></s>\n<div class=\"skin-box-bd clear-fix\">\n<div class=\"w790\">\n<table class=\"yyqiang\" border=\"0\" width=\"w790\" cellspacing=\"0\" cellpadding=\"0\">\n<tbody>\n<tr>\n<td rowspan=\"3\"><img class=\"img-ks-lazyload\" src=\"https://gdp.alicdn.com/imgextra/i2/1020209367/TB207y3yrGYBuNjy0FoXXciBFXa_!!1020209367.jpg\" alt=\"\" usemap=\"#Map231540545\" width=\"790\" height=\"1042\" border=\"0\"></td>\n</tr>\n</tbody>\n</table>\n</div>\n<map name=\"Map231540545\"> \n<area coords=\"4,77,196,379\" href=\"https://detail.tmall.com/item.htm?spm=a1z10.1-b-s.w15670448-18045979099.4.9d2374e87gy0yw&amp;id=571343526288&amp;scene=taobao_shop\" target=\"_blank\">\n \n<area coords=\"200,79,393,379\" href=\"https://detail.tmall.com/item.htm?spm=a1z10.1-b-s.w15670448-18045979099.1.9d2374e87gy0yw&amp;id=566883929935&amp;rn=66e112ebd93bf2472cdc857a573a1208&amp;scene=taobao_shop\" target=\"_blank\">\n \n<area coords=\"398,78,594,380\" href=\"https://detail.tmall.com/item.htm?spm=a1z10.1-b-s.w15670448-18045979099.3.9d2374e87gy0yw&amp;id=567860529369&amp;sku_properties=1627207:3266760&amp;scene=taobao_shop\" target=\"_blank\">\n \n<area coords=\"598,79,788,379\" href=\"https://detail.tmall.com/item.htm?spm=a1z10.1-b-s.w15670448-18045979099.9.9d2374e87gy0yw&amp;id=567083882656&amp;rn=34edaf1d0f286375d553a8656215cbc2&amp;scene=taobao_shop\" target=\"_blank\">\n \n<area coords=\"3,386,194,685\" href=\"https://detail.tmall.com/item.htm?spm=a1z10.1-b-s.w15670448-18045979099.5.9d2374e87gy0yw&amp;id=565834601485&amp;rn=34edaf1d0f286375d553a8656215cbc2&amp;scene=taobao_shop\" target=\"_blank\">\n \n<area coords=\"199,386,392,683\" href=\"https://detail.tmall.com/item.htm?spm=a1z10.1-b-s.w15670448-18045979099.10.9d2374e87gy0yw&amp;id=566275682233&amp;rn=a80e372158a87f4e6bdac8d85fa5562e&amp;scene=taobao_shop\" target=\"_blank\">\n \n<area coords=\"397,387,592,684\" href=\"https://detail.tmall.com/item.htm?spm=a1z10.1-b-s.w15670448-18045979099.2.9d2374e87gy0yw&amp;id=567052343626&amp;rn=c29d7bf4ba21a15732593e4361d46117&amp;scene=taobao_shop\" target=\"_blank\">\n \n<area coords=\"599,387,790,683\" href=\"https://detail.tmall.com/item.htm?spm=a1z10.1-b-s.w15670448-18045979099.6.9d2374e87gy0yw&amp;id=566437191505&amp;rn=ad1f973dd589ffdff28a815a0245107f&amp;scene=taobao_shop\" target=\"_blank\">\n \n<area coords=\"4,692,194,994\" href=\"https://detail.tmall.com/item.htm?spm=a1z10.1-b-s.w5003-18380275056.1.9d2374e87gy0yw&amp;id=568970088602&amp;rn=b83bf26a29c3458e736a5c0b6914dc84&amp;scene=taobao_shop\" target=\"_blank\">\n \n<area coords=\"200,693,391,995\" href=\"https://detail.tmall.com/item.htm?spm=a1z10.1-b-s.w15670448-18045979099.17.9d2374e8SSBSi6&amp;id=569854471220&amp;rn=42f7c5c45ceff3c314e4cb1a7ce77d16&amp;sku_properties=1627207:28323&amp;scene=taobao_shop\" target=\"_blank\">\n \n<area coords=\"399,693,594,995\" href=\"https://detail.tmall.com/item.htm?spm=a1z10.1-b-s.w15670448-18045979099.8.9d2374e87gy0yw&amp;id=548827164130&amp;rn=91a9192e2670304f9da8dd67402dd583&amp;scene=taobao_shop\" target=\"_blank\">\n \n<area coords=\"599,694,788,996\" href=\"https://detail.tmall.com/item.htm?spm=a1z10.1-b-s.w15670448-18455314895.2.9d2374e87gy0yw&amp;id=565993811241&amp;rn=fc13e66bbcb04ca853fcb6882c23c406&amp;scene=taobao_shop\" target=\"_blank\">\n </map></div>\n<s class=\"skin-box-bt\"></s></div>\n</div>\n<div id=\"shop14589529042\" class=\"J_TModule\" data-widgetid=\"14589529042\" data-componentid=\"15670448\" data-spm=\"110.0.15670448-14589529042\" data-title=\"S1轮播广告\">\n<div class=\"tb-module tshop-um tshop-um-s1\">\n<div class=\"s1\">\n<div class=\"shop-custom abs\">\n<div class=\"item\">\n<div class=\"zfh zui_area\">&nbsp;</div>\n</div>\n</div>\n</div>\n</div>\n</div>\n</div>\n</div>\n<div id=\"description\" class=\"J_DetailSection tshop-psm tshop-psm-bdetaildes\">\n<div class=\"content ke-post\"><img id=\"desc-module-1\" class=\"desc_anchor img-ks-lazyload\" src=\"https://assets.alicdn.com/kissy/1.0.0/build/imglazyload/spaceball.gif\">\n<p><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i1/1020209367/TB2z96dcPQnBKNjSZSgXXXHGXXa_!!1020209367.jpg\" align=\"absmiddle\"></p>\n<img id=\"desc-module-2\" class=\"desc_anchor img-ks-lazyload\" src=\"https://assets.alicdn.com/kissy/1.0.0/build/imglazyload/spaceball.gif\">\n<p><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i1/1020209367/TB2vQDpkCtYBeNjSspaXXaOOFXa_!!1020209367.jpg\" align=\"absmiddle\"><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i3/1020209367/TB2F99jd5MnBKNjSZFzXXc_qVXa_!!1020209367.jpg\" align=\"absmiddle\"></p>\n<img id=\"desc-module-3\" class=\"desc_anchor img-ks-lazyload\" src=\"https://assets.alicdn.com/kissy/1.0.0/build/imglazyload/spaceball.gif\">\n<p><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i3/1020209367/TB2EvwacFooBKNjSZFPXXXa2XXa_!!1020209367.jpg\" align=\"absmiddle\" data-spm-anchor-id=\"a220o.1000855.0.i14.e22612b6KTVBDB\"><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i2/1020209367/TB2mtD3cOOYBuNjSsD4XXbSkFXa_!!1020209367.jpg\" align=\"absmiddle\"></p>\n</div>\n</div>\n</body>\n</html>', '10', '1020209367', '0', '\0', '350.00', '649.00', '100', '[]', '1', '0');
INSERT INTO `tb_product` VALUES ('4', '2018-06-19 21:08:46', '2018-06-20 18:22:29', '【618返场享6期免息】Huawei/华为 P20 Pro 全面屏徕卡三摄4G手机', null, 'upload/img/u/2018/06/19/7PF4.jpg', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<div id=\"attributes\" class=\"attributes\">\n<div id=\"J_AttrList\" class=\"attributes-list\">\n<div id=\"J_BrandAttr\" class=\"tm-clear tb-hidden tm_brandAttr\" data-spm-anchor-id=\"a220o.1000855.0.i10.437c49cfKMqLYq\">\n<div class=\"name\">品牌名称：<strong class=\"J_EbrandLogo\">Huawei/华为</strong></div>\n</div>\n<p class=\"attr-list-hd tm-clear\"><a class=\"ui-more-nbg tm-MRswitchAttrs\" href=\"https://detail.tmall.com/item.htm?spm=a220m.1000858.1000725.1.3f3747f2WOvKif&amp;id=566605082411&amp;skuId=3766512543986&amp;standard=1&amp;user_id=2838892713&amp;cat_id=2&amp;is_b=1&amp;rn=b2e07f86a8fb1a780ea989c2084e50da#J_Attrs\">更多参数</a><em>产品参数：</em></p>\n<ul id=\"J_AttrUL\">\n<li title=\"2018011606049290\">证书编号：2018011606049290</li>\n<li title=\"有效\">证书状态：有效</li>\n<li title=\"TD-LTE数字移动电话机\">产品名称：TD-LTE数字移动电话机</li>\n<li title=\"CLT-AL00（开关电源适配器：HW-050450C00    输出：5VDC,2A 或 4.5V...\">3C规格型号：CLT-AL00（开关电源适配器：HW-050450C00 输出：5VDC,2A 或 4.5V...</li>\n<li title=\"Huawei/华为 P20 pro\">产品名称：Huawei/华为 P20 pro</li>\n<li title=\"&nbsp;P20 Pro\">华为型号:&nbsp;P20 Pro</li>\n<li title=\"&nbsp;宝石蓝&nbsp;渐变极光色&nbsp;渐变樱粉金&nbsp;亮黑色\">机身颜色:&nbsp;宝石蓝&nbsp;渐变极光色&nbsp;渐变樱粉金&nbsp;亮黑色</li>\n<li title=\"&nbsp;6GB\">运行内存RAM:&nbsp;6GB</li>\n<li title=\"&nbsp;64GB&nbsp;128GB&nbsp;256GB\">存储容量:&nbsp;64GB&nbsp;128GB&nbsp;256GB</li>\n<li title=\"&nbsp;双卡双待单通，主副卡不区分卡槽，Nano SIM\">网络模式:&nbsp;双卡双待单通，主副卡不区分卡槽，Nano SIM</li>\n</ul>\n</div>\n</div>\n<div id=\"mall-banner\">\n<div data-spm=\"1998132255\"><a href=\"https://pages.tmall.com/wow/a/act/19577/upr?wh_weex=true&amp;wh_biz=tm&amp;wh_pid=industry-150851&amp;acm=lb-zebra-163061-948625.1003.4.3619870&amp;scm=1003.4.lb-zebra-163061-948625.OTHER_32_3619870\" target=\"_blank\" rel=\"noopener\"><img src=\"https://img.alicdn.com/tfs/TB1i2FFt29TBuNjy0FcXXbeiFXa-790-120.jpg\" width=\"790\" height=\"120\"></a></div>\n<div id=\"J_DescTMS1\">&nbsp;</div>\n</div>\n<div id=\"J_TmpActBanner\">&nbsp;</div>\n<div id=\"J_DcTopRightWrap\">&nbsp;</div>\n<div id=\"description\" class=\"J_DetailSection tshop-psm tshop-psm-bdetaildes\">\n<div class=\"content ke-post\">\n<p>&nbsp;</p>\n<p><a href=\"https://topauth.taobao.com/container?appkey=12262395&amp;state=p-MBzRHzLEzSEzLDzRFzMAzRFzLIzSC\" target=\"_blank\" rel=\"noopener\"><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i2/2838892713/TB2.pbmymtYBeNjSspkXXbU8VXa_!!2838892713.jpg\" alt=\"\"><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i1/2838892713/TB2uUicdcUrBKNjSZPxXXX00pXa_!!2838892713.jpg\" align=\"absmiddle\"><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i4/2838892713/TB2qz.nlgmTBuNjy1XbXXaMrVXa_!!2838892713.jpg\" align=\"absmiddle\"><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i4/2838892713/TB28ceLgKuSBuNjy1XcXXcYjFXa_!!2838892713.jpg\" align=\"absmiddle\"></a><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i2/2838892713/TB2TgCdbpgkyKJjSspfXXcj1XXa_!!2838892713.jpg\" align=\"absmiddle\"><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i4/2838892713/TB2XfUPdhuTBuNkHFNRXXc9qpXa_!!2838892713.jpg\" align=\"absmiddle\"><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i1/2838892713/TB2mIJMlFGWBuNjy0FbXXb4sXXa_!!2838892713.jpg\" align=\"absmiddle\"><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i3/2838892713/TB2EwgUc5jQBKNjSZFnXXa_DpXa_!!2838892713.jpg\" align=\"absmiddle\"><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i1/2838892713/TB2B7FeddcnBKNjSZR0XXcFqFXa_!!2838892713.jpg\" align=\"absmiddle\"><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i4/2838892713/TB2Pe70cZUrBKNjSZPxXXX00pXa_!!2838892713.jpg\" align=\"absmiddle\"></p>\n</div>\n</div>\n</body>\n</html>', '9', '566605082411', '0', '\0', '4988.00', null, '100', '[{\"name\":\"网络\",\"parameterGroup\":[{\"key\":\"网络类型\\t\",\"value\":\"4G全网通\"},{\"key\":\"网络模式\",\"value\":\"双卡双待单通，主副卡不区分卡槽，Nano SIM\"}]}]', '1', '0');
INSERT INTO `tb_product` VALUES ('5', '2018-06-19 22:03:36', '2018-06-20 18:08:36', '预售诗篇2018秋季新款假两件拼接连帽格纹灯笼袖女卫衣6C58370080', null, 'upload/img/u/2018/06/19/OGD6.jpg', '<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n<div id=\"attributes\" class=\"attributes\">\n<div id=\"J_AttrList\" class=\"attributes-list\">\n<div id=\"J_BrandAttr\" class=\"tm-clear tb-hidden tm_brandAttr\">\n<div class=\"name\" data-spm-anchor-id=\"a220o.1000855.0.i3.5d2f4e7biJ5vim\">品牌名称：<strong class=\"J_EbrandLogo\">PSALTER/诗篇</strong></div>\n</div>\n<p class=\"attr-list-hd tm-clear\"><em>产品参数：</em></p>\n<ul id=\"J_AttrUL\">\n<li title=\"&nbsp;棉内胆\">内胆类型:&nbsp;棉内胆</li>\n<li title=\"&nbsp;聚酯纤维84.5% 新型聚酯纤维13% 聚氨酯弹性纤维(氨纶)2.5%\">材质成分:&nbsp;聚酯纤维84.5% 新型聚酯纤维13% 聚氨酯弹性纤维(氨纶)2.5%</li>\n<li title=\"&nbsp;纯电商(只在线上销售)\">销售渠道类型:&nbsp;纯电商(只在线上销售)</li>\n<li title=\"&nbsp;6C58370080\">货号:&nbsp;6C58370080</li>\n<li title=\"&nbsp;直筒\">服装版型:&nbsp;直筒</li>\n<li title=\"&nbsp;常规\">厚薄:&nbsp;常规</li>\n<li title=\"&nbsp;通勤\">风格:&nbsp;通勤</li>\n<li title=\"&nbsp;英伦\">通勤:&nbsp;英伦</li>\n<li title=\"&nbsp;套头\">款式:&nbsp;套头</li>\n<li title=\"&nbsp;单件\">组合形式:&nbsp;单件</li>\n<li title=\"&nbsp;常规\">衣长:&nbsp;常规</li>\n<li title=\"&nbsp;长袖\">袖长:&nbsp;长袖</li>\n<li title=\"&nbsp;连帽\">领子:&nbsp;连帽</li>\n<li title=\"&nbsp;灯笼袖\">袖型:&nbsp;灯笼袖</li>\n<li id=\"J_attrBrandName\" title=\"&nbsp;PSALTER/诗篇\">品牌:&nbsp;PSALTER/诗篇</li>\n<li title=\"&nbsp;一粒扣\">衣门襟:&nbsp;一粒扣</li>\n<li title=\"&nbsp;字母\">图案:&nbsp;字母</li>\n<li title=\"&nbsp;拼接\">服装款式细节:&nbsp;拼接</li>\n<li title=\"&nbsp;30-34周岁\">适用年龄:&nbsp;30-34周岁</li>\n<li title=\"&nbsp;2018年秋季\">年份季节:&nbsp;2018年秋季</li>\n<li title=\"&nbsp;紫色\">颜色分类:&nbsp;紫色</li>\n<li title=\"&nbsp;36&nbsp;38&nbsp;40&nbsp;42&nbsp;44\">尺码:&nbsp;36&nbsp;38&nbsp;40&nbsp;42&nbsp;44</li>\n</ul>\n</div>\n</div>\n<div id=\"mall-banner\">\n<div data-spm=\"1998132255\"><a href=\"https://pages.tmall.com/wow/a/act/19577/upr?wh_weex=true&amp;wh_biz=tm&amp;wh_pid=industry-150977&amp;acm=lb-zebra-163061-948625.1003.4.3619870&amp;scm=1003.4.lb-zebra-163061-948625.OTHER_75_3619870\" target=\"_blank\" rel=\"noopener\"><img src=\"https://img.alicdn.com/tfs/TB1FUy5whGYBuNjy0FnXXX5lpXa-790-120.jpg\" width=\"790\" height=\"120\"></a></div>\n<div id=\"J_DescTMS1\">&nbsp;</div>\n</div>\n<div id=\"J_TmpActBanner\">&nbsp;</div>\n<div id=\"J_DcTopRightWrap\">\n<div id=\"J_DcTopRight\" class=\"J_DcAsyn tb-shop\">\n<div id=\"shop15507324486\" class=\"J_TModule\" data-widgetid=\"15507324486\" data-componentid=\"16301125\" data-spm=\"110.0.16301125-15507324486\" data-title=\"图片轮播\">\n<div class=\"tb-module tshop-um tshop-um-xsj-slider-layer\">\n<div class=\"xsj-box-bd    type-shuzi\">\n<div class=\"J_TWidget  modb750  slider-box\" data-widget-type=\"Carousel\" data-widget-config=\"{\'nextBtnCls\':\'next\',\'duration\':0.8,\'activeTriggerCls\':\'selected\',\'easing\':\'backOut\',\'effect\':\'scrollx\',\'navCls\':\'slider-thumbs\',\'triggerType\':\'mouse\',\'prevBtnCls\':\'prev\',\'contentCls\':\'slider-large\',\'autoplay\':true}\">\n<div class=\"slider-ks\">\n<div class=\"slider-large\">\n<div class=\"xitem um-center ks-switchable-panel-internal1794\"><a href=\"https://content.tmall.com/wow/pegasus/subject/809/651856143/9843880?wh_actSignId=34876280&amp;wh_sellerId=651856143&amp;wh_shopId=64121476&amp;wh_saleSite=1&amp;id=9843880&amp;wh_weex=true\" target=\"_blank\" rel=\"noopener\"><img src=\"https://gdp.alicdn.com/imgextra/i3/651856143/TB2hkA.x1SSBuNjy0FlXXbBpVXa-651856143.jpg\"></a></div>\n<div class=\"xitem um-center ks-switchable-panel-internal1794\"><a href=\"https://psalter.tmall.com/p/rd214023.htm\" target=\"_blank\" rel=\"noopener\"><img src=\"https://gdp.alicdn.com/imgextra/i3/651856143/TB2hTPAunJYBeNjy1zeXXahzVXa-651856143.jpg\"></a></div>\n<div class=\"xitem um-center ks-switchable-panel-internal1794\"><a href=\"https://psalter.tmall.com/p/rd924887.htm\" target=\"_blank\" rel=\"noopener\"><img src=\"https://gdp.alicdn.com/imgextra/i4/651856143/TB2qyPOukSWBuNjSszdXXbeSpXa-651856143.jpg\"></a></div>\n<div class=\"xitem um-center ks-switchable-panel-internal1794\"><a href=\"https://psalter.tmall.com/p/rd636380.htm\" target=\"_blank\" rel=\"noopener\"><img src=\"https://gdp.alicdn.com/imgextra/i1/651856143/TB2tjUKuuGSBuNjSspbXXciipXa-651856143.jpg\"></a></div>\n</div>\n</div>\n</div>\n</div>\n</div>\n</div>\n<div id=\"shop17358957223\" class=\"J_TModule\" data-widgetid=\"17358957223\" data-componentid=\"5003\" data-spm=\"110.0.5003-17358957223\" data-title=\"自定义内容区\" data-spm-max-idx=\"10\">\n<div class=\"skin-box tb-module tshop-pbsm tshop-pbsm-shop-self-defined\"><s class=\"skin-box-tp\"></s>\n<div class=\"skin-box-bd clear-fix\">\n<div class=\"rel\" data-title=\"power by dianmishu 4.0\" data-rn=\"a3lfY\">&nbsp;</div>\n</div>\n<s class=\"skin-box-bt\"></s></div>\n</div>\n</div>\n</div>\n<div id=\"description\" class=\"J_DetailSection tshop-psm tshop-psm-bdetaildes\">\n<div class=\"content ke-post\"><img id=\"desc-module-1\" class=\"desc_anchor img-ks-lazyload\" src=\"https://assets.alicdn.com/kissy/1.0.0/build/imglazyload/spaceball.gif\">\n<p><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i3/651856143/TB2oyqmlByWBuNkSmFPXXXguVXa_!!651856143.jpg\" align=\"absmiddle\"><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i2/651856143/TB2uuGvwuSSBuNjy0FlXXbBpVXa_!!651856143.jpg\" align=\"absmiddle\"></p>\n<img id=\"desc-module-2\" class=\"desc_anchor img-ks-lazyload\" src=\"https://assets.alicdn.com/kissy/1.0.0/build/imglazyload/spaceball.gif\">\n<div><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i2/651856143/TB2pgtklMvD8KJjy0FlXXagBFXa-651856143.jpg\"></div>\n<div>\n<table width=\"650\">\n<tbody>\n<tr>\n<td width=\"104\">&nbsp;</td>\n<td width=\"83\">&nbsp;</td>\n<td width=\"78\">&nbsp;</td>\n<td width=\"80\">&nbsp;</td>\n<td width=\"82\">&nbsp;</td>\n<td width=\"82\">&nbsp;</td>\n<td width=\"67\">&nbsp;</td>\n<td width=\"38\">&nbsp;</td>\n</tr>\n<tr>\n<td align=\"center\" bgcolor=\"#000000\"><strong>尺码</strong></td>\n<td align=\"center\" bgcolor=\"#000000\"><strong>肩宽</strong></td>\n<td align=\"center\" bgcolor=\"#000000\"><strong>胸围</strong></td>\n<td align=\"center\" bgcolor=\"#000000\"><strong>腰围</strong></td>\n<td align=\"center\" bgcolor=\"#000000\"><strong>臀围</strong></td>\n<td align=\"center\" bgcolor=\"#000000\"><strong>袖长</strong></td>\n<td align=\"center\" bgcolor=\"#000000\"><strong>衣长</strong></td>\n<td align=\"center\" bgcolor=\"#000000\">&nbsp;</td>\n</tr>\n<tr>\n<td align=\"center\"><strong>S(36)</strong></td>\n<td width=\"83\">-</td>\n<td width=\"78\">96</td>\n<td width=\"80\">-</td>\n<td width=\"82\">-</td>\n<td width=\"82\">66</td>\n<td width=\"67\">67</td>\n<td width=\"38\">&nbsp;</td>\n</tr>\n<tr>\n<td align=\"center\" width=\"104\"><strong>M(38)</strong></td>\n<td>-</td>\n<td>100</td>\n<td>-</td>\n<td>-</td>\n<td>67</td>\n<td>68</td>\n<td width=\"38\">&nbsp;</td>\n</tr>\n<tr>\n<td align=\"center\"><strong>L(40)</strong></td>\n<td>-</td>\n<td>105</td>\n<td>-</td>\n<td>-</td>\n<td>68</td>\n<td>70</td>\n<td width=\"38\">&nbsp;</td>\n</tr>\n<tr>\n<td align=\"center\"><strong>XL(42)</strong></td>\n<td>-</td>\n<td>110</td>\n<td>-</td>\n<td>-</td>\n<td>69</td>\n<td>72</td>\n<td width=\"38\">&nbsp;</td>\n</tr>\n<tr>\n<td align=\"center\" width=\"104\"><strong>XXL(44)</strong></td>\n<td>-</td>\n<td>117</td>\n<td>-</td>\n<td>-</td>\n<td>69</td>\n<td>74</td>\n<td width=\"38\">&nbsp;</td>\n</tr>\n<tr>\n<td colspan=\"8\" height=\"40\">*温馨提示：因手工平铺测量，与实际尺寸可能有1-3厘米误差</td>\n</tr>\n<tr>\n<td>&nbsp;</td>\n<td>&nbsp;</td>\n<td>&nbsp;</td>\n<td>&nbsp;</td>\n<td>&nbsp;</td>\n<td>&nbsp;</td>\n<td>&nbsp;</td>\n<td width=\"38\">&nbsp;</td>\n</tr>\n</tbody>\n</table>\n</div>\n<p>&nbsp;</p>\n<img id=\"desc-module-3\" class=\"desc_anchor img-ks-lazyload\" src=\"https://assets.alicdn.com/kissy/1.0.0/build/imglazyload/spaceball.gif\">\n<p><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i4/651856143/TB2HuD_tH9YBuNjy0FgXXcxcXXa_!!651856143.jpg\" align=\"absmiddle\"><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i3/651856143/TB21Tn7tH5YBuNjSspoXXbeNFXa_!!651856143.jpg\" align=\"absmiddle\"><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i2/651856143/TB2Ty_9tL5TBuNjSspmXXaDRVXa_!!651856143.jpg\" align=\"absmiddle\"><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i4/651856143/TB2xmO5tMmTBuNjy1XbXXaMrVXa_!!651856143.jpg\" align=\"absmiddle\">&nbsp;</p>\n<img id=\"desc-module-4\" class=\"desc_anchor img-ks-lazyload\" src=\"https://assets.alicdn.com/kissy/1.0.0/build/imglazyload/spaceball.gif\"><img class=\"img-ks-lazyload\" src=\"https://img.alicdn.com/imgextra/i4/651856143/TB2yBBBt9tYBeNjSspaXXaOOFXa_!!651856143.jpg\" usemap=\"#Map\" border=\"0\" data-spm-anchor-id=\"a220o.1000855.0.i4.5d2f4e7biJ5vim\"><map name=\"Map\">\n<area coords=\"553,399,786,524\" shape=\"rect\" href=\"https://detail.tmall.com/item.htm?spm=a220z.1000880.0.0.HGburQ&amp;id=568515535573\">\n<area coords=\"547,103,785,391\" shape=\"rect\" href=\"https://detail.tmall.com/item.htm?spm=a220z.1000880.0.0.HGburQ&amp;id=555984396482\">\n</map></div>\n</div>\n<div id=\"J_DcBottomRightWrap\">\n<div id=\"J_DcBottomRight\" class=\"J_DcAsyn tb-shop\">\n<div id=\"shop17340348497\" class=\"J_TModule\" data-widgetid=\"17340348497\" data-componentid=\"5003\" data-spm=\"110.0.5003-17340348497\" data-title=\"自定义内容区\">\n<div class=\"skin-box tb-module tshop-pbsm tshop-pbsm-shop-self-defined\"><s class=\"skin-box-tp\"></s>\n<div class=\"skin-box-bd clear-fix\">\n<p><img src=\"https://gdp.alicdn.com/imgextra/i1/651856143/TB2NAzwk4rI8KJjy0FpXXb5hVXa-651856143.jpg\" alt=\"\"></p>\n<p>&nbsp;</p>\n</div>\n<s class=\"skin-box-bt\"></s></div>\n</div>\n<div id=\"shop14448594700\" class=\"J_TModule\" data-widgetid=\"14448594700\" data-componentid=\"5003\" data-spm=\"110.0.5003-14448594700\" data-title=\"自定义内容区\">\n<div class=\"skin-box tb-module tshop-pbsm tshop-pbsm-shop-self-defined\"><s class=\"skin-box-tp\"></s>\n<div class=\"skin-box-bd clear-fix\"><img class=\"img-ks-lazyload\" src=\"https://gdp.alicdn.com/imgextra/i4/651856143/TB27hizo0bJ8KJjy1zjXXaqapXa-651856143.jpg\" width=\"790\" height=\"226\">&nbsp;\n<div>&nbsp;</div>\n<img class=\"img-ks-lazyload\" src=\"https://gdp.alicdn.com/imgextra/i2/651856143/TB20uzgo3nH8KJjSspcXXb3QFXa-651856143.jpg\" width=\"790\" height=\"604\" data-spm-anchor-id=\"a220o.1000855.5003-14448594700.i0.5d2f4e7biJ5vim\"></div>\n</div>\n</div>\n</div>\n</div>\n</body>\n</html>', '7', '569087692255', '0', '\0', '966.00', '1680.00', '100', '[]', '1', '0');
INSERT INTO `tb_product` VALUES ('6', '2018-06-20 17:53:32', '2018-06-20 18:15:24', '测试', '测试', 'upload/img/u/2018/06/19/KSD3.jpg', null, '3', '213123', '0', '\0', '22.00', '112.00', '100', '[{\"name\":\"测试\",\"parameterGroup\":[{\"key\":\"参数名1\",\"value\":\"参数值1\"}]}]', '1', '1');

-- ----------------------------
-- Table structure for tb_product_attachment
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_attachment`;
CREATE TABLE `tb_product_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attachmentId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `sort_num` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `attachmentId` (`attachmentId`) USING BTREE,
  KEY `productId` (`productId`),
  CONSTRAINT `tb_product_attachment_ibfk_1` FOREIGN KEY (`attachmentId`) REFERENCES `tb_attachment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_product_attachment_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `tb_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_product_attachment
-- ----------------------------

-- ----------------------------
-- Table structure for tb_product_attribute
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_attribute`;
CREATE TABLE `tb_product_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productId` int(20) NOT NULL,
  `attributeValueId` int(20) NOT NULL,
  `price` int(20) NOT NULL DEFAULT '0',
  `thumbnail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `productId` (`productId`),
  KEY `attributeValueId` (`attributeValueId`),
  CONSTRAINT `tb_product_attribute_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `tb_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_product_attribute_ibfk_2` FOREIGN KEY (`attributeValueId`) REFERENCES `tb_attribute_value` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_product_attribute
-- ----------------------------

-- ----------------------------
-- Table structure for tb_product_attribute_value
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_attribute_value`;
CREATE TABLE `tb_product_attribute_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productId` int(11) NOT NULL,
  `attributeValues` varchar(500) NOT NULL DEFAULT '' COMMENT '规格值组',
  `stock` int(255) NOT NULL DEFAULT '0' COMMENT '库存',
  `price` float(10,2) NOT NULL DEFAULT '0.00' COMMENT '价格',
  `thumbnail` varchar(255) DEFAULT '' COMMENT '规格图片',
  `SKU` varchar(50) NOT NULL COMMENT 'SKU编码',
  PRIMARY KEY (`id`),
  KEY `productId` (`productId`),
  CONSTRAINT `tb_product_attribute_value_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `tb_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_product_attribute_value
-- ----------------------------
INSERT INTO `tb_product_attribute_value` VALUES ('37', '1', '[{\"attributeId\":2,\"id\":7,\"value\":\"蓝色\"},{\"attributeId\":6,\"id\":13,\"value\":\"S\"}]', '50', '79.00', 'upload/img/u/2018/06/19/N4ZT.jpg', 'SKU713');
INSERT INTO `tb_product_attribute_value` VALUES ('38', '1', '[{\"attributeId\":2,\"id\":7,\"value\":\"蓝色\"},{\"attributeId\":6,\"id\":14,\"value\":\"M\"}]', '50', '79.00', '', 'SKU714');
INSERT INTO `tb_product_attribute_value` VALUES ('39', '3', '[{\"attributeId\":2,\"id\":7,\"value\":\"蓝色\"},{\"attributeId\":6,\"id\":13,\"value\":\"S\"}]', '38', '350.00', '', 'SKU713');
INSERT INTO `tb_product_attribute_value` VALUES ('40', '3', '[{\"attributeId\":2,\"id\":7,\"value\":\"蓝色\"},{\"attributeId\":6,\"id\":14,\"value\":\"M\"}]', '38', '350.00', '', 'SKU714');
INSERT INTO `tb_product_attribute_value` VALUES ('41', '3', '[{\"attributeId\":2,\"id\":7,\"value\":\"蓝色\"},{\"attributeId\":6,\"id\":15,\"value\":\"L\"}]', '38', '350.00', '', 'SKU715');
INSERT INTO `tb_product_attribute_value` VALUES ('42', '3', '[{\"attributeId\":2,\"id\":7,\"value\":\"蓝色\"},{\"attributeId\":6,\"id\":16,\"value\":\"XL\"}]', '38', '350.00', '', 'SKU716');
INSERT INTO `tb_product_attribute_value` VALUES ('43', '4', '[{\"attributeId\":9,\"id\":23,\"value\":\"宝石蓝\"},{\"attributeId\":10,\"id\":27,\"value\":\"4G全网通\"},{\"attributeId\":11,\"id\":28,\"value\":\"64GB\"}]', '686', '4988.00', 'upload/img/u/2018/06/19/UXHP.jpg', 'SKU232728');
INSERT INTO `tb_product_attribute_value` VALUES ('44', '4', '[{\"attributeId\":9,\"id\":23,\"value\":\"宝石蓝\"},{\"attributeId\":10,\"id\":27,\"value\":\"4G全网通\"},{\"attributeId\":11,\"id\":29,\"value\":\"128GB\"}]', '1351', '5488.00', 'upload/img/u/2018/06/19/UXHP.jpg', 'SKU232729');
INSERT INTO `tb_product_attribute_value` VALUES ('45', '4', '[{\"attributeId\":9,\"id\":23,\"value\":\"宝石蓝\"},{\"attributeId\":10,\"id\":27,\"value\":\"4G全网通\"},{\"attributeId\":11,\"id\":30,\"value\":\"256GB\"}]', '645', '6388.00', 'upload/img/u/2018/06/19/UXHP.jpg', 'SKU232730');
INSERT INTO `tb_product_attribute_value` VALUES ('46', '4', '[{\"attributeId\":9,\"id\":24,\"value\":\"亮黑色\"},{\"attributeId\":10,\"id\":27,\"value\":\"4G全网通\"},{\"attributeId\":11,\"id\":28,\"value\":\"64GB\"}]', '63', '4988.00', '', 'SKU242728');
INSERT INTO `tb_product_attribute_value` VALUES ('47', '4', '[{\"attributeId\":9,\"id\":24,\"value\":\"亮黑色\"},{\"attributeId\":10,\"id\":27,\"value\":\"4G全网通\"},{\"attributeId\":11,\"id\":29,\"value\":\"128GB\"}]', '2496', '5488.00', '', 'SKU242729');
INSERT INTO `tb_product_attribute_value` VALUES ('48', '4', '[{\"attributeId\":9,\"id\":24,\"value\":\"亮黑色\"},{\"attributeId\":10,\"id\":27,\"value\":\"4G全网通\"},{\"attributeId\":11,\"id\":30,\"value\":\"256GB\"}]', '0', '0.00', '', 'SKU242730');
INSERT INTO `tb_product_attribute_value` VALUES ('49', '4', '[{\"attributeId\":9,\"id\":25,\"value\":\"渐变极光色\"},{\"attributeId\":10,\"id\":27,\"value\":\"4G全网通\"},{\"attributeId\":11,\"id\":28,\"value\":\"64GB\"}]', '603', '4988.00', '', 'SKU252728');
INSERT INTO `tb_product_attribute_value` VALUES ('50', '4', '[{\"attributeId\":9,\"id\":25,\"value\":\"渐变极光色\"},{\"attributeId\":10,\"id\":27,\"value\":\"4G全网通\"},{\"attributeId\":11,\"id\":29,\"value\":\"128GB\"}]', '4577', '5488.00', '', 'SKU252729');
INSERT INTO `tb_product_attribute_value` VALUES ('51', '4', '[{\"attributeId\":9,\"id\":25,\"value\":\"渐变极光色\"},{\"attributeId\":10,\"id\":27,\"value\":\"4G全网通\"},{\"attributeId\":11,\"id\":30,\"value\":\"256GB\"}]', '1762', '6288.00', '', 'SKU252730');
INSERT INTO `tb_product_attribute_value` VALUES ('52', '4', '[{\"attributeId\":9,\"id\":26,\"value\":\"渐变樱粉金\"},{\"attributeId\":10,\"id\":27,\"value\":\"4G全网通\"},{\"attributeId\":11,\"id\":28,\"value\":\"64GB\"}]', '433', '4988.00', 'upload/img/u/2018/06/19/7PF4.jpg', 'SKU262728');
INSERT INTO `tb_product_attribute_value` VALUES ('53', '4', '[{\"attributeId\":9,\"id\":26,\"value\":\"渐变樱粉金\"},{\"attributeId\":10,\"id\":27,\"value\":\"4G全网通\"},{\"attributeId\":11,\"id\":29,\"value\":\"128GB\"}]', '1499', '5488.00', 'upload/img/u/2018/06/19/7PF4.jpg', 'SKU262729');
INSERT INTO `tb_product_attribute_value` VALUES ('54', '4', '[{\"attributeId\":9,\"id\":26,\"value\":\"渐变樱粉金\"},{\"attributeId\":10,\"id\":27,\"value\":\"4G全网通\"},{\"attributeId\":11,\"id\":30,\"value\":\"256GB\"}]', '0', '0.00', 'upload/img/u/2018/06/19/BTRW.jpg', 'SKU262730');
INSERT INTO `tb_product_attribute_value` VALUES ('55', '5', '[{\"attributeId\":2,\"id\":37,\"value\":\"紫色\"},{\"attributeId\":12,\"id\":31,\"value\":\"36\"}]', '13', '966.00', 'upload/img/u/2018/06/19/OGD6.jpg', 'SKU3731');
INSERT INTO `tb_product_attribute_value` VALUES ('56', '5', '[{\"attributeId\":2,\"id\":37,\"value\":\"紫色\"},{\"attributeId\":12,\"id\":33,\"value\":\"38\"}]', '15', '966.00', 'upload/img/u/2018/06/19/OGD6.jpg', 'SKU3733');
INSERT INTO `tb_product_attribute_value` VALUES ('57', '5', '[{\"attributeId\":2,\"id\":37,\"value\":\"紫色\"},{\"attributeId\":12,\"id\":35,\"value\":\"40\"}]', '15', '966.00', 'upload/img/u/2018/06/19/OGD6.jpg', 'SKU3735');
INSERT INTO `tb_product_attribute_value` VALUES ('58', '5', '[{\"attributeId\":2,\"id\":37,\"value\":\"紫色\"},{\"attributeId\":12,\"id\":36,\"value\":\"42\"}]', '12', '966.00', 'upload/img/u/2018/06/19/OGD6.jpg', 'SKU3736');
INSERT INTO `tb_product_attribute_value` VALUES ('59', '6', '[{\"attributeId\":2,\"id\":2,\"value\":\"白色\"},{\"attributeId\":6,\"id\":14,\"value\":\"M\"}]', '111', '22.00', 'upload/img/u/2018/06/19/KSD3.jpg', 'SKU214');
INSERT INTO `tb_product_attribute_value` VALUES ('60', '6', '[{\"attributeId\":2,\"id\":2,\"value\":\"白色\"},{\"attributeId\":6,\"id\":15,\"value\":\"L\"}]', '11', '22.00', 'upload/img/u/2018/06/19/KSD3.jpg', 'SKU215');

-- ----------------------------
-- Table structure for tb_product_promotion
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_promotion`;
CREATE TABLE `tb_product_promotion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productId` int(11) NOT NULL,
  `promotionId` int(11) NOT NULL,
  `discount` float DEFAULT NULL,
  `reduceMoney` float DEFAULT NULL,
  `lastPrice` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `promotionId` (`promotionId`),
  KEY `tb_product_promotion_ibfk_1` (`productId`),
  CONSTRAINT `tb_product_promotion_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `tb_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_product_promotion_ibfk_2` FOREIGN KEY (`promotionId`) REFERENCES `tb_promotion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_product_promotion
-- ----------------------------
INSERT INTO `tb_product_promotion` VALUES ('3', '1', '1', '8.9', '8.69', '70.31');
INSERT INTO `tb_product_promotion` VALUES ('8', '2', '3', '9.98', '4', '1994');

-- ----------------------------
-- Table structure for tb_promotion
-- ----------------------------
DROP TABLE IF EXISTS `tb_promotion`;
CREATE TABLE `tb_promotion` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 NOT NULL,
  `tag` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '活动标签',
  `startDate` datetime NOT NULL,
  `endDate` datetime NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `creatDate` datetime DEFAULT NULL,
  `type` smallint(4) NOT NULL COMMENT '优惠类型：0满减优惠，1打折促销',
  `reduceMoney` float(8,2) DEFAULT NULL,
  `lastSpendMoney` float(8,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_promotion
-- ----------------------------
INSERT INTO `tb_promotion` VALUES ('1', '618电商节', '618', '2018-06-18 00:00:00', '2018-06-18 23:59:59', null, '2018-06-14 13:44:21', '0', null, null);
INSERT INTO `tb_promotion` VALUES ('3', '秒杀', '秒杀', '2018-06-17 00:00:00', '2018-06-20 23:59:59', null, '2018-06-15 10:36:46', '0', null, null);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `role` varchar(50) DEFAULT '' COMMENT '角色 英文',
  `name` varchar(255) DEFAULT '' COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', 'administrator', '超级管理员');
INSERT INTO `tb_role` VALUES ('2', 'user', '普通用户');

-- ----------------------------
-- Table structure for tb_role_lanmu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_lanmu`;
CREATE TABLE `tb_role_lanmu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lanmu_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `lanmu_id` (`lanmu_id`),
  KEY `tb_role_lanmu_ibfk_2` (`role_id`),
  CONSTRAINT `tb_role_lanmu_ibfk_1` FOREIGN KEY (`lanmu_id`) REFERENCES `tb_lanmu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_role_lanmu_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_role_lanmu
-- ----------------------------
INSERT INTO `tb_role_lanmu` VALUES ('1', '25', '1');
INSERT INTO `tb_role_lanmu` VALUES ('2', '26', '1');
INSERT INTO `tb_role_lanmu` VALUES ('6', '28', '1');
INSERT INTO `tb_role_lanmu` VALUES ('7', '29', '1');
INSERT INTO `tb_role_lanmu` VALUES ('8', '30', '1');
INSERT INTO `tb_role_lanmu` VALUES ('9', '31', '1');
INSERT INTO `tb_role_lanmu` VALUES ('10', '32', '1');
INSERT INTO `tb_role_lanmu` VALUES ('13', '35', '1');
INSERT INTO `tb_role_lanmu` VALUES ('14', '36', '1');
INSERT INTO `tb_role_lanmu` VALUES ('15', '37', '1');
INSERT INTO `tb_role_lanmu` VALUES ('16', '38', '1');
INSERT INTO `tb_role_lanmu` VALUES ('17', '27', '1');
INSERT INTO `tb_role_lanmu` VALUES ('18', '39', '1');
INSERT INTO `tb_role_lanmu` VALUES ('19', '40', '1');
INSERT INTO `tb_role_lanmu` VALUES ('20', '41', '1');
INSERT INTO `tb_role_lanmu` VALUES ('21', '42', '1');
INSERT INTO `tb_role_lanmu` VALUES ('22', '43', '1');
INSERT INTO `tb_role_lanmu` VALUES ('23', '44', '1');
INSERT INTO `tb_role_lanmu` VALUES ('24', '45', '1');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(128) DEFAULT NULL COMMENT '登陆名',
  `nickname` varchar(128) DEFAULT NULL COMMENT '昵称',
  `realname` varchar(128) DEFAULT NULL COMMENT '实名',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `salt` varchar(32) DEFAULT NULL COMMENT '盐',
  `email` varchar(64) DEFAULT NULL COMMENT '邮件',
  `email_status` varchar(32) DEFAULT NULL COMMENT '邮箱状态（是否认证等）',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机电话',
  `mobile_status` varchar(32) DEFAULT NULL COMMENT '手机状态（是否认证等）',
  `telephone` varchar(32) DEFAULT NULL COMMENT '固定电话',
  `amount` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '金额（余额）',
  `gender` varchar(16) DEFAULT NULL COMMENT '性别',
  `role_id` int(11) DEFAULT NULL COMMENT '权限',
  `signature` varchar(2048) DEFAULT NULL COMMENT '签名',
  `content_count` int(11) unsigned DEFAULT '0' COMMENT '内容数量',
  `comment_count` int(11) unsigned DEFAULT '0' COMMENT '评论数量',
  `qq` varchar(16) DEFAULT NULL COMMENT 'QQ号码',
  `wechat` varchar(32) DEFAULT NULL COMMENT '微信号',
  `weibo` varchar(256) DEFAULT NULL COMMENT '微博',
  `facebook` varchar(256) DEFAULT NULL,
  `linkedin` varchar(256) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `company` varchar(256) DEFAULT NULL COMMENT '公司',
  `occupation` varchar(256) DEFAULT NULL COMMENT '职位、职业',
  `address` varchar(256) DEFAULT NULL COMMENT '地址',
  `zipcode` varchar(128) DEFAULT NULL COMMENT '邮政编码',
  `site` varchar(256) DEFAULT NULL COMMENT '个人网址',
  `graduateschool` varchar(256) DEFAULT NULL COMMENT '毕业学校',
  `education` varchar(256) DEFAULT NULL COMMENT '学历',
  `avatar` varchar(256) DEFAULT NULL COMMENT '头像',
  `idcardtype` varchar(128) DEFAULT NULL COMMENT '证件类型：身份证 护照 军官证等',
  `idcard` varchar(128) DEFAULT NULL COMMENT '证件号码',
  `status` int(32) DEFAULT '0' COMMENT '状态0正常1拉黑',
  `created` datetime DEFAULT NULL COMMENT '创建日期',
  `create_source` varchar(128) DEFAULT NULL COMMENT '用户来源（可能来之oauth第三方）',
  `logged` datetime DEFAULT NULL COMMENT '最后的登陆时间',
  `activated` datetime DEFAULT NULL COMMENT '激活时间',
  `token` varchar(255) DEFAULT '',
  `openId` varchar(100) DEFAULT NULL COMMENT '微信公众平台openId',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `mobile` (`mobile`),
  KEY `status` (`status`),
  KEY `created` (`created`),
  KEY `content_count` (`content_count`),
  KEY `comment_count` (`comment_count`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表，保存用户信息。';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'gongzhen', 'gongzhen', '', '123456', '', '', '', '', '', '', '0.00', '', '1', '0', '0', null, '', '', '', '', '', '2018-06-03 13:25:12', '', '', '', '', '', '', '', '', '', '', '0', '2018-06-03 13:25:22', '', '2018-06-03 13:25:29', '2018-06-13 15:02:00', '', null);
INSERT INTO `tb_user` VALUES ('2', 'hello', 'hello', null, '123456', null, null, null, null, null, '15813722389', '0.00', null, '2', null, '0', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', '2018-06-13 15:01:54', null, '2018-06-13 15:01:57', '2018-06-13 15:02:03', '', null);
