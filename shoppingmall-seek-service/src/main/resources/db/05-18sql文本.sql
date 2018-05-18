/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : shoppingmall

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-05-18 11:45:13
*/

use shoppingMall;

-- ----------------------------
-- Table structure for authentication
-- ----------------------------
DROP TABLE IF EXISTS `authentication`;
CREATE TABLE `authentication` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '实名认证id',
  `userName` varchar(20) DEFAULT NULL COMMENT '用户真实姓名',
  `userAboutAddress` varchar(50) DEFAULT NULL COMMENT '用户大概地址',
  `userAddress` varchar(50) DEFAULT NULL COMMENT '用户真实地址',
  `userPhoneNumber` varchar(20) DEFAULT NULL COMMENT '用户真实电话',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authentication
-- ----------------------------

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `commodityName` varchar(20) DEFAULT NULL COMMENT '商品名称',
  `commodityIntroduce` varchar(2000) DEFAULT NULL COMMENT '商品介绍',
  `bigPictureUrl` varchar(100) DEFAULT NULL COMMENT '商品大图片路径',
  `commodityTypeRelationId` int(11) DEFAULT NULL COMMENT '商品分类id_外键 commodityTypeRelation(商品分类关系表)',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('1', '特步', '这是一双鞋', null, '1');

-- ----------------------------
-- Table structure for commodityevaluation
-- ----------------------------
DROP TABLE IF EXISTS `commodityevaluation`;
CREATE TABLE `commodityevaluation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品评价id',
  `evaluationTime` datetime DEFAULT NULL COMMENT '商品评价时间',
  `evaluationContent` varchar(200) DEFAULT NULL COMMENT '商品评价内容',
  `evaluationTypeId` int(11) DEFAULT NULL COMMENT '商品评价状态_外键 Type(类型表)',
  `userId` int(11) DEFAULT NULL COMMENT '所属用户_外键 User(用户表)',
  `commodityId` int(11) DEFAULT NULL COMMENT '商品id 外键(商品表)',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commodityevaluation
-- ----------------------------

-- ----------------------------
-- Table structure for commodityinventory
-- ----------------------------
DROP TABLE IF EXISTS `commodityinventory`;
CREATE TABLE `commodityinventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  `commodityNumber` int(11) DEFAULT NULL COMMENT '商品数量',
  `commodityPrice` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `commodityId` int(11) DEFAULT NULL COMMENT '商品id_外键 commodity(商品表)',
  `commoditySku` varchar(200) DEFAULT NULL COMMENT '商品规格关系表',
  `smallPictureUrl` varchar(100) DEFAULT NULL COMMENT '商品小图片路径',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commodityinventory
-- ----------------------------
INSERT INTO `commodityinventory` VALUES ('1', '20', '120.00', '1', '{\"commoditySku\": [{\"topicId\": 2,\"detailedId\": 1},{\"topicId\": 1,\"detailedId\": 3}]}','小图片');
INSERT INTO `commodityinventory` VALUES ('2', '20', '120.00', '1', '{\"commoditySku\": [{\"topicId\": 2,\"detailedId\": 2},{\"topicId\": 1,\"detailedId\": 4}]}','小图片');

-- ----------------------------
-- Table structure for commoditytyperelation
-- ----------------------------
DROP TABLE IF EXISTS `commoditytyperelation`;
CREATE TABLE `commoditytyperelation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品分类关系id',
  `shopPrimaryTypeId` int(11) DEFAULT NULL COMMENT '商品一级分类_外键 Type(类型表)',
  `shopMinorTypeId` int(11) DEFAULT NULL COMMENT '商品二级分类_外键 Type(类型表)',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commoditytyperelation
-- ----------------------------
INSERT INTO `commoditytyperelation` VALUES ('1', '1', '2');

-- ----------------------------
-- Table structure for customerservice
-- ----------------------------
DROP TABLE IF EXISTS `customerservice`;
CREATE TABLE `customerservice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '售后id',
  `causeOfReturn` varchar(200) DEFAULT NULL COMMENT '退货原因',
  `demandTime` int(11) DEFAULT NULL COMMENT '需求时间',
  `acceptanceTime` datetime DEFAULT NULL COMMENT '受理时间',
  `commitTime` datetime DEFAULT NULL COMMENT '提交时间',
  `typeId` int(11) DEFAULT NULL COMMENT '类型id_外键 Type(类型表)',
  `orderId` int(11) DEFAULT NULL COMMENT '订单id_外键 OrderFrom(订单表)',
  `userId` int(11) DEFAULT NULL COMMENT '用户id_外键 User(用户表)',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customerservice
