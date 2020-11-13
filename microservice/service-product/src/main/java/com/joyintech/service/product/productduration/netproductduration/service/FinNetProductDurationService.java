/*
 * 文件名：FinNetProductDurationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zqz
 * 修改时间：2019年1月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.productduration.netproductduration.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.productduration.netproductduration.dao.FinNetProductDurationMapper;
import com.joyintech.service.product.productduration.netproductduration.dto.FinCashPlanDto;
import com.joyintech.service.product.productduration.netproductduration.dto.FinNetProductDurationDto;
import com.joyintech.service.product.productduration.netproductduration.dto.FinNetProductDurationLayerDto;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.dao.PrdSaleParamMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.entity.BokPositionPrd;
import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinFloatCalcRule;
import com.joyintech.tams.common.base.entity.FinFloatCalcRuleDetail;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.PrdSaleParam;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zqz
 * @version 1.0 2019年1月15日
 * @see FinNetProductDurationService
 * @since 1.0
 */
@Service("backFinNetProductDurationService")
public class FinNetProductDurationService extends BaseService<FinNetProductDurationDto> {

    @Autowired
    private FinNetProductDurationMapper finNetProductDurationMapper;

    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;

    @Autowired
    private FinProductMapper finProductMapper;
    
    @Autowired
    private PrdSaleParamMapper prdSaleParamMapper;
    @Autowired
    private FinProductAddMapper finProductAddMapper;
    

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodMarketIdList(Map<String, Object> map) {
        return finNetProductDurationMapper.getFinprodMarketIdList(map);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public FinNetProductDurationDto getNetValueInfo(Map<String, Object> map) {
        return finNetProductDurationMapper.getNetValueInfo(map);
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal getTdyBuyShareAmt(Map<String, Object> map) {
        return finNetProductDurationMapper.getTdyBuyShareAmt(map);
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Long getPurSpeed(String finprodId) {
        return finProductAddMapper.selectByPrimaryKey(finprodId).getPurSpeed();
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal getAfterTdySellShareAmt(Map<String, Object> map) {
        return finNetProductDurationMapper.getAfterTdySellShareAmt(map);
    }
    /**
     * 查询净值化存续期信息
     * @param map
     * @return
     */
	public FinNetProductDurationDto selectInfo(Map<String, Object> map) {
		if (StringUtil.isNotEmpty(map.get("tradeId"))) {
			TrdFinProductDealTotal total = trdFinProductDealTotalMapper.selectByPrimaryKey((String) map.get("tradeId"));
			if (total == null) {
				return null;
			}
			map.put("dealType", total.getPs());
			FinProduct product = finProductMapper.selectByPrimaryKey(total.getFinprodId());
			if (product == null) {
				return null;
			}
			map.put("profitType", product.getProfitType());
			map.put("finprodId", total.getFinprodId());
			FinNetProductDurationDto dto = finNetProductDurationMapper.selectInfo(map);
			dto.setRedFeeLc(total.getFeeAmtPay());
            dto.setRedPurFeeHd(total.getFeeAmtUnpay());
            
			dto.setRedFeeLc03(total.getFeeAmtPay());
			
            dto.setRedPurFeeHd03(total.getFeeAmtUnpay()==null?BigDecimal.ZERO:total.getFeeAmtUnpay());
			return dto;
		}
		return null;
	}

	public FinCashPlanDto queryBonusRule(Map<String, Object> map) {
		return finNetProductDurationMapper.queryBonusRule(map);
	}

	public List<TrdFinProductDealTotal> queryAfterTrdFinProductDealTotal(FinNetProductDurationDto dto) {
		return finNetProductDurationMapper.queryAfterTrdFinProductDealTotal(dto);
	}

	public BokPositionPrd queryMaxDateBokPositionPrd(FinNetProductDurationDto dto) {
		return finNetProductDurationMapper.queryMaxDateBokPositionPrd(dto);
	}

	public List<TrdFinProductDealTotal> queryAfterAssetTrdFinProductDealTotal(FinNetProductDurationDto dto) {
		return finNetProductDurationMapper.queryAfterAssetTrdFinProductDealTotal(dto);
	}
	/**
	 * add by dingjunfeng 产品在做提前终止和产品到期之前 需要判断资产有没有全部处置
	 * @param dto
	 * @return
	 */
	public Integer queryMaxDatePtlSecPosition(FinNetProductDurationDto dto){
		return finNetProductDurationMapper.queryMaxDatePtlSecPosition(dto);
	}
	 /**
     * 查询非收益型产品除了认购是否还存在其他有效存续期交易
     * add by wm 
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Integer queryIsExistSubTrade(Map<String, Object> map) {
    	map.put("JYFX_CPRG", DictCodeList.交易方向_产品认购.getValue());
    	map.put("JYFX_CPCQDJ", DictCodeList.交易方向_产品除权登记.getValue());
    	map.put("JYFX_CPFF", DictCodeList.交易方向_产品付费.getValue());
    	map.put("SYLX_SYX", DictCodeList.收益类型_收益型.getValue());
    	map.put("FINPROD_TYPE", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
    	map.put("isCancel", DictCodeList.是否_否.getValue());
        return finNetProductDurationMapper.queryIsExistSubTrade(map);
    }
    /**
     * 根据金融产品代码查询分层金额明细
     * @param map 参数
     * @return 分层金额明细
     */
    public List<FinNetProductDurationLayerDto> queryLayAmtDetailByFinprodId(Map<String, Object> map){
        return finNetProductDurationMapper.queryLayAmtDetailByFinprodId(map);
    }
    /**
     * 根据金融产品代码查询分层金额明细
     * @param bussId 业务编号
     * @return 分层金额明细
     */
    public List<FinNetProductDurationLayerDto> queryLayAmtDetailByBussId(Map<String, Object> map){
        return finNetProductDurationMapper.queryLayAmtDetailByBussId(map);
    }
    /**
     *  获取货币型产品分红方式
     *  
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<String> getFinprodPayType(Map<String, Object> map) {
        return finNetProductDurationMapper.getFinprodPayType(map);
    }
    
	public PrdSaleParam queryFinProductInfo(Map<String, Object> map){
		return prdSaleParamMapper.selectByPrimaryKey((String)map.get("finprodId"));
	}
	/*
     * 获取现金流信息
     */
	public Map<String, Object> getFinCashInfo(Map<String, Object> map){
		//判断vDate是否为Long   需转成date
		if(StringUtil.isNotEmpty(map.get("vDate"))&&Long.class.isAssignableFrom(map.get("vDate").getClass())) {
			Date date = new Date(Long.valueOf(map.get("vDate").toString()));
			map.put("vDate", date);
		}
		Map<String, Object> cashMap = new HashMap<String, Object>();
		//产品id、分支序号、现金流一级类型 费用、现金流二级类型 浮动管理费 查询现金流信息
		List<FinCashInfo> finCashInfoList = finNetProductDurationMapper.getFinCashInfo(map);
		if(finCashInfoList==null || finCashInfoList.size() == 0)
			return null;
		cashMap.put("finCashInfo", finCashInfoList.get(0));
		//期限现金流id
		map.put("timeCashId", finCashInfoList.get(0).getCashId());
		//通过期限现金流id、确认日获取现金流计算规则
		List<FinCashCalcRule> finCashCalcRuleList = finNetProductDurationMapper.getFinCashCalcRule(map);
		if(finCashCalcRuleList==null || finCashCalcRuleList.size() == 0) {
			cashMap.put("finCashCalcRule", null);
		}
		else {
			cashMap.put("finCashCalcRule", finCashCalcRuleList.get(0));
		}
		//通过期限现金流id、确认日获取业绩报酬计算规则	
		List<FinFloatCalcRule> finFloatCalcRuleList = finNetProductDurationMapper.getFinFloatCalcRule(map);
		if(finFloatCalcRuleList==null || finFloatCalcRuleList.size() == 0) {
			cashMap.put("finFloatCalcRule", null);
		}
		else {
			cashMap.put("finFloatCalcRule", finFloatCalcRuleList.get(0));
		}
		//通过期限现金流id、确认日获取业绩报酬计算明细
		List<FinFloatCalcRuleDetail> finFloatCalcRuleDetailList = finNetProductDurationMapper.getFinFloatCalcRuleDetail(map);
		if(finFloatCalcRuleDetailList==null || finFloatCalcRuleDetailList.size() == 0) {
			cashMap.put("finFloatCalcRuleDetail", null);
		}
		else {
			cashMap.put("finFloatCalcRuleDetail", finFloatCalcRuleDetailList.get(0));
		}
		//通过期限产品id、branch、业绩基准类型、生效日期查询业绩基准数据
		map.put("finBaseRuleType", map.get("baseRuleType01"));		//业绩基准类型   产品纰漏 01
		List<FinBaseRuleInfo> finBaseRuleInfoList = finNetProductDurationMapper.getFinBaseRuleInfo(map);
		if(finBaseRuleInfoList==null || finBaseRuleInfoList.size() == 0) {
			cashMap.put("finBaseRuleInfo01", null);		//业绩基准类型为01产品纰漏的业绩基准数据
		}
		else {
			cashMap.put("finBaseRuleInfo01", finBaseRuleInfoList.get(0));
		}
		//通过期限产品id、branch、业绩基准类型、生效日期查询业绩基准数据
		map.put("finBaseRuleType", map.get("baseRuleType02"));		//业绩基准类型   浮动管理费 02
		finBaseRuleInfoList = finNetProductDurationMapper.getFinBaseRuleInfo(map);
		if(finBaseRuleInfoList==null || finBaseRuleInfoList.size() == 0) {
			cashMap.put("finBaseRuleInfo02", null);		//业绩基准类型为02浮动管理费的业绩基准数据
		}
		else {
			cashMap.put("finBaseRuleInfo02", finBaseRuleInfoList.get(0));
		}
		return cashMap;
	}
	/**
     * 根据金融产品代码删除金融产品标的拓展表（FinTradeProductAdd）
     * @param 
     * @return 1
     */
	public int deleteFinTradeProductAddByFinprodId(Map<String, Object> map){
		return finNetProductDurationMapper.deleteFinTradeProductAddByFinprodId(map);
	}
	/**
     * 根据金融产品代码删除业绩报酬计算规则(FIN_FLOAT_CALC_RULE)	
     * @param 
     * @return 1
     */
	public int deleteFinFloatCalcRuleByFinprodId(Map<String, Object> map){
		//判断vDate是否为Long   需转成date
		if(StringUtil.isNotEmpty(map.get("vDate"))&&Long.class.isAssignableFrom(map.get("vDate").getClass())) {
			Date date = new Date(Long.valueOf(map.get("vDate").toString()));
			map.put("vDate", date);
		}
		return finNetProductDurationMapper.deleteFinFloatCalcRuleByFinprodId(map);
	}
	/**
     * 根据金融产品代码删除业绩报酬计算规则明细(FIN_FLOAT_CALC_RULE_DETAIL)	
     * @param 
     * @return 1
     */
	public int deleteFinFloatCalcRuleDetailByFinprodId(Map<String, Object> map){
		//判断vDate是否为Long   需转成date
		if(StringUtil.isNotEmpty(map.get("vDate"))&&Long.class.isAssignableFrom(map.get("vDate").getClass())) {
			Date date = new Date(Long.valueOf(map.get("vDate").toString()));
			map.put("vDate", date);
		}
		return finNetProductDurationMapper.deleteFinFloatCalcRuleDetailByFinprodId(map);
	}
	/**
     * 删除业绩基准表数据(FIN_BASE_RULE_INFO)		
     * @param 
     * @return 1
     */
	public int deleteFinBaseRuleInfoByFinprodId(Map<String, Object> map){
		//判断vDate是否为Long   需转成date
		if(StringUtil.isNotEmpty(map.get("vDate"))&&Long.class.isAssignableFrom(map.get("vDate").getClass())) {
			Date date = new Date(Long.valueOf(map.get("vDate").toString()));
			map.put("vDate", date);
		}
		return finNetProductDurationMapper.deleteFinBaseRuleInfoByFinprodId(map);
	}
	/**
     * 获取期限值
     * @param 
     * @return 
     */
	public List<SelectOptionDto> getTermDaysList(Map<String, Object> map){
		return finNetProductDurationMapper.getTermDaysList(map);
	}
	/**
     * 获取期次信息
     * @param 
     * @return 
     */
	public List<SelectOptionDto> getPeriodsList(Map<String, Object> map){
		//判断vDate是否为Long   需转成date
		if(StringUtil.isNotEmpty(map.get("vDate"))&&Long.class.isAssignableFrom(map.get("vDate").getClass())) {
			Date date = new Date(Long.valueOf(map.get("vDate").toString()));
			map.put("vDate", date);
		}
		return finNetProductDurationMapper.getPeriodsList(map);
	}
	/**
     * 存续期新建产品赎回画面期次信息查询
     * @param 
     * @return 
     */
	public List<FinNetProductDurationDto> getPeriodInfoList(Map<String, Object> map){
		//判断vDate是否为Long   需转成date
		if(StringUtil.isNotEmpty(map.get("netValueDate"))&&Long.class.isAssignableFrom(map.get("netValueDate").getClass())) {
			Date date = new Date(Long.valueOf(map.get("netValueDate").toString()));
			map.put("netValueDate", date);
		}
		//判断cDate是否为Long   需转成date
		if(StringUtil.isNotEmpty(map.get("cDate"))&&Long.class.isAssignableFrom(map.get("cDate").getClass())) {
			Date date = new Date(Long.valueOf(map.get("cDate").toString()));
			map.put("cDate", date);
		}
		if(StringUtil.isNotEmpty(map.get("confirmDate"))) {
			map.put("confirmDate", DateUtil.parse(map.get("confirmDate").toString()));
		}
		List<FinNetProductDurationDto> list = finNetProductDurationMapper.getPeriodInfoList(map);
		//通过期次结束日，产品代码，Breach获取净值日，单位净值
		for(FinNetProductDurationDto finNetProductDurationDto : list) {
			Map<String, Object> requestMap = new HashMap<>();
			requestMap.put("finprodId", map.get("finprodId"));	//产品代码
			requestMap.put("cDate", finNetProductDurationDto.getmDate());	//到期日
			requestMap.put("branch", 0L);	//到期日
			BokPositionPrd bokPositionPrd = finNetProductDurationMapper.getBokPositionPrd(requestMap);
			if(StringUtil.isNotEmpty(bokPositionPrd)){
				if(DictCodeList.交易方向_产品赎回.getValue().equals(map.get("dealType"))
	                    || DictCodeList.交易方向_产品提前赎回.getValue().equals(map.get("dealType"))
	                    || DictCodeList.交易方向_产品违约赎回.getValue().equals(map.get("dealType"))) {
					finNetProductDurationDto.setRedNetValueDate(bokPositionPrd.getCdate());
					finNetProductDurationDto.setRedUnitNetValue(bokPositionPrd.getTdyUnitValue());
				}
				else if(DictCodeList.交易方向_产品到期.getValue().equals(map.get("dealType"))
	                    || DictCodeList.交易方向_提前终止.getValue().equals(map.get("dealType"))) {
					finNetProductDurationDto.setExpireNetValueDate(bokPositionPrd.getCdate());
					finNetProductDurationDto.setExpireUnitNetValue(bokPositionPrd.getTdyUnitValue());
				}
				else {
					finNetProductDurationDto.setAddReduceNetValueDate(bokPositionPrd.getCdate());
					finNetProductDurationDto.setAddReduceUnitNetValue(bokPositionPrd.getTdyUnitValue());
				}
			}
		}
		return list;
	}
	/**
     * 存续期新建产品赎回画面期次信息查询(交易中数据)
     * @param 
     * @return 
     */
	public List<FinNetProductDurationDto> getPeriodInfoListIntrade(Map<String, Object> map){
		List<FinNetProductDurationDto> finNetProductDurationList = finNetProductDurationMapper.getPeriodInfoListIntrade(map);
		for(FinNetProductDurationDto finNetProductDurationDto : finNetProductDurationList) {
			map.put("finprodId", finNetProductDurationDto.getFinprodId());	//产品代码
			map.put("branch", finNetProductDurationDto.getBranch());		//分支序号branch
			//查询期次子产品浮动管理费
			TrdProductDeal trdProductDeal = finNetProductDurationMapper.getManeTrdProductDeal(map);
			if(StringUtil.isNotEmpty(trdProductDeal) && StringUtil.isNotEmpty(trdProductDeal.getIntAmt())) {
				if(DictCodeList.交易方向_产品赎回.getValue().equals(map.get("dealType"))
                     || DictCodeList.交易方向_产品提前赎回.getValue().equals(map.get("dealType"))
	                     || DictCodeList.交易方向_产品违约赎回.getValue().equals(map.get("dealType"))) {
					finNetProductDurationDto.setRedManaFreeVal(trdProductDeal.getIntAmt());
				}
				if(DictCodeList.交易方向_产品到期.getValue().equals(map.get("dealType"))
                	|| DictCodeList.交易方向_提前终止.getValue().equals(map.get("dealType"))) {
					finNetProductDurationDto.setExpireManaFreeVal(trdProductDeal.getIntAmt());
				}
			}
			else {
				if(DictCodeList.交易方向_产品赎回.getValue().equals(map.get("dealType"))
	                     || DictCodeList.交易方向_产品提前赎回.getValue().equals(map.get("dealType"))
		                     || DictCodeList.交易方向_产品违约赎回.getValue().equals(map.get("dealType"))) {
						finNetProductDurationDto.setRedManaFreeVal(BigDecimal.ZERO);
					}
					if(DictCodeList.交易方向_产品到期.getValue().equals(map.get("dealType"))
	                	|| DictCodeList.交易方向_提前终止.getValue().equals(map.get("dealType"))) {
						finNetProductDurationDto.setExpireManaFreeVal(BigDecimal.ZERO);
					}
			}
		}
		return finNetProductDurationList;
	}
	/**
     * 通过交易编号获取分支序号
     * @param 
     * @return 
     */
	public TrdProductDeal getTrdProductDealByTradeId(Map<String, Object> map){
		return finNetProductDurationMapper.getTrdProductDealByTradeId(map);
	}
	/**
     * 通过交易编号获取分支序号
     * @param 
     * @return 
     */
	public FinTradeProduct getFinTradeProductByPeriodId(Map<String, Object> map){
		//判断vDate是否为Long   需转成date
				if(StringUtil.isNotEmpty(map.get("vDate"))&&Long.class.getName().equals(map.get("vDate").getClass().getName())) {
					Date date = new Date(Long.valueOf(map.get("vDate").toString()));
					map.put("vDate", date);
				}
				if(StringUtil.isNotEmpty(map.get("mDate"))&&Long.class.getName().equals(map.get("mDate").getClass().getName())) {
					Date date = new Date(Long.valueOf(map.get("mDate").toString()));
					map.put("mDate", date);
				}
				List<FinTradeProduct> list = finNetProductDurationMapper.getFinTradeProductByPeriodId(map);
				if(list == null || list.size() == 0) {
					return null;
				}
				else {
					return list.get(0);
				}
	}
	/**
     * 根据选择的净值日查询单位净值
     * @param 
     * @return 
     */
	public BokPositionPrd getTrdValueByCdate(Map<String, Object> map){
		//判断vDate是否为Long   需转成date
		if(StringUtil.isNotEmpty(map.get("cDate"))&&Long.class.isAssignableFrom(map.get("cDate").getClass())) {
			Date date = new Date(Long.valueOf(map.get("cDate").toString()));
			map.put("cDate", date);
		}
		return finNetProductDurationMapper.getTrdValueByCdate(map);
	}

	public long checkId(String tradeId) {
		// TODO Auto-generated method stub
		return finNetProductDurationMapper.checkId(tradeId);
	}
	/**
     * 查询产品 查询日期最近的净值信息-货币型产品分红专用
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public FinNetProductDurationDto getNetValueInfoForHB(Map<String, Object> map) {
    	FinNetProductDurationDto finNetProductDurationDto = finNetProductDurationMapper.getNetValueInfo(map);
    	//查询当日的已经做的现金分红或者红利再投交易
    	map.put("PS_10", DictCodeList.交易方向_产品红利转投.getValue());
    	map.put("PS_09", DictCodeList.交易方向_产品现金分红.getValue());
    	map.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
    	map.put("IS_CANCEL_N", DictCodeList.是否_否.getValue());
    	BigDecimal payed = finNetProductDurationMapper.queryBounsAmtForHB(map);
    	//当天未付利润为前一天头寸中应付未付金额-现金分红-红利再投金额
    	if(finNetProductDurationDto!=null&&payed!=null){
    		finNetProductDurationDto.setPurDealAmt(ArithmeticUtil.subtract(finNetProductDurationDto.getPurDealAmt(), payed));
    	}
        return finNetProductDurationDto;
    }
}
