package com.zhkj.ribbons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class TrolleyRibbon {
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/queryAdvertisement")
    public String queryAdvertisement(){
        return restTemplate.getForEntity("http://SHOPPING-MALL-TROLLEY-SERVICE/queryAdvertisement",String.class).getBody();
    }
}
