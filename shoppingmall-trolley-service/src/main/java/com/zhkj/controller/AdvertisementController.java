package com.zhkj.controller;

import com.alibaba.fastjson.JSONArray;
import com.zhkj.dto.advertisement_dto.AdvertisementDTO;
import com.zhkj.service.AdvertisementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class AdvertisementController {
    @Autowired
    private AdvertisementServiceImpl advertisementService;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * 查询广告
     * @return
     */
    @PostMapping("/queryAdvertisement")
    public String queryAdvertisement(){
        List<AdvertisementDTO> list=advertisementService.queryAdvertisement();
        ServiceInstance serviceInstance = loadBalancerClient.choose("SHOPPING-MALL-TROLLEY-SERVICE");
        serviceInstance.getHost();
        System.out.println(serviceInstance.getPort());
        JSONArray jsonArray= (JSONArray) JSONArray.toJSON(list);
        String result="{\"advertisement\":"+jsonArray.toString()+"}";
        System.out.println(result);
        return result;
    }
}
