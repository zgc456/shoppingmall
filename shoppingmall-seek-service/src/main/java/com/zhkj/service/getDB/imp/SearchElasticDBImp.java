package com.zhkj.service.getDB.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhkj.service.entity.SearchConditionPageVO;
import com.zhkj.service.entity.SearchCondotionServiceVO;
import com.zhkj.service.getDB.ISearchElasticDB;
import com.zhkj.service.entity.CommodityKey;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
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
    public String search_Commodity(SearchCondotionServiceVO searchCondotionServiceVO) {
        GetResponse response=transportClient.prepareGet(CommodityKey.INDEX,CommodityKey.TYPES_COMMODITY, searchCondotionServiceVO.getId())
                .setOperationThreaded(false).get();
        return response.getSourceAsString();
    }

    @Override
    public void search_Commoditytyperelation(SearchCondotionServiceVO searchCondotionServiceVO) {

    }

    @Override
    public void search_Specificationsrelation(SearchCondotionServiceVO searchCondotionServiceVO) {
        BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();
        if (searchCondotionServiceVO !=null){ //根据id主键查询商品关系
            if (!searchCondotionServiceVO.getId().isEmpty()){
                boolQueryBuilder.filter(
                        QueryBuilders.termQuery(CommodityKey.ID, searchCondotionServiceVO.getId())
                );
            }
            if (searchCondotionServiceVO.getTypeid()>0){
                boolQueryBuilder.filter(
                  QueryBuilders.termQuery(CommodityKey.TYPEID,searchCondotionServiceVO.getTypeid())
                );
            }
            if (searchCondotionServiceVO.getCommodityid()>0){
                boolQueryBuilder.filter(
                        QueryBuilders.termQuery(CommodityKey.COMMODITY_ID,searchCondotionServiceVO.getCommodityid())
                );
            }
        }
        transportClient.prepareSearch(CommodityKey.INDEX)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(CommodityKey.TYPES_SPECIFICATIONSRELATION)
                .setQuery(boolQueryBuilder)
                .get();

    }

    @Override
    public void search_Specificationstopic(SearchCondotionServiceVO searchCondotionServiceVO) {

    }

    @Override
    public void search_Specificationsdetailed(SearchCondotionServiceVO searchCondotionServiceVO) {

    }

    @Override
    public void search_Discount(SearchCondotionServiceVO searchCondotionServiceVO) {

    }

    @Override
    public void search_Type(SearchCondotionServiceVO searchCondotionServiceVO) {

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
