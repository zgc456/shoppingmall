package com.zhkj.service.backstage;

public interface IBackstageHandleSearch {
    void updateSearch(String index,String type,String id,Object object);
    void addSearch(String index, String type, String id, Object object);
    void deleteSeach(String index, String type, String id);

}
