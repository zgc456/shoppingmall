package com.zhkj.service.entity;

import java.util.Set;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description: 商品规格分类 例如：颜色：红色、黑色、白色、 尺码:X、XL
 * @Date: Created in 17:09 2018/7/19 0019
 */
public class CommoditySpecificationTypeDTO {
    private String typeName;//商品规格分类名字，例如：颜色，尺码

    private Set<CommodityTypeSecondName> typeDetailed;//商品规格分类详细规格，例如红色、黑色、白色
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Set<CommodityTypeSecondName> getTypeDetailed() {
        return typeDetailed;
    }

    public void setTypeDetailed(Set<CommodityTypeSecondName> typeDetailed) {
        this.typeDetailed = typeDetailed;
    }

}
