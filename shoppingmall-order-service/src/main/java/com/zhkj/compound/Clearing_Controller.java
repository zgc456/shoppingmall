package com.zhkj.compound;

import com.alibaba.fastjson.JSON;
import com.zhkj.dto.order_dto.Clearing_Dto;
import com.zhkj.dto.order_dto.HarvestaddressEntity_Dto;
import com.zhkj.dto.order_dto.Order_Dto;
import com.zhkj.service.*;
import com.zhkj.vo.order_vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
     * localhost:8805/clearingController1/json/{"price": 3000,"list": {"name11111": "11111111111123","password": "4564"},"private_Key": 123456,"encrypt":"5D946D76A99E7D09E90C3D48FE05C132CD26662D514400057769412E8C8BE194EAB590BAC91C6C74C1977294BEF26EC12F073A873E54499B78F448B8C7AC2C4B"}/ciphertext/{"name11111": "11111111111123","password": "4564"}/userid/1
     * 测试
     * @param
     * @return
     */
    @RequestMapping("clearingController1/json/{json}/ciphertext/{ciphertext}/userid/{userid}")
    public Clearing_Dto Clearing_Controller1(@PathVariable("json")String json,@PathVariable("ciphertext")String ciphertext,@PathVariable("userid")Integer userid) {
        Clearing_Vo clearing_vo = JSON.parseObject(json,Clearing_Vo.class);
        Harvestaddress_Vo harvestaddress_vo=new Harvestaddress_Vo();
        HarvestaddressEntity_Dto harvestaddressEntity_dto= new HarvestaddressEntity_Dto();
        harvestaddressEntity_dto.setUserId(userid);
        harvestaddress_vo.setHarvestAddress(harvestaddressEntity_dto);
        clearing_vo.setJson_Name(ciphertext);
        //将地址给clearing_vo
        clearing_vo.setAddress( harvestAddressService.gainMyInformation(harvestaddress_vo));
        return data_service.transfer_Clearing(clearing_vo,clearing_vo.getJson_Name());
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
    @RequestMapping("clearingController/json/{json}/userid/{userid}")
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
    @Autowired
   private OrderFromService orderFromService;
    /**
     * 查询特定用户订单
     * @param json
     * {"userId": "1"}
     * @return 特定用户的订单
     */
    @RequestMapping(value = "selectOrderFrom/json/{json}",method = RequestMethod.GET)
    public HashMap<String,Object> selectOrderFrom(@PathVariable("json")String json){
        OrderFrom_Vo orderFrom_vo = JSON.parseObject(json,OrderFrom_Vo.class);
        HashMap<String,Object> map = orderFromService.selectUserOrderFrom(orderFrom_vo);
        return map;
    }

    /**
     * 添加订单 并且添加失效为两小时
     * @param json
     * {"commodityId": [{"feight": "0","commodityPrice": "15","commodityNumber": "2","commodityId": "1"},{"feight": "5","commodityPrice": "80","commodityNumber": "2","commodityId": "2"}],"userId": "1","orderFromPrice": "200","harvestAddressId": "1"}
     * @return 是否成功
     */
    @RequestMapping(value = "additionOrderFrom/json/{json}",method = RequestMethod.GET)
    public String additionOrderFrom(@PathVariable("json")String json){
        OrderFrom_Vo orderFrom_vo = JSON.parseObject(json,OrderFrom_Vo.class);
        boolean result = orderFromService.additionOrderFrom(orderFrom_vo);
        if (result){
            return "成功";
        }
        return "失败";
    }
}
