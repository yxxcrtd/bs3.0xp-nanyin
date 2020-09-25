package com.test.jixu;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:spring-mvc.xml" })
public class BaseTest {
	protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

	@Before
	public void before() {
		System.out.println("@Before");
	}

	@After
	public void after() {
		System.out.println("@After");
	}

}
