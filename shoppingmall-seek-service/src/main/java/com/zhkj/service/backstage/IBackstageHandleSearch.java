package com.zhkj.service.backstage;
/**
 * piPiXia
 * @Author: Jiankang.Ren
 * @Description:
 * @Date: 14:29 2018/5/18 0018
 */
public interface IBackstageHandleSearch {

    /**
     * piPiXia
     * @Author: Jiankang.Ren
     * @Description: 更新index接口
     * @Date: 14:29 2018/5/18 0018
     */
    void updateSearch(String index,String type,String id,Object object);
    /**
     * piPiXia
     * @Author: Jiankang.Ren
     * @Description: 添加数据
     * @Date: 14:30 2018/5/18 0018
     */
    void addSearch(String index, String type, String id, Object object);
    /**
     * piPiXia
     * @Author: Jiankang.Ren
     * @Description: 删除数据
     * @Date: 14:30 2018/5/18 0018
     */
    void deleteSeach(String index, String type, String id);


}
