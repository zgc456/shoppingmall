/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : shoppingmall

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-05-21 11:57:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `authentication`
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
INSERT INTO `authentication` VALUES ('1', '张三', '河南', '洛阳', '1374856421');
INSERT INTO `authentication` VALUES ('2', '李四', '河南', '洛阳', '15239126957');

-- ----------------------------
-- Table structure for `commodity`
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `commodityName` varchar(100) NOT NULL COMMENT '商品名称',
  `bigPictureUrl` varchar(2000) NOT NULL COMMENT '商品大图片路径',
  `typeName` varchar(20) NOT NULL COMMENT '商品分类名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('1', '日系川久潮牌保玲开衫男女款情侣装爱心毛衣圆领纯羊毛针织衫外套', 'https://img.alicdn.com/imgextra/i2/725677994/TB2HhDCqxSYBuNjSsphXXbGvVXa_!!725677994.jpg_960x960Q50s50.jpg_.webp', '女装');
INSERT INTO `commodity` VALUES ('2', '花花公子防晒衣男夏季夹克男装外套超薄款韩版修身帅气防晒服外衣', '男装衣服图片', '男装');

-- ----------------------------
-- Table structure for `commodityevaluation`
-- ----------------------------
DROP TABLE IF EXISTS `commodityevaluation`;
CREATE TABLE `commodityevaluation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品评价id',
  `evaluationTime` datetime NOT NULL COMMENT '商品评价时间',
  `evaluationContent` varchar(200) NOT NULL COMMENT '商品评价内容',
  `evaluationTypeId` int(11) NOT NULL COMMENT '商品评价类型(满意3/一般2/差1)',
  `userId` int(11) NOT NULL COMMENT '所属用户_外键 User(用户表)',
  `commodityId` int(11) NOT NULL COMMENT '商品id 外键(商品表)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品评价表';

-- ----------------------------
-- Records of commodityevaluation
-- ----------------------------

-- ----------------------------
-- Table structure for `commodityintroducepicture`
-- ----------------------------
DROP TABLE IF EXISTS `commodityintroducepicture`;
CREATE TABLE `commodityintroducepicture` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品图片id',
  `pictureUrl` varchar(2000) NOT NULL COMMENT '商品图片路径',
  `commodityId` mediumtext NOT NULL COMMENT '外键_商品表id',
  `levels` int(11) NOT NULL COMMENT '区分图片是标题图片还是详细图片',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商品图片表';

-- ----------------------------
-- Records of commodityintroducepicture
-- ----------------------------
INSERT INTO `commodityintroducepicture` VALUES ('1', 'https://img.alicdn.com/imgextra/i3/725677994/TB2_4.an8DH8KJjSszcXXbDTFXa_!!725677994.jpg_1152x1920Q50s50.jpg_.webphttps://img.alicdn.com/imgextra/i3/725677994/TB2_4.an8DH8KJjSszcXXbDTFXa_!!725677994.jpg_1152x1920Q50s50.jpg_.webp', '1', '0');
INSERT INTO `commodityintroducepicture` VALUES ('2', 'https://img.alicdn.com/imgextra/i2/725677994/TB2D5acXWmWBuNjy1XaXXXCbXXa_!!725677994.jpg_960x960Q50s50.jpg_.webp', '1', '0');
INSERT INTO `commodityintroducepicture` VALUES ('3', 'https://img.alicdn.com/imgextra/i3/725677994/TB2QgbwbTXYBeNkHFrdXXciuVXa_!!725677994.jpg_960x960Q50s50.jpg_.webp', '1', '0');

-- ----------------------------
-- Table structure for `commodity_specification_inventory_price`
-- ----------------------------
DROP TABLE IF EXISTS `commodity_specification_inventory_price`;
CREATE TABLE `commodity_specification_inventory_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commodityId` int(11) NOT NULL COMMENT '商品ID(外键_商品表主键)',
  `specification1` int(11) NOT NULL,
  `specification2` int(11) DEFAULT NULL,
  `specification3` int(11) DEFAULT NULL,
  `specification4` int(11) DEFAULT NULL,
  `inventory` int(11) NOT NULL COMMENT '库存量',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `picture` varchar(200) NOT NULL COMMENT '小图片',
  PRIMARY KEY (`id`),
  UNIQUE KEY `commodity_specification_inventory_price_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格库存价格表(包括小图片)';

-- ----------------------------
-- Records of commodity_specification_inventory_price
-- ----------------------------
INSERT INTO `commodity_specification_inventory_price` VALUES ('1', '1', '1', '2', '4', '5', '89', '345.60', '小图片');
INSERT INTO `commodity_specification_inventory_price` VALUES ('2', '2', '1', '2', '4', '6', '50', '89.90', '商品小图片');

-- ----------------------------
-- Table structure for `commodity_specification_relation`
-- ----------------------------
DROP TABLE IF EXISTS `commodity_specification_relation`;
CREATE TABLE `commodity_specification_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `specificationName` varchar(20) NOT NULL COMMENT '商品规格名称',
  `levels` int(11) NOT NULL COMMENT '商品规格层级id',
  `parentId` int(11) NOT NULL COMMENT '商品规格父规格id(没有夫规格填0)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `commodity_specification_relation_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格关系表';

