package com.jixu.adapter.custom.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.jixu.adapter.common.util.JixuRestTemplate;
import com.jixu.adapter.service.export.ExpAssembleOutput;
import com.jixu.adapter.types.CallBackResult;
import com.jixu.adapter.types.CustomParams;
import com.jixu.adapter.types.ExeRecordInfo;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.ServiceApplication;
import com.orm.obj.InterfaceLog;

@Component
public class CutomCallBackSaleService implements CustomExecuteService{

	private static final Logger logger = LoggerFactory.getLogger(CutomCallBackSaleService.class);
	
	@Autowired(required = false)
    @Qualifier("loadBalancedRestTemplate")
	private JixuRestTemplate restTemplate; 
	
	@Override
	public ResultContainer executeCustom(CustomParams params) {
		ResultContainer container=new ResultContainer();
		//InterfaceSource source=params.getSource();	//适配器的基本配置
		HashMap<String, Object> outparams=params.getParamsMap();	//执行适配器的参数
		HashMap<String, Object> customparams=new HashMap<String, Object>();
		
		//获取本次执行生成的文件名及大小
		InterfaceLog log=params.getIlog();
		ExeRecordInfo recordinfo=JSONObject.parseObject(log.getRecordInfo(), ExeRecordInfo.class);
		
		if(outparams!=null) {
			customparams.putAll(outparams);	//销售调用适配器传过来的参数集
		}else {
			String errorMsg="上游没有传入微服务调用参数";
			container.setCorrectFlag(false);
			container.setErrorMsg(errorMsg);
			logger.error("Error", errorMsg);
			return container;
		}
		
		if(recordinfo!=null) {
			customparams.put("GenFilePath", recordinfo.getFilePath());	//生成的文件路径(包含文件名)
			customparams.put("GenFileSize", recordinfo.getFileSize());	//生成的文件大小
		}
		
		String callbackpath="/callBackPath";
		//获取销售接口传过来的反调微服务方法路径
		if(outparams.containsKey("CallBackPath"))
			callbackpath=outparams.get("CallBackPath").toString();
		
		//微服务调用销售接口
		CallBackResult result=restTemplate.postForObject(
		          ServiceApplication.CUSTOMER_INTERF_NAME + callbackpath, customparams,
		          CallBackResult.class);
		
		if(!result.isCorrect()) {
			//错误信息记录到错误日志里
			container.setCorrectFlag(false);
			container.setErrorMsg(result.getErrorMsg());
		}
		
		return container;
	}
	
}