-- ----------------------------

-- ----------------------------
-- Table structure for discount
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
-- Table structure for discounttype
-- ----------------------------
DROP TABLE IF EXISTS `discounttype`;
CREATE TABLE `discounttype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `typeName` varchar(50) DEFAULT NULL COMMENT '类型名称',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discounttype
-- ----------------------------

-- ----------------------------
-- Table structure for harvestaddress
-- ----------------------------
DROP TABLE IF EXISTS `harvestaddress`;
CREATE TABLE `harvestaddress` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收货地址id',
  `harvestAddressName` varchar(50) NOT NULL COMMENT '收货地址',
  `harvestIsDefault` int(11) DEFAULT '0' COMMENT '该收货地址是否默认',
  `typeId` int(11) DEFAULT NULL COMMENT '收货地址类型_外键 Type(类型表)',
  `userId` int(11) DEFAULT NULL COMMENT '用户id_外键 User(用户表)',
  `userName` varchar(50) DEFAULT NULL COMMENT '收获地址名字',
  `userPhone` varchar(50) DEFAULT NULL COMMENT '收货地址电话',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of harvestaddress
-- ----------------------------

-- ----------------------------
-- Table structure for mycollect
-- ----------------------------
DROP TABLE IF EXISTS `mycollect`;
CREATE TABLE `mycollect` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '我的收藏id',
  `userId` int(11) DEFAULT NULL COMMENT '用户id_外键 User(用户表)',
  `commodityId` int(11) DEFAULT NULL COMMENT '商品id_外键 Commodity(商品表)',
  `commodityIntroduce` varchar(200) DEFAULT NULL COMMENT '商品介绍',
  `commodityPrice` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `smallPictureUrl` varchar(100) DEFAULT NULL COMMENT '商品小图片路径',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for orderfromshop0
-- ----------------------------
DROP TABLE IF EXISTS `orderfromshop0`;
CREATE TABLE `orderfromshop0` (
  `id` int(11) DEFAULT NULL COMMENT '订单商品id',
  `feight` decimal(10,2) DEFAULT NULL COMMENT '运费',
  `commodityPrice` decimal(10,2) DEFAULT NULL COMMENT '商品实际购买价格',
  `commodityNumber` int(11) DEFAULT NULL COMMENT '商品实际购买数量',
  `logisticsTypeId` int(11) DEFAULT NULL COMMENT '订单商品物流状态_外键 Type(类型表)',
  `commodityId` int(11) DEFAULT NULL COMMENT '订单商品id_外键 commodity(商品表)',
  `orderFromId` varchar(50) DEFAULT NULL COMMENT '订单id_外键 orderFrom(订单表)',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderfromshop0
-- ----------------------------

-- ----------------------------
-- Table structure for orderfromshop1
-- ----------------------------
DROP TABLE IF EXISTS `orderfromshop1`;
CREATE TABLE `orderfromshop1` (
  `id` int(11) DEFAULT NULL COMMENT '订单商品id',
  `feight` decimal(10,2) DEFAULT NULL COMMENT '运费',
  `commodityPrice` decimal(10,2) DEFAULT NULL COMMENT '商品实际购买价格',
  `commodityNumber` int(11) DEFAULT NULL COMMENT '商品实际购买数量',
  `logisticsTypeId` int(11) DEFAULT NULL COMMENT '订单商品物流状态_外键 Type(类型表)',
  `commodityId` int(11) DEFAULT NULL COMMENT '订单商品信息_外键 commodity(商品表)',
  `orderFromId` varchar(50) DEFAULT NULL COMMENT '订单id_外键 orderFrom(订单表)',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderfromshop1
-- ----------------------------

-- ----------------------------
-- Table structure for orderfromtype
-- ----------------------------
DROP TABLE IF EXISTS `orderfromtype`;
CREATE TABLE `orderfromtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `typeName` varchar(50) DEFAULT NULL COMMENT '类型名称',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderfromtype
-- ----------------------------

