package com.zhkj.controller;

import com.zhkj.service.ISearchService;
import com.zhkj.service.entity.CommodityTemplate;
import com.zhkj.service.entity.SearchConditionPageVO;
import com.zhkj.service.getDB.ISearchElasticDB;
import com.zhkj.util.ServiceMultiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ControllerTest {
    @Autowired
    ISearchElasticDB serviceDB;
    @Autowired
    ISearchService service;
    @GetMapping("/searchByCondition")
    public ServiceMultiResult<CommodityTemplate> searchByCondition(@ModelAttribute SearchConditionPageVO searchConditionPageVO){
        System.out.println(searchConditionPageVO.toString());
        return service.search(searchConditionPageVO);
    }
    @GetMapping("searchAllCommodity")
    public List<ServiceMultiResult<CommodityTemplate>> searchAllCommodity(){
        return service.getAllTypeCommodity();
    }
}
