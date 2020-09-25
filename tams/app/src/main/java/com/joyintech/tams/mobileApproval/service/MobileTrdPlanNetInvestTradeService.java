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
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.trade.plannet.dto.TrdPlanNetInvestTradeDto;
import com.joyintech.tams.invest.trade.plannet.service.TrdPlanNetInvestTrade002Service;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>方法：</b>MobileTrdPlanNetInvestTradeService<br>
 * <b>描述：</b>计划类净值型交易<br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 */
@Service
public class MobileTrdPlanNetInvestTradeService {

    @Autowired
    private MobileApprovalService mobileApprovalService;
    
    @Autowired
    private MobileApprovalMapper mobileApprovalMapper;
    
    @Autowired
    private TrdPlanNetInvestTrade002Service trdPlanNetInvestTrade002Service;

    @Autowired
	private BussTrdTransferOrderService bussTrdTransferOrderService;

    public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
    	TrdPlanNetInvestTradeDto dto = JsonUtils.json2Object(businessData, TrdPlanNetInvestTradeDto.class);
    	
    	
//		2、处理数据字典



		//赎回确认查询资产可用持仓
		if(null!=dto.getPs()&&"T19".equals(dto.getPs())){
//			PtlSecPositionFlowDto param = new PtlSecPositionFlowDto();
//			param.setPortfolioId(dto.getPortfolioId());
//			param.setFinprodId(dto.getFinprodId());
//			param.setCdate(dto.getTradeDate());
//			param.setSecAcctId(dto.getChlId());
//			BigDecimal result = trdPlanNetInvestTrade002Service.getPtlSecPosition(param);
//			dto.setAssetAvailablePosition(result);
//设置确认速度T+ 不可见
			dto.setSettleSpreed(null);
			//设置申购费不可见
			dto.setFeeAmtPay(null);
		}

		//当交易方向为 ，赎回确认T19，
		if(null!=dto.getPs()&&"T19".equals(dto.getPs()) ){
			dto.setPurCfmDate(dto.getTradeDate());
			dto.setTradeDate(null);

		}

//		交易方向
    	dto.setPs(SysDataUtil.getNameFromDataDict(DictCode.交易方向, dto.getPs()));
//		资产二级分类
    	dto.setType9(SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, dto.getType9()));
//		资产三级分类
    	dto.setType10(SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类, dto.getType10()));
//		资产四级分类
    	dto.setType11(SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类, dto.getType11()));
//		交易场所
    	dto.setTradeMarket(SysDataUtil.getNameFromDataDict(DictCode.交易场所, dto.getTradeMarket()));
    	if("卖出".equals(dto.getPs())) {
    		dto.setPurCfmDate(null);
    		dto.setProAvailableCash(null);
			dto.setProAvailableCash1(null);
			dto.setAccAvailableCash(null);
    	}


    	if("申购".equals(dto.getPs())) {
    		//申购不显示 预计到账日，托管户可用现金，净值日期
			dto.setRedCfmDate(null);
			dto.setAccAvailableCash(null);
			dto.setCpriceDate(null);

//    		if (null != dto.getPortfolioId() && null != dto.getCcy() && null != dto.getTradeDate()) {
//				Map<String, Object> param2 = new HashMap<>();
//				param2.put("portfolioId", dto.getPortfolioId());
//				param2.put("ccy", dto.getCcy());
//				param2.put("vdate", DateUtil.format(dto.getTradeDate(), DateUtil.DEFAULT_DATETIME_FORMAT));
//				param2.put("calId", dto.getCalendarId());
//				param2.put("chlId", dto.getChlId());
//				Map<String, Object> result2 = trdPlanNetInvestTrade002Service.getProAvailableCash(param2);
//				//赋值组合T+1日可用现金
//				dto.setProAvailableCash(new BigDecimal((String)result2.get("cashT")));
//				dto.setProAvailableCash1(new BigDecimal((String) result2.get("cashT1")));
//			}
    	}



    	if("申购确认".equals(dto.getPs())) {
//    		if (null != dto.getPortfolioId() && null != dto.getCcy() && null != dto.getTradeDate()) {
//				Map<String, Object> param2 = new HashMap<>();
//				param2.put("portfolioId", dto.getPortfolioId());
//				param2.put("ccy", dto.getCcy());
//				param2.put("vdate", DateUtil.format(dto.getTradeDate(), DateUtil.DEFAULT_DATETIME_FORMAT));
//				param2.put("calId", dto.getCalendarId());
//				param2.put("chlId", dto.getChlId());
//				Map<String, Object> result2 = trdPlanNetInvestTrade002Service.getProAvailableCash(param2);
//				//赋值组合T+1日可用现金
//				dto.setProAvailableCash(new BigDecimal((String)result2.get("cashT")));
//				dto.setProAvailableCash1(new BigDecimal((String) result2.get("cashT1")));
//				dto.setAccAvailableCash(new BigDecimal((String) result2.get("cashT2")));
//			}
    		dto.setPurCfmDate(dto.getTradeDate());
    		dto.setTradeDate(null);
    		dto.setRedCfmDate(null);
    		dto.setSettleSpreed(null);



    	}
		dto.setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种, dto.getCcy()));




//		 是否发送指令显示为N，应改为否
		if (null!=dto.getBussTrdTransferOrder()) {
			for (BussTrdTransferOrderDto item : dto.getBussTrdTransferOrder()){
				item.setIsNeedSend(SysDataUtil.getNameFromDataDict(DictCode.是否, item.getIsNeedSend()));
				item.setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种, item.getCcy()));
				//查询收款账户网点
				if(null!=dto.getPs()&&"申购确认".equals(dto.getPs())){
					String value = MobileUtils.getValuefromCodeNameAndCode(item.getCcy(),DictCode.币种);
					StlCapacctInfo result = bussTrdTransferOrderService.queryStlCapacctInfoByBusiAcctNo(value,item.getRecAcctNo());
					if(null!=result)
						item.setRecAcctPoint(result.getLargePayno());
				}
			}
		}


//    	通道
    	if(dto.getChlId()!=null) {
    		dto.setChlId(mobileApprovalMapper.getChannelName(dto.getChlId()));
    	}
//    	证券账户
    	if(dto.getSecManageAcctId()!=null) {
    		dto.setSecManageAcctId(mobileApprovalMapper.getSecAcctName(dto.getSecManageAcctId()));
    	}
    	
    	
//    	委托投资人员信息
//    	交易对手
    	if(null!=dto.getTrdInvestPersonInfo()&&dto.getTrdInvestPersonInfo().getCounterId()!=null) {
    		dto.getTrdInvestPersonInfo().setCounterId(mobileApprovalMapper.getCustomerName1(dto.getTrdInvestPersonInfo().getCounterId()));
    	}

        //组装下一审核人及操作类型信息
        ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
        todoDto.setBusinessData(dto);

//        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
        return MobileUtils.Objetc2Json("000",todoDto,"");
    }
}
