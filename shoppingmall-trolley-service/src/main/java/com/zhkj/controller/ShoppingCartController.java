package com.zhkj.controller;

import com.alibaba.fastjson.JSONArray;
import com.zhkj.dto.shoppingcart_dto.ShoppingCartDTO;
import com.zhkj.service.ShoppingCartServiceImpl;
import com.zhkj.vo.shoppingCart_vo.ShoppingCartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class ShoppingCartController {
    @Autowired
    private ShoppingCartServiceImpl shoppingCartService;
    @GetMapping("/queryShoppingCart")
    public String queryShoppingCart(@RequestParam("userId") Integer userId){
        List<ShoppingCartDTO> list=shoppingCartService.queryShoppingCart(userId);
        double totalPrice=0;
        for (int i=0;i<list.size();i++){
            totalPrice+=list.get(i).getCommodityPrice()*list.get(i).getCommodityNumber();
        }
        System.out.println(totalPrice);
        JSONArray jsonArray= (JSONArray) JSONArray.toJSON(list);
        String result="{\"foods\":"+jsonArray.toString()+"\"totalPrice\":"+totalPrice+"}";
        System.out.println(result);
        return result;
    }
    @GetMapping("/saveShoppingCart")
    public void saveShoppingCart(@ModelAttribute ShoppingCartVO shoppingCartVO){
        List<ShoppingCartDTO> list=shoppingCartService.queryShoppingCart(shoppingCartVO.getUserId());
        for (ShoppingCartDTO shop:list) {
            if(shoppingCartVO.getCommodityId()==shop.getCommodityId()&&shoppingCartVO.getUserId()==shop.getUserId()){
                if(updateShoppingCart(shoppingCartVO)>0){
                    queryShoppingCart(shoppingCartVO.getUserId());
                    return;
                }
            }

        }
        int result = shoppingCartService.saveShoppingCart(shoppingCartVO);
        if (result > 0) {
            System.out.println("成功！");
        } else {
            System.out.println("失败");
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

}
