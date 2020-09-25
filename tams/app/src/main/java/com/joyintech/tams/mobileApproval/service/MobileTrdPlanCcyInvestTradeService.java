package com.joyintech.tams.mobileApproval.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.invest.trade.planccy.dto.TrdPlanCcyInvestTradeDto;
import com.joyintech.tams.invest.trade.planccy.service.TrdPlanCcyInvestTrade002Service;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;

@Service
public class MobileTrdPlanCcyInvestTradeService {

	@Autowired
    private MobileApprovalService mobileApprovalService;
	@Autowired
	private MobileApprovalMapper mobileApprovalMapper;
	@Autowired
	private TrdPlanCcyInvestTrade002Service trdPlanCcyInvestTrade002Service;
	@Autowired
	private BussTrdTransferOrderService bussTrdTransferOrderService;

    /**
     * 日期相关
     */
    @Autowired
    private DateCalService dateCalService;
	
	public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
		TrdPlanCcyInvestTradeDto dto = JsonUtils.json2Object(businessData, TrdPlanCcyInvestTradeDto.class);

		//查询记录的操作类型
		String actionType = mobileApprovalMapper.selectActionType(dto.getWorkflowId());

		//2、处理数据字典

		dto.setType6(dto.getType6Str());
		dto.setType9(dto.getType9Str());
		dto.setType10(dto.getType10Str());
		dto.setType11(dto.getType11Str());


		//指令信息
		if(null!=dto.getBussTrdTransferOrder()){
			MobileUtils.dealBussTrdTransferOrder(dto.getBussTrdTransferOrder());
			for(BussTrdTransferOrderDto item:dto.getBussTrdTransferOrder())
			//申购确认 查询收款账户网点
			if(null!=dto.getPs()&&"T05".equals(dto.getPs())){
				String value = MobileUtils.getValuefromCodeNameAndCode(item.getCcy(),DictCode.币种);
				StlCapacctInfo result = bussTrdTransferOrderService.queryStlCapacctInfoByBusiAcctNo(value,item.getRecAcctNo());
				if(null!=result)
				    item.setRecAcctPoint(result.getLargePayno());
			}
		}


		//交易对手
		if(dto.getTrdInvestPersonInfo().getCounterId()!=null) {
			dto.getTrdInvestPersonInfo().setCounterId(mobileApprovalMapper.getCustomerName1(dto.getTrdInvestPersonInfo().getCounterId()));
		}
//      当交易方向为申购、赎回时不显示单位净值
		if(null!=dto.getPs()&&("T04".equals(dto.getPs())||"T06".equals(dto.getPs()))){
			dto.setUnitCprice(null);
			if(null!=dto.getPs()&&("T04".equals(dto.getPs()))){
				//申购，申购撤销不显示托管户剩余现金
				dto.setAccAvailableCash(null);
				//申购撤销不显示资产可用持仓
				if(StringUtil.isNotEmpty(actionType)&&actionType.equals("revoke")){
					dto.setAssetAvailablePosition(null);
				}
			}
		}
//T05
		if(null!=dto.getPs()&&("T05".equals(dto.getPs()))){
//			dto.setBussTrdTransferOrder(null);
			dto.setSettleSpeed(null);
			dto.setRedCfmDate(null);
//			if (null != dto.getPortfolioId() && null != dto.getCcy() && null != dto.getTradeDate()) {
//				Map<String, Object> param2 = new HashMap<>();
//				param2.put("portfolioId", dto.getPortfolioId());
//				param2.put("ccy", dto.getCcy());
//				param2.put("vdate", DateUtil.format(dto.getTradeDate(), DateUtil.DEFAULT_DATETIME_FORMAT));
//				param2.put("calId", dto.getCalendarId());
//				param2.put("chlId", dto.getChlId());

//		        if(StringUtil.isNotEmpty(dto.getCalendarId())){
//		            List<String> holidayList = dateCalService.getDealCalendarHoliday(DateUtil.parse(DateUtil.format(dto.getTradeDate())),DateUtil.addDay(DateUtil.parse(DateUtil.format(dto.getTradeDate())), 15),dto.getCalendarId());
//		            Date tradeDay = dateCalService.calculationTradingDay(DateUtil.parse(DateUtil.format(dto.getTradeDate())), 1, holidayList, "1");
//		            param2.put("vdate1",DateUtil.format(tradeDay));
//		        }

//				Map<String, Object> result2 = trdPlanCcyInvestTrade002Service.getProAvailableCash(param2);
//				//赋值组合T+1日可用现金
//				dto.setAccAvailableCash((String) result2.get("cashT2"));
//				dto.setProAvailableCash1((String) result2.get("cashT1"));
//				dto.setProAvailableCash(new BigDecimal((String)result2.get("cashT")));

//			}
		}
//      T05申购确认和赎回确认T19以及赎回到账T24的交易日期都应改为确认日期
		if(null!=dto.getPs()&&("T05".equals(dto.getPs())||"T19".equals(dto.getPs())||"T24".equals(dto.getPs()))){
			dto.setConfirmDate(dto.getTradeDate());
			dto.setTradeDate(null);
		}

		//赎回确认不显示清算速度
		if(null!=dto.getPs()&&"T19".equals(dto.getPs())){
			dto.setSettleSpeed(null);
		}

		dto.setPs(dto.getPsStr());
		//卖出不显示组合T日，T+1日，托管户可用现金
		if(null!=dto.getPs()&&"卖出".equals(dto.getPs())){
			dto.setProAvailableCash1(null);
			dto.setProAvailableCash(null);
			dto.setAccAvailableCash(null);
			//卖出撤销是交易日期应显示为确认日期
			if(StringUtil.isNotEmpty(actionType)&&"revoke".equals(actionType)){
				dto.setConfirmDate(dto.getTradeDate());
				dto.setTradeDate(null);
				dto.setSettleSpeed(null);
			}
		}



		dto.setCcy(dto.getCcyStr());
		//风险试算信息
		if(null!=dto.getRiskTrialRetDto()){
			MobileUtils.dealRiskTrialRetDto(dto.getRiskTrialRetDto());
		}

      
        //组装下一审核人及操作类型信息
        ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
        todoDto.setBusinessData(dto);

//        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
        return MobileUtils.Objetc2Json("000",todoDto,"");
    }
	
}
