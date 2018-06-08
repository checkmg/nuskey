package com.tips.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tips.spring.config.RestServiceConfig;
import com.tips.spring.service.ExternalService;

@Service
public class ExternalServiceImpl implements ExternalService {

	@Autowired
	private RestServiceConfig restServiceConfig;
	
	@Autowired(required=true)
	@Qualifier("externalSvcTemplate")
	private RestTemplate externalSvcTemplate;
	
	
	public RestServiceConfig getRestServiceConfig() {
		return restServiceConfig;
	}


	public void setRestServiceConfig(RestServiceConfig restServiceConfig) {
		this.restServiceConfig = restServiceConfig;
	}


	public RestTemplate getExternalSvcTemplate() {
		return externalSvcTemplate;
	}


	public void setExternalSvcTemplate(RestTemplate externalSvcTemplate) {
		this.externalSvcTemplate = externalSvcTemplate;
	}


	public void connectExternalService() {
		externalSvcTemplate.getForEntity(restServiceConfig.getExternalUrl(), String.class);
	}

}
