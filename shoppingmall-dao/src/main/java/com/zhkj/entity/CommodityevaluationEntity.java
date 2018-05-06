package com.zhkj.entity;

import java.sql.Timestamp;
import java.util.Objects;

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
        return id == that.id &&
                Objects.equals(evaluationTime, that.evaluationTime) &&
                Objects.equals(evaluationContent, that.evaluationContent) &&
                Objects.equals(evaluationTypeId, that.evaluationTypeId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, evaluationTime, evaluationContent, evaluationTypeId, userId);
    }
}
