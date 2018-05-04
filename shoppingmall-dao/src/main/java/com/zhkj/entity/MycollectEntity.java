package com.zhkj.entity;

import java.util.Objects;

public class MycollectEntity {
    private int id;
    private Integer userId;
    private Integer commdityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommdityId() {
        return commdityId;
    }

    public void setCommdityId(Integer commdityId) {
        this.commdityId = commdityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MycollectEntity that = (MycollectEntity) o;
        return id == that.id &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(commdityId, that.commdityId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, commdityId);
    }
}
