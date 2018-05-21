package com.zhkj.service.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhkj.dto.seek_dto.CommodityDTO;
import com.zhkj.dto.seek_dto.PromotionitemDTO;
import com.zhkj.service.ISearchService;
import com.zhkj.service.entity.CommodityKey;
import com.zhkj.service.entity.CommodityTemplate;
import com.zhkj.service.entity.SearchConditionPageVO;
import com.zhkj.util.ServiceMultiResult;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.min.Min;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class SearchServiceImp implements ISearchService {
    private static final Logger logger=LoggerFactory.getLogger(SearchServiceImp.class);
    @Autowired
    private TransportClient transportClient;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<ServiceMultiResult<CommodityTemplate>> getAllTypeCommodity() {
        List<ServiceMultiResult<CommodityTemplate>> serviceMultiResults=new ArrayList<>();
        List<CommodityTemplate> types=getCommodityType();
        if (types.size()>0){
            for (CommodityTemplate type : types) {
                ServiceMultiResult<CommodityTemplate> serviceMultiResult=new ServiceMultiResult<>();
                SearchResponse response= transportClient.prepareSearch(CommodityKey.INDEX)
                        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                        .setTypes(CommodityKey.TYPES_COMMODITY)
                        .setQuery(QueryBuilders.termQuery(CommodityKey.COMMODITY_TYPE_RELATION_ID,type.getId()))
                        .get();
                List<CommodityTemplate> lists=new ArrayList<>();
                for (SearchHit hit : response.getHits()) {
                    try {
                        CommodityTemplate template=objectMapper.readValue(hit.getSourceAsString(),CommodityTemplate.class);
                        CommodityTemplate minTemplate=this.getCommodityPrice(template.getId());
                        if (minTemplate!=null){
                            template.setCommodityPrice(minTemplate.getCommodityPrice());
                            template.setCommodityNumber(minTemplate.getCommodityNumber());
                            lists.add(template);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                serviceMultiResult.setTypename(type.getTypeName());
                serviceMultiResult.setResult(lists);
                serviceMultiResult.setTotal(new Long(lists.size()));
                serviceMultiResults.add(serviceMultiResult);
            }
        }
        return serviceMultiResults;
    }

    @Override
    public ServiceMultiResult<CommodityTemplate> search(SearchConditionPageVO searchConditionPageVO) {
        int from=0;
        int size=0;
        if (searchConditionPageVO.getSize()>0&&searchConditionPageVO.getSize()>searchConditionPageVO.getFrom()){
            from=searchConditionPageVO.getFrom();
            size=searchConditionPageVO.getSize();
        }

        ServiceMultiResult<CommodityTemplate> serviceMultiResult =new ServiceMultiResult<>();
        BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();
        //商品名称
        if (searchConditionPageVO.getCommodityName()!=null&&!searchConditionPageVO.getCommodityName().isEmpty()){
            boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.COMMODITY_NAME, searchConditionPageVO.getCommodityName()));
        }
        //商品介绍
        if (searchConditionPageVO.getCommodityIntroduce()!=null&&!searchConditionPageVO.getCommodityIntroduce().isEmpty()){
            boolQueryBuilder.filter(
                    QueryBuilders.termQuery(CommodityKey.COMMODITY_INTRODUCE, searchConditionPageVO.getCommodityIntroduce())
            );
        }

        //待修改 商品区间  占时不做
//        RangeQueryBuilder rangeQueryBuilder= QueryBuilders.rangeQuery(CommodityKey.COMMODITY_PRICE);
//        if ((searchConditionPageVO.getCommodityPriceGTE()!=null&& searchConditionPageVO.getCommodityPriceGTE()>0)||(searchConditionPageVO.getCommodityPriceLTE()!=null&& searchConditionPageVO.getCommodityPriceLTE()>0)){
//            if (searchConditionPageVO.getCommodityPriceGTE()!=null&& searchConditionPageVO.getCommodityPriceGTE()>0){
//                rangeQueryBuilder.gte(searchConditionPageVO.getCommodityPriceGTE());
//            }
//            if (searchConditionPageVO.getCommodityPriceLTE()!=null&& searchConditionPageVO.getCommodityPriceLTE()>0){
//                rangeQueryBuilder.lte(searchConditionPageVO.getCommodityPriceLTE());
//            }
//            boolQueryBuilder.filter(rangeQueryBuilder);
//        }

        SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                .setTypes(
                        CommodityKey.TYPES_COMMODITY
                )
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(boolQueryBuilder)
                .setFrom(from)
                .setSize(size)
                .get();
        List<CommodityTemplate> lists=new ArrayList<>();
        for (SearchHit hit:response.getHits()){
            System.out.println(hit.getSourceAsString());//打印查询的信息
            try {
                CommodityTemplate template=objectMapper.readValue(hit.getSourceAsString(),CommodityTemplate.class);
                CommodityTemplate specificationsrelationTemplate=this.getCommodityPrice(template.getId());
                if (specificationsrelationTemplate!=null) {
                    template.setCommodityPrice(specificationsrelationTemplate.getCommodityPrice());
                    template.setCommodityNumber(specificationsrelationTemplate.getCommodityNumber());
                }else{
                    template.setCommodityPrice(0.0);
                    template.setCommodityNumber(0L);
                }
                if (template!=null){
                    lists.add(template);
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        if (lists!=null&&lists.size()>0){
            if (searchConditionPageVO.getOrderDesc()>0){
                Collections.sort(lists,Collections.reverseOrder());
            }else {
                Collections.sort(lists);
            }
            serviceMultiResult.setResult(lists);
            Long total= Long.valueOf(lists.size());
            serviceMultiResult.setTotal(total);
        }
        return serviceMultiResult;
    }

    @Override
    public ServiceMultiResult<CommodityTemplate> byDateSearchCommodity(SearchConditionPageVO searchConditionPageVO) {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();
        if (searchConditionPageVO!=null){
            ServiceMultiResult<CommodityTemplate> serviceMultiResult=new ServiceMultiResult<>();
            if (searchConditionPageVO.getStartDate()!=null&&searchConditionPageVO!=null){
                Long starDate=null;
                Long endDate=null;
                try {
                    starDate=format.parse(searchConditionPageVO.getStartDate()).getTime();
                    endDate=format.parse(searchConditionPageVO.getEndDate()).getTime();
                    boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.START_TIME,starDate));
                    boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.END_TIME,endDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                        .setTypes(CommodityKey.TYPES_PROMOTIONITEM)
                        .setQuery(boolQueryBuilder)
                        .get();
                List<CommodityTemplate> lists=new ArrayList<>();
                for (SearchHit hit : response.getHits()) {
                        CommodityTemplate template=new CommodityTemplate();
                    try {
                        PromotionitemDTO promotionitemDTO = objectMapper.readValue(hit.getSourceAsString(),PromotionitemDTO.class);
                        template.setId(new Integer(promotionitemDTO.getId()).longValue());
                        template.setCommodityNumber(promotionitemDTO.getCommodityNumber().longValue());
                        template.setCommodityPrice(promotionitemDTO.getDiscountPrice().doubleValue());
                        template.setStartTime(promotionitemDTO.getStartTime());
                        template.setEndTime(promotionitemDTO.getEndTime());
                        System.out.println(promotionitemDTO.toString());//这里会输出
                        SearchResponse response1=transportClient.prepareSearch(CommodityKey.INDEX)
                                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                                .setTypes(CommodityKey.TYPES_COMMODITY)
                                .setQuery(QueryBuilders.termQuery(CommodityKey.ID,promotionitemDTO.getCommodityId()))
                                .get();
                        for (SearchHit searchHitFields : response1.getHits()) {
                            CommodityDTO commodityDTO=objectMapper.readValue(searchHitFields.getSourceAsString(),CommodityDTO.class);
                            template.setCommodityName(commodityDTO.getCommodityName());
                            template.setCommodityIntroduce(commodityDTO.getCommodityIntroduce());
                            template.setBigPictureUrl(commodityDTO.getBigPictureUrl());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lists.add(template);
                }
                serviceMultiResult.setResult(lists);
                serviceMultiResult.setTotal(response.getHits().totalHits);
                return serviceMultiResult;
            }
        }
        logger.error("parameter cannot null:"+searchConditionPageVO);
        return null;
    }

    @Override
    public ServiceMultiResult<CommodityTemplate> byIdSearchCommodity(Long id) {


        return null;
    }

    /**
     * 根据商品id获取价钱最低的商品
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
        for (SearchHit hit : response.getHits()) {
            try {
                CommodityTemplate template=objectMapper.readValue(hit.getSourceAsString(),CommodityTemplate.class);
                Double price=template.getCommodityPrice();
                if (price!=null&&price==min.getValue()){
                    return template;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * 获取商品类型
     * @return 返回商品类型集合
     */
    public List<CommodityTemplate> getCommodityType(){
        List<CommodityTemplate> list=new ArrayList<>();
        SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(CommodityKey.TYPES_TYPE)
                .get();
        for (SearchHit hit : response.getHits()) {
            try {
                CommodityTemplate type=objectMapper.readValue(hit.getSourceAsString(),CommodityTemplate.class);
                if (type!=null){
                    list.add(type);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
