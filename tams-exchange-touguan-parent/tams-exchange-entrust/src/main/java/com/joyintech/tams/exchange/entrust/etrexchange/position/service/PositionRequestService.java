package com.joyintech.tams.exchange.entrust.etrexchange.position.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.exchange.entrust.etrexchange.common.dto.EtrExchangeTradePortfolioRequest;
import com.joyintech.tams.exchange.entrust.etrexchange.common.dto.EtrExchangeTradeSaveRequest;
import com.joyintech.tams.exchange.entrust.etrexchange.position.constant.PositionConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.position.service.CommonPositionEngineService;

@Service
public class PositionRequestService {
	
	@Autowired
	private CommonPositionEngineService commonPositionEngineService;

	/**
	 * EtrExchangeTradeSaveRequest请求对象使用
	 * @param entity
	 */
	public void sendEtrExchangeTradeSaveRequest(EtrExchangeTradeSaveRequest entity){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		this.setCommonField(entity, resultMap);//设置公共字段，个性化的需要在具体接口中覆盖
		String type6 = commonPositionEngineService.getType6(entity.getFinprodId());
		String finprodType = entity.getFinprodType2();
		if(StringUtil.equals(type6, DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue())) {
			finprodType += "-"+type6;
		}
		String interType = commonPositionEngineService.getPositionInterType(PositionConstant.POSITION_TYPE_ETR, entity.getTradePlat(), finprodType, entity.getPs(), entity.getOptType(), entity.getClass().getName());
		if(StringUtil.equals(interType,PositionConstant.INTERF_TYPE_BOND002)) {
			this.bond002(entity, resultMap);
		}else {
			//TODO 根据条件判断调用其他接口
		}
		PositionHttpRequest.send(resultMap);
	}
	
	/**
	 * doDelete时使用
	 * @param workflowId
	 * @param modelType
	 * @param busiType
	 * @param ps
	 */
	public void sendDeleteByWorkFlowId(String workflowId, String modelType,String busiType,String ps){
		PositionHttpRequest.doDeleteByWorkFlowId(workflowId,modelType,busiType,ps);
	}
	
	/**
	 * 场内现券-指令    bond002
	 * @param entity
	 * @param resultMap
	 */
	private void bond002(EtrExchangeTradeSaveRequest entity,Map<String,Object> resultMap) {
		resultMap.put("interfaceType",PositionConstant.INTERF_TYPE_BOND002);
		if(StringUtil.equals(entity.getPs(),BussinessDictCodeList.交易方向_行权回售.getValue())){
			resultMap.put("busiType",DictCodeListExt3.头寸业务类型_债券行权交易.getValue());
        }else{
        	resultMap.put("busiType",DictCodeListExt3.头寸业务类型_现券交易.getValue());
        }
		resultMap.put("busiAct",DictCodeListExt3.业务动作_投资指令.getValue());
		resultMap.put("ps", entity.getPs());
		
		String deliveryType = commonPositionEngineService.getDeliveryType(entity.getFinprodId());
		String settleSpreed = StringUtil.EMPTY;
		if(deliveryType.equals(DictCodeListExt3.结算方式_头寸引擎_净额结算.getValue())) {
			settleSpreed =  BussinessDictCodeList.清算速度_T加1.getValue();
		}else if(deliveryType.equals(DictCodeListExt3.结算方式_头寸引擎_RTGS.getValue())) {
			settleSpreed =  BussinessDictCodeList.清算速度_T加0.getValue();
		}else {
			settleSpreed = BussinessDictCodeList.清算速度_T加1.getValue();
		}
		resultMap.put("settleSpeed", settleSpreed);
		resultMap.put("bidResult", DictCodeList.是否_否.getValue());
		resultMap.put("purchModle", null);
		resultMap.put("tradePlace", entity.getTradeMarket());
		resultMap.put("sharesTradePlatform", entity.getTradePlat());
		resultMap.put("deliveryType", deliveryType);
		resultMap.put("workFlowId",entity.getWorkflowId());
		resultMap.put("cdate",DateUtil.format(entity.getVdate()));
		resultMap.put("ccy",entity.getCcy());
		resultMap.put("finprodId",entity.getFinprodId());
		resultMap.put("bTotalTradeId",null);
		if(CollectionUtils.isNotEmpty(entity.getPortfolioList())){
			List<Map<String,Object>> ptlList = new ArrayList<>();
			for(EtrExchangeTradePortfolioRequest etrExchangeTradePortfolioRequest:entity.getPortfolioList()) {
				Map<String,Object> pltMap = new HashMap<>();
                pltMap.put("portfolioId", etrExchangeTradePortfolioRequest.getPortfolioId());
                pltMap.put("tradeId", null);
                pltMap.put("etrId", entity.getEntrustId());
                pltMap.put("etrSecAmount", null);
                pltMap.put("ordId", null);
                pltMap.put("ordSecAmount", null);
                pltMap.put("cashAcctId", etrExchangeTradePortfolioRequest.getAcctNo());
                pltMap.put("secAcctId", etrExchangeTradePortfolioRequest.getSecAcctId());
                pltMap.put("secAmount", null);//TODO 根据指令单位是张还是手来处理
                pltMap.put("cashAmount", etrExchangeTradePortfolioRequest.getFprice());
                pltMap.put("invAim", etrExchangeTradePortfolioRequest.getInvAim());
                pltMap.put("integrateSecAmount", null);
                pltMap.put("ownSecAmount", null);
                ptlList.add(pltMap);
			}
			resultMap.put("ptlList",ptlList);
		}
	}
	
	/**
	 * 设置公共字段
	 * @param entity
	 * @param optType
	 * @param resultMap
	 */
	private void setCommonField(EtrExchangeTradeSaveRequest entity,Map<String,Object> resultMap) {
		String balanceCheck="Y";
        if(StringUtil.isNotEmpty(entity.getSoftSkipMode()) && StringUtil.equals(entity.getSoftSkipMode(),"Y")){
            balanceCheck="N";
        }
        resultMap.put("userId", SecurityUtil.getLoginUser().getUserName());
        resultMap.put("totalTradeId", entity.getEntrustId());
        resultMap.put("balanceCheck", balanceCheck);
        resultMap.put("flowStatus", DictCodeListExt3.流程节点_流程提交.getValue());
        resultMap.put("modelType", DictCodeListExt1.子系统代码_委托.getValue());
        
        if(StringUtil.equals(entity.getOptType(), "new")) {
			resultMap.put("decisionAct",DictCodeListExt3.系统动作_新建.getValue());
			resultMap.put("systemAct",DictCodeListExt3.系统动作_新建.getValue());
		}else if(StringUtil.equals(entity.getOptType(), "modify")) {
			resultMap.put("decisionAct",DictCodeListExt3.系统动作_修改.getValue());
			resultMap.put("systemAct",DictCodeListExt3.系统动作_修改.getValue());
		}
	}
}
