package com.tips.spring.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tips.spring.config.RestServiceConfig;

@Component
public class ServiceBeans {

    @Bean(name = "externalSvcTemplate")
    public RestTemplate restTemplateForZendeskApi(@Qualifier("externalSvcHttpRequestFactory") HttpComponentsClientHttpRequestFactory factory) {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(factory);
        return restTemplate;
    }

    @Bean(name = "externalSvcHttpRequestFactory")
    private HttpComponentsClientHttpRequestFactory httpRequestFactoryForZendeskApi(RestServiceConfig restServiceConfig) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(restServiceConfig.getConnectionTimeout());
        factory.setReadTimeout(restServiceConfig.getReadTimeout());

        return factory;
    }
	
}
