package com.zhkj.service;

import com.zhkj.api.type_api.TypeService;
import com.zhkj.copy_properties.Conver_Type;
import com.zhkj.dto.type_dto.TypeDTO;
import com.zhkj.mapper.type_mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public List<TypeDTO> selectAllTypeName() {
        List<TypeDTO> list=new ArrayList<>();
        return Conver_Type.convertToList(list,typeMapper.selectAllTypeName(),"com.zhkj.dto.type_dto.TypeDTO");
    }
}
