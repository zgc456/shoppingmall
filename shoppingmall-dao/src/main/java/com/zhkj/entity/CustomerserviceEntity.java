package com.zhkj.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class CustomerserviceEntity {
    private int id;
    private String causeOfReturn;
    private Integer demandTime;
    private Timestamp acceptanceTime;
    private Timestamp commitTime;
    private Integer typeId;
    private Integer orderId;
    private Integer userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCauseOfReturn() {
        return causeOfReturn;
    }

    public void setCauseOfReturn(String causeOfReturn) {
        this.causeOfReturn = causeOfReturn;
    }

    public Integer getDemandTime() {
        return demandTime;
    }

    public void setDemandTime(Integer demandTime) {
        this.demandTime = demandTime;
    }

    public Timestamp getAcceptanceTime() {
        return acceptanceTime;
    }

    public void setAcceptanceTime(Timestamp acceptanceTime) {
        this.acceptanceTime = acceptanceTime;
    }

    public Timestamp getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Timestamp commitTime) {
        this.commitTime = commitTime;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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
        CustomerserviceEntity that = (CustomerserviceEntity) o;
        return id == that.id &&
                Objects.equals(causeOfReturn, that.causeOfReturn) &&
                Objects.equals(demandTime, that.demandTime) &&
                Objects.equals(acceptanceTime, that.acceptanceTime) &&
                Objects.equals(commitTime, that.commitTime) &&
                Objects.equals(typeId, that.typeId) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, causeOfReturn, demandTime, acceptanceTime, commitTime, typeId, orderId, userId);
    }
}
