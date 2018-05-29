package com.zhkj.controller;

import com.alibaba.fastjson.JSONArray;
import com.zhkj.dto.mycollect_dto.MyCollectDTO;
import com.zhkj.service.MycollectServiceImpl;
import com.zhkj.vo.mycollect_vo.MyCollectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class MyCollectController {
    @Autowired
    private MycollectServiceImpl mycollectService;
    @GetMapping("/queryByUserIdCollectAll")
    public String queryByUserIdCollectAll(@RequestParam("userId") Integer userId){
        MyCollectVO myCollectVO=new MyCollectVO();
        myCollectVO.setUserId(userId);
        List<MyCollectDTO> list=mycollectService.queryByUserIdCollectAll(myCollectVO);
        JSONArray jsonArray= (JSONArray) JSONArray.toJSON(list);
        String result="{\"foods\":"+jsonArray.toString()+"}";
        System.out.println(result);
        return result;
    }
    @GetMapping("/addMyCollect")
    public String saveMyCollect(@ModelAttribute MyCollectVO myCollectVO){
       int result= mycollectService.save(myCollectVO);
       if(result>0){
           return "添加成功";
       }else {
           return "添加失败";
       }
    }
    @GetMapping("/deleteMyCollect")
    public String deleteCollectById(){
        MyCollectVO myCollectVO=new MyCollectVO();
        List list=new ArrayList();
        list.add(3);
        list.add(4);
        myCollectVO.setList(list);
        int result=mycollectService.deleteCollectById(myCollectVO);
        if(result>0){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }
}
