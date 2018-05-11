package com.zhkj.service.backstage;

public interface IBackstageHandleSearch {
    void updateSearch(String index,String type,String id,String object);
    void addSearch(String index, String type, String id, String object);
    void deleteSeach(String index, String type, String id, String object);
}
