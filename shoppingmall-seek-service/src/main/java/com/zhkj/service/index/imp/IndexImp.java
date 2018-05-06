package com.zhkj.service.index.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhkj.service.index.Iindex;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexImp implements Iindex {
    @Autowired
    private TransportClient client;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public Boolean saveIndex(String index, String type,String id, Object object) {
        try {
            IndexResponse response=client.prepareIndex(index,type,id)
                    .setSource(objectMapper.writeValueAsBytes(object),XContentType.JSON)
                    .get();
            if (response.status()== RestStatus.CREATED){
                return true;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
