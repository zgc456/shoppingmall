package com.zhkj.controller;

import com.alibaba.fastjson.JSONArray;
import com.zhkj.dto.type_dto.TypeDTO;
import com.zhkj.service.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class TypeController {
    @Autowired
    private TypeServiceImpl typeService;
    @GetMapping("/selectAllTypeName")
    private String selectAllTypeName(){
        List<TypeDTO> list=typeService.selectAllTypeName();
        JSONArray jsonArray= (JSONArray) JSONArray.toJSON(list);
        String result="{\"type\":"+jsonArray.toString()+"}";
        System.out.println(result);
        return result;
    }
}
