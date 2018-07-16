package com.zhkj.service;

import com.zhkj.api.advertisement_api.AdvertisementService;
import com.zhkj.copy_properties.Conver_Type;
import com.zhkj.dto.advertisement_dto.AdvertisementDTO;
import com.zhkj.mapper.advertisement_mapper.AdvertisementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {
    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Override
    public List<AdvertisementDTO> queryAdvertisement() {
        List<AdvertisementDTO> list=new ArrayList<>();
        return Conver_Type.convertToList(list,advertisementMapper.queryAdvertisement(),"com.zhkj.dto.advertisement_dto.AdvertisementDTO");
    }
}
