package com.zhkj.dto.seek_dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description:
 * @Date: Created in 14:29 2018/5/11 0011
 */
public class PromotionitemDTO {
    private int id;
    private Timestamp startTime;
    private Timestamp endTime;
    private BigDecimal discountPrice;
    private Integer commodityNumber;
    private Integer commodityId;


}
