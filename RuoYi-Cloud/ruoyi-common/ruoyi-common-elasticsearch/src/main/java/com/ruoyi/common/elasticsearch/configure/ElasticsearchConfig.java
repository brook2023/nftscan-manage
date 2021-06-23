package com.ruoyi.common.elasticsearch.configure;


import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @author king
 * @description TODO
 * @date 2021/5/26
 **/
@Configuration
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {

    @Value("${spring.elasticsearch.rest.uris}")
    private String hostAndPort;
    @Value("${spring.elasticsearch.rest.username}")
    private String user;
    @Value("${spring.elasticsearch.rest.password}")
    private String password;


    /**-
     * 重写父类方法
     * @return
     */
    @Override
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(hostAndPort)
                .withBasicAuth(user, password)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
