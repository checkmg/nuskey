package com.tips.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestServiceConfig {

	@Value("CONF_EXT_URL")
	private String externalUrl;
	
	@Value("CONF_READ_TIMEOUT")
	private int readTimeout;
	
	@Value("CONF_CONNECTION_TIMEOUT")
	private int connectionTimeout;
	
	@Value("CONF_MAX_RETRY")
	private int maxRetry;

	public String getExternalUrl() {
		return externalUrl;
	}

	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getMaxRetry() {
		return maxRetry;
	}

	public void setMaxRetry(int maxRetry) {
		this.maxRetry = maxRetry;
	}
	
	
}
