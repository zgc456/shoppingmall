package com.zhkj.controller;

import com.alibaba.fastjson.JSONArray;
import com.zhkj.dto.commodity_dto.CommodityDTO;
import com.zhkj.dto.shoppingcart_dto.ShoppingCartDTO;
import com.zhkj.dto.specification_dto.SpecificationsrelationDTO;
import com.zhkj.service.ShoppingCartServiceImpl;
import com.zhkj.vo.shoppingCart_vo.ShoppingCartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
public class ShoppingCartController {
    @Autowired
    private ShoppingCartServiceImpl shoppingCartService;
    @Autowired
    private SpecificationRelationController specificationRelationController;
    @GetMapping("/queryShoppingCart")
    public String queryShoppingCart(@RequestParam("userId") Integer userId){
        List<ShoppingCartDTO> list=shoppingCartService.queryShoppingCart(userId);
        double totalPrice=0;
        for (int i=0;i<list.size();i++){
            totalPrice+=list.get(i).getCommodityPrice()*list.get(i).getCommodityNumber();
        }
        System.out.println(totalPrice);

        JSONArray jsonArray= (JSONArray) JSONArray.toJSON(list);
        String result="{\"foods\":"+jsonArray.toString()+",\"totalPrice\":"+totalPrice+"}";
        System.out.println(result);
        return result;
    }
    @GetMapping("/saveShoppingCart")
    public void saveShoppingCart(@ModelAttribute ShoppingCartVO shoppingCartVO){
        List<ShoppingCartDTO> list = shoppingCartService.queryShoppingCart(shoppingCartVO.getUserId());
        List<SpecificationsrelationDTO> dtoList = specificationRelationController.selectMaxByCommodityId(shoppingCartVO.getCommodityId());
        for (ShoppingCartDTO shop : list) {
            if (shoppingCartVO.getCommodityId() == shop.getCommodityId() && shoppingCartVO.getUserId() == shop.getUserId()) {
                shoppingCartVO.setCommodityNumber(shoppingCartVO.getCommodityNumber() + shop.getCommodityNumber());
                for (SpecificationsrelationDTO spe : dtoList) {
                    if (shoppingCartVO.getCommodityNumber() <= spe.getCommodityNumber()) {
                        if (updateShoppingCart(shoppingCartVO) > 0) {
                            queryShoppingCart(shoppingCartVO.getUserId());
                            return;
                        }
                    } else {
                        System.out.println("不能再加了");
                        queryShoppingCart(shoppingCartVO.getUserId());
                        return;
                    }
                }
            }
        }
        int result = shoppingCartService.saveShoppingCart(shoppingCartVO);
        if (result > 0) {
            System.out.println("成功！");
            queryShoppingCart(shoppingCartVO.getUserId());
        } else {
            System.out.println("失败");
            queryShoppingCart(shoppingCartVO.getUserId());
        }

    }
    @GetMapping("/deleteShoppingCart")
    public String deleteShoppingCart(){
        ShoppingCartVO shoppingCartVO=new ShoppingCartVO();
        List list=new ArrayList();
        list.add(4);
        list.add(5);
        shoppingCartVO.setList(list);
        int result=shoppingCartService.deleteShoppingCart(shoppingCartVO);
        if(result>0){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }
    @GetMapping("/updateShoppingCart")
    public int updateShoppingCart(@ModelAttribute ShoppingCartVO shoppingCartVO){
        int result=shoppingCartService.updateShoppingCart(shoppingCartVO);
        if(result>0){
            System.out.println("修改成功！");
        }
        return result;
    }
    @GetMapping("/decreaseNumber")
    public void decreaseNumber(@ModelAttribute ShoppingCartVO shoppingCartVO){
        List<ShoppingCartDTO> list=shoppingCartService.queryShoppingCart(shoppingCartVO.getUserId());
        for (ShoppingCartDTO shop:list) {
            if(shoppingCartVO.getCommodityId()==shop.getCommodityId()&&shoppingCartVO.getUserId()==shop.getUserId()){
                shoppingCartVO.setCommodityNumber(shop.getCommodityNumber()-1);
                if(shoppingCartVO.getCommodityNumber()>= 1) {
                    if (updateShoppingCart(shoppingCartVO) > 0) {
                        queryShoppingCart(shoppingCartVO.getUserId());
                    }
                }else {
                    System.out.println("不能再减了");
                    queryShoppingCart(shoppingCartVO.getUserId());
                }
            }

        }
    }
    @GetMapping("/addNumber")
    public void addNumber(@ModelAttribute ShoppingCartVO shoppingCartVO){
        Map<String, Object> map = new HashMap<>();
        List<ShoppingCartDTO> list = shoppingCartService.queryShoppingCart(shoppingCartVO.getUserId());
        List<SpecificationsrelationDTO> dtoList = specificationRelationController.selectMaxByCommodityId(shoppingCartVO.getCommodityId());
        for (ShoppingCartDTO shop : list) {
            if (shoppingCartVO.getCommodityId() == shop.getCommodityId() && shoppingCartVO.getUserId() == shop.getUserId()) {
                shoppingCartVO.setCommodityNumber(shop.getCommodityNumber() + 1);
                for (SpecificationsrelationDTO spe : dtoList) {
                    if (shoppingCartVO.getCommodityNumber() <= spe.getCommodityNumber()) {
                        if (updateShoppingCart(shoppingCartVO) > 0) {
                            queryShoppingCart(shoppingCartVO.getUserId());
                        }
                    } else {
                        System.out.println("不能再加了");
                        queryShoppingCart(shoppingCartVO.getUserId());
                    }
                }
            }
        }
    }
}
