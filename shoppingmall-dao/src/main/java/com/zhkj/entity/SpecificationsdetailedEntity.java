package com.zhkj.entity;

public class SpecificationsdetailedEntity {
    private int id;
    private String dName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecificationsdetailedEntity that = (SpecificationsdetailedEntity) o;

        if (id != that.id) return false;
        if (dName != null ? !dName.equals(that.dName) : that.dName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dName != null ? dName.hashCode() : 0);
        return result;
    }
}
