package com.zhkj.service.entity;


/**
 * 返回给前台对象
 */
public class CommodityTemplate implements Comparable<CommodityTemplate> {
    /**
     * 商品
     */
    private Long id;
    /**
     * 商品名称
     */
    private String commodityname;
    /**
     * 商品介绍
     */
    private String commodityintroduce;
    /**
     * 商品图片路径
     */
    private String bigpictureurl;
    /**
     * 商品数量
     */
    private Long commoditynumber;
    /**
     * 商品价格
     */
    private Double commodityprice;
    /**
     * 商品类型名字
     */
    private String typename;

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommodityname() {
        return commodityname;
    }

    public void setCommodityname(String commodityname) {
        this.commodityname = commodityname;
    }

    public String getCommodityintroduce() {
        return commodityintroduce;
    }

    public void setCommodityintroduce(String commodityintroduce) {
        this.commodityintroduce = commodityintroduce;
    }

    public String getBigpictureurl() {
        return bigpictureurl;
    }

    public void setBigpictureurl(String bigpictureurl) {
        this.bigpictureurl = bigpictureurl;
    }

    public Long getCommoditynumber() {
        return commoditynumber;
    }

    public void setCommoditynumber(Long commoditynumber) {
        this.commoditynumber = commoditynumber;
    }

    public Double getCommodityprice() {
        return commodityprice;
    }

    public void setCommodityprice(Double commodityprice) {
        this.commodityprice = commodityprice;
    }

    @Override
    public String toString() {
        return "CommodityTemplate{" +
                "id=" + id +
                ", commodityname='" + commodityname + '\'' +
                ", commodityintroduce='" + commodityintroduce + '\'' +
                ", bigpictureurl='" + bigpictureurl + '\'' +
                ", commoditynumber=" + commoditynumber +
                ", commodityprice=" + commodityprice +
                '}';
    }

    @Override
    public int compareTo(CommodityTemplate o) {
        if (this.commodityprice>=o.getCommodityprice()){
            return 1;
        }
        return -1;
    }
}