-- ----------------------------
-- Records of commodity_specification_relation
-- ----------------------------
INSERT INTO `commodity_specification_relation` VALUES ('1', '颜色', '1', '0');
INSERT INTO `commodity_specification_relation` VALUES ('2', '黑色', '2', '1');
INSERT INTO `commodity_specification_relation` VALUES ('3', '红色', '2', '1');
INSERT INTO `commodity_specification_relation` VALUES ('4', '尺码', '1', '0');
INSERT INTO `commodity_specification_relation` VALUES ('5', 's', '2', '4');
INSERT INTO `commodity_specification_relation` VALUES ('6', 'm', '2', '4');

-- ----------------------------
-- Table structure for `customerservice`
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
-- Table structure for `discount`
-- ----------------------------
DROP TABLE IF EXISTS `discount`;
CREATE TABLE `discount` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '折扣id',
  `discountPrice` decimal(10,2) DEFAULT NULL COMMENT '折扣价格',
  `discountIntroduce` varchar(50) DEFAULT NULL COMMENT '折扣介绍',
  `discountTypeId` int(11) DEFAULT NULL COMMENT '折扣类型id_外键 Type(类型表)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品折扣表';

-- ----------------------------
-- Records of discount
-- ----------------------------

-- ----------------------------
-- Table structure for `discounttype`
-- ----------------------------
DROP TABLE IF EXISTS `discounttype`;
CREATE TABLE `discounttype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `typeName` varchar(50) DEFAULT NULL COMMENT '类型名称',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品折扣类型表';

-- ----------------------------
-- Records of discounttype
-- ----------------------------

-- ----------------------------
-- Table structure for `harvestaddress`
-- ----------------------------
DROP TABLE IF EXISTS `harvestaddress`;
CREATE TABLE `harvestaddress` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收货地址id',
  `harvestAddressName` varchar(50) NOT NULL COMMENT '收货地址',
  `harvestIsDefault` int(11) DEFAULT '0' COMMENT '该收货地址是否默认',
  `typeId` int(11) DEFAULT NULL COMMENT '收货地址类型_外键 Type(类型表)',
  `userId` int(11) DEFAULT NULL COMMENT '用户id_外键 User(用户表)',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of harvestaddress
-- ----------------------------

-- ----------------------------
-- Table structure for `mycollect`
-- ----------------------------
DROP TABLE IF EXISTS `mycollect`;
CREATE TABLE `mycollect` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '我的收藏id',
  `userId` int(11) DEFAULT NULL COMMENT '用户id_外键 User(用户表)',
  `commodityId` int(11) DEFAULT NULL COMMENT '商品id_外键 Commdity(商品表)',
  `commodityIntroduce` varchar(200) DEFAULT NULL COMMENT '商品介绍',
  `commodityPrice` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `smallPictureUrl` varchar(100) DEFAULT NULL COMMENT '商品小图片路径',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mycollect
-- ----------------------------
INSERT INTO `mycollect` VALUES ('1', '1', '1', '韩国进口 海太蜂蜜', '29.90', 'https://img..com/imgextra/i4/725677994/TB2mSCGeZuYBuNkSmRyXXcA3pXa_!!725677994-0-sm.jpgalicdn');

-- ----------------------------
-- Table structure for `orderfrom`
-- ----------------------------
DROP TABLE IF EXISTS `orderfrom`;
CREATE TABLE `orderfrom` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `orderNumber` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `orderCreationTime` datetime DEFAULT NULL COMMENT '订单创建时间',
  `orderEndTime` datetime DEFAULT NULL COMMENT '订单结束时间',
  `userId` int(11) DEFAULT NULL COMMENT '用户编号_外键 User(用户表)',
  `paymentTypeId` int(11) DEFAULT NULL COMMENT '订单支付状态_外键 Type(类型表)',
  `orderfromPrice` decimal(10,2) DEFAULT NULL COMMENT '订单总价',
  `harvestAddressId` int(11) DEFAULT NULL COMMENT '订单收货地址id_外键 harvestAddress',
  `transactionNumber` varchar(50) DEFAULT NULL COMMENT '支付宝交易号',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderfrom
-- ----------------------------

-- ----------------------------
-- Table structure for `orderfromshop0`
-- ----------------------------
DROP TABLE IF EXISTS `orderfromshop0`;
CREATE TABLE `orderfromshop0` (
  `id` int(11) DEFAULT NULL COMMENT '订单商品id',
  `feight` decimal(10,2) DEFAULT NULL COMMENT '运费',
  `commodityPrice` decimal(10,2) DEFAULT NULL COMMENT '商品实际购买价格',
  `commodityNumber` int(11) DEFAULT NULL COMMENT '商品实际购买数量',
  `logisticsTypeId` int(11) DEFAULT NULL COMMENT '订单商品物流状态_外键 Type(类型表)',
  `commodityId` int(11) DEFAULT NULL COMMENT '订单商品id_外键 commodity(商品表)',
  `orderFromId` int(11) DEFAULT NULL COMMENT '订单id_外键 orderFrom(订单表)',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderfromshop0
-- ----------------------------

-- ----------------------------
-- Table structure for `orderfromshop1`
-- ----------------------------
DROP TABLE IF EXISTS `orderfromshop1`;
CREATE TABLE `orderfromshop1` (
  `id` int(11) DEFAULT NULL COMMENT '订单商品id',
  `feight` decimal(10,2) DEFAULT NULL COMMENT '运费',
  `commodityPrice` decimal(10,2) DEFAULT NULL COMMENT '商品实际购买价格',
  `commodityNumber` int(11) DEFAULT NULL COMMENT '商品实际购买数量',
  `logisticsTypeId` int(11) DEFAULT NULL COMMENT '订单商品物流状态_外键 Type(类型表)',
  `commodityId` int(11) DEFAULT NULL COMMENT '订单商品信息_外键 commodity(商品表)',
  `orderFromId` int(11) DEFAULT NULL COMMENT '订单id_外键 orderFrom(订单表)',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderfromshop1
-- ----------------------------

-- ----------------------------
-- Table structure for `orderfromtype`
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
-- Table structure for `promotionitem`
-- ----------------------------
DROP TABLE IF EXISTS `promotionitem`;
CREATE TABLE `promotionitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '促销商品id',
  `startTime` datetime NOT NULL COMMENT '促销开始时间',
  `endTime` datetime NOT NULL COMMENT '促销结束时间',
  `discountPrice` decimal(10,2) NOT NULL COMMENT '商品促销价格',
  `commodityNumber` int(11) NOT NULL COMMENT '促销商品数量',
  `commodityId` int(11) NOT NULL COMMENT '商品原信息_外键 Commdity(商品表)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品促销表';

