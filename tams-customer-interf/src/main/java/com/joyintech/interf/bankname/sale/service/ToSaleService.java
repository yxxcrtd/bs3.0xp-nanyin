package com.joyintech.interf.bankname.sale.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.interf.bankname.config.ServiceInterfApplication;
import com.joyintech.interf.bankname.sale.dto.ResultContainer;

/**
 * 
 * 资管推销售业务处理
 * 〈功能详细描述〉
 * @author GQQ
 * @version 1.0 2019年7月14日
 * @see ToSaleService
 * @since 1.0
 */
@Service
public class ToSaleService {
	
	 @Autowired
    private JoyinRestTemplate restTemplate;
	
	//按日期生成文件定时任务调用
	public Map<String, Object> sendToSaleByDate(int adapterID,String date) {
		//适配器生成文件参数
		HashMap<String, Object> outParams = new HashMap<String, Object>();
		outParams.put("saledate", date);
		outParams.put("adapterID", adapterID);
		
		ResultContainer resultContainer = restTemplate.postForObject(
	            ServiceInterfApplication.ADAPTER_SERVICE_NAME + "/ExecuteJixuAdapter/ExecuteJixuAdapter", outParams,
	            ResultContainer.class);
		//返回状态
		Map<String, Object> res = new HashMap<String, Object>();
		
		res.put("flag", resultContainer.isCorrectFlag());
		res.put("message", resultContainer.getErrorMsg());
		return res;
	}
	
	//按产品生成文件页面调用
	public Map<String, Object> sendToSaleByFinprodId(int adapterID,String finprodId) {
		//适配器生成文件参数
		HashMap<String, Object> outParams = new HashMap<>();
		outParams.put("finprodId", finprodId);
		
		restTemplate.postForObject(
	            ServiceInterfApplication.ADAPTER_SERVICE_NAME + "/ExecuteJixuAdaptere", outParams,
	            String.class);
		//返回状态
		ResultContainer resultContainer = restTemplate.postForObject(
	            ServiceInterfApplication.ADAPTER_SERVICE_NAME + "/ExecuteJixuAdapter/ExecuteJixuAdapter", outParams,
	            ResultContainer.class);
		
		Map<String, Object> res = new HashMap<String, Object>();
		
		res.put("flag", resultContainer.isCorrectFlag());
		res.put("message", resultContainer.getErrorMsg());
		return res;
	}
	
}
