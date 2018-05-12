package com.zhkj.compound;

import com.alibaba.fastjson.JSON;
import com.zhkj.dto.order_dto.Clearing_Dto;
import com.zhkj.dto.order_dto.HarvestaddressEntity_Dto;
import com.zhkj.dto.order_dto.OrderFrom_Dto;
import com.zhkj.result.ResultAll;
import com.zhkj.result.ResultUtils;
import com.zhkj.service.*;
import com.zhkj.vo.order_vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 给用户提供
 */
@RestController
@CrossOrigin
public class Clearing_Controller {
    @Autowired
    private Data_Service data_service;
    @Autowired
    private Encrypt_Service encrypt_service;
    @Autowired
    private HarvestAddressService harvestAddressService;
    @Autowired
    DiscountService discountService;
    @Autowired
    private ShoppingCartServices shoppingCartService;
    //公有的返回值类
    ResultUtils resultUtils=new ResultUtils();
    private  static Logger logger=LoggerFactory.getLogger(Clearing_Controller.class);
    /**
     * 访问示例
     *localhost:8805/clearingController1/json/{"userId":1,"OrderfromPrice":3000}
     * 测试
     * @param
     * @return
     */
   // @RequestMapping("clearingController1/json/{json}")
    @RequestMapping("clearingController1")
    public Clearing_Dto Clearing_Controller1(@ModelAttribute OrderFrom_Dto orderFrom_dto) {
       //拿到json 转成对象
        Clearing_Vo clearing_vo = new Clearing_Vo();
   //     OrderFrom_Dto orderFrom_dto=JSON.parseObject(json,OrderFrom_Dto.class);
        //设置购物车商品
        clearing_vo.setLists(shoppingCartService.gainUserInformation(orderFrom_dto));
        //创建地址对象
        Harvestaddress_Vo harvestaddress_vo=new Harvestaddress_Vo();
        //创建地址对象返回值
        HarvestaddressEntity_Dto harvestaddressEntity_dto= new HarvestaddressEntity_Dto();
        //传入用户id
        harvestaddressEntity_dto.setUserId(orderFrom_dto.getUserId());
        //获取地址信息
        harvestaddress_vo.setHarvestAddress(harvestaddressEntity_dto);
        clearing_vo.setPrice(orderFrom_dto.getOrderfromPrice());
        //将地址给clearing_vo
        clearing_vo.setAddress( harvestAddressService.gainMyInformation(harvestaddress_vo));
        return data_service.transfer_Clearing(clearing_vo);
    }
   @RequestMapping("c")
   public String a(){
         return "成功";
   }
//    /**
//     * 测试加密
//     * @param a
//     * @param b
//     * @return
//     */
//    @RequestMapping("clearingController1/a/{a}/b/{b}")
//    public String test(@PathVariable("a") String a, @PathVariable("b") String b) {
//        return encrypt_service.encrypt(a, b);
//    }

//    /**
//     * 给结算页传输数据
//     * @param
//     * @return
//     */
//    @RequestMapping("clearingController/json/{json}/userid/{userid}")
//    public Clearing_Dto Clearing_Controller(@PathVariable("json")String json,@PathVariable("ciphertext")String ciphertext) {
//        Clearing_Vo clearing_vo = JSON.parseObject(json,Clearing_Vo.class);
//        return data_service.transfer_Clearing(clearing_vo,ciphertext);
//    }
//    /**
//     * 给订单页传输数据
//     * @param
//     * @return
//     */
//    @RequestMapping("orderController/json/{json}/ciphertext/{ciphertext}")
//    public Order_Dto order_Controller(@PathVariable("json")String json,@PathVariable("ciphertext")String ciphertext) {
//        Order_Vo order_vo = JSON.parseObject(json,Order_Vo.class);
//        return data_service.transfer_Order(order_vo,ciphertext);
//    }
    /**
     * 添加地址
     * @param
     * @return
     */
   // @RequestMapping("addAddress/json/{json}")
    @RequestMapping("addAddress")
    public ResultAll addAddress(@RequestBody  Harvestaddress_Vo harvestaddress_vo) {
        ResultUtils resultUtils=new ResultUtils();
        try {
       //     Harvestaddress_Vo harvestaddress_vo = JSON.parseObject(json,Harvestaddress_Vo.class);
             harvestAddressService.addHarvestAddress(harvestaddress_vo);
            List<HarvestaddressEntity_Dto> lists=  harvestAddressService.gainMyInformation(harvestaddress_vo);
            return resultUtils.resultAll(1,"添加成功",lists);
        }catch (Exception e){
            logger.error("添加失败 参数信息"+harvestaddress_vo+"错误类型"+e.getMessage());
            return resultUtils.resultAll(-1,"添加失败",null);
        }
    }
    /**
     *{"harvestAddress": {"id": "1","harvestAddressName": "111","harvestIsDefault": "0","typeId": "1","userId": "1"}}
     * 修改地址
     * @param
     * @return
     */
 //   @RequestMapping("updateAddress/json/{json}")
    @RequestMapping("updateAddress")
    public ResultAll updateAddress(@RequestBody Harvestaddress_Vo harvestaddress_vo) {
        try {
         //   Harvestaddress_Vo harvestaddress_vo = JSON.parseObject(json,Harvestaddress_Vo.class);
            harvestAddressService.updateHarvestAddress(harvestaddress_vo);
            List<HarvestaddressEntity_Dto> lists=  harvestAddressService.gainMyInformation(harvestaddress_vo);
            return resultUtils.resultAll(1,"修改成功",lists);
        }catch (Exception e){
            logger.error("修改失败 参数信息"+harvestaddress_vo+"错误类型"+e.getMessage());
            return resultUtils.resultAll(-1,"添加失败",null);
        }
    }
    /**
     * 查询地址
     * @param
     * @return
     */
  //  @RequestMapping("selectAddress/json/{json}")
    @RequestMapping("selectAddress")
    public ResultAll selectAddress(@RequestBody Harvestaddress_Vo harvestaddress_vo) {
        try {
         //   Harvestaddress_Vo harvestaddress_vo = JSON.parseObject(json,Harvestaddress_Vo.class);
            List<HarvestaddressEntity_Dto>  listHarvestaddressEntity_Dto=   harvestAddressService.gainMyInformation(harvestaddress_vo);
            return resultUtils.resultAll(1,"查询成功", listHarvestaddressEntity_Dto);
        }catch (Exception e){
            logger.error("修改失败 参数信息"+harvestaddress_vo+"错误类型"+e.getMessage());
            return resultUtils.resultAll(-1,"查询失败",null);
        }
    }
    /**
     * 删除地址
     * localhost:8805/removeAddress/json/{"harvestAddress":{“id”:"1","harvestAddressName":"1","harvestIsDefault":"0","typeId":"1","userId":"1"}}
     * @param
     * @return
     */
  //  @RequestMapping("removeAddress/json/{json}")
    @RequestMapping("removeAddress")
    public ResultAll removeAddress(@RequestBody Harvestaddress_Vo harvestaddress_vo) {
        try {
         //   Harvestaddress_Vo harvestaddress_vo = JSON.parseObject(json,Harvestaddress_Vo.class);
            harvestAddressService.removeHarvestAddress(harvestaddress_vo);
            List<HarvestaddressEntity_Dto>  listHarvestaddressEntity_Dto=   harvestAddressService.gainMyInformation(harvestaddress_vo);
            return resultUtils.resultAll(1,"删除成功", listHarvestaddressEntity_Dto);
        }catch (Exception e){
            logger.error("修改失败 参数信息"+harvestaddress_vo+"错误类型"+e.getMessage());
            return resultUtils.resultAll(-1,"删除失败",null);
        }
    }
    /**
     * 验证金钱
     * @param
     * @return
     */
    //@RequestMapping("discount/json/{json}")
    @RequestMapping("discount")
    public double discount(@RequestBody Discount_Vo discount_vo) {
       // Discount_Vo  discount_vo = JSON.parseObject(json,Discount_Vo.class);

        return discountService.calculatePrice(discount_vo);
    }
    @Autowired
   private OrderFromService orderFromService;
    /**
     * 查询特定用户订单
     * @param
     * {"userId": "1"}
     * @return 特定用户的订单
     */
    //@RequestMapping(value = "selectOrderFrom/json/{json}",method = RequestMethod.GET)
    @RequestMapping(value = "selectOrderFrom")
    public ResultAll selectOrderFrom(@RequestBody OrderFrom_Vo orderFrom_vo){
        try{
            //OrderFrom_Vo orderFrom_vo = JSON.parseObject(json,OrderFrom_Vo.class);
            HashMap<String,Object> map = orderFromService.selectUserOrderFrom(orderFrom_vo);
            return resultUtils.resultAll(1,"查询成功",map);
        }catch (Exception e){
            logger.error("修改失败 参数信息"+orderFrom_vo+"错误类型"+e.getMessage());
            return resultUtils.resultAll(-1,"查询失败",null);
        }
    }
    /**
     * 添加订单 并且添加失效为两小时
     * @param
     *
     * @return 是否成功
     */
    @RequestMapping(value = "additionOrderFrom")
    public ResultAll additionOrderFrom(@RequestBody OrderFrom_Vo orderFrom_vo){
        try{
         //   OrderFrom_Vo orderFrom_vo = JSON.parseObject(json,OrderFrom_Vo.class);
            OrderFrom_Dto orderFrom_dto= orderFromService.additionOrderFrom(orderFrom_vo);
            if (null==orderFrom_dto){
                logger.info("添加订单失败 参数信息"+orderFrom_vo);
                return resultUtils.resultAll(-1,"添加订单失败",null);
            }
            return resultUtils.resultAll(1,"添加订单成功",orderFrom_dto);
        }catch (Exception e){
            logger.error("添加订单失败 参数信息"+orderFrom_vo+"错误类型"+e.getMessage());
            return resultUtils.resultAll(-1,"添加订单失败",null);
        }
    }
}
