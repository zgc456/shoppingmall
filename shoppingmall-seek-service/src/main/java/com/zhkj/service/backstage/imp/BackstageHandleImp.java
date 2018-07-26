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
    private static final String KAFKA_TOPIC_COMMODITY_SPECIFICATION_INVENTORY_PRICE="commodityspecificationinventoryprice";
    private static final String KAFKA_TOPIC_COMMODITY_SPECIFICATION_RELATION="commodityspecificationrelation";
    private static final Logger logger =LoggerFactory.getLogger(BackstageHandleImp.class);
    @Autowired
    private TransportClient client;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ModelMapper modelMapper;
    //kafka监听商品
    @KafkaListener(topics = IndexMessageVO.TOPIC_COMMODITY)
    public void kafkaCommodity(String content){
        CommodityDTO commodityDTO=null;
        System.out.println("kafka监听商品");
        System.out.println(content);
        try {
            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
            String object=message.getObjects();
            if (object!=null){
                commodityDTO=objectMapper.readValue(message.getObjects(),CommodityDTO.class);
            }
            executeElastiSearch(message,IndexMessageVO.TOPIC_COMMODITY,commodityDTO);
        } catch (IOException e) {
            logger.error("Cannot parse json for"+content,e);
        }
    }
    //kafka监听商品规格价钱库存
    @KafkaListener(topics =KAFKA_TOPIC_COMMODITY_SPECIFICATION_INVENTORY_PRICE)
    public void kafkaCommoditytyperelation(String content){
        CommoditySpecificationInventoryPriceDTO csip=null;
        System.out.println("kafka监听商品规格价钱库存");
        System.out.println(content);
        try {
            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
            String object=message.getObjects();
            if (object!=null){
                csip=objectMapper.readValue(message.getObjects(),CommoditySpecificationInventoryPriceDTO.class);
            }
            executeElastiSearch(message,IndexMessageVO.TOPIC_COMMODITY_SPECIFICATION_INVENTORY_PRICE,csip);
        } catch (IOException e) {
            logger.error("Cannot parse json for"+content,e);
        }
    }
    //kafka监控商品规格关系
    @KafkaListener(topics =KAFKA_TOPIC_COMMODITY_SPECIFICATION_RELATION)
    public void kafkaDiscount(String content){
        CommoditySpecificationRelationDTO csr=null;
        System.out.println("kafka监控商品规格关系");
        System.out.println(content);
        try {
            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
            String object=message.getObjects();
            if (object!=null){
                csr=objectMapper.readValue(message.getObjects(),CommoditySpecificationRelationDTO.class);
            }
            executeElastiSearch(message,IndexMessageVO.TOPIC_COMMODITY_SPECIFICATION_RELATION,csr);
        } catch (IOException e) {
            logger.error("Cannot parse json for"+content,e);
        }
    }
    //kafka监控抢购
    @KafkaListener(topics =IndexMessageVO.TOPIC_PROMOTIONITEM)
    public void kafkaPromotionitem(String content){
        PromotionitemDTO promotionitemDTO=null;
        System.out.println("kafka监控抢购");
        System.out.println(content);
        try {
            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
            promotionitemDTO=objectMapper.readValue(message.getObjects(),PromotionitemDTO.class);
            executeElastiSearch(message,IndexMessageVO.TOPIC_PROMOTIONITEM,promotionitemDTO);
        } catch (IOException e) {
            logger.error("Cannot parse json for"+content,e);
        }
    }
    //kafka监控商品评论
    @KafkaListener(topics =IndexMessageVO.TOPIC_COMMODITYEVALUATION)
    public void kafkaSpecificationsdetailed(String content){
        CommodityevaluationDTO commodityevaluationDTO=null;
        System.out.println("kafka监控商品评论");
        System.out.println(content);
        try {
            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
            commodityevaluationDTO=objectMapper.readValue(message.getObjects(),CommodityevaluationDTO.class);
            executeElastiSearch(message,IndexMessageVO.TOPIC_COMMODITYEVALUATION,commodityevaluationDTO);
        } catch (IOException e) {
            logger.error("Cannot parse json for"+content,e);
        }
    }
    //kafka监控商品图片
    @KafkaListener(topics = IndexMessageVO.TOPIC_COMMODITYINTRODUCEPICTURE)
    public void kafkaSpecificationsrelation(String content){
        CommodityintroducepictureDTO commodityintroducepictureDTO=null;
        System.out.println("kafka监控商品图片");
        System.out.println(content);
        try {
            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
            commodityintroducepictureDTO=objectMapper.readValue(message.getObjects(),CommodityintroducepictureDTO.class);
            executeElastiSearch(message,IndexMessageVO.TOPIC_COMMODITYINTRODUCEPICTURE,commodityintroducepictureDTO);
        } catch (IOException e) {
            logger.error("Cannot parse json for"+content,e);
        }
    }
    //kafka监控用户
    @KafkaListener(topics =IndexMessageVO.TOPIC_USER)
    public void kafkaSpecificationstopic(String content){
        UserDTO userDTO=null;
        System.out.println("kafka监控用户");
        System.out.println(content);
        try {
            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
            userDTO=objectMapper.readValue(message.getObjects(),UserDTO.class);
            executeElastiSearch(message,IndexMessageVO.TOPIC_USER,userDTO);
        } catch (IOException e) {
            logger.error("Cannot parse json for"+content,e);
        }
    }
    //kafka监控商品类型
    @KafkaListener(topics = IndexMessageVO.TOPIC_TYPE)
    public void kafkaType(String content){
        TypeDTO typeDTO=null;
        System.out.println("kafka监控商品类型");
        System.out.println(content);
        try {
            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
            typeDTO=objectMapper.readValue(message.getObjects(),TypeDTO.class);
            executeElastiSearch(message,IndexMessageVO.TOPIC_TYPE,typeDTO);
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
                String json=objectMapper.writeValueAsString(object);
                System.out.println(json);
                response=client.prepareUpdate(index,type,id)
                        .setDoc(json,XContentType.JSON)
                        .get();
            } catch (JsonProcessingException e) {
                logger.error("object parameter analysis defeated!"+object,e);
            }
        }
        if(response.status()== RestStatus.OK){
            logger.debug("更新成功",object);
        }else{
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
                System.out.println(objectMapper.writeValueAsString(object));
                response=this.client.prepareIndex(index,type,id)
                        .setSource(objectMapper.writeValueAsString(object),XContentType.JSON)
                        .get();
                RestStatus restStatus=response.status();
                logger.debug(restStatus.toString());
            } catch (IOException e) {
                logger.error("object parameter analysis defeated!"+object,e);
            }
        }
        logger.debug(response.status().toString());
    }

    @Override
    public void deleteSeach(String index,String type, String id) {
        DeleteResponse response=this.client.prepareDelete(index,type,id)
                .get();
        RestStatus restStatus= response.status();
        System.out.println(restStatus);
        logger.warn(restStatus.name());
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
                    .field(CommodityKey.END_TIME,promotionitemDTO.getEndTime())
                    .field(CommodityKey.START_TIME,promotionitemDTO.getStartTime())
                    .field(CommodityKey.DISCOUNT_PRICE,promotionitemDTO.getDiscountPrice())
                    .field(CommodityKey.COMMODITY_NUMBER,promotionitemDTO.getCommodityNumber())
                    .field(CommodityKey.COMMODITY_ID,promotionitemDTO.getCommodityId())
                    .field(CommodityKey.SPECIFICATION1,promotionitemDTO.getSpecification1())
                    .field(CommodityKey.SPECIFICATION2,promotionitemDTO.getSpecification2())
                    .field(CommodityKey.SPECIFICATION3,promotionitemDTO.getSpecification3())
                    .field(CommodityKey.SPECIFICATION4,promotionitemDTO.getSpecification4())
                    .endObject();
        } catch (IOException e) {
            logger.error("解析promotionitemDTO错误"+promotionitemDTO,e);
            return null;
        }
    }
}