-- ----------------------------
-- Records of promotionitem
-- ----------------------------

-- ----------------------------
-- Table structure for `shoppingcart`
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `commodityNumber` int(11) DEFAULT NULL COMMENT '商品数量',
  `commodityId` int(11) DEFAULT NULL COMMENT '商品id_外键 Commodity(商品表)',
  `commodityName` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `commoditySIPId` int(11) DEFAULT NULL COMMENT '库存id对应详情，commodity_specification_inventory_price外键',
  `userId` int(11) DEFAULT NULL COMMENT '用户id_外键 User(用户表)',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------

-- ----------------------------
-- Table structure for `specificationsdetailed`
-- ----------------------------
DROP TABLE IF EXISTS `specificationsdetailed`;
CREATE TABLE `specificationsdetailed` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品规格详细id',
  `dName` varchar(50) NOT NULL COMMENT '商品规格详细名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格详细表';

-- ----------------------------
-- Records of specificationsdetailed
-- ----------------------------
INSERT INTO `specificationsdetailed` VALUES ('1', '黑色');
INSERT INTO `specificationsdetailed` VALUES ('2', '白色');
INSERT INTO `specificationsdetailed` VALUES ('3', 's');
INSERT INTO `specificationsdetailed` VALUES ('4', 'm');
INSERT INTO `specificationsdetailed` VALUES ('5', 'x');

-- ----------------------------
-- Table structure for `specificationstopic`
-- ----------------------------
DROP TABLE IF EXISTS `specificationstopic`;
CREATE TABLE `specificationstopic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品规格标题id',
  `name` varchar(20) DEFAULT NULL COMMENT '商品规格标题名称',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格表';

-- ----------------------------
-- Records of specificationstopic
-- ----------------------------
INSERT INTO `specificationstopic` VALUES ('1', '尺码');
INSERT INTO `specificationstopic` VALUES ('2', '颜色');

-- ----------------------------
-- Table structure for `transactionrecord`
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
-- Table structure for `type`
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `typeName` varchar(50) NOT NULL COMMENT '类型名称',
  `levels` int(11) NOT NULL COMMENT '区分分类层级',
  `parentId` int(11) NOT NULL COMMENT '分类的父分类Id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类型表';

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('2', '衣服', '1', '0');
INSERT INTO `type` VALUES ('3', '男装', '2', '2');
INSERT INTO `type` VALUES ('4', '女装', '2', '2');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `nickName` varchar(20) NOT NULL COMMENT '用户昵称',
  `loginName` varchar(20) NOT NULL COMMENT '登录名称',
  `logingPassword` varchar(20) NOT NULL COMMENT '登录密码',
  `headPortraitUrl` varchar(100) NOT NULL COMMENT '头像路径',
  `userTypeName` varchar(20) NOT NULL COMMENT '用户账号类型',
  `authenticationId` int(11) NOT NULL COMMENT '实名认证id_外键 Authentication(实名认证表)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for `userdiscount`
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
-- Table structure for `usertype`
-- ----------------------------
DROP TABLE IF EXISTS `usertype`;
CREATE TABLE `usertype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `userTypeName` varchar(50) NOT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户类型表';

-- ----------------------------
-- Records of usertype
-- ----------------------------
