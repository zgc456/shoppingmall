package com.zhkj.service.backstage.imp;

import static org.elasticsearch.common.xcontent.XContentFactory.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhkj.dto.seek_dto.*;
import com.zhkj.service.backstage.IBackstageHandleSearch;
import com.zhkj.service.backstage.entity.IndexMessageVO;
import com.zhkj.service.entity.CommodityKey;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
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
    @KafkaListener(topics = IndexMessageVO.TOPIC_COMMODITY)
    public void kafkaCommodity(String content){
        System.out.println(content);
        try {
            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
            executeElastiSearch(message,IndexMessageVO.TOPIC_COMMODITY,objectMapper.readValue(message.getObjects(),CommodityDTO.class));
        } catch (IOException e) {
            logger.error("Cannot parse json for"+content,e);
        }
    }
    @KafkaListener(topics =IndexMessageVO.TOPIC_COMMODITYTYPERELATION)
    public void kafkaCommoditytyperelation(String content){
//        try {
//            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
//            executeElastiSearch(message,IndexMessageVO.TOPIC_COMMODITYTYPERELATION,objectMapper.readValue(message.getObjects(),CommoditytyperelationDTO.class));
//        } catch (IOException e) {
//            logger.error("Cannot parse json for"+content,e);
//        }
    }
    @KafkaListener(topics =IndexMessageVO.TOPIC_DISCOUNT)
    public void kafkaDiscount(String content){
//        try {
//            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
//            executeElastiSearch(message,IndexMessageVO.TOPIC_DISCOUNT,objectMapper.readValue(message.getObjects(),DiscountDTO.class));
//        } catch (IOException e) {
//            logger.error("Cannot parse json for"+content,e);
//        }
    }
    @KafkaListener(topics =IndexMessageVO.TOPIC_PROMOTIONITEM)
    public void kafkaPromotionitem(String content){
        try {
            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
            executeElastiSearch(message,IndexMessageVO.TOPIC_PROMOTIONITEM,objectMapper.readValue(message.getObjects(),PromotionitemDTO.class));
        } catch (IOException e) {
            logger.error("Cannot parse json for"+content,e);
        }
    }
    @KafkaListener(topics =IndexMessageVO.TOPIC_SPECIFICATIONSDETAILED)
    public void kafkaSpecificationsdetailed(String content){
//        try {
//            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
//            executeElastiSearch(message,IndexMessageVO.TOPIC_SPECIFICATIONSDETAILED,objectMapper.readValue(message.getObjects(),SpecificationsdetailedDTO.class));
//        } catch (IOException e) {
//            logger.error("Cannot parse json for"+content,e);
//        }
    }
    @KafkaListener(topics = IndexMessageVO.TOPIC_SPECIFICATIONSRELATION)
    public void kafkaSpecificationsrelation(String content){
//        try {
//            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
//            executeElastiSearch(message,IndexMessageVO.TOPIC_SPECIFICATIONSRELATION,objectMapper.readValue(message.getObjects(),SpecificationsrelationDTO.class));
//        } catch (IOException e) {
//            logger.error("Cannot parse json for"+content,e);
//        }
    }
    @KafkaListener(topics =IndexMessageVO.TOPIC_SPECIFICATIONSTOPIC)
    public void kafkaSpecificationstopic(String content){
//        try {
//            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
//            executeElastiSearch(message,IndexMessageVO.TOPIC_SPECIFICATIONSTOPIC,objectMapper.readValue(message.getObjects(),SpecificationstopicDTO.class));
//        } catch (IOException e) {
//            logger.error("Cannot parse json for"+content,e);
//        }
    }
    @KafkaListener(topics = IndexMessageVO.TOPIC_TYPE)
    public void kafkaType(String content){
        try {
            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
            executeElastiSearch(message,IndexMessageVO.TOPIC_TYPE,objectMapper.readValue(message.getObjects(),TypeDTO.class));
        } catch (IOException e) {
            logger.error("Cannot parse json for"+content,e);
        }
    }

    /**
     *  执行修改elasticsearch数据
     * @param indexMessageVO 消息中间件传输对象
     * @param type 修改那个类型
     * @param object
     */
    public void executeElastiSearch(IndexMessageVO indexMessageVO,String type,Object object){
        switch (indexMessageVO.getOperation()){
            case IndexMessageVO.SAVE :
                this.addSearch(CommodityKey.INDEX,type,indexMessageVO.getId(),object);
                break;
            case IndexMessageVO.DEL :
                this.deleteSeach(CommodityKey.INDEX,type,indexMessageVO.getId());
                break;
            case IndexMessageVO.UPDATE :
                this.updateSearch(CommodityKey.INDEX,type,indexMessageVO.getId(),object);
                break;
            default:
                logger.warn("Not support content" + indexMessageVO);
                break;
        }
    }
    @Override
    public void updateSearch(String index, String type, String id, Object object) {
        UpdateResponse response=null;
        if (object instanceof PromotionitemDTO){
            response=client.prepareUpdate(index,type,id)
                    .setDoc(analysisObject((PromotionitemDTO)object))
                    .get();
        }else {
            try {
                byte[] source=objectMapper.writeValueAsBytes(object);
                response=client.prepareUpdate(index,type,id)
                        .setDoc(source,XContentType.JSON)
                        .get();
            } catch (JsonProcessingException e) {
                logger.error("object parameter analysis defeated!"+object,e);
            }
        }
        if(response.status()== RestStatus.OK){
            System.out.println("更新成功!");
            logger.debug("更新成功",object);
        }else{
            System.out.println("更新失败!");
            logger.debug("更新成功",object);
        }
    }

    @Override
    public void addSearch(String index, String type, String id, Object object) {
        PromotionitemDTO promotionitemDTO;
        IndexResponse response=null;
        if (object instanceof PromotionitemDTO){
            promotionitemDTO=(PromotionitemDTO)object;
            if (promotionitemDTO!=null){
                response=this.client.prepareIndex(index,type,id)
                        .setSource(analysisObject(promotionitemDTO))
                        .get();
            }else {
                logger.warn("promotionitemDTO is null,Not support null");
            }
        }else {
            try {
                byte[] source=objectMapper.writeValueAsBytes(object);
                response=this.client.prepareIndex(index,type,id)
                        .setSource(source,XContentType.JSON)
                        .get();
                RestStatus restStatus=response.status();
                System.out.println(restStatus);
            } catch (IOException e) {
                logger.error("object parameter analysis defeated!"+object,e);
            }
        }
        System.out.println(response.status());
        logger.debug(response.status().toString());
    }

    @Override
    public void deleteSeach(String index,String type, String id) {
        DeleteResponse response=this.client.prepareDelete(index,type,id)
                .get();
        RestStatus restStatus= response.status();
        System.out.println(restStatus);
    }

    /**
     * 把促销类型解析成XContentBuilder类型
     * @param promotionitemDTO
     * @return
     */
    public XContentBuilder analysisObject(PromotionitemDTO promotionitemDTO){
        try {
            return jsonBuilder()
                    .startObject()
                    .field(CommodityKey.ID,promotionitemDTO.getId())
//                    .field(CommodityKey.END_TIME,promotionitemDTO.getEndTime().getTime())
//                    .field(CommodityKey.START_TIME,promotionitemDTO.getStartTime().getTime())
                    .field(CommodityKey.DISCOUNT_PRICE,promotionitemDTO.getDiscountPrice())
                    .field(CommodityKey.COMMODITY_NUMBER,promotionitemDTO.getCommodityNumber())
//                    .field(CommodityKey.COMMODITYID,promotionitemDTO.getCommodityId())
                    .endObject();
        } catch (IOException e) {
            logger.error("解析promotionitemDTO错误"+promotionitemDTO,e);
            return null;
        }
    }
}
