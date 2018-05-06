package com.zhkj.controller;

import com.zhkj.service.getDB.ISearchElasticDB;
import com.zhkj.service.ISearchService;
import com.zhkj.service.entity.SearchCondition;
import com.zhkj.service.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControllerTest {
    @Autowired
    ISearchElasticDB serviceDB;
    @Autowired
    ISearchService service;
    @GetMapping("/getCommodityById")
    public void test(@RequestParam("id") String id){
         serviceDB.search_Commodity(id);
    }
    @PostMapping("/deleteByQuery")
    public void deleteByQuery(@RequestParam("name") String name){
        serviceDB.searchDeleteByQuery(name);
    }
    @PostMapping("/searchByCondition")
    public void searchByCondition(@ModelAttribute SearchCondition searchCondition){
        service.search(searchCondition);
    }
    @PostMapping("/searchEay")
    public void seachEay(@ModelAttribute Test test){
        service.searchEay(test);
    }
}
