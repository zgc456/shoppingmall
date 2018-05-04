package com.zhkj.entity;

import java.util.Objects;

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
        return id == that.id &&
                Objects.equals(dName, that.dName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, dName);
    }
}
