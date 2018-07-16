package com.zhkj.mapper.advertisement_mapper;

import com.zhkj.entity.AdvertisementEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementMapper {
    List<AdvertisementEntity> queryAdvertisement();
}
