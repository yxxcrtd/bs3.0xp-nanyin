/**
 * <b>文件名：</b>MobilePtlManagementInfoService<br>
 * <b>描述：</b><br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 * Copyright(c) 2020 Joyin Tech,LTD. All Rights Reserved.
 */
package com.joyintech.tams.mobileApproval.service;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.invest.trade.fund.dto.TrdFundInvestTradeDto;
import com.joyintech.tams.invest.trade.fund.service.TrdFundInvestTrade002Service;
import com.joyintech.tams.mobileApproval.dao.MobileApprovalMapper;
import com.joyintech.tams.mobileApproval.dto.ReturnTodoDto;
import com.joyintech.tams.mobileApproval.util.MobileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>方法：</b>MobileTrdFundInvestTradeService<br>
 * <b>描述：</b>公募基金交易<br>
 * <b>作者：</b>Administrator<br>
 * <b>时间：</b>2020/5/7 14:21<br>
 */
@Service
public class MobileTrdFundInvestTradeService {

    @Autowired
    private MobileApprovalService mobileApprovalService;
    
    @Autowired
    private MobileApprovalMapper mobileApprovalMapper;
	@Autowired
	private TrdFundInvestTrade002Service trdFundInvestTrade002Service;

    public String getWorkFlowInfo(String businessData) throws Exception {
        //1、获取业务数据
    	TrdFundInvestTradeDto dto = JsonUtils.json2Object(businessData, TrdFundInvestTradeDto.class);


//		2、处理数据字典
		//认购T52不显示单位净值，资产可用持仓，预计到账日
		if(null!=dto.getPs()&&"T52".equals(dto.getPs())){
			dto.setUnitCprice(null) ;
			dto.setAssetAvailablePosition(null);
			dto.setRedCfmDate(null);
		}

		//申购确认T05不显示通道费用，资产可用持仓，预计到账日
		if(null!=dto.getPs()&&"T05".equals(dto.getPs())){
			dto.setChannelAmt(null);
			dto.setAssetAvailablePosition(null);
			dto.setRedCfmDate(null);
		}

		//查询组合可用现金   //交易方向为买入T01，认购T52，申购,申购确认（T05）,认购确认（T18）时显示
//		if((null!=dto.getPs()&&"T18".equals(dto.getPs()))||(null!=dto.getPs()&&"T05".equals(dto.getPs()))||(null!=dto.getPs()&&"T01".equals(dto.getPs()))||(null!=dto.getPs()&&"T04".equals(dto.getPs()))||(null!=dto.getPs()&&"T52".equals(dto.getPs()))) {
//			if (null != dto.getPortfolioId() && null != dto.getCcy() && null != dto.getTradeDate()) {
//				Map<String, Object> param2 = new HashMap<>();
//				param2.put("portfolioId", dto.getPortfolioId());
//				param2.put("ccy", dto.getCcy());
//				param2.put("vdate", DateUtil.format(dto.getTradeDate(), DateUtil.DEFAULT_DATETIME_FORMAT));
//				param2.put("calId", dto.getCalendarId());
//				param2.put("chlId",dto.getChlId());
//				param2.put("secAcctId", dto.getSecManageAcctId());
//
//				Map<String, Object> result2 = trdFundInvestTrade002Service.getProAvailableCash(param2);
//				//赋值组合T+1日可用现金
//				dto.setProAvailableCash1((String) result2.get("cashT1"));
//				//组合T日可用金额
//				dto.setProAvailableCash(new BigDecimal(result2.get("cashT").toString()));
//				if(null!=result2.get("T_2")&&StringUtil.isNotEmpty(result2.get("T_2").toString())){
//					dto.setChannelAvailableCash(new BigDecimal(result2.get("T_2").toString()));
//				}
//			}
//		}




//       交易方向为申购确认（T05），认购确认（T18）,赎回确认(T19)不显示交易日期
		if((null!=dto.getPs()&&"T05".equals(dto.getPs()))||(null!=dto.getPs()&&"T18".equals(dto.getPs()))||(null!=dto.getPs()&&"T19".equals(dto.getPs()))){
			dto.setPurCfmDate(dto.getTradeDate());
			dto.setTradeDate(null);
		}

		//申购确认与认购确认不显示指令信息
		if((null!=dto.getPs()&&"T05".equals(dto.getPs()))||(null!=dto.getPs()&&"T18".equals(dto.getPs()))){
			dto.setBussTrdTransferOrder(null);
		}

		//申购确认（T05）,认购确认（T18）查询通道可用现金
//		if((null!=dto.getPs()&&"T05".equals(dto.getPs())||(null!=dto.getPs()&&"T18".equals(dto.getPs())))){
//			Map param1 = new HashMap();
//			Map result = new HashMap();
//			param1.put("portfolioId", dto.getPortfolioId());
//			param1.put("ccy", dto.getCcy());
//			if (null != dto.getPurCfmDate()) {
//				String PurCfmDate = DateUtil.format(dto.getPurCfmDate(), DateUtil.DEFAULT_DATETIME_FORMAT);
//				param1.put("vdate", PurCfmDate);
//			}
//
//			param1.put("calId", dto.getCalendarId());
//			param1.put("chlId", dto.getChlId());
//			param1.put("secAcctId", dto.getSecManageAcctId());
//			result = trdFundInvestTrade002Service.getProAvailableCash(param1);
//			if(null!=result&&null!=result.get("T_2")&&null==dto.getChannelAvailableCash())
//			dto.setAppChannelAvailableCash((String)result.get("T_2"));
//		}

		//申购确认(T05)，认购确认(T18)，赎回确认（T19）查询资产二三四级分类
		if((null!=dto.getPs()&&"T19".equals(dto.getPs())||(null!=dto.getPs()&&"T05".equals(dto.getPs())||(null!=dto.getPs()&&"T18".equals(dto.getPs()))))){
			  Map param = new HashMap<>();
			  param.put("FINPROD_TYPE_TG", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
			  param.put("finprodMarketId",dto.getFinprodMarketId());
			  List<SelectOptionDto> list = mobileApprovalMapper.selectAssetType(param);
			  HashMap result = (HashMap) list.get(0).getObject();
			  dto.setType9((String)result.get("type9"));
			  dto.setType10((String)result.get("type10"));
			  dto.setType11((String)result.get("type11"));

		}
		//当交易方向为申购确认(T05)，认购确认(T18),赎回确认（T19）时，不显示确认速度
		if(null!=dto.getPs()&&"T05".equals(dto.getPs())||(null!=dto.getPs()&&"T05".equals(dto.getPs()))||(null!=dto.getPs()&&"T19".equals(dto.getPs()))){

			dto.setSettleSpreed(null);
		}

//		交易方向

    	dto.setPs(SysDataUtil.getNameFromDataDict(DictCode.交易方向, dto.getPs()));
//		资产二级分类
    	dto.setType9(SysDataUtil.getNameFromDataDict(DictCode.行内资产二级分类, dto.getType9()));
//		资产三级分类
    	dto.setType10(SysDataUtil.getNameFromDataDict(DictCode.行内资产三级分类, dto.getType10()));
//		资产四级分类
    	dto.setType11(SysDataUtil.getNameFromDataDict(DictCode.行内资产四级分类, dto.getType11()));
//		交易所基金类型
    	dto.setTrmFundType(SysDataUtil.getNameFromDataDict(DictCode.交易所基金类型, dto.getTrmFundType()));
//		交易场所
    	dto.setTradeMarket(SysDataUtil.getNameFromDataDict(DictCode.交易场所, dto.getTradeMarket()));
//      是否发送指令

		dto.setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种, dto.getCcy()));


		if (null!=dto.getBussTrdTransferOrder()) {
			for (BussTrdTransferOrderBase item : dto.getBussTrdTransferOrder()) {
				item.setIsNeedSend(SysDataUtil.getNameFromDataDict(DictCode.是否, item.getIsNeedSend()));
				item.setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种, item.getCcy()));
			}
			}
//申购不显示资产可用持仓
		if(null!=dto.getPs()&&"申购".equals(dto.getPs())){
			dto.setAssetAvailablePosition(null);
			dto.setRedCfmDate(null);
			dto.setCpriceDate(null);
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
