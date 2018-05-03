package com.zhkj.result;

import java.util.List;

/**
 * 订单，购物车，支付 返回值定义
 */
public class ResultAll<T> {
    /**
     * 状态
     */
    private int status;
    /**
     * 返回描述
     */
    private String message;
    /**
     * 返回的dto对象
     */
    private T dtoObject;

    public T getDtoObject() {
        return dtoObject;
    }

    public void setDtoObject(T dtoObject) {
        this.dtoObject = dtoObject;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
