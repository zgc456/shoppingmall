package com.zhkj.mapper.mycollect_mapper;

import com.zhkj.entity.MycollectEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface MyCollectMapper {
    /**
     * 根据用户id查询出收藏的商品
     * @param map
     * @return
     */
    List<MycollectEntity> queryByUserIdCollectAll(Map<String,Object> map);

    int save(Map<String,Object> map);

    int deleteCollectById(Map<String,Object> map);
}
