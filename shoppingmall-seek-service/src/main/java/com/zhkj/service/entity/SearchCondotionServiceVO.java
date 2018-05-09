package com.zhkj.service.entity;

import java.util.List;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description: 后台服务与服务间的参数对象
 * @Date: Created in 9:52 2018/5/9 0009
 */
public class SearchCondotionServiceVO {
    /**
     * 商品类型
     */
    private Long typeid;
    /**
     * id
     */
    private String id;
    /**
     * 商品id
     */
    private Long commodityid;

    public Long getTypeid() {
        return typeid;
    }

    public void setTypeid(Long typeid) {
        this.typeid = typeid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(Long commodityid) {
        this.commodityid = commodityid;
    }
}
