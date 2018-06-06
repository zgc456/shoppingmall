package com.zhkj.service;

import com.zhkj.api.mycollect_api.MyCollectService;
import com.zhkj.copy_properties.Conver_Type;
import com.zhkj.dto.mycollect_dto.MyCollectDTO;
import com.zhkj.mapper.mycollect_mapper.MyCollectMapper;
import com.zhkj.vo.mycollect_vo.MyCollectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MycollectServiceImpl implements MyCollectService {
    @Autowired
    private MyCollectMapper myCollectMapper;
    @Override
    public List<MyCollectDTO> queryByUserIdCollectAll(MyCollectVO myCollectVO) {
        List<MyCollectDTO> list=new ArrayList<>();
        return Conver_Type.convertToList(list,myCollectMapper.queryByUserIdCollectAll(BeanMap.create(myCollectVO)),"com.zhkj.dto.mycollect_dto.MyCollectDTO");
    }

    @Override
    public int save(MyCollectVO myCollectVO) {
        return myCollectMapper.save(BeanMap.create(myCollectVO));
    }

    @Override
    public int deleteCollectById(MyCollectVO myCollectVO) {
        return myCollectMapper.deleteCollectById(BeanMap.create(myCollectVO));
    }
}
