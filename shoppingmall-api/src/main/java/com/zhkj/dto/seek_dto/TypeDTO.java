package com.zhkj.dto.seek_dto;

import java.util.Objects;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description:
 * @Date: Created in 20:52 2018/5/21 0021
 */
public class TypeDTO {
    private int id;
    private String typeName;
    private int levels;
    private int parentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getLevels() {
        return levels;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeDTO typeDTO = (TypeDTO) o;
        return id == typeDTO.id &&
                levels == typeDTO.levels &&
                parentId == typeDTO.parentId &&
                Objects.equals(typeName, typeDTO.typeName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, typeName, levels, parentId);
    }
}
