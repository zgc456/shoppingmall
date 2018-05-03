package com.zhkj.compound;

import com.alibaba.fastjson.JSON;
import com.zhkj.dto.order_dto.Clearing_Dto;
import com.zhkj.dto.order_dto.HarvestaddressEntity_Dto;
import com.zhkj.dto.order_dto.Order_Dto;
import com.zhkj.service.Data_Service;
import com.zhkj.service.DiscountService;
import com.zhkj.service.Encrypt_Service;
import com.zhkj.service.HarvestAddressService;
import com.zhkj.vo.order_vo.Clearing_Vo;
import com.zhkj.vo.order_vo.Discount_Vo;
import com.zhkj.vo.order_vo.Harvestaddress_Vo;
import com.zhkj.vo.order_vo.Order_Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 给用户提供
 */
@RestController
public class Clearing_Controller {
    @Autowired
    private Data_Service data_service;
    @Autowired
    private Encrypt_Service encrypt_service;
    @Autowired
    private HarvestAddressService harvestAddressService;
@Autowired
    DiscountService discountService;
    /**
     * 访问示例
     * localhost:8805/clearingController1/json/{"price": 3000,"list": {"name11111": "11111111111123","password": "4564"},"encrypt_returning": {"json_Name": {"name11111": "11111111111123","password": "4564"},"private_Key": 123456,"encrypt":"5D946D76A99E7D09E90C3D48FE05C132CD26662D514400057769412E8C8BE194EAB590BAC91C6C74C1977294BEF26EC12F073A873E54499B78F448B8C7AC2C4B"}}/ciphertext/{"name11111": "11111111111123","password": "4564"}
     * 测试
     * @param
     * @return
     */
    @RequestMapping("clearingController1/json/{json}/ciphertext/{ciphertext}")
    public Clearing_Dto Clearing_Controller1(@PathVariable("json")String json,@PathVariable("ciphertext")String ciphertext) {
        Clearing_Vo clearing_vo = JSON.parseObject(json,Clearing_Vo.class);
        return data_service.transfer_Clearing(clearing_vo,ciphertext);
    }

    /**
     * 测试加密
     * @param a
     * @param b
     * @return
     */
    @RequestMapping("clearingController1/a/{a}/b/{b}")
    public String test(@PathVariable("a") String a, @PathVariable("b") String b) {
        return encrypt_service.encrypt(a, b);
    }

    /**
     * 给结算页传输数据
     * @param
     * @return
     */
    @RequestMapping("clearingController/json/{json}/ciphertext/{ciphertext}")
    public Clearing_Dto Clearing_Controller(@PathVariable("json")String json,@PathVariable("ciphertext")String ciphertext) {
        Clearing_Vo clearing_vo = JSON.parseObject(json,Clearing_Vo.class);
        return data_service.transfer_Clearing(clearing_vo,ciphertext);
    }
    /**
     * 给订单页传输数据
     * @param
     * @return
     */
    @RequestMapping("orderController/json/{json}/ciphertext/{ciphertext}")
    public Order_Dto order_Controller(@PathVariable("json")String json,@PathVariable("ciphertext")String ciphertext) {
        Order_Vo order_vo = JSON.parseObject(json,Order_Vo.class);
        return data_service.transfer_Order(order_vo,ciphertext);
    }

    /**
     * 添加地址
     * @param json
     * @return
     */
    @RequestMapping("addAddress/json/{json}")
    public String addAddress(@PathVariable("json")String json) {
        Harvestaddress_Vo harvestaddress_vo = JSON.parseObject(json,Harvestaddress_Vo.class);
         harvestAddressService.addHarvestAddress(harvestaddress_vo);
         return "成功";
    }

    /**
     *{"harvestAddress": {"id": "1","harvestAddressName": "111","harvestIsDefault": "0","typeId": "1","userId": "1"}}
     * 修改地址
     * @param json
     * @return
     */
    @RequestMapping("updateAddress/json/{json}")
    public String updateAddress(@PathVariable("json")String json) {
        Harvestaddress_Vo harvestaddress_vo = JSON.parseObject(json,Harvestaddress_Vo.class);
         harvestAddressService.updateHarvestAddress(harvestaddress_vo);
        return "成功";
    }

    /**
     * 查询地址
     * @param json
     * @return
     */
    @RequestMapping("selectAddress/json/{json}")
    public List<HarvestaddressEntity_Dto> selectAddress(@PathVariable("json")String json) {
        Harvestaddress_Vo harvestaddress_vo = JSON.parseObject(json,Harvestaddress_Vo.class);
        return harvestAddressService.gainMyInformation(harvestaddress_vo);
    }

    /**
     * 删除地址
     * localhost:8805/removeAddress/json/{"harvestAddress":{“id”:"1","harvestAddressName":"1","harvestIsDefault":"0","typeId":"1","userId":"1"}}
     * @param json
     * @return
     */
    @RequestMapping("removeAddress/json/{json}")
    public String removeAddress(@PathVariable("json")String json) {
        Harvestaddress_Vo harvestaddress_vo = JSON.parseObject(json,Harvestaddress_Vo.class);
         harvestAddressService.removeHarvestAddress(harvestaddress_vo);
        return "成功";
    }

    /**
     * 验证金钱
     * @param json
     * @return
     */
    @RequestMapping("discount/json/{json}")
    public double discount(@PathVariable("json")String json) {
        Discount_Vo  discount_vo = JSON.parseObject(json,Discount_Vo.class);
        return discountService.calculatePrice(discount_vo);
    }

}
