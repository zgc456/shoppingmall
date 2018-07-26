package com.zhkj.service.imp;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhkj.dto.seek_dto.*;
import com.zhkj.service.ISearchService;
import com.zhkj.service.backstage.entity.IndexMessageVO;
import com.zhkj.service.entity.*;
import com.zhkj.util.ServiceMultiResult;
import joptsimple.internal.Strings;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;


@Service
public class SearchServiceImp implements ISearchService {
    private SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//转换时间对象
    private static final Logger logger=LoggerFactory.getLogger(SearchServiceImp.class);
    @Autowired
    private TransportClient transportClient;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 已完善
     * @return
     */
    @Override
    public List<ServiceMultiResult<CommodityTemplate>> getAllTypeCommodity() {
        List<ServiceMultiResult<CommodityTemplate>> serviceMultiResults=new ArrayList<>();
//#######################################根据商品condition查询所有commodity
        List<CommodityDTO> commodityDTOList=new LinkedList<>();
        SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(CommodityKey.TYPES_COMMODITY)
                .get();
        response.getHits().forEach(hit -> {
            try {
                commodityDTOList.add(objectMapper.readValue(hit.getSourceAsString(),CommodityDTO.class));
            } catch (IOException e) {
                logger.error(hit.getSourceAsString()+"转换失败",e);
            }
        });
//########################################
        if (commodityDTOList.size()>0) {
            List<TypeDTO> list=this.getAllSecondLevelType();//获取所有商品分类二级分类
            list.forEach(typeDTO -> serviceMultiResults.add(this.analysisCommodityList(commodityDTOList,typeDTO)));
            return serviceMultiResults;
        }else {
            return null;
        }
    }

    /**
     * 根据商品类型返回这个类型的所有商品
     * @param lists
     * @return
     */
    public ServiceMultiResult<CommodityTemplate> analysisCommodityList(List<CommodityDTO> lists,TypeDTO typeDTO){
        ServiceMultiResult<CommodityTemplate> serviceMultiResult = new ServiceMultiResult<>();
        List<Integer> numbers = new ArrayList<>();
        if (typeDTO!=null){
            List<CommodityTemplate> commodityDTOList = new ArrayList<>();
            serviceMultiResult.setTypename(typeDTO.getTypeName());
            for (int i=0;i<lists.size();i++){
                if (lists.get(i) != null && typeDTO.getTypeName().equals(lists.get(i).getTypeName())) {
                    numbers.add(i);
                    CommoditySpecificationInventoryPriceDTO commoditySpecificationInventoryPriceDTO = getCommodityPrice(new Long(lists.get(i).getId()));
                    CommodityTemplate commodityTemplate = new CommodityTemplate();
                    commodityTemplate.setId(new Long(lists.get(i).getId()));
                    commodityTemplate.setBigPictureUrl(lists.get(i).getBigPictureUrl());
                    commodityTemplate.setCommodityName(lists.get(i).getCommodityName());
                    if (commoditySpecificationInventoryPriceDTO!=null){
                        commodityTemplate.setCommodityPrice(commoditySpecificationInventoryPriceDTO.getPrice());//商品最低价钱
                        commodityTemplate.setInventory(new Long(commoditySpecificationInventoryPriceDTO.getInventory()));//库存
                    }
                    commodityDTOList.add(commodityTemplate);
                }
            }
            numbers.forEach(integer -> lists.remove(integer));
            serviceMultiResult.setResult(commodityDTOList);
            serviceMultiResult.setTotal(new Long(serviceMultiResult.getResult().size()));
        }else {
            logger.warn(typeDTO+"不能为null");
        }
        return serviceMultiResult;
    }

