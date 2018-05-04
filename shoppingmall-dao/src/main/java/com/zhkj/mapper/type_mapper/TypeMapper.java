package com.zhkj.mapper.type_mapper;

import com.zhkj.entity.TypeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeMapper {
    List<TypeEntity> selectAllTypeName();
}
