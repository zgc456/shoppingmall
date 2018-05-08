package com.zhkj.service.getDB.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhkj.service.entity.SearchCondition;
import com.zhkj.service.getDB.ISearchElasticDB;
import com.zhkj.service.entity.CommodityKey;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchElasticDBImp implements ISearchElasticDB{
    @Autowired
    private TransportClient transportClient;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public String search_Commodity(SearchCondition searchCondition) {
        GetResponse response=transportClient.prepareGet(CommodityKey.INDEX,CommodityKey.TYPES_COMMODITY,searchCondition.getId())
                .setOperationThreaded(false).get();
        return response.getSourceAsString();
    }

    @Override
    public void search_Commoditytyperelation(SearchCondition searchCondition) {

    }

    @Override
    public void search_Specificationsrelation(SearchCondition searchCondition) {

    }

    @Override
    public void search_Specificationstopic(SearchCondition searchCondition) {

    }

    @Override
    public void search_Specificationsdetailed(SearchCondition searchCondition) {

    }

    @Override
    public void search_Discount(SearchCondition searchCondition) {

    }

    @Override
    public void search_Type(SearchCondition searchCondition) {

    }


    @Override
    public void searchDelete(String id) {
       DeleteResponse response= transportClient.prepareDelete(CommodityKey.INDEX,CommodityKey.TYPES_COMMODITY,id).get();
    }

    @Override
    public void searchDeleteByQuery(String name) {
        BulkByScrollResponse response= DeleteByQueryAction.INSTANCE.newRequestBuilder(transportClient)
                .filter(QueryBuilders.matchQuery("name",name))
                .source("test").get();
        Long deleted=response.getDeleted();
        System.out.println(deleted);
    }
}
