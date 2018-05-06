package com.zhkj.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class TransactionrecordEntity {
    private int id;
    private Timestamp creationTime;
    private Integer orderId;
    private Integer userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionrecordEntity that = (TransactionrecordEntity) o;
        return id == that.id &&
                Objects.equals(creationTime, that.creationTime) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, creationTime, orderId, userId);
    }
}
