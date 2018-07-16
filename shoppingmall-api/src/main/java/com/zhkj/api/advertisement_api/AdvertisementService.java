package com.zhkj.api.advertisement_api;

import com.zhkj.dto.advertisement_dto.AdvertisementDTO;

import java.util.List;

public interface AdvertisementService {
    List<AdvertisementDTO> queryAdvertisement();
}
