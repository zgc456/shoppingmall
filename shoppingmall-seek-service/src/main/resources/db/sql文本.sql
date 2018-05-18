/*
Navicat MySQL Data Transfer

Source Server         : centos
Source Server Version : 50722
Source Host           : 47.95.240.252:3306
Source Database       : shoppingMall

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-04-28 14:28:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `commodity`
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `commodityName` varchar(20) DEFAULT NULL COMMENT '商品名称',
  `commodityIntroduce` varchar(200) DEFAULT NULL COMMENT '商品介绍',
  `bigPictureUrl` varchar(100) DEFAULT '' COMMENT '商品大图片路径',
  `commodityTypeRelationId` int(11) DEFAULT NULL COMMENT '商品分类id_外键 commodityTypeRelation(商品分类关系表)',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('1', '牛奶', '营养价值高', '', '1');
INSERT INTO `commodity` VALUES ('2', '面包', '吃的', '', '2');
INSERT INTO `commodity` VALUES ('3', '十万个为什么', '有益于儿童和少儿的书籍', '', '1');
INSERT INTO `commodity` VALUES ('4', 'iphoneX', '来自美国的手机', '', '1');
INSERT INTO `commodity` VALUES ('5', '平底锅', '炒菜，红太狼打灰太狼，吃鸡必备神器', '', '1');
INSERT INTO `commodity` VALUES ('6', '中兴手机', '中国制造的', '', null);
INSERT INTO `commodity` VALUES ('7', '中鼎手机', '中鼎制造的', '', null);
INSERT INTO `commodity` VALUES ('8', '中俄制造', '中俄制造的', '', null);

-- ----------------------------
-- Table structure for `commoditytyperelation`
-- ----------------------------
DROP TABLE IF EXISTS `commoditytyperelation`;
CREATE TABLE `commoditytyperelation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品分类关系id',
  `shopPrimaryTypeId` int(11) DEFAULT NULL COMMENT '商品一级分类_外键 Type(类型表)',
  `shopMinorTypeId` int(11) DEFAULT NULL COMMENT '商品二级分类_外键 Type(类型表)',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commoditytyperelation
-- ----------------------------
INSERT INTO `commoditytyperelation` VALUES ('1', '1', '2');
INSERT INTO `commoditytyperelation` VALUES ('2', '1', '3');
INSERT INTO `commoditytyperelation` VALUES ('3', '2', '3');

-- ----------------------------
-- Table structure for `discount`
-- ----------------------------
DROP TABLE IF EXISTS `discount`;
CREATE TABLE `discount` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '折扣id',
  `discountPrice` decimal(10,2) DEFAULT NULL COMMENT '折扣价格',
  `discountIntroduce` varchar(50) DEFAULT NULL COMMENT '折扣介绍',
  `discountTypeId` int(11) DEFAULT NULL COMMENT '折扣类型id_外键 Type(类型表)',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discount
-- ----------------------------

-- ----------------------------
-- Table structure for `promotionitem`
-- ----------------------------
DROP TABLE IF EXISTS `promotionitem`;
CREATE TABLE `promotionitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '促销商品id',
  `startTime` datetime DEFAULT NULL COMMENT '促销开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '促销结束时间',
  `discountPrice` decimal(10,2) DEFAULT NULL COMMENT '商品促销价格',
  `commodityNumber` int(11) DEFAULT NULL COMMENT '促销商品数量',
  `commodityId` int(11) DEFAULT NULL COMMENT '商品原信息_外键 Commdity(商品表)',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of promotionitem
-- ----------------------------

-- ----------------------------
-- Table structure for `specificationsdetailed`
-- ----------------------------
DROP TABLE IF EXISTS `specificationsdetailed`;
CREATE TABLE `specificationsdetailed` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品规格详细id',
  `dName` varchar(50) DEFAULT NULL COMMENT '商品规格详细名称',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of specificationsdetailed
-- ----------------------------
INSERT INTO `specificationsdetailed` VALUES ('1', '黑色');
INSERT INTO `specificationsdetailed` VALUES ('2', '白色');
INSERT INTO `specificationsdetailed` VALUES ('3', '蓝色');
INSERT INTO `specificationsdetailed` VALUES ('4', 'M');
INSERT INTO `specificationsdetailed` VALUES ('5', 'L');
INSERT INTO `specificationsdetailed` VALUES ('6', 'M');

-- ----------------------------
-- Table structure for `specificationsrelation`
-- ----------------------------
DROP TABLE IF EXISTS `specificationsrelation`;
CREATE TABLE `specificationsrelation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品规格关系id',
  `commodityNumber` int(11) DEFAULT NULL COMMENT '商品数量',
  `commodityPrice` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `commodityId` int(11) DEFAULT NULL COMMENT '商品id_外键 commodity(商品表)',
  `smallPictureUrl` varchar(100) DEFAULT '' COMMENT '商品小图片路径',
  `typeId` int(11) DEFAULT '0' COMMENT '商品促销状态_外键 Type(类型表)',
  `speciTopicId` int(11) DEFAULT '0' COMMENT '商品规格标题_外键 specificationsTopic(商品规格标题表)',
  `speciDetailedId` int(11) DEFAULT '0' COMMENT '商品规格详细_外键 sepcificationsDetailed(商品规格详细表)',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of specificationsrelation
-- ----------------------------
INSERT INTO `specificationsrelation` VALUES ('1', '100', '10.00', '1', 'd:\\\\pp.png', '6', '1', '1');
INSERT INTO `specificationsrelation` VALUES ('2', '200', '2.00', '2', 'd:\\\\pp.png', '6', '1', '1');
INSERT INTO `specificationsrelation` VALUES ('3', '10', '50.00', '1', '', '0', '0', '0');

-- ----------------------------
-- Table structure for `specificationstopic`
-- ----------------------------
DROP TABLE IF EXISTS `specificationstopic`;
CREATE TABLE `specificationstopic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品规格标题id',
  `name` varchar(20) DEFAULT NULL COMMENT '商品规格标题名称',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of specificationstopic
-- ----------------------------
INSERT INTO `specificationstopic` VALUES ('1', '颜色');
INSERT INTO `specificationstopic` VALUES ('2', '尺寸');

-- ----------------------------
-- Table structure for `type`
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `typeName` varchar(50) DEFAULT NULL COMMENT '类型名称',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('1', '食物');
INSERT INTO `type` VALUES ('2', '饮品');
INSERT INTO `type` VALUES ('3', '金属');
INSERT INTO `type` VALUES ('4', '手机');
INSERT INTO `type` VALUES ('5', '厨具');
INSERT INTO `type` VALUES ('6', '书');
