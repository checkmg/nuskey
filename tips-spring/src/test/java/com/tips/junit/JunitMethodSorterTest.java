package com.tips.junit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JunitMethodSorterTest {

	static StringBuffer order = new StringBuffer();
	
	@Rule 
	public TestName name = new TestName();
	
	@AfterClass
	public static void verifyOrder() {
		Assert.assertTrue("aTestbTest".equals(order.toString()));
	}
	
	@Test
	public void aTest() {
		order.append(name.getMethodName());
	}
	
	@Test
	public void bTest() {
		order.append(name.getMethodName());
	}

}
