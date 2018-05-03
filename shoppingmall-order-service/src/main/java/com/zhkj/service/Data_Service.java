package com.zhkj.service;

import com.zhkj.api.order_api.Data_Api;
import com.zhkj.dto.order_dto.Clearing_Dto;
import com.zhkj.dto.order_dto.Order_Dto;
import com.zhkj.vo.order_vo.Clearing_Vo;
import com.zhkj.vo.order_vo.Order_Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 整合传输
 */
@Service
public class Data_Service implements Data_Api {
    @Autowired
    private Encrypt_Service encrypt_service;

    /**
     *
     * 数据接收传输方
     * @param clearing_vo 参数对象 参考对象属性
     * @return 返回给前端页面
     */
    @Override
    public Clearing_Dto transfer_Clearing(Clearing_Vo clearing_vo,String json) {
        String private_Key = clearing_vo.getEncrypt_returning().getPrivate_Key();
        String enctypt = clearing_vo.getEncrypt_returning().getEncrypt();
        Boolean boolean_Enctypt = encrypt_service.cipher_Text_Alignment(json, private_Key, enctypt);
        if (boolean_Enctypt) {
            Clearing_Dto clearing_dto = new Clearing_Dto();
            clearing_dto.setList(clearing_vo.getList());
            clearing_dto.setPrice(clearing_vo.getPrice());
            clearing_dto.setStatus(1);
            //折扣信息缺少
            clearing_dto.setEncrypt_returning(clearing_vo.getEncrypt_returning());
            return clearing_dto;
        } else {
            return null;
        }
    }

    /**
     * 给订单页面传输数据
     * @param order_vo
     * @return
     */
    @Override
    public Order_Dto transfer_Order(Order_Vo order_vo,String json) {
        String enctyptToString = order_vo.getEncrypt_returning().getJson_Name();
        String private_Key = order_vo.getEncrypt_returning().getPrivate_Key();
        String enctypt = order_vo.getEncrypt_returning().getEncrypt();
        Boolean boolean_Enctypt = encrypt_service.cipher_Text_Alignment(json, private_Key, enctypt);
        if (boolean_Enctypt) {
            Order_Dto order_dto = new Order_Dto();
            order_dto.setPrice(order_vo.getPrice());
            order_dto.setAddress_vo(order_vo.getAddress_vo());
            order_dto.setStatus(1);
            order_dto.setEncrypt_returning(order_vo.getEncrypt_returning());
            return order_dto;
        } else {
            return null;
        }
    }
}