-- ----------------------------
-- Table structure for promotionitem
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
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `commodityNumber` int(11) DEFAULT NULL COMMENT '商品数量',
  `commodityPrice` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `commodityId` int(11) DEFAULT NULL COMMENT '商品id_外键 Commodity(商品表)',
  `smallPictureUrl` varchar(100) DEFAULT NULL COMMENT '商品小图片路径',
  `commodityIntroduce` varchar(200) DEFAULT NULL COMMENT '商品介绍',
  `userId` int(11) DEFAULT NULL COMMENT '用户id_外键 User(用户表)',
  `commodityInventoryId` int(11) DEFAULT NULL COMMENT '商品库存id_外键 (commodityInventory)',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------

-- ----------------------------
-- Table structure for specificationsdetailed
-- ----------------------------
DROP TABLE IF EXISTS `specificationsdetailed`;
CREATE TABLE `specificationsdetailed` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品规格详细id',
  `dName` varchar(50) DEFAULT NULL COMMENT '商品规格详细名称',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of specificationsdetailed
-- ----------------------------
INSERT INTO `specificationsdetailed` VALUES ('1', '黑色');
INSERT INTO `specificationsdetailed` VALUES ('2', '白色');
INSERT INTO `specificationsdetailed` VALUES ('3', '41');
INSERT INTO `specificationsdetailed` VALUES ('4', '42');

-- ----------------------------
-- Table structure for specificationsrelation
-- ----------------------------
DROP TABLE IF EXISTS `specificationsrelation`;
CREATE TABLE `specificationsrelation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品规格关系id',
  `specificationSku` varchar(200) DEFAULT NULL COMMENT '商品规格关系',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of specificationsrelation
-- ----------------------------
INSERT INTO `specificationsrelation` VALUES ('1', '{\"topicId\": 1,\"detailedId\": [3, 4]}');
INSERT INTO `specificationsrelation` VALUES ('2', '{\"topicId\": 2,\"detailedId\": [1, 2]}');

-- ----------------------------
-- Table structure for specificationstopic
-- ----------------------------
DROP TABLE IF EXISTS `specificationstopic`;
CREATE TABLE `specificationstopic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品规格标题id',
  `name` varchar(20) DEFAULT NULL COMMENT '商品规格标题名称',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of specificationstopic
-- ----------------------------
INSERT INTO `specificationstopic` VALUES ('1', '尺码');
INSERT INTO `specificationstopic` VALUES ('2', '颜色');

-- ----------------------------
-- Table structure for transactionrecord
-- ----------------------------
DROP TABLE IF EXISTS `transactionrecord`;
CREATE TABLE `transactionrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '交易记录id',
  `creationTime` datetime DEFAULT NULL COMMENT '创建时间',
  `orderId` int(11) DEFAULT NULL COMMENT '订单id_外键 OrderFrom(订单表)',
  `userId` int(11) DEFAULT NULL COMMENT '用户id_外键 User(用户表)',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of transactionrecord
-- ----------------------------

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `typeName` varchar(50) DEFAULT NULL COMMENT '类型名称',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('1', '鞋');
INSERT INTO `type` VALUES ('2', '运动鞋');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `nickName` varchar(20) NOT NULL COMMENT '用户昵称',
  `loginName` varchar(20) DEFAULT NULL COMMENT '登录名称',
  `logingPassword` varchar(20) DEFAULT NULL COMMENT '登录密码',
  `headPortraitUrl` varchar(100) DEFAULT NULL COMMENT '头像路径',
  `userTypeId` int(11) DEFAULT NULL COMMENT '用户账号类型',
  `authenticationId` int(11) DEFAULT NULL COMMENT '实名认证id_外键 Authentication(实名认证表)',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for userdiscount
-- ----------------------------
DROP TABLE IF EXISTS `userdiscount`;
CREATE TABLE `userdiscount` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userId` int(11) DEFAULT NULL COMMENT '用户id_外键 User(用户表)',
  `discountId` int(11) DEFAULT NULL COMMENT '折扣id_外键 discount(折扣表) ',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userdiscount
-- ----------------------------

-- ----------------------------
-- Table structure for usertype
-- ----------------------------
DROP TABLE IF EXISTS `usertype`;
CREATE TABLE `usertype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `userTypeName` varchar(50) DEFAULT NULL COMMENT '类型名称',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usertype
-- ----------------------------
