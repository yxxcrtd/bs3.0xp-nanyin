package com.joyintech.tams.mobileApproval.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.component.business.trade.BussTrdAvailableCashQueryService;
import com.joyintech.tams.component.business.trade.controller.BussTrdAvailableCashQueryController;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.trade.trdplanrateinvesttrade.controller.TrdPlanRateInvestTrade002Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.trade.trdplanrateinvesttrade.dto.TrdPlanRateInvestTradeDto;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MobileTrdPlanRateInvestTradeService {

	@Autowired
	BussTrdAvailableCashQueryController bussTrdAvailableCashQueryController;

	@Autowired
	TrdPlanRateInvestTrade002Controller trdPlanRateInvestTrade002Controller;

	@Autowired(required = false)
	private BussTrdAvailableCashQueryService bussTrdAvailableCashQueryService;

	@Autowired(required = false)
	private DateCalService dateCalService;

	@Autowired(required = false)
	private BussCommonMapperDelegateService bussCommonMapperDelegateService;

	@Autowired
    private MobileApprovalService mobileApprovalService;
	@Autowired
	private MobileApprovalMapper mobileApprovalMapper;

	public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
		TrdPlanRateInvestTradeDto dto = JsonUtils.json2Object(businessData, TrdPlanRateInvestTradeDto.class);

		String actionType = mobileApprovalMapper.selectActionType(dto.getWorkflowId());
        //2、处理数据字典

		//设置清算速度
		if(2==dto.getSettleSpeed()){
			dto.setMySettleSpeed("02");
		}else if(1==dto.getSettleSpeed()){
			dto.setMySettleSpeed("01");
		}else if(3==dto.getSettleSpeed()){
			dto.setMySettleSpeed("03");
		}
		dto.setMySettleSpeed(SysDataUtil.getNameFromDataDict(DictCode.清算速度, dto.getMySettleSpeed()));

//查询组合T日,T+1日可用现金,资产可用持仓
//		Map<String,Object> param1 = new HashMap<>();
//		param1.put("tradeDate",DateUtil.format(dto.getTradeDate(),DateUtil.DEFAULT_DATETIME_FORMAT));
//		param1.put("finprodId",dto.getFinprodId());
//		param1.put("settleSpeed",dto.getSettleSpeed());
//
//		Map<String,String> result1 = trdPlanRateInvestTrade002Controller.settleDateCalcu(param1);
//		String calendarId = result1.get("calendarId");
//
//		Map<String, Object> param2 = new HashMap<>();
//		param2.put("tradeDate",DateUtil.format(dto.getTradeDate(),DateUtil.DEFAULT_DATE_FORMAT_EN));
//		param2.put("calendarId",calendarId);
//		param2.put("ccy",dto.getCcy());
//		param2.put("acctTypeList",DictCodeListExt1.资金账户类型_产品托管户.getValue()+ "," +DictCodeListExt1.资金账户类型_券商资金账户.getValue());
//		param2.put("finprodId",dto.getFinprodId());
//		param2.put("portfolioId",dto.getPortfolioId());
//
//		Map  result2 = new HashMap<String, BigDecimal>();
//		result2 = bussTrdAvailableCashQueryController.getAvailableCash(param2);
//		//当交易方向为买入的时候赋值组合T日，
//
//
//		if(null!=dto.getPs()&&"T01".equals(dto.getPs())){
//			dto.setAppAccAvailableCashStr((String) (result2.get("T_0")));
//			dto.setAppAccAvailableCashT1Str((String) result2.get("T_1"));
//		}else{
//			dto.setAppAssetPositionStr((String)result2.get("position"));
//		}

		//dto.setSettleSpeed(SysDataUtil.getNameFromDataDict(DictCode.清算速度, dto.getSettleSpeed()));
		dto.setIspayUnpaidint(SysDataUtil.getNameFromDataDict(DictCode.是否, dto.getIspayUnpaidint()));
		dto.setCounterId(mobileApprovalMapper.getCounterName(dto.getCounterId()));;

		dto.setType9(dto.getType9Str());
		dto.setType10(dto.getType10Str());
		dto.setType11(dto.getType11Str());
		dto.setFinprodType(dto.getFinprodTypeStr());
		dto.setTradeStatus(dto.getTradeStatusStr());
		dto.setCcy(dto.getCcyStr());
		dto.setPs(dto.getPsStr());


		dto.setPrinAmt(MobileUtils.transformTwoDecimalPoint(dto.getPrinAmt()));

		//指令信息
		for (BussTrdTransferOrderDto vo : dto.getBussTrdTransferOrder()) {
			vo.setCcy(vo.getCcyText());
			vo.setPayType(vo.getPayTypeText());
			if(null!=dto.getPs()&&("买入".equals(dto.getPs())||"卖出".equals(dto.getPs()))){
				vo.setIsNeedSend(SysDataUtil.getNameFromDataDict(DictCode.是否,vo.getIsNeedSend()));
			}
//			vo.setIsNeedSend(SysDataUtil.getNameFromDataDict(DictCode.是否,vo.getIsNeedSend()));
	//		vo.setInOutBank(SysDataUtil.getNameFromDataDict(DictCode.资产来源,vo.getInOutBank()));
		}
//卖出且撤销不显示资产类型
		if(StringUtil.isNotEmpty(actionType)&&"revoke".equals(actionType)&&"卖出".equals(dto.getPs()))
			dto.setFinprodType(null);


		//交易对手


		if(null!=dto.getTrdInvestPersonInfo002Dto()&&dto.getTrdInvestPersonInfo002Dto().getCounterId()!=null) {
			dto.getTrdInvestPersonInfo002Dto().setCounterId(mobileApprovalMapper.getCustomerName1(dto.getTrdInvestPersonInfo002Dto().getCounterId()));
		}


		//风险试算信息
		MobileUtils.dealRiskTrialRetDto(dto.getRiskTrialRetDto());





		//组装下一审核人及操作类型信息
        ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
        todoDto.setBusinessData(dto);

//        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
        return MobileUtils.Objetc2Json("000",todoDto,"");
    }
}
