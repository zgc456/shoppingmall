package com.zhkj.vo.activity_vo;

public class UserVo {
    //客户唯一标识
    private String name;
    //抢购的商品标识
    private int  hoppingName;
    //抢购的开始时间以小时数表示
    private int startTime;

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getHoppingName() {
        return hoppingName;
    }

    public void setHoppingName(int hoppingName) {
        this.hoppingName = hoppingName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
