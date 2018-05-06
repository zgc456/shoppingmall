package com.zhkj.service.imp;

import com.zhkj.service.ISearchService;
import com.zhkj.service.entity.CommodityKey;
import com.zhkj.service.entity.CommodityTemplate;
import com.zhkj.service.entity.SearchCondition;
import com.zhkj.service.entity.Test;
import com.zhkj.util.ServiceMultiResult;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.elasticsearch.index.query.QueryBuilders.fuzzyQuery;

@Service
public class SearchServiceImp implements ISearchService {
    @Autowired
    private TransportClient transportClient;

    @Override
    public ServiceMultiResult<CommodityTemplate> byConditionSearch(SearchCondition searchCondition) {
        return null;
    }

    @Override
    public void search(SearchCondition searchCondition) {
//        if (searchCondition.getCommodityPriceGTE()!=null&&searchCondition.getCommodityPriceGTE()>0){
//            rangeQueryBuilder.gte(searchCondition.getCommodityPriceGTE());
//        }
//        if (searchCondition.getCommodityPriceLTE()!=null&&searchCondition.getCommodityPriceLTE()>=0){
//            rangeQueryBuilder.lte(searchCondition.getCommodityPriceLTE());
//        }
//        queryBuilder.filter(rangeQueryBuilder);
//        if (searchCondition.getDiscountIntroduce()!=null&&!searchCondition.getDiscountIntroduce().isEmpty()){
//            queryBuilder.filter(
//              QueryBuilders.termQuery(CommodityKey.DISCOUNT_INTRODUCE,searchCondition.getDiscountIntroduce())
//            );
//        }
//        if (searchCondition.getTypeNames()!=null&&!searchCondition.getTypeNames().isEmpty()){
//            queryBuilder.filter(
//                    QueryBuilders.termQuery(CommodityKey.TYPE_NAME,searchCondition.getTypeNames())
//            );
//        }
//
//        SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
//                .setTypes(
//                        CommodityKey.TYPES_COMMODITY
//                        , CommodityKey.TYPES_COMMODITYTYPERELATION
//                        , CommodityKey.TYPES_DISCOUNT
//                        , CommodityKey.TYPES_SPECIFICATIONSDETAILED
//                        , CommodityKey.TYPES_SPECIFICATIONSRELATION
//                        , CommodityKey.TYPES_SPECIFICATIONSTOPIC
//                        , CommodityKey.TYPES_TYPE
//                )
//                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                .setQuery(queryBuilder)
//                .setFrom(0)
//                .setSize(1)
//                .setExplain(true)
//                .get();
//        for (SearchHit hit:response.getHits()){
//            System.out.println(hit.getSourceAsString());
//        }
        BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();
        if (searchCondition.getCommodityName()!=null&&!searchCondition.getCommodityName().isEmpty()){
            boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.COMMODITY_NAME,searchCondition.getCommodityName()));
        }
        if (searchCondition.getCommodityIntroduce()!=null&&!searchCondition.getCommodityIntroduce().isEmpty()){
            boolQueryBuilder.filter(
                QueryBuilders.termQuery(CommodityKey.COMMODITY_INTRODUCE,searchCondition.getCommodityIntroduce())
        );
        }
        RangeQueryBuilder rangeQueryBuilder= QueryBuilders.rangeQuery(CommodityKey.COMMODITY_PRICE);
        if (searchCondition.getCommodityPriceGTE()!=null&&searchCondition.getCommodityPriceGTE()>0){
            rangeQueryBuilder.gte(searchCondition.getCommodityPriceGTE());
        }
        boolQueryBuilder.filter(rangeQueryBuilder);
        SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                .setTypes(
                        CommodityKey.TYPES_COMMODITY
                        ,CommodityKey.TYPES_TYPE
                        ,CommodityKey.TYPES_COMMODITYTYPERELATION
                        ,CommodityKey.TYPES_SPECIFICATIONSTOPIC
                        ,CommodityKey.TYPES_SPECIFICATIONSDETAILED
                        ,CommodityKey.TYPES_DISCOUNT
                        ,CommodityKey.TYPES_SPECIFICATIONSRELATION
                )
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(boolQueryBuilder)
                .setFrom(0).setSize(10).setExplain(true).get();
        for (SearchHit hit:response.getHits()){
            System.out.println(hit.getSourceAsString());
        }

    }

    @Override
    public void searchEay(Test test) {
        BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();
        boolQueryBuilder.filter(
                QueryBuilders.termQuery("name",test.getName())
        );
        SearchResponse response=transportClient.prepareSearch("test")
                .setTypes("t")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(boolQueryBuilder)
                .setFrom(0)
                .setSize(3)
                .setExplain(true)
                .get();
        for (SearchHit hit:response.getHits()){
            System.out.println(hit.getSourceAsString());
        }
    }
}
