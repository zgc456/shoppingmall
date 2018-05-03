package com.zhkj.entity;

import java.sql.Timestamp;

/**
 * Created by lenovo on 2018/4/16.
 */
public class CustomerserviceEntity {
    private int id;
    private String causeOfReturn;
    private Integer demandTime;
    private Timestamp acceptanceTime;
    private Timestamp commitTime;
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

        if (id != that.id) return false;
        if (causeOfReturn != null ? !causeOfReturn.equals(that.causeOfReturn) : that.causeOfReturn != null)
            return false;
        if (demandTime != null ? !demandTime.equals(that.demandTime) : that.demandTime != null) return false;
        if (acceptanceTime != null ? !acceptanceTime.equals(that.acceptanceTime) : that.acceptanceTime != null)
            return false;
        if (commitTime != null ? !commitTime.equals(that.commitTime) : that.commitTime != null) return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (causeOfReturn != null ? causeOfReturn.hashCode() : 0);
        result = 31 * result + (demandTime != null ? demandTime.hashCode() : 0);
        result = 31 * result + (acceptanceTime != null ? acceptanceTime.hashCode() : 0);
        result = 31 * result + (commitTime != null ? commitTime.hashCode() : 0);
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
