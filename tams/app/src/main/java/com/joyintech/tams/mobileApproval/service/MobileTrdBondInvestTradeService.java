/**
 * <b>文件名：</b>MobilePtlManagementInfoService<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 * Copyright(c) 2020 Joyin Tech,LTD. All Rights Reserved.
 */
package com.joyintech.tams.mobileApproval.service;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.trade.controller.BussTrdAvailableCashQueryController;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeDto;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>方法：</b>MobileTrdBondInvestTradeService<br>
 * <b>描述：</b>债券二级交易<br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 */
@Service
public class MobileTrdBondInvestTradeService {

    @Autowired
    private MobileApprovalService mobileApprovalService;
    
    @Autowired
    private MobileApprovalMapper mobileApprovalMapper;

	@Autowired
	private BussTrdAvailableCashQueryController bussTrdAvailableCashQueryController;
    public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
    	TrdBondInvestTradeDto dto = JsonUtils.json2Object(businessData, TrdBondInvestTradeDto.class);

    	String actionType = mobileApprovalMapper.selectActionType(dto.getWorkflowId());

    	//银行间与交易场所不显示本方券可用持仓和融入券可用持仓
		if(null!=dto.getTradeMarket()&&(("01".equals(dto.getTradeMarket()))||("03".equals(dto.getTradeMarket()))||("04".equals(dto.getTradeMarket())))){
			dto.setAssetOwnAvailablePosition(null);
			dto.setAssetIntegrateAvailablePosition(null);
		}

		//组合T日可用现金
		//查询组合T日,T+1日可用现金,资产可用持仓


//		Map<String, Object> param2 = new HashMap<>();
//		param2.put("tradeDate",DateUtil.format(dto.getTradeDate(),DateUtil.DEFAULT_DATE_FORMAT_EN));
//		param2.put("calendarId",dto.getCalendarId());
//		param2.put("ccy",dto.getCcy());
//		param2.put("acctTypeList", DictCodeListExt1.资金账户类型_产品托管户.getValue()+ "," +DictCodeListExt1.资金账户类型_券商资金账户.getValue());
//		param2.put("finprodId",dto.getFinprodId());
//		param2.put("portfolioId",dto.getPortfolioId());
//
//		Map  result2 = new HashMap<String, BigDecimal>();
//		result2 = bussTrdAvailableCashQueryController.getAvailableCash(param2);
		//当交易方向为买入的时候赋值组合T日，

    if(null!=dto.getBussTrdAvailableCashQuery()){
			if (null != dto.getPs() && "T01".equals(dto.getPs())) {
//				dto.getBussTrdAvailableCashQuery().setAvalibleCash(new BigDecimal((result2.get("T_0")).toString()));
//				dto.getBussTrdAvailableCashQuery().setAvalibleCashT1(new BigDecimal((result2.get("T_1")).toString()));
				dto.getBussTrdAvailableCashQuery().setAssetPosition(null);
			} else {
				dto.getBussTrdAvailableCashQuery().setAvalibleCash(null);
				dto.getBussTrdAvailableCashQuery().setAvalibleCashT1(null);
//				dto.getBussTrdAvailableCashQuery().setAssetPosition(new BigDecimal((result2.get("position")).toString()));
			}


		}

//到期收益率*100
		dto.setYield(new BigDecimal(100).multiply(dto.getYield()));

		 //撤销买入查询资产二级三级四级分类
		if(StringUtil.isNotEmpty(dto.getPs())&&"T01".equals(dto.getPs())&&"revoke".equals(actionType)){
			Map param = new HashMap<>();
			//param.put("FINPROD_TYPE_TG", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
			param.put("finprodId",dto.getFinprodId());

			List<SelectOptionDto> list = mobileApprovalMapper.selectAssetType1(param);
			if(null!=list&&list.size()>0){
				HashMap result = (HashMap) list.get(0).getObject();
				dto.setType9((String)result.get("type9"));
				dto.setType10((String)result.get("type10"));
				dto.setType11((String)result.get("type11"));
			}

		}
    	
//		2、处理数据字典
//		交易场所
    	dto.setTradeMarket(SysDataUtil.getNameFromDataDict(DictCode.交易场所, dto.getTradeMarket()));
//		交易方向
    	dto.setPs(SysDataUtil.getNameFromDataDict(DictCode.交易方向, dto.getPs()));
//		交易市场
    	dto.setTradeMarketMode(SysDataUtil.getNameFromDataDict(DictCode.交易市场, dto.getTradeMarketMode()));
//		交易平台
    	dto.setTradePlat(SysDataUtil.getNameFromDataDict(DictCode.交易平台, dto.getTradePlat()));
//		投资目的
    	dto.setInvAim(SysDataUtil.getNameFromDataDict(DictCode.投资目的, dto.getInvAim()));
//		转仓投资目的
    	dto.setTurnInvAim(SysDataUtil.getNameFromDataDict(DictCode.投资目的, dto.getTurnInvAim()));
    	//是否发送指令
		if (null!=dto.getBussTrdTransferOrder()) {
			for (BussTrdTransferOrderDto item : dto.getBussTrdTransferOrder()){
				item.setIsNeedSend(SysDataUtil.getNameFromDataDict(DictCode.是否, item.getIsNeedSend()));

				item.setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种, item.getCcy()));
		}}


//    	交易对手
		if(null!=dto.getTrdInvestPersonInfo()&&dto.getTrdInvestPersonInfo().getCounterId()!=null) {
			dto.getTrdInvestPersonInfo().setCounterId(mobileApprovalMapper.getCustomerName1(dto.getTrdInvestPersonInfo().getCounterId()));
		}

		if(dto.getCounterId()!=null) {
			dto.setCounterId(mobileApprovalMapper.getCustomerName1(dto.getCounterId()));
		}




//		资产二级分类
    	dto.setType9(SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, dto.getType9()));
//		资产三级分类
    	dto.setType10(SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类, dto.getType10()));
//		资产四级分类
    	dto.setType11(SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类, dto.getType11()));

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
    		dto.getTrdInvestPersonInfo().setCounterId(mobileApprovalMapper.getCustomerName(dto.getTrdInvestPersonInfo().getCounterId()));
    	}
    	



    	
        //组装下一审核人及操作类型信息
        ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
        todoDto.setBusinessData(dto);


		if (null!=dto.getBussTrdTransferOrder()) {
			for (BussTrdTransferOrderDto item : dto.getBussTrdTransferOrder())
				item.setIsNeedSend(SysDataUtil.getNameFromDataDict(DictCode.是否, item.getIsNeedSend()));
		}



//        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
        return MobileUtils.Objetc2Json("000",todoDto,"");
    }
}
