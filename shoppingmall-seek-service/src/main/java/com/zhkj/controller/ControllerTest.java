package com.zhkj.controller;

import com.zhkj.service.ISearchService;
import com.zhkj.service.entity.CommodityTemplate;
import com.zhkj.service.entity.SearchConditionPageVO;
import com.zhkj.service.entity.Test;
import com.zhkj.service.getDB.ISearchElasticDB;
import com.zhkj.util.ServiceMultiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ControllerTest {
    @Autowired
    ISearchElasticDB serviceDB;
    @Autowired
    ISearchService service;
    @GetMapping("/getCommodityById")
    public void test(@ModelAttribute SearchConditionPageVO condition){
        if (condition!=null&&condition.getId().isEmpty()) {
//            serviceDB.search_Commodity(condition);
        }
    }
    @PostMapping("/deleteByQuery")
    public void deleteByQuery(@RequestParam("name") String name){
        serviceDB.searchDeleteByQuery(name);
    }
    @GetMapping("/searchByCondition")
    public ServiceMultiResult<CommodityTemplate> searchByCondition(@ModelAttribute SearchConditionPageVO searchConditionPageVO){
        System.out.println(searchConditionPageVO.toString());
        return service.search(searchConditionPageVO);
    }
    @PostMapping("/searchEay")
    public void seachEay(@ModelAttribute Test test){
        service.searchEay(test);
    }
    @GetMapping("test")
    public List<ServiceMultiResult<CommodityTemplate>> test(){
        return service.getAllTypeCommodity();
    }
}