    /**
     * 根据条件查询商品列表
     * @param condition 条件为null就查询所有commodity
     * @return
     */
    @Override
    public ServiceMultiResult<CommodityTemplate> byConditionSearchAllCommodity(SearchConditionPageVO condition) {
        ServiceMultiResult<CommodityTemplate> serviceMultiResult =new ServiceMultiResult<>();//创建返回给前台对象
        BoolQueryBuilder boolQueryBuilder=boolQuery();//创建查询
        SearchRequestBuilder searchRequestBuilder=transportClient.prepareSearch(CommodityKey.INDEX)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(CommodityKey.TYPES_COMMODITY);
        if (condition.getCommodityName()!=null) {
            boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.COMMODITY_NAME, condition.getCommodityName()));
            searchRequestBuilder.setQuery(boolQueryBuilder);
        }
        if (condition.getId()!=null){
            boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.ID,condition.getId()));
            searchRequestBuilder.setQuery(boolQueryBuilder);
        }
        if (condition.getBegin()!=0){
            searchRequestBuilder.setFrom(condition.getBegin());
        }
        if (condition.getSize()!=0){
            searchRequestBuilder.setSize(condition.getSize());
        }
        SearchResponse response=searchRequestBuilder.get();
        List<CommodityTemplate> lists=new ArrayList<>();
        response.getHits().forEach(hit -> {
            try {
                CommodityTemplate commodityTemplate=new CommodityTemplate();
                CommodityDTO commodityDTO=objectMapper.readValue(hit.getSourceAsString(),CommodityDTO.class);//商品的信息
                CommoditySpecificationInventoryPriceDTO commoditySpecificationInventoryPriceDTO=this.getCommodityPrice(new Long(commodityDTO.getId()));
                if (commoditySpecificationInventoryPriceDTO!=null){
                    modelMapper.map(commoditySpecificationInventoryPriceDTO,commodityTemplate);
                }
                modelMapper.map(commodityDTO,commodityTemplate);
                lists.add(commodityTemplate);
            } catch (IOException e) {
                logger.warn(hit.getSourceAsString()+"转换CommodityDTO.class失败",e);
            }
        });
        serviceMultiResult.setResult(lists);
        serviceMultiResult.setTotal(new Long(lists.size()));
        if (lists.size()>0){
            if (condition.getOrderDesc()==0){
                Collections.sort(lists);//升序
            }else {
                Collections.sort(lists,Collections.reverseOrder());//降序
            }
        }
        return serviceMultiResult;
    }

    /**
     * 查commodity_specification_inventory_price
     * 查询商品详情(价格,数量,规格,小图片)
     * @param CommodityId 商品id 可为空,为空不查询这一项
     * @param priceLTE 商品最低价 可为空,为空不查询这一项
     * @param priceGTE 商品最高价 可为空,为空不查询这一项
     * @return 返回商品详情集合
     */
    public List<CommoditySpecificationInventoryPriceDTO> byConditonSearchCommoditySpecificaation(Long CommodityId,Double priceLTE,Double priceGTE){
        List<CommoditySpecificationInventoryPriceDTO> list=new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder=boolQuery();
        if (CommodityId!=null){
            boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.COMMODITY_ID,CommodityId));
        }
        RangeQueryBuilder rangeQueryBuilder=QueryBuilders.rangeQuery(CommodityKey.PRICE);
        if (priceLTE!=null){
            rangeQueryBuilder.lte(priceLTE);
        }
        if (priceGTE!=null){
            rangeQueryBuilder.gte(priceGTE);
        }
        boolQueryBuilder.filter(rangeQueryBuilder);
        SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(CommodityKey.TYPES_COMMODITY_SPECIFICATION_INVENTORY_PRICE)
                .setQuery(boolQueryBuilder)
                .get();
        response.getHits().forEach(hit -> {
            try {
                list.add(objectMapper.readValue(hit.getSourceAsString(),CommoditySpecificationInventoryPriceDTO.class));
            } catch (IOException e) {
                logger.error(hit.getSourceAsString()+"解析出现错误",e);
            }
        });
        return list;
    }

    @Override
    public ServiceMultiResult<CommodityTemplate> byDateSearchCommodity(SearchConditionPageVO searchConditionPageVO) {
        BoolQueryBuilder boolQueryBuilder=boolQuery();//bool查询
        ServiceMultiResult<CommodityTemplate> serviceMultiResult=new ServiceMultiResult<>();//返回给前台的对象
        if (searchConditionPageVO.getStartDate()!=null&&searchConditionPageVO.getEndDate()!=null){//抢购开始时间不为空
//            Long starDate=null;
//            Long endDate=null;
            try {
                long starDate=format.parse(searchConditionPageVO.getStartDate()).getTime();//抢购开始时间转换为long类型
                long endDate=format.parse(searchConditionPageVO.getEndDate()).getTime();//抢购结束时间转换为long类型
//                boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.START_TIME,starDate));//添加抢购商品开始时间
//                boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.END_TIME,endDate));//添加抢购商品结束时间
                boolQueryBuilder.must(QueryBuilders.rangeQuery(CommodityKey.END_TIME).gte(starDate).lte(endDate));
                boolQueryBuilder.should(QueryBuilders.rangeQuery(CommodityKey.START_TIME).gte(endDate).lte(starDate));

            } catch (ParseException e) {
                logger.error("时间转换出现问题",e);
            }
            SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)//查询es结束
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setTypes(CommodityKey.TYPES_PROMOTIONITEM)
                    .setQuery(boolQueryBuilder)
                    .get();
            List<CommodityTemplate> lists=new ArrayList<>();//创建展示给前台商品模板集合
            response.getHits().forEach(hit -> {//循环出es promotionitem类型的数据
                CommodityTemplate template=new CommodityTemplate();//创建要给前台展示的对象
                try {
                    PromotionitemDTO promotionitemDTO=objectMapper.readValue(hit.getSourceAsString(),PromotionitemDTO.class);
                    template.setInventory(new Long(promotionitemDTO.getCommodityNumber()));//抢购数量
                    template.setCommodityPrice(promotionitemDTO.getDiscountPrice());//抢购价
//                    template.setStartTime(promotionitemDTO.getStartTime());//商品抢购开始时间
//                    template.setEndTime(promotionitemDTO.getEndTime());//商品抢购结束时间
                    CommodityDTO commodityDTO=this.byCommodityIdGetCommodity(new Long(promotionitemDTO.getCommodityId()));//根据id获取商品
                    modelMapper.map(commodityDTO,template);
                } catch (IOException e) {
                    logger.error(hit.getSourceAsString()+"转换成PromotionitemDTO.class失败",e);
                }
                lists.add(template);
            });
            serviceMultiResult.setResult(lists);
            serviceMultiResult.setTotal(response.getHits().totalHits);
            return serviceMultiResult;
        }
        logger.error("parameter cannot null:"+searchConditionPageVO);
        return null;
    }

    /**
     * 根据id获取一条商品信息
     * @param id
     * @return
     */
    public CommodityDTO byCommodityIdGetCommodity(Long id){
        if (id==null){
            logger.warn("parameter is null");
            return null;
        }
        List<CommodityDTO> lists= new LinkedList<>();
        CommodityDTO dto=null;
        if (id==null||id<=0){
            logger.warn("查询了得到了空值,因为parameter不符合查询规范");
            return null;
        }
        GetResponse response=transportClient.prepareGet(CommodityKey.INDEX,CommodityKey.TYPES_COMMODITY,String.valueOf(id)).get();
        try {
            dto=objectMapper.readValue(response.getSourceAsString(),CommodityDTO.class);
        } catch (IOException e) {
            logger.error(response.getSourceAsString()+"转换成CommodityDTO.class失败",e);
        }
        return dto;
    }

    @Override
    public CommodityDetailsDTO byIdSearchCommodity(Long id) {
        CommodityDetailsDTO commodityDetailsDTO=new CommodityDetailsDTO();//前台商品详情对象
        //先根据商品ID获取商品信息,商品大图，商品名字，商品类型
        CommodityDTO commodityDTO=byCommodityIdGetCommodity(id);
        if (commodityDTO==null){
            logger.warn("parameter error\t"+id);
            return null;
        }
        commodityDetailsDTO.setId(String.valueOf(id));//商品id

        commodityDetailsDTO.setCommodityName(commodityDTO.getCommodityName());//商品名称

        //根据商品ID获取商品的轮播图和商品详情图片(根据id获取商品所有图片), 给商品设置轮播图片和介绍图片
        this.byCommodityIdGetAllPicture(id,commodityDetailsDTO);

        //根据商品ID获取商品评价
        commodityDetailsDTO.setCommodityevaluationDTOS(byIdGetAllCommodityevaluation(String.valueOf(id)));

        //获取商品规格中价格最低的和所有规格加起来的库存量，并注册到前台对象中
        CommoditySpecificationInventoryPriceDTO commoditySpecificationInventoryPriceDTO=this.getCommodityPrice(id);

        commodityDetailsDTO.setPrice(commoditySpecificationInventoryPriceDTO.getPrice());//价格

        commodityDetailsDTO.setInventory((int) commoditySpecificationInventoryPriceDTO.getInventory());//总库存量

        //根据商品ID获取商品所有规格，包括价格、库存、商品小图片
        this.byIdGetAllSpecificationCommodity(String.valueOf(id),commodityDetailsDTO,CommodityKey.TYPES_COMMODITY_SPECIFICATION_INVENTORY_PRICE,null,null);

        return commodityDetailsDTO;
    }

    @Override
    public CommodityDetailsDTO byIdSearchRobCommodity(Long id, String startDate, String endDate) {
        CommodityDetailsDTO commodityDetailsDTO=new CommodityDetailsDTO();//前台商品详情对象

        //先根据商品ID获取商品信息,商品大图，商品名字，商品类型
        CommodityDTO commodityDTO=this.byCommodityIdGetCommodity(id);

        commodityDetailsDTO.setCommodityName(commodityDTO.getCommodityName());//商品名称

        //根据商品ID获取商品的轮播图和商品详情图片(根据id获取商品所有图片), 给商品设置轮播图片和介绍图片
        this.byCommodityIdGetAllPicture(id,commodityDetailsDTO);

        //根据商品ID获取商品评价
        commodityDetailsDTO.setCommodityevaluationDTOS(byIdGetAllCommodityevaluation(String.valueOf(id)));

        //获取商品规格中价格最低的和所有规格加起来的库存量，并注册到前台对象中
        CommoditySpecificationInventoryPriceDTO commoditySpecificationInventoryPriceDTO=this.getCommodityPrice(id);

        commodityDetailsDTO.setPrice(commoditySpecificationInventoryPriceDTO.getPrice());//价格

        commodityDetailsDTO.setInventory((int) commoditySpecificationInventoryPriceDTO.getInventory());//总库存量
        long start= 0;
        long end=0;
        try {
            start = format.parse(startDate).getTime();
            end=format.parse(endDate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //根据商品ID获取商品所有规格，包括价格、库存、商品小图片 ,抢购商品时间
        this.byIdGetAllSpecificationCommodity(String.valueOf(id),commodityDetailsDTO,CommodityKey.TYPES_PROMOTIONITEM,start,end);
        //商品时间
        commodityDetailsDTO.setId(String.valueOf(id));//商品id
        return commodityDetailsDTO;
    }

    @Override
    public CommoditySpecificationInventoryPriceDTO byConditionGetCommoditySpecification(CommoditySpecificationInventoryPriceDTO commoditySpecificationInventoryPriceDTO) {
        BoolQueryBuilder boolQueryBuilder=boolQuery();
        if (commoditySpecificationInventoryPriceDTO.getCommodityId()>0){
            boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.COMMODITY_ID,commoditySpecificationInventoryPriceDTO.getCommodityId()));
        }
        if (commoditySpecificationInventoryPriceDTO.getSpecification1()!=null){
            boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.SPECIFICATION1,commoditySpecificationInventoryPriceDTO.getSpecification1()));
        }
        if (commoditySpecificationInventoryPriceDTO.getSpecification2()!=null){
            boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.SPECIFICATION2,commoditySpecificationInventoryPriceDTO.getSpecification2()));
        }
        if (commoditySpecificationInventoryPriceDTO.getSpecification3()!=null){
            boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.SPECIFICATION3,commoditySpecificationInventoryPriceDTO.getSpecification3()));
        }
        if (commoditySpecificationInventoryPriceDTO.getSpecification4()!=null){
            boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.SPECIFICATION4,commoditySpecificationInventoryPriceDTO.getSpecification4()));
        }
        SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(CommodityKey.TYPES_COMMODITY_SPECIFICATION_INVENTORY_PRICE)
                .setQuery(boolQueryBuilder)
                .get();
        List<CommoditySpecificationInventoryPriceDTO> list=new LinkedList<>();
        response.getHits().forEach(hit -> {
            try {
                list.add(objectMapper.readValue(hit.getSourceAsString(),CommoditySpecificationInventoryPriceDTO.class));
            } catch (IOException e) {
                logger.warn(hit.getSourceAsString()+"类型转换有问题",e);
            }
        });
        return list.get(0)!=null ? list.get(0):null;
    }

    @Override
    public List<CommodityevaluationDTO> byIdGetAllCommodityevaluation(String id) {
        if (id!=null){
            List<CommodityevaluationDTO> list=new LinkedList<>();//评论集合
            SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setTypes(CommodityKey.TYPES_COMMODITYEVALUATION)
                    .setQuery(QueryBuilders.termQuery(CommodityKey.COMMODITY_ID,id))
                    .get();
            response.getHits().forEach(hit -> {
                try {
                    CommodityevaluationDTO commodityevaluationDTO=objectMapper.readValue(hit.getSourceAsString(),CommodityevaluationDTO.class);
                    commodityevaluationDTO.setUserName(byUserIdGetUserName(commodityevaluationDTO.getUserId()));
                    list.add(commodityevaluationDTO);
                } catch (IOException e) {
                    logger.warn(hit.getSourceAsString()+"转换类型失败",e);
                }
            });

            return list;
        }
        logger.warn("parameter cannot be null");
        return null;
    }

    @Override
    public CommodityOrderInfo byCommoditySpecificationInventoryPrice(long id, boolean isRob) throws IOException {
        String type=CommodityKey.TYPES_COMMODITY_SPECIFICATION_INVENTORY_PRICE;
        if (isRob){
            type=CommodityKey.TYPES_PROMOTIONITEM;
        }
        GetResponse response=transportClient.prepareGet(CommodityKey.INDEX,type,String.valueOf(id)).get();
        CommodityOrderInfo csdtp=objectMapper.readValue(response.getSourceAsString(),CommodityOrderInfo.class);
        if(csdtp.getId()<=0){
            logger.warn("parameter error "+id);
            return null;
        }
        if (isRob) {
            BoolQueryBuilder boolQueryBuilder = boolQuery();
            boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.COMMODITY_ID, csdtp.getCommodityId()));
            boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.SPECIFICATION1, csdtp.getSpecification1()));
            if (!Strings.isNullOrEmpty(csdtp.getSpecification2())) {
                boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.SPECIFICATION1, csdtp.getSpecification2()));
            }
            if (!Strings.isNullOrEmpty(csdtp.getSpecification3())) {
                boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.SPECIFICATION1, csdtp.getSpecification3()));
            }
            if (!Strings.isNullOrEmpty(csdtp.getSpecification4())) {
                boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.SPECIFICATION1, csdtp.getSpecification4()));
            }
            SearchResponse searchResponse = transportClient.prepareSearch(CommodityKey.INDEX)
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setTypes(CommodityKey.TYPES_COMMODITY_SPECIFICATION_INVENTORY_PRICE)
                    .setQuery(boolQueryBuilder)
                    .get();
            searchResponse.getHits().forEach(hit -> {
                try {
                    CommoditySpecificationInventoryPriceDTO commoditySpecificationInventoryPriceDTO = objectMapper.readValue(hit.getSourceAsString(), CommoditySpecificationInventoryPriceDTO.class);
                    csdtp.setPicture(commoditySpecificationInventoryPriceDTO.getPicture());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        CommodityDTO commodityDTO=this.byCommodityIdGetCommodity(id);
        csdtp.setCommodityName(commodityDTO.getCommodityName());
        return csdtp;
    }
//    @Autowired
//    private KafkaTemplate kafkaTemplate;
//    @Override
//    public void sendKafkaData() {
//        kafkaTemplate.send("commodity","{\"id\":\"1\",\"objects\":\"{\\\"bigPictureUrl\\\":\\\"图片\\\",\\\"commodityName\\\":\\\"布鲁杰克黑啤酒\\\",\\\"id\\\":1,\\\"typeName\\\":\\\"哈哈\\\"}\",\"operation\":\"update\",\"retry\":1}");
//    }

    public static void main(String[] args) {
        CommodityDTO commodityDTO=new CommodityDTO(1,"布鲁杰克黑啤酒","图片","哈哈");
        IndexMessageVO indexMessageVO=new IndexMessageVO("update",1,"{\"bigPictureUrl\":\"图片\",\"commodityName\":\"布鲁杰克黑啤酒\",\"id\":1,\"typeName\":\"哈哈\"}","1");
        System.out.println(JSON.toJSONString(indexMessageVO));
    }
    /**
     * 获取商品规格的父规格
     * @param commoditySpecification 根据二级规格获取一级规格
     * @return 返回二级规格的父规格
     */
    private CommoditySpecificationRelationDTO getCommoditySpecificationRelatoin(String commoditySpecification){
        if (commoditySpecification == null){
            logger.warn("parameter is null \t" +commoditySpecification);
        }
        int [] number=new int[1];
        SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(CommodityKey.TYPES_COMMODITY_SPECIFICATION_RELATION)//这里动态type
                .setQuery(QueryBuilders.termQuery(CommodityKey.SPECIFICATION_NAME,commoditySpecification))
                .get();
        response.getHits().forEach(hit -> {
            try {
                number[0]= objectMapper.readValue(hit.getSourceAsString(),CommoditySpecificationRelationDTO.class).getParentId();
            } catch (IOException e) {
                logger.error(hit.getSourceAsString()+"转换CommoditySpecificationRelationDTO.class错误",e);
            }
        });
        //记录日志，判断是否能查询到
        GetResponse response1=transportClient.prepareGet(CommodityKey.INDEX,CommodityKey.TYPES_COMMODITY_SPECIFICATION_RELATION,String.valueOf(number[0])).get();
        try {
            return objectMapper.readValue(response1.getSourceAsString(),CommoditySpecificationRelationDTO.class);
        } catch (IOException e) {
            logger.error(response1.getSourceAsString()+"转换CommoditySpecificationRelationDTO.class错误",e);
            return null;
        }
    }
    /**
     * 查询commodity_specification_inventory_price
     * 根据商品id获取商品所有规格的商品
     * @param commodityId
     * @return
     */
    private void byIdGetAllSpecificationCommodity(String commodityId,CommodityDetailsDTO commodityDetailsDTO,String esType,Long start,Long end){
        if (commodityId == null){
            logger.warn("parameter is null \t" +commodityId);
        }
        BoolQueryBuilder boolQueryBuilder=boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery(CommodityKey.COMMODITY_ID,commodityId));

        if (start!=null&&start>0&&end!=null&&end>0){
            boolQueryBuilder.must(QueryBuilders.rangeQuery(CommodityKey.END_TIME).gte(start).lte(end));
            boolQueryBuilder.should(QueryBuilders.rangeQuery(CommodityKey.START_TIME).gte(end).lte(start));
        }

        SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(esType)//这里需要动态type
                .setQuery(boolQueryBuilder)
                .get();
        if(response.getHits().getTotalHits()<=0){
            logger.warn("没有获得数据\tparameter \t"+commodityId);
        }
        List<CommoditySpecificationDTO> lists=new ArrayList<>();
        response.getHits().forEach(hit -> {
            try {
                lists.add(objectMapper.readValue(hit.getSourceAsString(),CommoditySpecificationDTO.class));
            } catch (IOException e) {
                logger.error(hit.getSourceAsString()+"转换CommoditySpecificationInventoryPriceDTO.class错误",e);
            }
        });//这里一下的都是刚刚整合过来

        // 拿出当前一条商品规格数据去得到标题，之后把商品标题
        //循环所有商品规格
        Set<CommodityTypeSecondName> set1=new HashSet<>();
        Set<CommodityTypeSecondName> set2=new HashSet<>();
        Set<CommodityTypeSecondName> set3=new HashSet<>();
        Set<CommodityTypeSecondName> set4=new HashSet<>();
        lists.stream().forEach(dto->{
            if (dto.getSpecification1()!=null&&!dto.getSpecification1().isEmpty()){
                set1.add(new CommodityTypeSecondName(dto.getSpecification1()));
                if (dto.getSpecification2()!=null&&!dto.getSpecification2().isEmpty()){
                    set2.add(new CommodityTypeSecondName(dto.getSpecification2()));
                    if (dto.getSpecification3()!=null&&!dto.getSpecification3().isEmpty()){
                        set3.add(new CommodityTypeSecondName(dto.getSpecification3()));
                        if (dto.getSpecification4()!=null&&!dto.getSpecification4().isEmpty()){
                            set4.add(new CommodityTypeSecondName(dto.getSpecification4()));
                        }
                    }
                }
            }
        });
        //拿出当前id的第一个对象
        CommoditySpecificationDTO commoditySpecificationDTO= lists.get(0);

        //商品规格集合对象
        CommoditySpecificationTypeDTO commoditySpecificationTypeDTO1=new CommoditySpecificationTypeDTO();
        CommoditySpecificationTypeDTO commoditySpecificationTypeDTO2=new CommoditySpecificationTypeDTO();
        CommoditySpecificationTypeDTO commoditySpecificationTypeDTO3=new CommoditySpecificationTypeDTO();
        CommoditySpecificationTypeDTO commoditySpecificationTypeDTO4=new CommoditySpecificationTypeDTO();
        if (commoditySpecificationDTO.getSpecification1()!=null&&!commoditySpecificationDTO.getSpecification1().isEmpty()){
            commoditySpecificationTypeDTO1.setTypeDetailed(set1);
            commoditySpecificationTypeDTO1.setTypeName(this.getCommoditySpecificationRelatoin(commoditySpecificationDTO.getSpecification1()).getSpecificationName());
        }
        if (commoditySpecificationDTO.getSpecification2()!=null&&!commoditySpecificationDTO.getSpecification2().isEmpty()){
            commoditySpecificationTypeDTO2.setTypeDetailed(set2);
            commoditySpecificationTypeDTO2.setTypeName(this.getCommoditySpecificationRelatoin(commoditySpecificationDTO.getSpecification2()).getSpecificationName());
        }
        if (commoditySpecificationDTO.getSpecification3()!=null&&!commoditySpecificationDTO.getSpecification3().isEmpty()){
            commoditySpecificationTypeDTO3.setTypeDetailed(set3);
            commoditySpecificationTypeDTO3.setTypeName(this.getCommoditySpecificationRelatoin(commoditySpecificationDTO.getSpecification3()).getSpecificationName());
        }
        if (commoditySpecificationDTO.getSpecification4()!=null&&!commoditySpecificationDTO.getSpecification4().isEmpty()){
            commoditySpecificationTypeDTO4.setTypeDetailed(set4);
            commoditySpecificationTypeDTO4.setTypeName(this.getCommoditySpecificationRelatoin(commoditySpecificationDTO.getSpecification4()).getSpecificationName());
        }
        commoditySpecificationDTO.setCommodityId(Long.parseLong(commodityId));
        commoditySpecificationDTO.setCommoditySpecificationType1(commoditySpecificationTypeDTO1);
        commoditySpecificationDTO.setCommoditySpecificationType2(commoditySpecificationTypeDTO2);
        commoditySpecificationDTO.setCommoditySpecificationType3(commoditySpecificationTypeDTO3);
        commoditySpecificationDTO.setCommoditySpecificationType4(commoditySpecificationTypeDTO4);
        if (commoditySpecificationDTO.getStartTime()>0&&commoditySpecificationDTO.getEndTime()>0){
            commoditySpecificationDTO.setStartDate(new Date(commoditySpecificationDTO.getStartTime()));
            commoditySpecificationDTO.setEndDate(new Date(commoditySpecificationDTO.getEndTime()));
        }
        commodityDetailsDTO.setCommoditySpecificationDTO(commoditySpecificationDTO);
    }
    /**
     * 查询commodity_specification_inventory_price
     * 根据商品id获取商品所有商品规格
     * @param id
     * @return
     */
    private List<CommoditySpecificationInventoryPriceDTO> byCommodityIdGetAllSpecificationDatails(Long id){
        List<CommoditySpecificationInventoryPriceDTO> commoditySpecificationInventoryPriceDTOS=new ArrayList<>();
        if (id==null||id==0){
            logger.warn(id+"parameter cannot is null or cannot is 0");
        }
        SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(CommodityKey.TYPES_COMMODITY_SPECIFICATION_INVENTORY_PRICE)
                .setQuery(QueryBuilders.termQuery(CommodityKey.COMMODITY_ID,id))
                .get();
        response.getHits().forEach(hit -> {
            try {
                commoditySpecificationInventoryPriceDTOS.add(objectMapper.readValue(hit.getSourceAsString(),CommoditySpecificationInventoryPriceDTO.class));
            } catch (IOException e) {
                logger.warn(hit.getSourceAsString()+"转换CommoditySpecificationInventoryPriceDTO.class失败",e);
            }
        });
        return commoditySpecificationInventoryPriceDTOS;
    }
    /**
     *  根据商品id获取所有商品图片 操作的是commodityintroducepicture
     * @param id
     * @param commodityDetailsDTO
     */
    private void byCommodityIdGetAllPicture(Long id,CommodityDetailsDTO commodityDetailsDTO){
        List<CommodityintroducepictureDTO> commodityintroducepictureDTOList=new ArrayList<>();
        if (id==null||id==0){
            logger.warn(id+"parameter cannot is null or cannot is 0");
        }
        SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(CommodityKey.TYPES_COMMODITYINTRODUCEPICTURE)
                .setQuery(QueryBuilders.termQuery(CommodityKey.COMMODITY_ID,id))
                .get();
        response.getHits().forEach(hit -> {
            try {
                commodityintroducepictureDTOList.add(objectMapper.readValue(hit.getSourceAsString(),CommodityintroducepictureDTO.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        List<String> headPictures=new ArrayList<>();//商品轮播图片
        List<String> detailsPicture=new ArrayList<>();//商品详情图片
        commodityintroducepictureDTOList.forEach(commodityintroducepictureDTO -> {
            if (commodityintroducepictureDTO.getLevels()==0){
                headPictures.add(commodityintroducepictureDTO.getPictureUrl());
            }else {
                detailsPicture.add(commodityintroducepictureDTO.getPictureUrl());
            }
        });

        commodityDetailsDTO.setHeadPictures(headPictures);//商品头部图片

        commodityDetailsDTO.setDetailsPictures(detailsPicture);//商品详细图片
    }
    /**
     * 根据商品id获取价钱最低的商品
     * @param id 商品id
     */
    private CommoditySpecificationInventoryPriceDTO getCommodityPrice(Long id){
        CommoditySpecificationInventoryPriceDTO commoditySpecificationInventoryPriceDTO=null;
        int sum=0;
        SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                .setTypes(CommodityKey.TYPES_COMMODITY_SPECIFICATION_INVENTORY_PRICE)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery(CommodityKey.COMMODITY_ID,id))
                .addAggregation(AggregationBuilders.min("min"+CommodityKey.PRICE).field(CommodityKey.PRICE))
                .get();
        Min min=response.getAggregations().get("min"+CommodityKey.PRICE);
        for (SearchHit hit : response.getHits()) {
            try {
                CommoditySpecificationInventoryPriceDTO commoditySpecificationInventoryPriceDTO1=objectMapper.readValue(hit.getSourceAsString(),CommoditySpecificationInventoryPriceDTO.class);
                Double price=commoditySpecificationInventoryPriceDTO1.getPrice();
                sum+=commoditySpecificationInventoryPriceDTO1.getInventory();//所有规格的库存相加
                if (price!=null&&price==min.getValue()){
                    commoditySpecificationInventoryPriceDTO=commoditySpecificationInventoryPriceDTO1;
                    commoditySpecificationInventoryPriceDTO.setInventory(sum);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return  null;
            }
        }

        return  commoditySpecificationInventoryPriceDTO;
    }

    /**
     * 获取所有二级商品类型
     * @return
     */
    private List<TypeDTO> getAllSecondLevelType(){
        List<TypeDTO> list=new ArrayList<>();
        SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(CommodityKey.TYPES_TYPE)
                .setQuery(QueryBuilders.termQuery(CommodityKey.LEVELS,2))
                .get();
        response.getHits().forEach(hit -> {
            try {
                list.add(objectMapper.readValue(hit.getSourceAsString(),TypeDTO.class));
            } catch (IOException e) {
                logger.error(hit.getSourceAsString()+"转换失败",e);
            }
        });
        return list;
    }

    /**
     * 根据用户的id获取用户名字
     * @param id
     * @return 返回用户名字
     */
    private String byUserIdGetUserName(long id){
        List<User> users=new LinkedList<>();
        SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(CommodityKey.TYPES_USER)
                .setQuery(QueryBuilders.termQuery(CommodityKey.ID,id))
                .get();
        response.getHits().forEach(hit -> {
            try {
                users.add(objectMapper.readValue(hit.getSourceAsString(),User.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        if(users.size()>0){
            return users.get(0).getNickName();
        }
        logger.warn("parameter is "+id+"\n result error");
        return "";
    }
}
