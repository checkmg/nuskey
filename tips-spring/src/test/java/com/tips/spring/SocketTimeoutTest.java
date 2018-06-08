package com.tips.spring;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.tips.spring.beans.ServiceBeans;
import com.tips.spring.config.RestServiceConfig;
import com.tips.spring.config.TestPropertiesConfig;
import com.tips.spring.service.ExternalService;
import com.tips.spring.service.impl.ExternalServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		TestPropertiesConfig.class,
		RestServiceConfig.class,
		ServiceBeans.class
})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SocketTimeoutTest {

	@Rule 
	public TestName name = new TestName();
	
	private ExternalServiceImpl externalService;
    private static ServerSocket serverSocket;
    private static int serverPort;
    
	@Autowired	
	RestServiceConfig restServiceConfig;
	
	@Autowired
	@Qualifier("externalSvcTemplate")
	RestTemplate  externalSvcTemplate;
	
	@BeforeClass
	public static void beforeClass() throws IOException {
		serverSocket = new ServerSocket(0, 1);
		serverPort = serverSocket.getLocalPort();
		new Socket().connect(serverSocket.getLocalSocketAddress());		
		System.setProperty("CONF_EXT_URL", "http://localhost:"+serverPort+"/api/tips");		
	}
	
	@AfterClass
	public static void afterClass() throws IOException {
		if(serverSocket!=null && !serverSocket.isClosed())
		    serverSocket.close();		
	}
	
	@Before
	public void setup() {
		externalService = new ExternalServiceImpl();
		externalService.setRestServiceConfig(restServiceConfig);
		externalService.setExternalSvcTemplate(externalSvcTemplate);
	}
	
	@After
	public void after() {
		if(name.getMethodName().equals("aSocketTimeoutTest")) {
			System.setProperty("CONF_CONNECTION_TIMEOUT", "10000");
		}
	}
	
	@Test
	public void aSocketTimeoutTest() {
		long start = System.currentTimeMillis();
		try {
			externalService.connectExternalService();
			Assert.fail();
		} catch(Exception e) {			
		}
		Assert.assertTrue((System.currentTimeMillis()-start) > restServiceConfig.getConnectionTimeout());
	}
	
	@Test
	public void bSocketTimeout10SecTest() {
		long start = System.currentTimeMillis();
		try {
			externalService.connectExternalService();
			Assert.fail();
		} catch(Exception e) {			
			e.printStackTrace();
		}
		Assert.assertTrue((System.currentTimeMillis()-start) > restServiceConfig.getConnectionTimeout());
		
	}
}
