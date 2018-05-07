package com.zhkj.service.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImp implements ISearchService {
    @Autowired
    private TransportClient transportClient;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public ServiceMultiResult<CommodityTemplate> byConditionSearch(SearchCondition searchCondition) {
        return null;
    }

    @Override
    public ServiceMultiResult<CommodityTemplate> search(SearchCondition searchCondition) {
        ServiceMultiResult<CommodityTemplate> serviceMultiResult =new ServiceMultiResult<>();
        BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();
        if (searchCondition.getCommodityName()!=null&&!searchCondition.getCommodityName().isEmpty()){
            boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.COMMODITY_NAME,searchCondition.getCommodityName()));
        }
        if (searchCondition.getCommodityIntroduce()!=null&&!searchCondition.getCommodityIntroduce().isEmpty()){
            boolQueryBuilder.filter(
                    QueryBuilders.termQuery(CommodityKey.COMMODITY_INTRODUCE,searchCondition.getCommodityIntroduce())
            );
        }

        //待修改
        RangeQueryBuilder rangeQueryBuilder= QueryBuilders.rangeQuery(CommodityKey.COMMODITY_PRICE);
        if ((searchCondition.getCommodityPriceGTE()!=null&&searchCondition.getCommodityPriceGTE()>0)||(searchCondition.getCommodityPriceLTE()!=null&&searchCondition.getCommodityPriceLTE()>0)){
            if (searchCondition.getCommodityPriceGTE()!=null&&searchCondition.getCommodityPriceGTE()>0){
                rangeQueryBuilder.gte(searchCondition.getCommodityPriceGTE());
            }
            if (searchCondition.getCommodityPriceLTE()!=null&&searchCondition.getCommodityPriceLTE()>0){
                rangeQueryBuilder.lte(searchCondition.getCommodityPriceLTE());
            }
            boolQueryBuilder.filter(rangeQueryBuilder);
        }
        //

        if (searchCondition.getDiscountIntroduce()!=null&&!searchCondition.getDiscountIntroduce().isEmpty()){
            boolQueryBuilder.filter(
                    QueryBuilders.termQuery(CommodityKey.DISCOUNT_INTRODUCE,searchCondition.getDiscountIntroduce())
            );
        }
        if (searchCondition.getTypeName()!=null&&!searchCondition.getTypeName().isEmpty()){
            boolQueryBuilder.filter(
                    QueryBuilders.termQuery(CommodityKey.TYPE_NAME,searchCondition.getTypeName())
            );
        }
        SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                .setTypes(
                        CommodityKey.TYPES_COMMODITY
                        ,CommodityKey.TYPES_TYPE
                        ,CommodityKey.TYPES_COMMODITYTYPERELATION
                        ,CommodityKey.TYPES_SPECIFICATIONSTOPIC
                        ,CommodityKey.TYPES_SPECIFICATIONSDETAILED
                        ,CommodityKey.TYPES_DISCOUNT
                )
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(boolQueryBuilder)
                .setFrom(0).setSize(10).setExplain(true).get();
        List<CommodityTemplate> lists=new ArrayList<>();
        for (SearchHit hit:response.getHits()){
            try {
                CommodityTemplate template=objectMapper.readValue(hit.getSourceAsString(),CommodityTemplate.class);
                List<CommodityTemplate> list=this.getCommodityPrice(template.getId());

                if (template!=null){
                    lists.add(template);
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        if (lists!=null&&lists.size()>0){
            serviceMultiResult.setResult(lists);
            Long total= Long.valueOf(lists.size());
            serviceMultiResult.setTotal(total);
        }
        return serviceMultiResult;
    }

    /**
     * 根据商品id获取价钱
     * @param id
     * @return
     */
    public List<CommodityTemplate> getCommodityPrice(Long id){
        List<CommodityTemplate> lists=new ArrayList<>();
        SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                .setTypes(CommodityKey.TYPES_SPECIFICATIONSRELATION)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery(CommodityKey.COMMODITY_ID,id))
                .addAggregation(AggregationBuilders.min(CommodityKey.COMMODITY_PRICE).field(CommodityKey.COMMODITY_PRICE))
                .get();
        for (SearchHit hit : response.getHits()) {
            try {
                CommodityTemplate template=objectMapper.readValue(hit.getSourceAsString(),CommodityTemplate.class);
                lists.add(template);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lists;
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
