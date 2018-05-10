package com.zhkj.entity;

public class UsertypeEntity {
    private int id;
    private String userTypeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsertypeEntity that = (UsertypeEntity) o;

        if (id != that.id) return false;
        if (userTypeName != null ? !userTypeName.equals(that.userTypeName) : that.userTypeName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userTypeName != null ? userTypeName.hashCode() : 0);
        return result;
    }
}
