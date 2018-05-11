package com.zhkj.service.backstage.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhkj.dto.seek_dto.CommodityDTO;
import com.zhkj.service.backstage.IBackstageHandleSearch;
import com.zhkj.service.backstage.entity.IndexMessageVO;
import com.zhkj.service.entity.CommodityKey;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class BackstageHandleImp implements IBackstageHandleSearch {
    private static final Logger logger =LoggerFactory.getLogger(BackstageHandleImp.class);
    @Autowired
    private TransportClient client;
    @Autowired
    private ObjectMapper objectMapper;
    @KafkaListener(topics = "zhuohua")
    public void kafkaZhuohua(String conten){
        System.out.println(conten);

    }
    @KafkaListener(topics = IndexMessageVO.TOPIC_COMMODITY)
    public void kafkaCommodity(String content){
        System.out.println(content);
        try {
            IndexMessageVO message=objectMapper.readValue(content,IndexMessageVO.class);
            executeElastiSearch(message,IndexMessageVO.TOPIC_COMMODITY);
        } catch (IOException e) {
            logger.error("Cannot parse json for"+content,e);
        }
    }
    @KafkaListener(topics =IndexMessageVO.TOPIC_COMMODITYTYPERELATION)
    public void kafkaCommoditytyperelation(String content){

    }
    @KafkaListener(topics =IndexMessageVO.TOPIC_DISCOUNT)
    public void kafkaDiscount(String content){

    }
    @KafkaListener(topics =IndexMessageVO.TOPIC_PROMOTIONITEM)
    public void kafkaPromotionitem(String content){}
    @KafkaListener(topics =IndexMessageVO.TOPIC_SPECIFICATIONSDETAILED)
    public void kafkaSpecificationsdetailed(String content){}
    @KafkaListener(topics = "specificationsrelation")
    public void kafkaSpecificationsrelation(String content){}
    @KafkaListener(topics = "specificationstopic")
    public void kafkaSpecificationstopic(String content){}
    @KafkaListener(topics = IndexMessageVO.TOPIC_TYPE)
    public void kafkaType(String content){}

    /**
     * 执行修改elasticsearch数据
     * @param indexMessageVO
     */
    public void executeElastiSearch(IndexMessageVO indexMessageVO,String type){
        switch (indexMessageVO.getOperation()){
            case IndexMessageVO.SAVE :
                this.addSearch(CommodityKey.INDEX,type,indexMessageVO.getId(),indexMessageVO.getObjects());
                break;
            case IndexMessageVO.DEL :
                this.deleteSeach(CommodityKey.INDEX,type,indexMessageVO.getId(),indexMessageVO.getObjects());
                break;
            case IndexMessageVO.UPDATE :
                this.updateSearch(CommodityKey.INDEX,type,indexMessageVO.getId(),indexMessageVO.getObjects());
                break;
            default:
                logger.warn("Not support content" + indexMessageVO);
                break;
        }
    }
    @Override
    public void updateSearch(String index, String type, String id, String object) {
            UpdateResponse response=client.prepareUpdate(index,type,id)
                    .setDoc(object,XContentType.JSON)
                    .get();
            if(response.status()== RestStatus.OK){
                System.out.println("更新成功！");
            }
            UpdateRequest updateRequest=new UpdateRequest();
            updateRequest.index();
            updateRequest.doc();
    }

    @Override
    public void addSearch(String index, String type, String id, String object) {
            IndexResponse response=this.client.prepareIndex(index,type,id)
                    .setSource(object,XContentType.JSON)
                    .get();
            RestStatus restStatus=response.status();
            System.out.println(restStatus);

    }

    @Override
    public void deleteSeach(String index,String type, String id, String object) {
        DeleteResponse response=this.client.prepareDelete(index,type,id)
                .get();
        RestStatus restStatus= response.status();
        System.out.println(restStatus);
    }
}
