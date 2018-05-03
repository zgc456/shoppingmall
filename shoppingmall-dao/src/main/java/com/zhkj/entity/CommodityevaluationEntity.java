package com.zhkj.entity;

import java.sql.Timestamp;

/**
 * Created by lenovo on 2018/4/21.
 */
public class CommodityevaluationEntity {
    private int id;
    private Timestamp evaluationTime;
    private String evaluationContent;
    private Integer evaluationTypeId;
    private Integer userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(Timestamp evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    public String getEvaluationContent() {
        return evaluationContent;
    }

    public void setEvaluationContent(String evaluationContent) {
        this.evaluationContent = evaluationContent;
    }

    public Integer getEvaluationTypeId() {
        return evaluationTypeId;
    }

    public void setEvaluationTypeId(Integer evaluationTypeId) {
        this.evaluationTypeId = evaluationTypeId;
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

        CommodityevaluationEntity that = (CommodityevaluationEntity) o;

        if (id != that.id) return false;
        if (evaluationTime != null ? !evaluationTime.equals(that.evaluationTime) : that.evaluationTime != null)
            return false;
        if (evaluationContent != null ? !evaluationContent.equals(that.evaluationContent) : that.evaluationContent != null)
            return false;
        if (evaluationTypeId != null ? !evaluationTypeId.equals(that.evaluationTypeId) : that.evaluationTypeId != null)
            return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (evaluationTime != null ? evaluationTime.hashCode() : 0);
        result = 31 * result + (evaluationContent != null ? evaluationContent.hashCode() : 0);
        result = 31 * result + (evaluationTypeId != null ? evaluationTypeId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
