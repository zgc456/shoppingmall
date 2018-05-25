package com.zhkj.service.backstage.entity;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description:
 * @Date: Created in 10:58 2018/5/10 0010
 */
public class IndexMessageVO {
    public static final String SAVE="save";
    public static final String DEL="del";
    public static final String UPDATE="update";

    public static final String TOPIC_COMMODITY="commodity";
    public static final String TOPIC_COMMODITY_SPECIFICATION_INVENTORY_PRICE="commodity_specification_inventory_price";
    public static final String TOPIC_COMMODITY_SPECIFICATION_RELATION="commodity_specification_relation";
    public static final String TOPIC_COMMODITYEVALUATION="commodityevaluation";
    public static final String TOPIC_COMMODITYINTRODUCEPICTURE="commodityintroducepicture";
    public static final String TOPIC_PROMOTIONITEM="promotionitem";
    public static final String TOPIC_TYPE="type";
    public static final String TOPIC_USER="user";

    /**最多重试次数*/
    public static final int MAX_RETRY=3;

    /**对应要做的变动(SAVE DEL UPDATE)*/
    private String operation;
    /**对应重试次数(MAX_RETRY)*/
    private int retry = 0;
    /**存入的对象*/
    private String object;
    /**
     * 对象的ID
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String table) {
        this.id = table;
    }

    public IndexMessageVO(){}

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    public String getObjects() {
        return object;
    }

    public void setObjects(String objects) {
        this.object = objects;
    }
}
