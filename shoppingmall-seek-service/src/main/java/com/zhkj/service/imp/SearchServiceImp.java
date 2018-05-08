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
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.min.Min;
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
        //商品名称
        if (searchCondition.getCommodityName()!=null&&!searchCondition.getCommodityName().isEmpty()){
            boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.COMMODITY_NAME,searchCondition.getCommodityName()));
        }
        //商品介绍
        if (searchCondition.getCommodityIntroduce()!=null&&!searchCondition.getCommodityIntroduce().isEmpty()){
            boolQueryBuilder.filter(
                    QueryBuilders.termQuery(CommodityKey.COMMODITY_INTRODUCE,searchCondition.getCommodityIntroduce())
            );
        }

        //待修改 商品区间  占时不做
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
        //打折介绍
        if (searchCondition.getDiscountIntroduce()!=null&&!searchCondition.getDiscountIntroduce().isEmpty()){
            boolQueryBuilder.filter(
                    QueryBuilders.termQuery(CommodityKey.DISCOUNT_INTRODUCE,searchCondition.getDiscountIntroduce())
            );
        }
        //商品类型
        if (searchCondition.getTypeName()!=null&&!searchCondition.getTypeName().isEmpty()){
            boolQueryBuilder.filter(
                    QueryBuilders.termQuery(CommodityKey.TYPE_NAME,searchCondition.getTypeName())
            );
        }
        /*
           不查询的type
           ,CommodityKey.TYPES_TYPE 商品类型
           ,CommodityKey.TYPES_SPECIFICATIONSTOPIC 商品规格表
           ,CommodityKey.TYPES_SPECIFICATIONSDETAILED 商品详细规格
           ,CommodityKey.TYPES_DISCOUNT 商品详细规格
           ,CommodityKey.TYPES_COMMODITYTYPERELATION 这里不查询商品关系表 在下面已经查询过了
         */
        SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                .setTypes(
                        CommodityKey.TYPES_COMMODITY
                )
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(boolQueryBuilder)
                .setFrom(0)
                .setSize(100)
                .setExplain(true)
                .get();
        List<CommodityTemplate> lists=new ArrayList<>();
        for (SearchHit hit:response.getHits()){
            System.out.println(hit.getSourceAsString());
            try {
                CommodityTemplate template=objectMapper.readValue(hit.getSourceAsString(),CommodityTemplate.class);
                CommodityTemplate specificationsrelationTemplate=this.getCommodityPrice(template.getId());
                if (specificationsrelationTemplate!=null) {
                    template.setCommodityprice(specificationsrelationTemplate.getCommodityprice());
                    template.setCommoditynumber(specificationsrelationTemplate.getCommoditynumber());
                }else{
                    template.setCommodityprice(0.0);
                    template.setCommoditynumber(0L);
                }
                System.out.println(template.toString());
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
    public CommodityTemplate getCommodityPrice(Long id){

        SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                .setTypes(CommodityKey.TYPES_SPECIFICATIONSRELATION)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery(CommodityKey.COMMODITY_ID,id))
                .addAggregation(AggregationBuilders.min("min"+CommodityKey.COMMODITY_PRICE).field(CommodityKey.COMMODITY_PRICE))
                .get();
        Min min=response.getAggregations().get("min"+CommodityKey.COMMODITY_PRICE);
        System.out.println(min.getValue());
        for (SearchHit hit : response.getHits()) {
            try {
                CommodityTemplate template=objectMapper.readValue(hit.getSourceAsString(),CommodityTemplate.class);
                System.out.println(template.toString());
                Double price=template.getCommodityprice();
                if (price==min.getValue()){
                    return template;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
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
