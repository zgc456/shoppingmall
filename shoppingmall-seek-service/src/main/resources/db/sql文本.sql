#创建 shopping_mall 数据库
DROP DATABASE IF EXISTS `shoppingMall`;
CREATE DATABASE IF NOT EXISTS `shoppingMall` CHARSET 'utf8';
#指向 shoppingMall 数据库
USE `shoppingMall`;
#创建 user(用户表)
DROP TABLE IF EXISTS `user`;
CREATE TABLE  `user`(
  `id` INT AUTO_INCREMENT UNIQUE COMMENT '用户id',
  `nickName` VARCHAR(20) NOT NULL COMMENT '用户昵称',
  `loginName` VARCHAR(20) COMMENT '登录名称',
  `logingPassword` VARCHAR(20) COMMENT	'登录密码',
  `headPortraitUrl` VARCHAR(100) COMMENT '头像路径',
  `userTypeId` INT COMMENT '用户账号类型',
  `authenticationId` INT COMMENT '实名认证id_外键 Authentication(实名认证表)'
)CHARSET 'utf8';
#创建 authentication(实名认证表)
DROP TABLE IF EXISTS `authentication`;
CREATE TABLE `authentication`(
  `id` INT AUTO_INCREMENT UNIQUE COMMENT '实名认证id',
  `userName` VARCHAR(20) COMMENT '用户真实姓名',
  `userAboutAddress` VARCHAR(50) COMMENT '用户大概地址',
  `userAddress` VARCHAR(50) COMMENT '用户真实地址',
  `userPhoneNumber` VARCHAR(20) COMMENT '用户真实电话'
)CHARSET 'utf8';
#创建 myCollect(我的收藏表)
DROP TABLE IF EXISTS `myCollect`;
CREATE TABLE `myCollect`(
  `id` INT AUTO_INCREMENT UNIQUE COMMENT '我的收藏id',
  `userId` INT COMMENT '用户id_外键 User(用户表)',
  `commdityId` INT COMMENT '商品id_外键 Commdity(商品表)'
)CHARSET 'utf8';
#创建 shoppingCart(购物车表)
DROP TABLE IF EXISTS `shoppingCart`;
CREATE TABLE `shoppingCart`(
  `id` INT AUTO_INCREMENT UNIQUE COMMENT '购物车id',
  `commdityNumber` INT COMMENT '商品数量',
  `commdityPrice` DECIMAL COMMENT '商品价格',
  `commdityId` INT COMMENT '商品id_外键 Commdity(商品表)',
  `userId` INT COMMENT '用户id_外键 User(用户表)'
)CHARSET 'utf8';
#创建 harvestAddress(收货地址表)
DROP TABLE IF EXISTS `harvestAddress`;
CREATE TABLE `harvestAddress`(
  `id` INT AUTO_INCREMENT UNIQUE COMMENT '收货地址id',
  `harvestAddressName` VARCHAR(50) NOT NULL COMMENT '收货地址',
  `harvestIsDefault` INT DEFAULT 0 COMMENT '该收货地址是否默认',
  `typeId` INT COMMENT '收货地址类型_外键 Type(类型表)',
  `userId` INT COMMENT '用户id_外键 User(用户表)'
)CHARSET 'utf8';
#创建 orderFrom(订单表)
DROP TABLE IF EXISTS `orderFrom`;
CREATE TABLE `orderFrom`(
  `id` INT AUTO_INCREMENT UNIQUE COMMENT '订单id',
  `orderNumber` VARCHAR(50) COMMENT '订单编号',
  `orderCreationTime` DATETIME COMMENT '订单创建时间',
  `orderEndTime` DATETIME COMMENT '订单结束时间',
  `userId` INT COMMENT '用户编号_外键 User(用户表)',
  `paymentTypeId` INT COMMENT '订单支付状态_外键 Type(类型表)',
  `logisticsTypeId` INT COMMENT '订单物流状态_外键 Type(类型表)'
)CHARSET 'utf8';
#创建 orderFromShop(订单商品表) 分表
DROP TABLE IF EXISTS `orderFromShop0`;
CREATE TABLE `orderFromShop0`(
  `id` INT UNIQUE COMMENT '订单商品id',
  `feight` DECIMAL COMMENT '运费',
  `commodityPrice` DECIMAL COMMENT '商品实际购买价格',
  `commodityNumber` INT COMMENT '商品实际购买数量',
  `logisticsTypeId` INT COMMENT '订单商品物流状态_外键 Type(类型表)',
  `commodityId` INT COMMENT '订单商品信息_外键 commodity(商品表)'
)CHARSET 'utf8';
CREATE TABLE `orderFromShop1`(
  `id` INT UNIQUE COMMENT '订单商品id',
  `feight` DECIMAL COMMENT '运费',
  `commodityPrice` DECIMAL COMMENT '商品实际购买价格',
  `commodityNumber` INT COMMENT '商品实际购买数量',
  `logisticsTypeId` INT COMMENT '订单商品物流状态_外键 Type(类型表)',
  `commodityId` INT COMMENT '订单商品信息_外键 commodity(商品表)',
  `speciTopicId` INT COMMENT '商品规格标题_外键 specificationsTopic(商品规格标题表)',
  `speciDetailedId` INT COMMENT '商品规格详细_外键 sepcificationsDetailed(商品规格详细表)'
)CHARSET 'utf8';
#创建 transactionRecord (交易记录表)
DROP TABLE IF EXISTS `transactionRecord`;
CREATE TABLE `transactionRecord`(
  `id` INT AUTO_INCREMENT UNIQUE COMMENT '交易记录id',
  `creationTime` DATETIME COMMENT '创建时间',
  `orderId` INT COMMENT '订单id_外键 OrderFrom(订单表)',
  `userId` INT COMMENT '用户id_外键 User(用户表)'
)CHARSET 'utf8';
#创建 type(类型表)
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`(
  `id` INT AUTO_INCREMENT UNIQUE COMMENT '类型id',
  `typeName` VARCHAR(50) COMMENT '类型名称'
)CHARSET 'utf8';
#创建 discount(折扣表)
DROP TABLE IF EXISTS `discount`;
CREATE TABLE `discount`(
  `id` INT AUTO_INCREMENT UNIQUE COMMENT '折扣id',
  `discountPrice` DECIMAL COMMENT '折扣价格',
  `discountIntroduce` VARCHAR(50) COMMENT '折扣介绍',
  `discountTypeId` INT COMMENT '折扣类型id_外键 Type(类型表)'
)CHARSET 'utf8';
#创建 userDiscount(用户折扣关系表)
DROP TABLE IF EXISTS `userDiscount`;
CREATE TABLE `userDiscount`(
  `id` INT AUTO_INCREMENT UNIQUE COMMENT 'id',
  `userId` INT COMMENT '用户id_外键 User(用户表)',
  `discountId` INT COMMENT '折扣id_外键 discount(折扣表) '
)CHARSET 'utf8';
#创建 commodity(商品表)
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity`(
  `id` INT AUTO_INCREMENT UNIQUE COMMENT '商品id',
  `commodityName` VARCHAR(20) COMMENT '商品名称',
  `commodityIntroduce` VARCHAR(200) COMMENT '商品介绍',
  `bigPictureUrl` VARCHAR(100) COMMENT '商品大图片路径',
  `commodityTypeId` INT COMMENT '商品类型id_外键 Type(类型表)'
)CHARSET 'utf8';
#创建 commodityTypeRelation(商品分类关系表)
DROP TABLE IF EXISTS `commodityTypeRelation`;
CREATE TABLE `commodityTypeRelation`(
  `id` INT AUTO_INCREMENT UNIQUE COMMENT '商品分类关系id',
  `shopPrimaryTypeId` INT COMMENT '商品一级分类_外键 Type(类型表)',
  `shopMinorTypeId` INT COMMENT '商品二级分类_外键 Type(类型表)',
  `commodityId` INT COMMENT '商品Id_外键 Commodity(商品表)'
)CHARSET 'utf8';
#创建 specificationsRelation(商品规格关系表)
DROP TABLE IF EXISTS `specificationsRelation`;
CREATE TABLE `specificationsRelation`(
  `id` INT AUTO_INCREMENT UNIQUE COMMENT '商品规格关系id',
  `commodityNumber` INT COMMENT '商品数量',
  `commodityPrice` DECIMAL COMMENT '商品价格',
  `smallPictureUrl` VARCHAR(100) COMMENT '商品小图片路径',
  `typeId` INT COMMENT '商品促销状态_外键 Type(类型表)'
)CHARSET 'utf8';
#创建 specificationsTopic(商品规格标题表)
DROP TABLE IF EXISTS `specificationsTopic`;
CREATE TABLE `specificationsTopic`(
  `id` INT AUTO_INCREMENT UNIQUE COMMENT '商品规格标题id',
  `name` VARCHAR(20) COMMENT '商品规格标题名称'
)CHARSET 'utf8';
#创建 specificationsDetailed(商品规格详细表)
DROP TABLE IF EXISTS `specificationsDetailed`;
CREATE TABLE `specificationsDetailed`(
  `id` INT AUTO_INCREMENT UNIQUE COMMENT '商品规格详细id',
  `name` VARCHAR(50) COMMENT '商品规格详细名称'
)CHARSET 'utf8';
#创建 CommodityEvaluation(商品评价表)
CREATE TABLE `commodityEvaluation`(
  `id` INT AUTO_INCREMENT UNIQUE COMMENT '商品评价id',
  `evaluationTime` DATETIME COMMENT '商品评价时间',
  `evaluationContent` VARCHAR(200) COMMENT '商品评价内容',
  `evaluationTypeId` INT COMMENT '商品评价状态_外键 Type(类型表)',
  `userId` INT COMMENT '所属用户_外键 User(用户表)'
)CHARSET 'utf8';
#创建 promotionItem(促销商品表)
DROP TABLE IF EXISTS `promotionitem`;
CREATE TABLE `promotionItem`(
  `id` INT AUTO_INCREMENT UNIQUE COMMENT '促销商品id',
  `startTime` DATETIME COMMENT '促销开始时间',
  `endTime` DATETIME COMMENT '促销结束时间',
  `discountPrice` DECIMAL COMMENT '商品促销价格',
  `commodityNumber` INT COMMENT '促销商品数量',
  `commodityId` INT COMMENT '商品原信息_外键 Commdity(商品表)'
)CHARSET 'utf8';
#创建 customerService(售后表)
DROP TABLE IF EXISTS `customerService`;
CREATE TABLE `customerService`(
  `id` INT AUTO_INCREMENT UNIQUE COMMENT '售后id',
  `causeOfReturn` VARCHAR(200) COMMENT '退货原因',
  `demandTime` INT COMMENT '需求时间',
  `acceptanceTime` DATETIME COMMENT '受理时间',
  `commitTime` DATETIME COMMENT '提交时间',
  `typeId` INT COMMENT '类型id_外键 Type(类型表)',
  `orderId` INT COMMENT '订单id_外键 OrderFrom(订单表)',
  `userId` INT COMMENT '用户id_外键 User(用户表)'
)CHARSET 'utf8';