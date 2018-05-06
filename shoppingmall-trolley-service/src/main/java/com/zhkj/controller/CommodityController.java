package com.zhkj.controller;

import com.alibaba.fastjson.JSONArray;
import com.zhkj.dto.commodity_dto.CommodityDTO;
import com.zhkj.mapper.commodity_mapper.CommodityMapper;
import com.zhkj.service.CommodityServiceImpl;
import com.zhkj.vo.commodity_vo.CommodityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class CommodityController {
    @Autowired
    private CommodityServiceImpl commodityService;
    @Autowired
    private CommodityMapper commodityMapper;
    @GetMapping("/selectAll")
    public String selectAll(){
        List<CommodityDTO> list=commodityService.selectAll();
        JSONArray jsonArray= (JSONArray) JSONArray.toJSON(list);
        String result="{\"foods\":"+jsonArray.toString()+"}";
        System.out.println(result);
        return result;
    }
    @GetMapping("/selectByTypeId")
    public String selectByTypeId(@RequestParam("id") Integer id){
        CommodityVO commodityVO=new CommodityVO();
        commodityVO.setTypeId(id);
        List<CommodityDTO> list=commodityService.selectByTypeId(commodityVO);
        JSONArray jsonArray= (JSONArray) JSONArray.toJSON(list);
        String result="{\"foods\":"+jsonArray.toString()+"}";
        System.out.println(result);
        return result;
    }
    @GetMapping("/selectByCommodityId")
    public String selectByCommodityId(@RequestParam("commodityId") Integer commodityId){
        CommodityVO commodityVO=new CommodityVO();
        commodityVO.setCommodityId(commodityId);
        List<CommodityDTO> list=commodityService.selectByCommodityId(commodityVO);
        JSONArray jsonArray= (JSONArray) JSONArray.toJSON(list);
        String result="{\"foods\":"+jsonArray.toString()+"}";
        System.out.println(result);
        return result;
    }
    @GetMapping("/selectCommodity")
    public String selectCommodity(){
        List<CommodityDTO> list=commodityService.selectCommodity();
        JSONArray jsonArray= (JSONArray) JSONArray.toJSON(list);
        String result="{\"goods\":"+jsonArray.toString()+"}";
        System.out.println(result);
        return result;
    }
}
