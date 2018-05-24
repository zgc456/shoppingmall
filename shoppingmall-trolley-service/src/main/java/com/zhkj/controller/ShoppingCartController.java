package com.zhkj.controller;

import com.alibaba.fastjson.JSONArray;
import com.zhkj.dto.inventory_dto.CommoditySpecificationInventoryPriceDTO;
import com.zhkj.dto.shoppingcart_dto.ShoppingCartDTO;
import com.zhkj.service.ShoppingCartServiceImpl;
import com.zhkj.vo.shoppingCart_vo.ShoppingCartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

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
    private CommoditySpeInvPriceController commoditySpeInvPriceController;
    @GetMapping("/queryShoppingCart")
    public String queryShoppingCart(@ModelAttribute ShoppingCartVO shoppingCartVO){
        List<ShoppingCartDTO> list=shoppingCartService.queryShoppingCart(shoppingCartVO);
        List<ShoppingCartDTO> lists=shoppingCartService.queryShoppingCartByUserId(shoppingCartVO.getUserId());
        double totalPrice=0;
        for (int i=0;i<lists.size();i++){
                totalPrice+=lists.get(i).getCommodityPrice()*lists.get(i).getCommodityNumber();

        }
        System.out.println(totalPrice);
        JSONArray jsonArray= (JSONArray) JSONArray.toJSON(list);
        String result="{\"foods\":"+jsonArray.toString()+",\"totalPrice\":"+totalPrice+"}";
        System.out.println(result);
        return result;
    }
    @GetMapping("/saveShoppingCart")
    public void saveShoppingCart(@ModelAttribute ShoppingCartVO shoppingCartVO){
        List<ShoppingCartDTO> list=shoppingCartService.queryShoppingCartByUserId(shoppingCartVO.getUserId());
        List<CommoditySpecificationInventoryPriceDTO> dtoList=commoditySpeInvPriceController.queryByInvPriceId(shoppingCartVO.getCommoditySipId());
        for (ShoppingCartDTO shop:list) {
            if(shoppingCartVO.getCommoditySipId()==shop.getCommoditySipId()&&shoppingCartVO.getUserId()==shop.getUserId()){
                shoppingCartVO.setCommodityNumber(shoppingCartVO.getCommodityNumber() + shop.getCommodityNumber());
                for (CommoditySpecificationInventoryPriceDTO inventoryPriceDTO : dtoList) {
                    if(shoppingCartVO.getCommodityNumber()<=inventoryPriceDTO.getInventory()) {
                        if (updateShoppingCart(shoppingCartVO) > 0) {
                            queryShoppingCart(shoppingCartVO);
                            return;
                        }
                    }else {
                        System.out.println("不能再加了");
                        queryShoppingCart(shoppingCartVO);
                        return;
                    }
                }
            }

        }
        int result = shoppingCartService.saveShoppingCart(shoppingCartVO);
        if (result > 0) {
            System.out.println("成功！");
            queryShoppingCart(shoppingCartVO);
        } else {
            System.out.println("失败");
            queryShoppingCart(shoppingCartVO);
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
        List<ShoppingCartDTO> list=shoppingCartService.queryShoppingCartByUserId(shoppingCartVO.getUserId());;
        Map<String,Object> map=new HashMap<>();
        for (ShoppingCartDTO shop:list) {
            if(shoppingCartVO.getCommoditySipId()==shop.getCommoditySipId()&&shoppingCartVO.getUserId()==shop.getUserId()){
                shoppingCartVO.setCommodityNumber(shop.getCommodityNumber()-1);
                if(shoppingCartVO.getCommodityNumber()>=1) {
                    if (updateShoppingCart(shoppingCartVO) > 0) {
                        queryShoppingCart(shoppingCartVO);
                    }
                }else {
                    System.out.println("不能再减了");
                    map.put("message","不能再减了");
                    queryShoppingCart(shoppingCartVO);
                }
            }

        }
    }
    @GetMapping("/addNumber")
    public void addNumber(@ModelAttribute ShoppingCartVO shoppingCartVO){
        Map<String,Object> map=new HashMap<>();
        List<ShoppingCartDTO> list=shoppingCartService.queryShoppingCartByUserId(shoppingCartVO.getUserId());
        List<CommoditySpecificationInventoryPriceDTO> dtoList=commoditySpeInvPriceController.queryByInvPriceId(shoppingCartVO.getCommoditySipId());
        for (ShoppingCartDTO shop:list) {
            if(shoppingCartVO.getCommoditySipId()==shop.getCommoditySipId()&&shoppingCartVO.getUserId()==shop.getUserId()) {
                shoppingCartVO.setCommodityNumber(shop.getCommodityNumber() + 1);
                for (CommoditySpecificationInventoryPriceDTO inventoryPriceDTO:dtoList) {
                    if(shoppingCartVO.getCommodityNumber()<=inventoryPriceDTO.getInventory()){
                        if (updateShoppingCart(shoppingCartVO) > 0) {
                            queryShoppingCart(shoppingCartVO);
                        }
                    } else {
                        System.out.println("不能再加了");
                        map.put("msg","不能再加了");
                        queryShoppingCart(shoppingCartVO);
                    }
                }
            }
        }
    }
}
