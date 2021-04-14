/**
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
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.trade.trdrepotrade.dto.TrdRepoTrade002Dto;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <b>方法：</b>MobileTrdRepoTradeService<br>
 * <b>描述：</b>质押式回购交易<br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 */
@Service
public class MobileTrdRepoTradeService {

    @Autowired
    private MobileApprovalService mobileApprovalService;
    
    @Autowired
    private MobileApprovalMapper mobileApprovalMapper;


    public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
    	TrdRepoTrade002Dto dto = JsonUtils.json2Object(businessData, TrdRepoTrade002Dto.class);



//如果是交易场所为银行间则不显示回购类型
		if(null!=dto.getTradeMarket()&&"01".equals(dto.getTradeMarket())){
			dto.setRepoType(null);
			dto.setVpayDate(null);
			dto.setMpayDate(null);
			if("03".equals(dto.getPs())) {
				dto.getBussTrdAvailableCashQuery().setAvalibleCash(null);
				dto.getBussTrdAvailableCashQuery().setAvalibleCashT1(null);
			}
		}else if(null!=dto.getTradeMarket()&&"03".equals(dto.getTradeMarket())) {
			if("03".equals(dto.getPs())) {
				dto.getBussTrdAvailableCashQuery().setAvalibleCash(null);
				dto.getBussTrdAvailableCashQuery().setAvalibleCashT1(null);
			}
		}else if(null!=dto.getTradeMarket()&&"04".equals(dto.getTradeMarket())) {
			if("03".equals(dto.getPs())) {
				dto.getBussTrdAvailableCashQuery().setAvalibleCash(null);
				dto.getBussTrdAvailableCashQuery().setAvalibleCashT1(null);
			}
		}
		//如果是交易场所且为竞价回购 则不显示汇总卷面总额
		if(null!=dto.getTradeMarket()&&("03".equals(dto.getTradeMarket())||"04".equals(dto.getTradeMarket()))){
			if(null!=dto.getRepoType()&&"H02".equals(dto.getRepoType())){
						dto.getBussRepoSec().setTotalPrinAmt(null);
			}
		}



//		2、处理数据字典

//		交易场所
    	dto.setTradeMarket(SysDataUtil.getNameFromDataDict(DictCode.交易场所, dto.getTradeMarket()));
//		交易方向
    	dto.setPs(SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, dto.getPs()));
//		交易市场
    	dto.setTradeMarketMode(SysDataUtil.getNameFromDataDict(DictCode.交易市场, dto.getTradeMarketMode()));
//		交易平台
    	dto.setTradePlat(SysDataUtil.getNameFromDataDict(DictCode.交易平台, dto.getTradePlat()));
//    	获取交易日历
    	if(dto.getCalendarId()!=null) {
    		dto.setCalendarId(mobileApprovalMapper.getCalendarName(dto.getCalendarId()));
    	}
//		清算速度
    	dto.setSettleSpeedStr(SysDataUtil.getNameFromDataDict(DictCode.清算速度, dto.getSettleSpeedStr()));
//		回购类型
    	dto.setRepoType(SysDataUtil.getNameFromDataDict(DictCode.回购类型, dto.getRepoType()));
//		期限品种
    	dto.setTermTypeReback(SysDataUtil.getNameFromDataDict(DictCode.回购期限品种, dto.getTermTypeReback()));
//		计息基础
    	dto.setBasis(SysDataUtil.getNameFromDataDict(DictCode.计息基础, dto.getBasis()));

		dto.setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种, dto.getCcy()));


//       指令
		if (null!=dto.getBussTrdTransferOrder()) {
			for (BussTrdTransferOrderDto item : dto.getBussTrdTransferOrder()) {
				item.setIsNeedSend(SysDataUtil.getNameFromDataDict(DictCode.是否, item.getIsNeedSend()));
				item.setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种, item.getCcy()));
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

		if(dto.getSecAcctId()!=null) {
			dto.setSecAcctId(mobileApprovalMapper.getSecAcctName(dto.getSecAcctId()));
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
