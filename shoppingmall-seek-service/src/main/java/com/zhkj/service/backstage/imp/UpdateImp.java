package com.zhkj.service.backstage.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhkj.service.backstage.IUpdateSearch;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateImp implements IUpdateSearch {
    @Autowired
    private TransportClient client;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public Boolean updateSearch(String index, String type, String id, Object object) {
        try {
            UpdateResponse response=client.prepareUpdate(index,type,id).setDoc(objectMapper.writeValueAsBytes(object),XContentType.JSON).get();
            if(response.status()== RestStatus.OK){
                return true;
            }
            return false;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
