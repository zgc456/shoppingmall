package com.zhkj.dto.seek_dto;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description:
 * @Date: Created in 20:52 2018/5/21 0021
 */
public class CommodityevaluationDTO {
    private int id;
    private Timestamp evaluationTime;
    private String evaluationContent;
    private int evaluationTypeId;
    private long userId;
    private int commodityId;
    private String userName;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
