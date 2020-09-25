package com.jixu.adapter.control.cotroller;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jixu.adapter.control.service.ConfigAdptService;
import com.jixu.adapter.control.service.ExecuteJixuAdapter;
import com.jixu.adapter.types.AdapterJobResultDto;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.DateUtil;
import com.joyintech.framework.util.StringUtil;

/**
 * 
 * @author wangqidong
 * 外部调用适配器入口
 */
@RestController
@RequestMapping("/ExecuteJixuAdapter")
public class ExecuteJixuAdapterController {

	@Autowired
    private ExecuteJixuAdapter executeJixuAdapter;
	
	@Resource(name="configAdptService")
	private ConfigAdptService configService;
	
	@PostMapping("ExecuteJixuAdapter")
	public ResultContainer ExecuteJixuAdapter(@RequestBody HashMap<String, Object> outParams){
		Object oAadapterID = outParams.get("adapterID");
		ResultContainer resultContainer = new ResultContainer();
		if (StringUtil.isNotEmpty(oAadapterID)){
			int adapterID = Integer.valueOf(oAadapterID+"");
			return executeJixuAdapter.executeAdapter(adapterID, outParams);
		}
		resultContainer.setCorrectFlag(false);
		resultContainer.setErrorMsg("适配器ID不可为空");
		return resultContainer;
	}
	
	@Async
	@PostMapping("ExecuteJixuAdapterAsync")
	public void ExecuteJixuAdapterAsync(@RequestBody HashMap<String, Object> outParams){
		Object oAadapterID = outParams.get("adapterID");
		if (StringUtil.isNotEmpty(oAadapterID)){
			int adapterID = Integer.valueOf(oAadapterID.toString());
			executeJixuAdapter.executeAdapter(adapterID, outParams);
		}
	}
	
	@PostMapping(value="fetchJobLatestResult")
	public AdapterJobResultDto getJobLatestStatus(@RequestBody HashMap<String, Object> params) {
		int adapterID=Integer.valueOf(params.get("adapterID").toString());	//适配器编号
		String executeTimeStr=params.get("executeTime").toString();	//执行时间 yyyymmdd hh:mm:ss
		Date executeTime=DateUtil.convertStr2Time3(executeTimeStr);
		AdapterJobResultDto dto=configService.getAdapterLatestStatus(adapterID, executeTime);
		return dto;
	}
}
