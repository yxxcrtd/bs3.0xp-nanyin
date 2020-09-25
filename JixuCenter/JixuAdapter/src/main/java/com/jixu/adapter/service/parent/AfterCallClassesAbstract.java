package com.jixu.adapter.service.parent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jixu.adapter.types.CallClassParamsDto;
import com.jixu.adapter.common.util.JixuRestTemplate;

/**
 * 
 * @author 桃汰猪
 *
 */
@SuppressWarnings("rawtypes")
public abstract class AfterCallClassesAbstract  implements Runnable {
	
	@Autowired(required = false)
    @Qualifier("loadBalancedRestTemplate")
	protected JixuRestTemplate restTemplate;
	
	@Autowired(required = false)
    @Qualifier("simpleRestTemplate")
	protected JixuRestTemplate simpleRestTemplate;
	
	private CallClassParamsDto dto;
	
	public CallClassParamsDto getDto() {
		return dto;
	}

	public void setDto(CallClassParamsDto dto) {
		this.dto = dto;
	}

	/**
	 * 该方法在适配器运行成功后会被调用
	 * @param params	适配器传到下游的参数
	 */
	public abstract void executeAfterProcess(CallClassParamsDto dto);
	
	/**
	 * @return 下游调用方法的名称，用于反射显示在适配器界面供调用选择
	 */
	public abstract String callClassName();
	
	@Override
	public void run() {
		this.executeAfterProcess(dto);
	}
}
