package com.jixu.adapter.service;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jixu.adapter.common.util.AdapterCoreUtility;
import com.jixu.adapter.common.util.BusinessUtil;
import com.jixu.adapter.common.util.ClientData;
import com.jixu.adapter.dao.intf.InterfaceAdapterDao;
import com.jixu.adapter.service.parent.AfterCallClassesAbstract;
import com.jixu.adapter.types.CallClassParamsDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;

/**
 * 发送下游报文
 * 
 * @author wangqidong
 *
 */
@Service
public class AfterCallClassJsonToDownstreamService extends AfterCallClassesAbstract {
	
	@Resource(name="interfaceAdapterDao")
    protected InterfaceAdapterDao interfaceSourceDao;

	private static final Logger logger = LoggerFactory.getLogger(AfterCallClassJsonToDownstreamService.class);

	@Override
	public void executeAfterProcess(CallClassParamsDto dto) {
		logger.info(dto.getAdapterID() + ":" + " 开始" + "---");
		
		ClientData response = new ClientData();
		BusinessUtil.createSendInterfHeader(response, dto);

		Map<String, Object> body = response.getBody();
		body.put("FILE_NAME", dto.getParams().get("outFileName"));
		body.put("FILE_PATH", dto.getParams().get("outFilePath"));
		body.put("FILE_CREATE_TIME", DateUtil.format(new Date(), DateUtil.DEFAULT_DATE_FORMAT_EN));
		String requestJson = JsonUtils.object2Json(response.convertToMap());
    	logger.info("请求报文:{}", requestJson);
		try {
			AdapterCoreUtility.send(response.convertToMap());
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info(dto.getAdapterID() + ": " + " 结束");
	}

	@Override
	public String callClassName() {
		return "webservice调用下游";
	}

}
