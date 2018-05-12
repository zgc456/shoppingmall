package com.zhkj.service.backstage;

public interface IBackstageHandleSearch {
    <T> void updateSearch(String index,String type,String id,Object object,Class<T> valueType);
    <T> void addSearch(String index, String type, String id, Object object,Class<T> valueType);
    <T> void deleteSeach(String index, String type, String id,Class<T> valueType);
}
