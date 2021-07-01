/*
 * <b>文件名：</b>MobilePtlManagementInfoService<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 * Copyright(c) 2020 Joyin Tech,LTD. All Rights Reserved.
 */
package com.joyintech.tams.mobileApproval.service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.entrust.etrrepo.dto.EtrRepoDto;
import com.joyintech.tams.invest.entrust.etrrepo.service.EtrRepo002Service;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <b>方法：</b>MobilePtlManagementInfoService<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 */
@Service
public class MobileEtrRepoService {

    @Autowired
    private MobileApprovalService mobileApprovalService;
    
    @Autowired
    private MobileApprovalMapper mobileApprovalMapper;

	@Autowired
	private EtrRepo002Service etrRepo002Service;


    public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
    	EtrRepoDto dto = JsonUtils.json2Object(businessData, EtrRepoDto.class);


//		2、处理数据字典

//交易场所为银行间不显示回购类型
		if(null!=dto.getAssetType1()&&"01".equals(dto.getTradeMarket())){
			dto.setAssetType1(null);
		}

//判断是质押式回购指令还是买断式回购指令
		 String pageid = mobileApprovalMapper.selectForPageId(dto.getWorkflowId());
		//买断式回购投资委托
		 if("EtrBoutRepo002".equals(pageid)){
		 	dto.setAPPentrustAmtTWO(dto.getEntrustAmt());
		 }
		 else if("EtrRepo002".equals(pageid)){
		 	dto.setAPPentrustAmtOne(dto.getEntrustAmt());
		 	//质押式回购指令查询组合T日,T+1日可用现金
//			 Map<String,Object> param = new HashMap<>();
//			 param.put("portfolioId",dto.getPortfolioId());
//			 param.put("ccy",dto.getCcy());
//			 param.put("vdate", DateUtil.format(dto.getVdate(),DateUtil.DEFAULT_DATETIME_FORMAT));
//			 param.put("calId",dto.getCalendarId());
//
//			 Map<String, Object> result = etrRepo002Service.getProAvailableCash(param);
//			 dto.setAppProAvailableCash1((BigDecimal) result.get("cashT1"));
//			 dto.setProAvailableCash((BigDecimal) result.get("cashT"));
		 }


		 //显示需要。 如果是质押式回购指令且为交易所、竞价回购、逆回购同时成立，将配置中的买断式回购指令的资产可用持仓置空，设置为可使用标准券额度。显示需要
		if("EtrRepo002".equals(pageid)){
			if(null!=dto.getTradeMarket()&&("03".equals(dto.getTradeMarket())||"03".equals(dto.getTradeMarket()))){
				if(null!=dto.getAssetType1()&&"H02".equals(dto.getAssetType1())){
					if(null!=dto.getPs()&&"03".equals(dto.getPs())){
						dto.setAssetAvailablePosition1(dto.getAssetAvailablePosition().toString());
						dto.setAssetAvailablePosition(null);
					}
                }
			}
		}

//		回购类型
		dto.setAssetType1(SysDataUtil.getNameFromDataDict(DictCode.回购类型, dto.getAssetType1()));


//		交易方向
    	dto.setPs(SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, dto.getPs()));
//		期限品种
    	dto.setTermType(SysDataUtil.getNameFromDataDict(DictCode.回购期限品种, dto.getTermType()));
//		价格模式
    	dto.setPriceModel(SysDataUtil.getNameFromDataDict(DictCode.价格模式, dto.getPriceModel()));
//		清算速度
    	dto.setSettleSpreed(SysDataUtil.getNameFromDataDict(DictCode.清算速度, dto.getSettleSpreed()));

    	dto.setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种, dto.getCcy()));

    	dto.setTradeMarket(SysDataUtil.getNameFromDataDict(DictCode.交易场所, dto.getTradeMarket()));
    	//    	获取交易日历
    	if(dto.getCalendarId()!=null) {
    		dto.setCalendarId(mobileApprovalMapper.getCalendarName(dto.getCalendarId()));
    	}
//    	证券账户
    	if(dto.getSecuritiesCust()!=null) {
    		dto.setSecuritiesCust(mobileApprovalMapper.getSecAcctName(dto.getSecuritiesCust()));
    	}
//    	通道
    	if(dto.getChlId()!=null) {
    		dto.setChlId(mobileApprovalMapper.getChannelName(dto.getChlId()));
    	}
    	
    	
//    	委托投资人员信息
//    	交易对手
    	if(null!=dto.getEtrInvestPersonInfo()&&dto.getEtrInvestPersonInfo().getCounterId()!=null) {
    		dto.getEtrInvestPersonInfo().setCounterId(mobileApprovalMapper.getCustomerName1(dto.getEtrInvestPersonInfo().getCounterId()));
    	}

        //组装下一审核人及操作类型信息
        ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
        todoDto.setBusinessData(dto);

//        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
        return MobileUtils.Objetc2Json("000",todoDto,"");
    }
}
