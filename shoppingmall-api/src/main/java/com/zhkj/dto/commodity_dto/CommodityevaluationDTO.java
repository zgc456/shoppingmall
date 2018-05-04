package com.zhkj.dto.commodity_dto;

import java.sql.Timestamp;

public class CommodityevaluationDTO {
    private Integer id;
    private Timestamp evaluationTime;
    private Timestamp evaluationContent;
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(Timestamp evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    public Timestamp getEvaluationContent() {
        return evaluationContent;
    }

    public void setEvaluationContent(Timestamp evaluationContent) {
        this.evaluationContent = evaluationContent;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
