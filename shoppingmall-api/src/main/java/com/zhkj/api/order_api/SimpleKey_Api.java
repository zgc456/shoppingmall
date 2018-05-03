package com.zhkj.api.order_api;

/**
 * 生成主键id策略
 */
public interface SimpleKey_Api {
    /**
     * 查询最后一列id
     * @return
     */
    public int orderfromshop0Entities();
    /**
     * 查询最后一列id
     * @return
     */
    public int orderfromshop1Entities();

    /**
     * 获取key
     * @return
     */
    public int getKey();

}
