package com.joyintech.invest.interf.s054.service;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfSendBaseService;
/**
 * 交易对手信息接口
 * @author wangqidong
 *
 */
@Service
@INF("S054")
public class SomoCounterService extends InterfSendBaseService<String>{

	/**
     * 转换为标准输出报文 {@inheritDoc}
     */
	@Override
	protected ClientData resolveData(Map<String, Object> request, ClientData response) {
		// 消息头
		BusinessUtil.createSendInterfHeader(request,response,getInfId());
		
        // 消息体
        Map<String, Object> body = response.getBody();
        // 交易表Map
        Map<String, Object> mstCounterParty = (Map<String, Object>)request.get("mstCounterParty");
        // 交易对手代码
        body.put("COUNTER_ID", mstCounterParty.get("counterId"));
        // 交易对手名称
        body.put("COUNTER_NAME", mstCounterParty.get("counterName"));
        // 交易对手类型
        body.put("COUNTER_TYPE", mstCounterParty.get("counterType"));
        // 关联市场机构代码
        body.put("LINK_ID", mstCounterParty.get("linkId"));
        // 人行一级分类
        body.put("P_TYPE_ONE", mstCounterParty.get("pTypeOne"));
        // 人行二级分类
        body.put("P_TYPE_TWO", mstCounterParty.get("pTypeTwo"));
        // 备注
        body.put("REMARK", mstCounterParty.get("remark"));
		return response;
	}

	/**
     * 接口预留先决处理操作 {@inheritDoc}
     */
	@Override
	protected void beforeHandle(Map<String, Object> request, ClientData response) {
		// TODO Auto-generated method stub
		
	}

	/**
     * 条件check {@inheritDoc}
     */
	@Override
	protected boolean checkWithCond(String obj, ClientData response) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
     * 接口预留后置处理操作 {@inheritDoc}
     */
	@Override
	protected void afterHandle(Map<String, Object> request, ClientData response) {
		// TODO Auto-generated method stub
		
	}

}
