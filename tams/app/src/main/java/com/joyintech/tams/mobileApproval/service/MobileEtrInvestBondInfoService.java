 /*
 * <b>文件名：</b>MobilePtlManagementInfoService<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 * Copyright(c) 2020 Joyin Tech,LTD. All Rights Reserved.
 */
package com.joyintech.tams.mobileApproval.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.entrust.etrinvestbondinfo.dto.EtrInvestBondInfoDto;
import com.joyintech.tams.invest.entrust.etrinvestbondinfo.service.EtrInvestBondInfoService;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>方法：</b>MobilePtlManagementInfoService<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 */
@Service
public class MobileEtrInvestBondInfoService {

	@Autowired
	EtrInvestBondInfoService etrInvestBondInfoService;

    @Autowired
    private MobileApprovalService mobileApprovalService;
    
    @Autowired
    private MobileApprovalMapper mobileApprovalMapper;


    public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
    	EtrInvestBondInfoDto dto = JsonUtils.json2Object(businessData, EtrInvestBondInfoDto.class);
    	
    	
//		2、处理数据字典
//		组合T日，T+1日可用现金
//		if(null!=dto.getPs()&&"T01".equals(dto.getPs())){
//			Map<String, Object> param = new HashMap<>();
//			param.put("portfolioId", dto.getPortfolioId());
//			param.put("ccy", dto.getCcy());
//			param.put("vdate", DateUtil.format(dto.getVdate(),DateUtil.DEFAULT_DATETIME_FORMAT));
//			param.put("calId", dto.getCalendarId());
//			param.put("chlFinprodId", dto.getFinprodId());
//
//			Map<String, Object> result1 = etrInvestBondInfoService.getProAvailableCash(param);
//			dto.setAppProAvailableCash(result1.get("cashT").toString());
//			dto.setAppProAvailableCashT1(result1.get("cashT1").toString());
//		}
//		else {
////查询可用持仓，资产可用持仓
//			Map<String, Object> param = new HashMap<>();
//			param.put("portfolioId", dto.getPortfolioId());
//			param.put("calId", dto.getCalendarId());
//			param.put("finprodId", dto.getFinprodId());
//			param.put("ccy", dto.getCcy());
//			param.put("secAcctId", dto.getSecAcctId());
//			param.put("chlId", dto.getChlId());
//			param.put("invAim", dto.getInvAim());
//
//			Map result = etrInvestBondInfoService.getAssetAvailablePosition(param);
//			dto.setAppAssetAvailablePosition((new BigDecimal(100).multiply((BigDecimal) result.get("position"))).toString());
//			dto.setAppAssetAvailablePosition1((new BigDecimal(100).multiply((BigDecimal) result.get("positionT1"))).toString());
//		}


// 		交易平台
		dto.setTradePlat(SysDataUtil.getNameFromDataDict(DictCode.交易平台, dto.getTradePlat()));


//		资产二级分类
    	dto.setAssetType1(SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, dto.getAssetType1()));
//		资产三级分类
    	dto.setAssetType10(SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类, dto.getAssetType10()));
//		资产四级分类
    	dto.setAssetType11(SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类, dto.getAssetType11()));
//		交易场所
    	dto.setTradeMarket(SysDataUtil.getNameFromDataDict(DictCode.交易场所, dto.getTradeMarket()));
//		交易市场
    	dto.setTradeMarketMode(SysDataUtil.getNameFromDataDict(DictCode.交易市场, dto.getTradeMarketMode()));
//		投资目的
    	dto.setInvAim(SysDataUtil.getNameFromDataDict(DictCode.投资目的, dto.getInvAim()));
//    	证券账户
    	if(dto.getSecAcctId()!=null) {
    		dto.setSecAcctId(mobileApprovalMapper.getSecAcctName(dto.getSecAcctId()));
    	}
//    	通道
    	if(dto.getChlId()!=null) {
    		dto.setChlId(mobileApprovalMapper.getChannelName(dto.getChlId()));
    	}
    	
    	
//    	委托投资人员信息
//    	交易对手
    	if(dto.getEtrInvestPersonInfo().getCounterId()!=null) {
    		dto.getEtrInvestPersonInfo().setCounterId(mobileApprovalMapper.getCustomerName1(dto.getEtrInvestPersonInfo().getCounterId()));
    	}

        //组装下一审核人及操作类型信息
        ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
        todoDto.setBusinessData(dto);

//        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
        return MobileUtils.Objetc2Json("000",todoDto,"");
    }
}
