/**
 * <b>文件名：</b>MobilePtlManagementInfoService<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 * Copyright(c) 2020 Joyin Tech,LTD. All Rights Reserved.
 */
package com.joyintech.tams.mobileApproval.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.portfolio.ptldvpfundallocation.controller.PtlDvpFundAllocation002Controller;
import com.joyintech.tams.invest.portfolio.ptldvpfundallocation.service.PtlDvpFundAllocation002Service;
import com.joyintech.tams.invest.portfolio.ptlfundallocation.dto.PtlFundAllocationDto;
import com.joyintech.tams.invest.portfolio.ptlfundallocation.service.PtlFundAllocation002Service;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>方法：</b>MobilePtlManagementInfoService<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 */
@Service
public class MobilePtlFundAllocationService {

    @Autowired
    private MobileApprovalService mobileApprovalService;
	@Autowired
	private PtlFundAllocation002Service ptlFundAllocation002Service;

    public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
    	PtlFundAllocationDto dto = JsonUtils.json2Object(businessData, PtlFundAllocationDto.class);
    	
    	List<BussTrdTransferOrderDto> bussTrdTransferOrderDtoList=dto.getBussTrdTransferOrder();
    	
    	//2、处理数据字典
		//查询付款账户余额


		//param.put("acctId",dto.getMyAcctNo());
//		if(StringUtil.isNotEmpty(DateUtil.format(dto.getSettleDate(),DateUtil.DEFAULT_DATE_FORMAT_EN))&&StringUtil.isNotEmpty(dto.getPortfolioId())&&StringUtil.isNotEmpty(dto.getCcy())&&StringUtil.isNotEmpty(dto.getMyAcctNo())){
//			Map<String, Object> param = new HashMap<>();
//			param.put("cdate",DateUtil.format(dto.getSettleDate(),DateUtil.DEFAULT_DATE_FORMAT_EN));
//			param.put("portfolioId",dto.getPortfolioId());
//			param.put("ccy",dto.getCcy());
//			param.put("acctId",dto.getMyAcctNo());
//
//			BigDecimal result = ptlFundAllocation002Service.getAmount(param);
//			dto.setMyAcctAmount(result);
//
//		}
//		//查询收款账户余额
//		if(StringUtil.isNotEmpty(DateUtil.format(dto.getSettleDate(),DateUtil.DEFAULT_DATE_FORMAT_EN))&&StringUtil.isNotEmpty(dto.getPortfolioId())&&StringUtil.isNotEmpty(dto.getCcy())&&StringUtil.isNotEmpty(dto.getMyAcctNo())){
//			Map<String, Object> param = new HashMap<>();
//			param.put("cdate",DateUtil.format(dto.getSettleDate(),DateUtil.DEFAULT_DATE_FORMAT_EN));
//			param.put("portfolioId",dto.getPortfolioId());
//			param.put("ccy",dto.getCcy());
//			param.put("acctId",dto.getCounterAcctNo());
//
//			BigDecimal result = ptlFundAllocation002Service.getAmount(param);
//			dto.setCounterAcctAmount(result);
//
//		}

//    	dto.setPortfolioType(SysDataUtil.getNameFromDataDict(DictCode.组合类型, dto.getProfitType()));
    	dto.setCcy(dto.getCcyStr());

		dto.setPayType(SysDataUtil.getNameFromDataDict(DictCode.指令类型, dto.getPayType()));
    	if(bussTrdTransferOrderDtoList.size()>0) {
    		BussTrdTransferOrderDto bdto=dto.getBussTrdTransferOrder().get(0);
    		dto.setMyAcctNo(bdto.getPayAcctName());
        	dto.setCounterAcctNo(bdto.getRecAcctName());
        	
        	MobileUtils.dealBussTrdTransferOrder(bussTrdTransferOrderDtoList);
        	
    	}
    	
        //组装下一审核人及操作类型信息
        ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
        todoDto.setBusinessData(dto);

//        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
        return MobileUtils.Objetc2Json("000",todoDto,"");
    }
}
