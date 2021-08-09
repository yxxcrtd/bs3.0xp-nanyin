package com.jixu.adapter.service.export;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.jixu.adapter.service.parent.AfterCallClassesAbstract;
import com.jixu.adapter.types.CallClassParamsDto;

@Component
public class TestAfterCallClasses extends AfterCallClassesAbstract{

	private static final Logger logger = LoggerFactory.getLogger(TestAfterCallClasses.class);
	/**
	 * 该方法在适配器运行成功后会被调用
	 * @param params	适配器传到下游的参数
	 */	
	@Override
	public void executeAfterProcess(CallClassParamsDto dto) {
//		System.out.println("代码测试实现类");
		logger.info("代码测试实现类");
	}
	
	@Override
	public String callClassName() {
		//返回该类的名称，用于反射显示在适配器界面供调用选择
		return "下游测试类";
	}
}
