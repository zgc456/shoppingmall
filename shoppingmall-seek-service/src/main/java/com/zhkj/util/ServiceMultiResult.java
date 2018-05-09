package com.zhkj.util;

import java.util.List;

/**
 * 所有业务通用的多个返回对象
 * @param <T>
 */
public class ServiceMultiResult<T> {
    private Long total;
    private String typename;
    private List<T> result;

    public ServiceMultiResult() {
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public ServiceMultiResult(Long total, List<T> result) {
        this.total = total;
        this.result = result;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
