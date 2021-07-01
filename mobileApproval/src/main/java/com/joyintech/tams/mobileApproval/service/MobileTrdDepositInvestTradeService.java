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
import com.joyintech.tams.invest.trade.trddepositinvesttrade.dto.TrdDepositInvestTrade001Dto;
import com.joyintech.tams.invest.trade.trddepositinvesttrade.service.TrdDepositInvestTrade002Service;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <b>方法：</b>MobileTrdDepositInvestTradeService<br>
 * <b>描述：</b>存款类交易<br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 */
@Service
public class MobileTrdDepositInvestTradeService {

    @Autowired
    private MobileApprovalService mobileApprovalService;
    
    @Autowired
    private MobileApprovalMapper mobileApprovalMapper;


    @Autowired
	private TrdDepositInvestTrade002Service trdDepositInvestTrade002Service;


    public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
    	TrdDepositInvestTrade001Dto dto = JsonUtils.json2Object(businessData, TrdDepositInvestTrade001Dto.class);

    	
//		2、处理数据字典
//		查询组合可用现金T日，T+1日

//		Map<String,Object> param = new HashMap<>();
//		param.put("finprodId",dto.getFinprodId());
//		param.put("vdate", DateUtil.format(dto.getVdate(),DateUtil.DEFAULT_DATETIME_FORMAT));
//		param.put("mdate",DateUtil.format(dto.getMdate(),DateUtil.DEFAULT_DATETIME_FORMAT));
//		param.put("portfolioId",dto.getPortfolioId());
//		param.put("ps",dto.getPs());
//		param.put("chlId",dto.getChlId());
//		param.put("tradeDate",DateUtil.format(dto.getTradeDate(),DateUtil.DEFAULT_DATETIME_FORMAT));
//		param.put("ccy",dto.getCcy());
//
//		List<SuggestDto> result = trdDepositInvestTrade002Service.available(param);
		//交易方向为买入T01时赋值组合T日，T+1日数值
//		if(null!=dto.getPs()&&"T01".equals(dto.getPs())){
//			dto.setAppAccAvailableCashT(MobileUtils.transformTwoDecimalPoint(new BigDecimal(result.get(0).getName())).toString() );
//			dto.setAppAccAvailableCashT1(result.get(1).getName());
//		}else {
//			dto.setAppBottomAssetAmount(result.get(0).getName());
//		}


//		交易方向
    	dto.setPs(SysDataUtil.getNameFromDataDict(DictCode.交易方向, dto.getPs()));
//		计息基础
    	dto.setBasis(SysDataUtil.getNameFromDataDict(DictCode.计息基础, dto.getBasis()));
//		资产二级分类
    	dto.setType9(SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, dto.getType9()));
//		资产三级分类
    	dto.setType10(SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类, dto.getType10()));
//		资产四级分类
    	dto.setType11(SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类, dto.getType11()));


		dto.setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种, dto.getCcy()));
//		 是否发送指令显示为N，应改为否
		if (null!=dto.getBussTrdTransferOrder()) {
			for (BussTrdTransferOrderDto item : dto.getBussTrdTransferOrder())
				item.setIsNeedSend(SysDataUtil.getNameFromDataDict(DictCode.是否, item.getIsNeedSend()));
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
    	if(null!=dto.getTrdInvestPersonInfo002Dto()&&dto.getTrdInvestPersonInfo002Dto().getCounterId()!=null) {
    		dto.getTrdInvestPersonInfo002Dto().setCounterId(mobileApprovalMapper.getCustomerName1(dto.getTrdInvestPersonInfo002Dto().getCounterId()));
    	}
    	

        //组装下一审核人及操作类型信息
        ReturnTodoDto todoDto = mobileApprovalService.getNextWorkFlowInfo(dto.getWorkflowId());
        todoDto.setBusinessData(dto);

//        return JSON.toJSONStringWithDateFormat(dto, DateUtil.DEFAULT_DATETIME_FORMAT);
        return MobileUtils.Objetc2Json("000",todoDto,"");
    }
}
