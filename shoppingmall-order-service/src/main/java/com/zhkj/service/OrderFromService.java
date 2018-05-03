package com.zhkj.service;

import com.zhkj.api.order_api.OrderFromService_Api;
import com.zhkj.copy_properties.Conver_Type;
import com.zhkj.dto.order_dto.OrderFromShop_Dto;
import com.zhkj.dto.order_dto.OrderFrom_Dto;
import com.zhkj.entity.OrderfromEntity;
import com.zhkj.entity.Orderfromshop0Entity;
import com.zhkj.mapper.order_mapper.OrderFromMapper;
import com.zhkj.mapper.order_mapper.OrderFromShopMapper;
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
    static Logger logger = Logger.getLogger(OrderFromService.class);
    @Override
    public boolean additionOrderFrom(OrderFrom_Vo orderFrom_vo) {
        OrderFrom_Dto orderFrom_dto = new OrderFrom_Dto();
        boolean result = false;
        Date date = new Date();
        String orderFromNumber = generateOrderNumber();
        if(orderFrom_vo != null){
            if(!(orderFromNumber.equals("") && orderFromNumber == null)){
                try {
                    OrderfromEntity orderfromEntity = new OrderfromEntity();
                    orderFrom_dto.setOrderCreationTime(new Timestamp(date.getTime()));
                    Timestamp endTime = new Timestamp(date.getTime());
                    endTime.setHours(endTime.getHours() + 2);
                    orderFrom_dto.setOrderEndTime(endTime);
                    orderFrom_dto.setOrderNumber(orderFromNumber);
                    orderFrom_dto.setUserId(orderFrom_vo.getUserId());
                    orderFrom_dto.setHarvestAddressId(orderFrom_vo.getHarvestAddressId());
                    orderFrom_dto.setOrderfromPrice(orderFrom_vo.getOrderFromPrice());
                    orderfromEntity = Conver_Type.convert(orderfromEntity,orderFrom_dto);
                    orderFromMapper.saveOrderFrom(orderfromEntity);
                    result = true;
                    if(result){
                        additionOrderFromShop(orderFrom_vo.getCommodityId(),orderFromNumber);
                        return true;
                    }
                }catch (Exception e){
                    logger.error("添加订单失败，错误信息"+e.getMessage()+"参数:"+orderFrom_vo);
                }

            }
        }

        return false;
    }

    @Override
    public HashMap<String,Object> selectUserOrderFrom(OrderFrom_Vo orderFrom_vo) {
        HashMap<String,Object> map = new HashMap<>();
        if(null != orderFrom_vo.getUserId() && orderFrom_vo.getUserId() > 0) {
            List<OrderfromEntity> orderFrom = orderFromMapper.selectOrderFromByUserId(orderFrom_vo.getUserId());
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
     * 添加订单商品
     * @param orderFromShop_vos 订单商品信息
     * @param orderFromNumber 订单号
     * @return
     */
    private boolean additionOrderFromShop(OrderFromShop_Vo[] orderFromShop_vos,String orderFromNumber){
        int index = 0;
        for(int i = 0;i < orderFromShop_vos.length;i++){
            SimpleKeyUtil.id=simpleKeyService.getKey();
            Orderfromshop0Entity orderfromshop0Entity = new Orderfromshop0Entity();
            OrderFromShop_Dto orderFromShop_dto = new OrderFromShop_Dto();
            orderFromShop_dto.setFeight(orderFromShop_vos[i].getFeight());
            orderFromShop_dto.setCommodityPrice(orderFromShop_vos[i].getCommodityPrice());
            orderFromShop_dto.setCommodityNumber(orderFromShop_vos[i].getCommodityNumber());
            orderFromShop_dto.setLogisticsTypeId(orderFromShop_vos[i].getCommodityId());
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
