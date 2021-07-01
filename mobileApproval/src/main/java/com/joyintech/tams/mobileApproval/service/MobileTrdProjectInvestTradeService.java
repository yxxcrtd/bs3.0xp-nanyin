package com.joyintech.tams.mobileApproval.service;

import com.joyintech.tams.component.business.trade.controller.BussTrdAvailableCashQueryController;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.trade.project.dto.TrdProjectInvestTrade002Dto;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;

@Service
public class MobileTrdProjectInvestTradeService {


	@Autowired
	BussTrdAvailableCashQueryController bussTrdAvailableCashQueryController;

	@Autowired
    private MobileApprovalService mobileApprovalService;

	@Autowired
	private MobileApprovalMapper mobileApprovalMapper;

	public String getWorkFlowInfo(String businessData) throws Exception {
		//1、获取业务数据
		TrdProjectInvestTrade002Dto dto = JsonUtils.json2Object(businessData, TrdProjectInvestTrade002Dto.class);

	    //2、处理数据字典


//		Map<String, Object> param2 = new HashMap<>();
//		param2.put("tradeDate",dto.getTradeDate());
//		param2.put("calendarId",dto.getCalendarId());
//		param2.put("ccy",dto.getCcy());
//		param2.put("acctTypeList", DictCodeListExt1.资金账户类型_产品托管户.getValue()+ "," +DictCodeListExt1.资金账户类型_券商资金账户.getValue());
//		param2.put("finprodId",dto.getFinprodId());
//		param2.put("portfolioId",dto.getPortfolioId());
//
//		Map  result2 = new HashMap<String, BigDecimal>();
//		result2 = bussTrdAvailableCashQueryController.getAvailableCash(param2);
//		//当交易方向为买入的时候赋值组合T日，
//
//
//		if(null!=dto.getPs()&&"T01".equals(dto.getPs())){
//			dto.setAppAvalibleCash((String) (result2.get("T_0")));
//			dto.setAppAvalibleCash1((String) result2.get("T_1"));
//		}else{
//			dto.setAppAssetPosition((String)result2.get("position"));
//		}

		dto.setProjectType(SysDataUtil.getNameFromDataDict(DictCode.项目类一级分类, dto.getProjectType()));
		dto.setType9(dto.getType9Name());
		dto.setType10(dto.getType10Name());
		dto.setType11(dto.getType11Name());
		dto.setPs(SysDataUtil.getNameFromDataDict(DictCode.交易方向, dto.getPs()));
		dto.setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种, dto.getCcy()));
		dto.setIspayUnpaidint(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getProjectType()));
		dto.setSettleSpeed(SysDataUtil.getNameFromDataDict(DictCode.清算速度, dto.getSettleSpeed()));
//设置实付金额
		dto.setFpriceAmt(dto.getTradeAmt());

		//指令信息
		for (BussTrdTransferOrderDto vo : dto.getBussTrdTransferOrder()) {
			vo.setCcy(vo.getCcyText());
			vo.setPayType(vo.getPayTypeText());
			if(null!=vo.getIsNeedSend()&&("Y".equals(vo.getIsNeedSend())||"N".equals(vo.getIsNeedSend()))){
				vo.setIsNeedSend(SysDataUtil.getNameFromDataDict(DictCode.是否, vo.getIsNeedSend()));
			}
//			vo.setInOutBank(SysDataUtil.getNameFromDataDict(DictCode.资产来源,vo.getInOutBank()));
		}
		
		//风险试算信息
		MobileUtils.dealRiskTrialRetDto(dto.getRiskTrialRetDto());

		//    	交易对手
		if(dto.getTrdInvestPersonInfo().getCounterId()!=null) {
			dto.getTrdInvestPersonInfo().setCounterId(mobileApprovalMapper.getCustomerName1(dto.getTrdInvestPersonInfo().getCounterId()));
		}

		//卖出撤销,卖出，买入，买入撤销不显示 资产类型
		//String actionType = mobileApprovalMapper.selectActionType(dto.getWorkflowId());
		if("买入".equals(dto.getPs())||"卖出".equals(dto.getPs())){
			dto.setProjectType(null);
		}

		//组装下一审核人及操作类型信息
	    ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
	    todoDto.setBusinessData(dto);
	
	//		        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
	    return MobileUtils.Objetc2Json("000",todoDto,"");
	}

}
