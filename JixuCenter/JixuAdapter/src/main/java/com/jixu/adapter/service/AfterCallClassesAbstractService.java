package com.jixu.adapter.service;

import org.springframework.stereotype.Service;

import com.jixu.adapter.service.parent.AfterCallClassesAbstract;
import com.jixu.adapter.types.CallClassParamsDto;
import com.jixu.common.util.ServiceApplication;
/**
 * 
 * @author wangqidong
 * 适配器调用下游
 *
 */
@Service
public class AfterCallClassesAbstractService extends AfterCallClassesAbstract {

    
    
	/**
	 * 该方法在适配器运行成功后会被调用
	 * @param dto	适配器传到下游的参数
	 */	
	@Override
	public void executeAfterProcess(CallClassParamsDto dto) {
	    restTemplate.postForObject(
          ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/SaleToAdapter/executeAfterProcess", dto,
          String.class);
		
	}

	@Override
	public String callClassName() {
		return "调用销售接口方法";
	}

}
