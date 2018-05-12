package com.zhkj.service.backstage.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhkj.dto.seek_dto.*;
import com.zhkj.dto.seek_dto.entity.*;
import com.zhkj.service.backstage.IBackstageHandleSearch;
import com.zhkj.service.backstage.entity.IndexMessageVO;
import com.zhkj.service.entity.CommodityKey;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class BackstageHandleImp implements IBackstageHandleSearch {
    private static final Logger logger =LoggerFactory.getLogger(BackstageHandleImp.class);
    @Autowired
    private TransportClient client;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ModelMapper modelMapper;
    //    @KafkaListener(topics = "zhuohua")
//    public void kafkaZhuohua(String conten){
//        System.out.println(conten);
//
//    }
    @KafkaListener(topics = IndexMessageVO.TOPIC_COMMODITY)
    public void kafkaCommodity(String content){
        System.out.println(content);
        try {
            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
            executeElastiSearch(message,IndexMessageVO.TOPIC_COMMODITY,objectMapper.readValue(message.getObjects(),Commodity.class),CommodityDTO.class);
        } catch (IOException e) {
            logger.error("Cannot parse json for"+content,e);
        }
    }
    @KafkaListener(topics =IndexMessageVO.TOPIC_COMMODITYTYPERELATION)
    public void kafkaCommoditytyperelation(String content){
        try {
            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
            executeElastiSearch(message,IndexMessageVO.TOPIC_COMMODITYTYPERELATION,objectMapper.readValue(message.getObjects(),Commoditytyperelation.class),CommoditytyperelationDTO.class);
        } catch (IOException e) {
            logger.error("Cannot parse json for"+content,e);
        }
    }
    @KafkaListener(topics =IndexMessageVO.TOPIC_DISCOUNT)
    public void kafkaDiscount(String content){
        try {
            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
            executeElastiSearch(message,IndexMessageVO.TOPIC_DISCOUNT,objectMapper.readValue(message.getObjects(),Discount.class),DiscountDTO.class);
        } catch (IOException e) {
            logger.error("Cannot parse json for"+content,e);
        }
    }
    @KafkaListener(topics =IndexMessageVO.TOPIC_PROMOTIONITEM)
    public void kafkaPromotionitem(String content){
        try {
            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
            executeElastiSearch(message,IndexMessageVO.TOPIC_PROMOTIONITEM,objectMapper.readValue(message.getObjects(),Promotionitem.class),PromotionitemDTO.class);
        } catch (IOException e) {
            logger.error("Cannot parse json for"+content,e);
        }
    }
    @KafkaListener(topics =IndexMessageVO.TOPIC_SPECIFICATIONSDETAILED)
    public void kafkaSpecificationsdetailed(String content){
        try {
            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
            executeElastiSearch(message,IndexMessageVO.TOPIC_SPECIFICATIONSDETAILED,objectMapper.readValue(message.getObjects(),Specificationsdetailed.class),SpecificationsdetailedDTO.class);
        } catch (IOException e) {
            logger.error("Cannot parse json for"+content,e);
        }
    }
    @KafkaListener(topics = IndexMessageVO.TOPIC_SPECIFICATIONSRELATION)
    public void kafkaSpecificationsrelation(String content){
        try {
            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
            executeElastiSearch(message,IndexMessageVO.TOPIC_SPECIFICATIONSRELATION,objectMapper.readValue(message.getObjects(),Specificationsrelation.class),SpecificationsrelationDTO.class);
        } catch (IOException e) {
            logger.error("Cannot parse json for"+content,e);
        }
    }
    @KafkaListener(topics =IndexMessageVO.TOPIC_SPECIFICATIONSTOPIC)
    public void kafkaSpecificationstopic(String content){
        try {
            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
            executeElastiSearch(message,IndexMessageVO.TOPIC_SPECIFICATIONSTOPIC,objectMapper.readValue(message.getObjects(),Specificationstopic.class),SpecificationstopicDTO.class);
        } catch (IOException e) {
            logger.error("Cannot parse json for"+content,e);
        }
    }
    @KafkaListener(topics = IndexMessageVO.TOPIC_TYPE)
    public void kafkaType(String content){
        try {
            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
            executeElastiSearch(message,IndexMessageVO.TOPIC_TYPE,objectMapper.readValue(message.getObjects(),Type.class),TypeDTO.class);
        } catch (IOException e) {
            logger.error("Cannot parse json for"+content,e);
        }
    }
    /**
     * 执行修改elasticsearch数据
     * @param indexMessageVO
     */
    public <T> void executeElastiSearch(IndexMessageVO indexMessageVO,String type,Object object,Class<T> valueType){
        switch (indexMessageVO.getOperation()){
            case IndexMessageVO.SAVE :
                this.addSearch(CommodityKey.INDEX,type,indexMessageVO.getId(),object,valueType);
                break;
            case IndexMessageVO.DEL :
                this.deleteSeach(CommodityKey.INDEX,type,indexMessageVO.getId(),valueType);
                break;
            case IndexMessageVO.UPDATE :
                this.updateSearch(CommodityKey.INDEX,type,indexMessageVO.getId(),object,valueType);
                break;
            default:
                logger.warn("Not support content" + indexMessageVO);
                break;
        }
    }
    @Override
    public <T>void updateSearch(String index, String type, String id, Object object,Class<T> valueType) {
        try {
            T t= modelMapper.map(object,valueType);
            byte[] source=objectMapper.writeValueAsBytes(t);
            UpdateResponse response=client.prepareUpdate(index,type,id)
                    .setDoc(source,XContentType.JSON)
                    .get();
            if(response.status()== RestStatus.OK){
                System.out.println("更新成功!");
            }else{
                System.out.println("更新失败!");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

//            UpdateRequest updateRequest=new UpdateRequest();
//            updateRequest.index();
//            updateRequest.doc();
    }

    @Override
    public <T> void addSearch(String index, String type, String id, Object object,Class<T> valueType) {
        try {
            T t= modelMapper.map(object,valueType);
            byte[] source=objectMapper.writeValueAsBytes(t);
            IndexResponse response=this.client.prepareIndex(index,type,id)
                    .setSource(source,XContentType.JSON)
                    .get();
            RestStatus restStatus=response.status();
            System.out.println(restStatus);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public <T>void deleteSeach(String index,String type, String id,Class<T> valueType) {
        DeleteResponse response=this.client.prepareDelete(index,type,id)
                .get();
        RestStatus restStatus= response.status();
        System.out.println(restStatus);
    }
}
