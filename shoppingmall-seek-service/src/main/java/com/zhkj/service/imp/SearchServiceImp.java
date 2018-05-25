package com.zhkj.service.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhkj.dto.seek_dto.*;
import com.zhkj.service.ISearchService;
import com.zhkj.service.entity.CommodityDetailsDTO;
import com.zhkj.service.entity.CommodityKey;
import com.zhkj.service.entity.CommodityTemplate;
import com.zhkj.service.entity.SearchConditionPageVO;
import com.zhkj.util.ServiceMultiResult;
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
     *
     * @param condition 条件为null就查询所有commodity
     * @return
     */
    @Override
    public ServiceMultiResult<CommodityTemplate> byConditionSearchAllCommodity(SearchConditionPageVO condition) {
        ServiceMultiResult<CommodityTemplate> serviceMultiResult =new ServiceMultiResult<>();//创建返回给前台对象
        BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();//创建查询
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
        BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();
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
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//转换时间对象
        BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();//bool查询
        ServiceMultiResult<CommodityTemplate> serviceMultiResult=new ServiceMultiResult<>();//返回给前台的对象
        if (searchConditionPageVO.getStartDate()!=null&&searchConditionPageVO.getEndDate()!=null){//抢购开始时间不为空
            Long starDate=null;
            Long endDate=null;
            try {
                starDate=format.parse(searchConditionPageVO.getStartDate()).getTime();//抢购开始时间转换为long类型
                endDate=format.parse(searchConditionPageVO.getEndDate()).getTime();//抢购结束时间转换为long类型
                boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.START_TIME,starDate));//添加抢购商品开始时间
                boolQueryBuilder.filter(QueryBuilders.termQuery(CommodityKey.END_TIME,endDate));//添加抢购商品结束时间
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
                    template.setStartTime(new Date(promotionitemDTO.getStartTime()));//商品抢购开始时间
                    template.setEndTime(new Date(promotionitemDTO.getEndTime()));//商品抢购结束时间
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
        List<String> headPictures=new ArrayList<>();//商品头部展示图片
        List<String> detailsPicture=new ArrayList<>();//商品详细图片
        Map<String,Set<String>> map=new HashMap<>();//商品规格规格内容
        CommodityDetailsDTO commodityDetailsDTO=new CommodityDetailsDTO();
        //根据id获取商品信息,查询有没有商品
        CommodityDTO commodityDTO=byCommodityIdGetCommodity(id);
        if (commodityDTO==null){
            logger.warn("parameter error\t"+id);
            return null;
        }
        //根据id获取商品所有图片
        List<CommodityintroducepictureDTO> commodityintroducepictureDTOS=this.byCommodityIdGetAllPicture(id);
        commodityintroducepictureDTOS.forEach(commodityintroducepictureDTO -> {
            if (commodityintroducepictureDTO.getLevels()==0){
                headPictures.add(commodityintroducepictureDTO.getPictureUrl());
            }else {
                detailsPicture.add(commodityintroducepictureDTO.getPictureUrl());
            }
        });
        //获取商品规格中价格最低的和所有规格加起来的库存量
        CommoditySpecificationInventoryPriceDTO priceDTO=this.getCommodityPrice(id);
        //获取本id 所有规格的商品 包括价格、库存、商品小图片
        List<CommoditySpecificationInventoryPriceDTO> commoditySpecificationInventoryPriceDTOList=byIdGetAllSpecificationCommodity(String.valueOf(id));
        //循环所有商品规格
        Set<String> set1=new HashSet<>();
        Set<String> set2=new HashSet<>();
        Set<String> set3=new HashSet<>();
        Set<String> set4=new HashSet<>();
        commoditySpecificationInventoryPriceDTOList.forEach(dto->{
            if (dto.getSpecification1()!=null&&!dto.getSpecification1().isEmpty()){
                set1.add(dto.getSpecification1());
                if (dto.getSpecification2()!=null&&!dto.getSpecification2().isEmpty()){
                    set2.add(dto.getSpecification2());
                    if (dto.getSpecification3()!=null&&!dto.getSpecification3().isEmpty()){
                        set3.add(dto.getSpecification3());
                        if (dto.getSpecification4()!=null&&!dto.getSpecification4().isEmpty()){
                            set4.add(dto.getSpecification4());
                        }
                    }
                }
            }
        });
        //拿出第一个商品
        CommoditySpecificationInventoryPriceDTO commoditySpecificationInventoryPriceDTO= commoditySpecificationInventoryPriceDTOList.get(0);
        //商品所有规格
        List<CommoditySpecificationRelationDTO> commoditySpecificationRelationDTOList=new LinkedList<>();
        if (commoditySpecificationInventoryPriceDTO.getSpecification1()!=null&&!commoditySpecificationInventoryPriceDTO.getSpecification1().isEmpty()){
            //给商品规格标题赋值
            map.put(getCommoditySpecificationRelatoin(commoditySpecificationInventoryPriceDTO.getSpecification1()).getSpecificationName(),set1);
        }
        if (commoditySpecificationInventoryPriceDTO.getSpecification2()!=null&&!commoditySpecificationInventoryPriceDTO.getSpecification2().isEmpty()){
            map.put(getCommoditySpecificationRelatoin(commoditySpecificationInventoryPriceDTO.getSpecification2()).getSpecificationName(),set2);
        }
        if (commoditySpecificationInventoryPriceDTO.getSpecification3()!=null&&!commoditySpecificationInventoryPriceDTO.getSpecification3().isEmpty()){
            map.put(getCommoditySpecificationRelatoin(commoditySpecificationInventoryPriceDTO.getSpecification3()).getSpecificationName(),set3);
        }
        if (commoditySpecificationInventoryPriceDTO.getSpecification4()!=null&&!commoditySpecificationInventoryPriceDTO.getSpecification4().isEmpty()){
            map.put(getCommoditySpecificationRelatoin(commoditySpecificationInventoryPriceDTO.getSpecification4()).getSpecificationName(),set4);
        }
        //开始给前台对象赋值
        commodityDetailsDTO.setId(String.valueOf(id));
        commodityDetailsDTO.setCommodityName(commodityDTO.getCommodityName());//商品名称
        commodityDetailsDTO.setPrice(priceDTO.getPrice());//价格
        commodityDetailsDTO.setHeadPictures(headPictures);//商品头部图片
        commodityDetailsDTO.setDetailsPictures(detailsPicture);//商品详细图片
        commodityDetailsDTO.setInventory(priceDTO.getInventory());//总库存量
        commodityDetailsDTO.setCommoditySpecificationParcular(map);//商品规格
        return commodityDetailsDTO;
    }

    @Override
    public CommoditySpecificationInventoryPriceDTO byConditionGetCommoditySpecification(CommoditySpecificationInventoryPriceDTO commoditySpecificationInventoryPriceDTO) {
        BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();
        if (commoditySpecificationInventoryPriceDTO.getId()<=0){
            logger.warn("parameter id must gt 0");
            return null;
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
            List<CommodityevaluationDTO> list=new LinkedList<>();
            SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setTypes(CommodityKey.TYPES_COMMODITYEVALUATION)
                    .setQuery(QueryBuilders.termQuery(CommodityKey.COMMODITY_ID,id))
                    .get();
            response.getHits().forEach(hit -> {
                try {
                    list.add(objectMapper.readValue(hit.getSourceAsString(),CommodityevaluationDTO.class));
                } catch (IOException e) {
                    logger.warn(hit.getSourceAsString()+"转换类型失败",e);
                }
            });
            return list;
        }
        logger.warn("parameter cannot be null");
        return null;
    }

    /**
     * 获取商品规格的父规格
     * @param commoditySpecification 根据二级规格获取一级规格
     * @return 返回二级规格的父规格
     */
    public CommoditySpecificationRelationDTO getCommoditySpecificationRelatoin(String commoditySpecification){
        if (commoditySpecification == null){
            logger.warn("parameter is null \t" +commoditySpecification);
        }
        int [] number=new int[1];
        SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(CommodityKey.TYPES_COMMODITY_SPECIFICATION_RELATION)
                .setQuery(QueryBuilders.termQuery(CommodityKey.SPECIFICATION_NAME,commoditySpecification))
                .get();
        response.getHits().forEach(hit -> {
            try {
                number[0]= objectMapper.readValue(hit.getSourceAsString(),CommoditySpecificationRelationDTO.class).getParentId();
            } catch (IOException e) {
                logger.error(hit.getSourceAsString()+"转换CommoditySpecificationRelationDTO.class错误",e);
            }
        });
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
    public List<CommoditySpecificationInventoryPriceDTO> byIdGetAllSpecificationCommodity(String commodityId){
        if (commodityId == null){
            logger.warn("parameter is null \t" +commodityId);
        }
        SearchResponse response=transportClient.prepareSearch(CommodityKey.INDEX)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(CommodityKey.TYPES_COMMODITY_SPECIFICATION_INVENTORY_PRICE)
                .setQuery(QueryBuilders.termQuery(CommodityKey.COMMODITY_ID,commodityId))
                .get();
        if(response.getHits().getTotalHits()<=0){
            logger.warn("没有获得数据\tparameter \t"+commodityId);
            return null;
        }
        List<CommoditySpecificationInventoryPriceDTO> lists=new ArrayList<>();
        response.getHits().forEach(hit -> {
            try {
                lists.add(objectMapper.readValue(hit.getSourceAsString(),CommoditySpecificationInventoryPriceDTO.class));
            } catch (IOException e) {
                logger.error(hit.getSourceAsString()+"转换CommoditySpecificationInventoryPriceDTO.class错误",e);
            }
        });
        return lists;
    }
    /**
     * 查询commodity_specification_inventory_price
     * 根据商品id获取商品所有商品规格
     * @param id
     * @return
     */
    public List<CommoditySpecificationInventoryPriceDTO> byCommodityIdGetAllSpecificationDatails(Long id){
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
     * @return
     */
    public List<CommodityintroducepictureDTO> byCommodityIdGetAllPicture(Long id){
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
        return commodityintroducepictureDTOList;
    }
    /**
     * 根据商品id获取价钱最低的商品
     * @param id 商品id
     * @return 返回商品id的最低价钱
     */
    public CommoditySpecificationInventoryPriceDTO getCommodityPrice(Long id){
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
                return null;
            }
        }
        return commoditySpecificationInventoryPriceDTO;
    }

    /**
     * 获取所有二级商品类型
     * @return
     */
    public List<TypeDTO> getAllSecondLevelType(){
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

}
