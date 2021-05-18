package com.joyintech.tams.valuation.batch.positionmake.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.csii.dc.client.util.StringUtil;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;


@Service
@Scope("prototype")
public class PositionMakeInterfWriterService {
	
	@Autowired(required = false)
    @Qualifier("loadBalancedRestTemplate")
    protected JoyinRestTemplate restTemplate;
	
	private static final String INTERF_PLACE_URL = "url_interf_place";
	
	/**
	 * 调用投管接口平台
	 */
	public void interfTaskRun(Map<String,Object> parameter,Date cdate,String seqNo) throws BatchException{
		String url = CacheUtil.getSysParam(INTERF_PLACE_URL);
		if(StringUtil.isEmpty(url)){
        	throw new BatchException("头寸接口平台URL未配置[系统参数：url_interf_place].");
        }
		String gpcode = CacheUtil.getSysParam("CALLINTERF_GPCODE");
		if(StringUtil.isEmpty(gpcode)){
        	throw new BatchException("头寸接口平台分组编码未配置[系统参数：CALLINTERF_GPCODE].");
        }
		String trigger = CacheUtil.getSysParam("CALLINTERF_TRIGGER");
		if(StringUtil.isEmpty(trigger)){
        	throw new BatchException("头寸接口平台触发器编码未配置[系统参数：CALLINTERF_TRIGGER].");
        }
		
		Map<String,Object> params = new HashMap<>();
        params.put("parameter",parameter);
        params.put("routers",new String []{});
        params.put("async",false);
        params.put("predate",DateUtil.format(cdate));
        params.put("preseqno", seqNo);
        params.put("gpcode",gpcode);
        params.put("trigger",trigger);
        //TODO 暂不考虑请求超时问题
        AjaxResult result = restTemplate.postForObject(url+"/callTrigger", params, AjaxResult.class);
        if(!"成功".equals(result.getMessage())){
        	throw new BatchException(result.getMessage());
        }
	}
}
