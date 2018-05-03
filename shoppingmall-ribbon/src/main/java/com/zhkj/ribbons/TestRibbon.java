package com.zhkj.ribbons;

import com.zhkj.dto.test.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 这个类用于测试 ，可删除
 */
@RestController
public class TestRibbon {
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("selectAll")
    public List<UserEntity> selectAll(){
        return restTemplate.getForEntity("http://SHOPPING-MALL-TEST/test",List.class).getBody();
    }

}
