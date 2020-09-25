package com.test.jixu;

import javax.annotation.Resource;

import org.junit.Test;

import com.jixu.adapter.control.service.ConfigAdptService;

public class TestAdapter extends BaseTest{
	
	@Resource(name="configAdptService")
	private ConfigAdptService configService;
	
}
