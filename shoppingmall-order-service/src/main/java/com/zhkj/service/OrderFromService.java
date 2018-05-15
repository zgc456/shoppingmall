package com.zhkj.service;

import com.zhkj.api.order_api.OrderFromService_Api;
import com.zhkj.copy_properties.Conver_Type;
import com.zhkj.dto.order_dto.OrderFromShop_Dto;
import com.zhkj.dto.order_dto.OrderFrom_Dto;
import com.zhkj.dto.shoppingcart_dto.ShoppingCartDTO;
import com.zhkj.entity.OrderfromEntity;
import com.zhkj.entity.Orderfromshop0Entity;
import com.zhkj.entity.ShoppingcartEntity;
import com.zhkj.mapper.order_mapper.OrderFromMapper;
import com.zhkj.mapper.order_mapper.OrderFromShopMapper;
import com.zhkj.mapper.order_mapper.ShoppingCartMappers;
import com.zhkj.service.sharding_jdbc_key.SimpleKeyService;
import com.zhkj.util.SimpleKeyUtil;
import com.zhkj.vo.order_vo.OrderFromShop_Vo;
import com.zhkj.vo.order_vo.OrderFrom_Vo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 订单的生成及订单商品的添加
 */
@Service
public class OrderFromService implements OrderFromService_Api {
    @Autowired
    private OrderFromMapper orderFromMapper;
    @Autowired
    private OrderFromShopMapper orderFromShopMapper;
    @Autowired
    private SimpleKeyService simpleKeyService;
    @Autowired
    private ShoppingCartMappers shoppingCartMappers;

    static Logger logger = Logger.getLogger(OrderFromService.class);
    @Override
    public OrderFrom_Dto additionOrderFrom(OrderFrom_Vo orderFrom_vo) {
        OrderFrom_Dto orderFrom_dto = new OrderFrom_Dto();
        boolean result = false;
        Date date = new Date();
        // 获取订单号
        String orderFromNumber = generateOrderNumber();
        if(orderFrom_vo != null){
            if(!(orderFromNumber.equals("") && orderFromNumber == null)){
                try {
                    // 创建订单实体类
                    OrderfromEntity orderfromEntity = new OrderfromEntity();
                    // 修改订单创建时间
                    orderFrom_dto.setOrderCreationTime(new Timestamp(date.getTime()));
                    // 计算订单结束时间
                    Timestamp endTime = new Timestamp(date.getTime());
                    endTime.setHours(endTime.getHours() + 2);
                    // 订单结束时间
                    orderFrom_dto.setOrderEndTime(endTime);
                    // 订单号
                    orderFrom_dto.setOrderNumber(orderFromNumber);
                    // 用户 id
                    orderFrom_dto.setUserId(orderFrom_vo.getUserId());
                    // 地址 id
                    orderFrom_dto.setHarvestAddressId(orderFrom_vo.getHarvestAddressId());
                    // 订单总价
                    orderFrom_dto.setOrderfromPrice(orderFrom_vo.getOrderFromPrice());
                    // 添加订单表
                    orderfromEntity = Conver_Type.convert(orderfromEntity,orderFrom_dto);
                    orderFromMapper.saveOrderFrom(orderfromEntity);
                    result = true;
                    if(result){
                        List<ShoppingcartEntity> shoppingcartEntities = shoppingCartMappers.selectByUserId(orderFrom_vo.getUserId());
                        orderFrom_vo.setShoppingcartEntityList(Conver_Type.convertToList(orderFrom_vo.getShoppingcartEntityList(),shoppingcartEntities,"com.zhkj.dto.shoppingcart_dto.ShoppingCartDTO"));
                        additionOrderFromShop(orderFrom_vo.getShoppingcartEntityList(),orderFromNumber);
                        return orderFrom_dto;
                    }
                }catch (Exception e){
                    logger.error("添加订单失败，错误信息"+e.getMessage()+"参数:"+orderFrom_vo);
                }

            }
        }

        return null;
    }

    @Override
    public HashMap<String,Object> selectUserOrderFrom(OrderFrom_Vo orderFrom_vo) {
        HashMap<String,Object> map = new HashMap<>();
        if(null != orderFrom_vo.getUserId() && orderFrom_vo.getUserId() > 0) {
            List<OrderfromEntity> orderFrom = selectOrderFromByUserIdOrTypeId(orderFrom_vo.getUserId(),null);
            map.put("orderFromList",orderFrom);
            if(null != orderFrom && orderFrom.size() > 0){
                HashMap<String,Object> orderFromShopMap = new HashMap<>();
                for (int i = 0;i < orderFrom.size();i++){
                    List<Orderfromshop0Entity> orderFromShopEntity = orderFromShopMapper.selectByOrderFrom(orderFrom.get(i).getOrderNumber());
                    orderFromShopMap.put(orderFrom.get(i).getOrderNumber(),orderFromShopEntity);
                }
                map.put("OrderFromShopList",orderFromShopMap);
            }
        }
        return map;
    }

    /**
     * 根据 userId 或 typeId 查询订单
     * @param userId 用户 id
     * @param typeId 类型 id
     * @return 订单信息
     */
    public List<OrderfromEntity> selectOrderFromByUserIdOrTypeId(Integer userId,Integer typeId){
        String sql = "";
        List<OrderfromEntity> orderfromEntityList = new ArrayList<>();
        if(null != userId && userId > 0){
            sql += " userId = " + userId;
            if(null != typeId && typeId > 0){
                sql += " AND paymentTypeId = " + typeId;
            }
            orderfromEntityList = orderFromMapper.selectOrderFromBySql(sql);
        }
        return orderfromEntityList;
    }

    /**
     * 添加订单商品
     * @param orderFromShop_vos 订单商品信息
     * @param orderFromNumber 订单号
     * @return
     */
    private boolean additionOrderFromShop(List<ShoppingCartDTO> orderFromShop_vos, String orderFromNumber){
        int index = 0;
        for(int i  = 0;i < orderFromShop_vos.size();i++){
            ShoppingCartDTO shoppingCartDTO = orderFromShop_vos.get(i);
            SimpleKeyUtil.id = simpleKeyService.getKey();
            Orderfromshop0Entity orderfromshop0Entity = new Orderfromshop0Entity();
            OrderFromShop_Dto orderFromShop_dto = new OrderFromShop_Dto();
            orderFromShop_dto.setCommodityPrice(shoppingCartDTO.getCommodityPrice());
            orderFromShop_dto.setCommodityNumber(shoppingCartDTO.getCommodityNumber());
            orderFromShop_dto.setCommodityId(shoppingCartDTO.getCommodityId());
            orderFromShop_dto.setLogisticsTypeId(1);
            orderFromShop_dto.setOrderFromId(orderFromNumber);
            orderfromshop0Entity = Conver_Type.convert(orderfromshop0Entity,orderFromShop_dto);
            orderFromShopMapper.insertShop(orderfromshop0Entity);
            index++;
        }
        if(index > 0){
            return true;
        }
        return false;
    }
    /**
     * 生成订单号
     *  订单格式
     *      年:yyyy  月:MM  天:dd  时:HH  分:mm  秒:ss  毫秒:SS
     * @return 生成的订单号
     */
    private String generateOrderNumber(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String orderNumber = simpleDateFormat.format(new Date());
        return orderNumber;
    }
}
