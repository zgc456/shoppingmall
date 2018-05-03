package com.zhkj.service.imp;

import com.zhkj.service.ISearchElasticDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

public class SearchElasticDBImp implements ISearchElasticDB{
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Override
    public void search_Commodity(Integer id) {

    }

    @Override
    public void search_Commoditytyperelation(Integer id) {

    }

    @Override
    public void search_Specificationsrelation(Integer id) {

    }

    @Override
    public void search_Specificationstopic(Integer id) {

    }

    @Override
    public void search_Specificationsdetailed(Integer id) {

    }

    @Override
    public void search_Discount(Integer id) {

    }

    @Override
    public void search_Type(Integer id) {

    }
}
