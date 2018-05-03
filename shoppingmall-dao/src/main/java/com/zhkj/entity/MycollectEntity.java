package com.zhkj.entity;

/**
 * Created by lenovo on 2018/4/16.
 */
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

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (commdityId != null ? !commdityId.equals(that.commdityId) : that.commdityId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (commdityId != null ? commdityId.hashCode() : 0);
        return result;
    }
}
