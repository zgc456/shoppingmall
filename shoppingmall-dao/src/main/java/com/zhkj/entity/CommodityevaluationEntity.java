package com.zhkj.entity;

import java.sql.Timestamp;

public class CommodityevaluationEntity {
    private int id;
    private Timestamp evaluationTime;
    private String evaluationContent;
    private int evaluationTypeId;
    private int userId;
    private int commodityId;

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

    public int getEvaluationTypeId() {
        return evaluationTypeId;
    }

    public void setEvaluationTypeId(int evaluationTypeId) {
        this.evaluationTypeId = evaluationTypeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommodityevaluationEntity that = (CommodityevaluationEntity) o;

        if (id != that.id) return false;
        if (evaluationTypeId != that.evaluationTypeId) return false;
        if (userId != that.userId) return false;
        if (commodityId != that.commodityId) return false;
        if (evaluationTime != null ? !evaluationTime.equals(that.evaluationTime) : that.evaluationTime != null)
            return false;
        if (evaluationContent != null ? !evaluationContent.equals(that.evaluationContent) : that.evaluationContent != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (evaluationTime != null ? evaluationTime.hashCode() : 0);
        result = 31 * result + (evaluationContent != null ? evaluationContent.hashCode() : 0);
        result = 31 * result + evaluationTypeId;
        result = 31 * result + userId;
        result = 31 * result + commodityId;
        return result;
    }
}
