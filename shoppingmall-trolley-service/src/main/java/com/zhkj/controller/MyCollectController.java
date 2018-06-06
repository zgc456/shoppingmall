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
    public void saveMyCollect(@ModelAttribute MyCollectVO myCollectVO) {
        List<MyCollectDTO> list=mycollectService.queryByUserIdCollectAll(myCollectVO);
        for (MyCollectDTO my:list) {
            if(my.getCommodityId()==myCollectVO.getCommodityId()&&my.getUserId()==myCollectVO.getUserId()){
                queryByUserIdCollectAll(myCollectVO.getUserId());
                System.out.println("你已收藏了本件商品");
                return ;
            }
        }
        int result = mycollectService.save(myCollectVO);
        if (result > 0) {
            System.out.println("添加成功");
            queryByUserIdCollectAll(myCollectVO.getUserId());
            return;
        } else {
            System.out.println( "添加失败");
            queryByUserIdCollectAll(myCollectVO.getUserId());
            return;
        }

    }
    @GetMapping("/deleteMyCollect")
    public String deleteCollectById(@RequestParam("array") int []array,@RequestParam("userId") Integer userId){
        MyCollectVO myCollectVO=new MyCollectVO();
        myCollectVO.setArray(array);
        myCollectVO.setUserId(userId);
        System.out.println(array.length);
        int result=mycollectService.deleteCollectById(myCollectVO);
        if(result>0){
            queryByUserIdCollectAll(myCollectVO.getUserId());
            return "删除成功";
        }else {
            queryByUserIdCollectAll(myCollectVO.getUserId());
            return "删除失败";
        }
    }
}
