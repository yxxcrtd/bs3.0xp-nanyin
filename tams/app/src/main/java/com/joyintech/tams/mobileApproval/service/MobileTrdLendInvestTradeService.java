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
import com.joyintech.tams.component.business.trade.controller.BussTrdAvailableCashQueryController;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.trade.lend.dto.TrdLendInvestTradeDto;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>方法：</b>MobileTrdLendInvestTradeService<br>
 * <b>描述：</b>拆借交易<br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 */
@Service
public class MobileTrdLendInvestTradeService {

    @Autowired
    private MobileApprovalService mobileApprovalService;
    
    @Autowired
    private MobileApprovalMapper mobileApprovalMapper;

    @Autowired
	private BussTrdAvailableCashQueryController bussTrdAvailableCashQueryController;


    public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
    	TrdLendInvestTradeDto dto = JsonUtils.json2Object(businessData, TrdLendInvestTradeDto.class);


    	//当交易方向为拆出，交易类型为普通载体时查询组合T日，T+1日可用现金
//		if(null!=dto.getBusiPs()&&"02".equals(dto.getBusiPs())&&null!=dto.getPaBussType()&&"L01".equals(dto.getPaBussType())){
//			Map<String, Object> param = new HashMap<>();
//			param.put("portfolioId",dto.getPortfolioId());
//			param.put("calendarId",dto.getFinTradeProduct().getCalendarId());
//
//			param.put("ccy", "CNY");
//			param.put("tradeDate", DateUtil.format(dto.getTradeDate(),DateUtil.DEFAULT_DATETIME_FORMAT));
//			param.put("acctTypeList", DictCodeListExt1.资金账户类型_产品托管户.getValue()+","+DictCodeListExt1.资金账户类型_券商资金账户.getValue());
//			Map  result = bussTrdAvailableCashQueryController.getAvailableCash(param);
//			dto.setAppAvalibleCash((String) result.get("T_0"));
//			dto.setAppAvalibleCash1((String) result.get("T_1"));
//
//
//
//		}

    	
//		2、处理数据字典
//		交易方向
    	dto.setBusiPs(SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, dto.getBusiPs()));
//		拆借类型
    	dto.setPaBussType(SysDataUtil.getNameFromDataDict(DictCode.拆借类型, dto.getPaBussType()));
//		计息基础
    	dto.getFinTradeProduct().setBasis(SysDataUtil.getNameFromDataDict(DictCode.计息基础, dto.getFinTradeProduct().getBasis()));
//    	获取交易日历
		dto.getFinTradeProduct().setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种, dto.getFinTradeProduct().getCcy()));


		if(null!=dto.getBussTrdTransferOrder()) {
			for (BussTrdTransferOrderDto bussTrdTransferOrderDto : dto.getBussTrdTransferOrder()) {
				bussTrdTransferOrderDto.setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种, bussTrdTransferOrderDto.getCcy()));
		 		if(null!=bussTrdTransferOrderDto.getIsNeedSend()&&!(("是".equals(bussTrdTransferOrderDto.getIsNeedSend()))||("否".equals(bussTrdTransferOrderDto.getIsNeedSend())))){
					bussTrdTransferOrderDto.setIsNeedSend(SysDataUtil.getNameFromDataDict(DictCode.是否, bussTrdTransferOrderDto.getIsNeedSend()));
				}

			}
		}

		if(null!=dto.getFinTradeProduct()&&dto.getFinTradeProduct().getCalendarId()!=null) {
    		dto.getFinTradeProduct().setCalendarId(mobileApprovalMapper.getCalendarName(dto.getFinTradeProduct().getCalendarId()));
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
