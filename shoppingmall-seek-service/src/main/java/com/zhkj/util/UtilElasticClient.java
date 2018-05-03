package com.zhkj.util;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class UtilElasticClient {
    @Bean
    public TransportClient getTransportClient() throws UnknownHostException {
        InetSocketTransportAddress node=new InetSocketTransportAddress(InetAddress.getByName("47.95.240.252"),9300);
        Settings settings=Settings.builder().put("cluster.name","my_es").build();
        TransportClient transportClient=new PreBuiltTransportClient(settings);
        transportClient.addTransportAddress(node);
        return transportClient;
    }

}
