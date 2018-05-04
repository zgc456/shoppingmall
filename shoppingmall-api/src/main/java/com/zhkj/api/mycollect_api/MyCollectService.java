package com.zhkj.api.mycollect_api;

import com.zhkj.dto.mycollect_dto.MyCollectDTO;
import com.zhkj.vo.mycollect_vo.MyCollectVO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface MyCollectService {
    @GetMapping("/queryByUserId")
    List<MyCollectDTO> queryByUserIdCollectAll(MyCollectVO myCollectVO);
    @GetMapping("/addMyCollect")
    int save(MyCollectDTO myCollectDTO);
    @GetMapping("/deleteMyCollect")
    int deleteCollectById(MyCollectVO myCollectVO);
}
