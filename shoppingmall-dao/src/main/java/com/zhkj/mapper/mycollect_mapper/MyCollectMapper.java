package com.zhkj.mapper.mycollect_mapper;

import com.zhkj.entity.MycollectEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 我的收藏
 */
@Repository
public interface MyCollectMapper {
    /**
     * 根据用户id查询出收藏的商品
     * @param map
     * @return
     */
    List<MycollectEntity> queryByUserIdCollectAll(Map<String,Object> map);

    /**
     * 添加我的收藏
     * @param map
     * @return
     */
    int save(Map<String,Object> map);

    /**
     * 删除我的收藏
     * @param map
     * @return
     */
    int deleteCollectById(Map<String,Object> map);
}
