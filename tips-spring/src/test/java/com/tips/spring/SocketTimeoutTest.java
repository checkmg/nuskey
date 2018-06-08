package com.tips.spring;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tips.spring.beans.ServiceBeans;
import com.tips.spring.config.RestServiceConfig;
import com.tips.spring.config.TestPropertiesConfig;

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
	
    private static ServerSocket serverSocket;
    private static int serverPort;
	
	@BeforeClass
	public static void beforeClass() throws IOException {
		serverSocket = new ServerSocket(0, 1);
		serverPort = serverSocket.getLocalPort();
		new Socket().connect(serverSocket.getLocalSocketAddress());		
		System.setProperty("CONF_EXT_URL", "http://localhost:"+serverPort+"/api/tips");
		
	}
	
	@Test
	public void aTest() {
		
	}
	
	@Test
	public void bTest() {
		
	}
}
