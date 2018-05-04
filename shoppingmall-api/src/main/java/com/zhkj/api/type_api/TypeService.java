package com.zhkj.api.type_api;

import com.zhkj.dto.type_dto.TypeDTO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface TypeService {
    @GetMapping("/selectAllTypeName")
    List<TypeDTO> selectAllTypeName();
}
