/*
 * 文件名：FinNetProductDurationControl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zqz
 * 修改时间：2019年1月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.productduration.netproductduration.controller;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.productduration.netproductduration.dto.FinCashPlanDto;
import com.joyintech.service.product.productduration.netproductduration.dto.FinNetProductDurationDto;
import com.joyintech.service.product.productduration.netproductduration.dto.FinNetProductDurationLayerDto;
import com.joyintech.service.product.productduration.netproductduration.service.FinNetProductDurationService;
import com.joyintech.tams.common.base.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 净值产品存续期交易录入
 * 〈功能详细描述〉
 * @author zqz
 * @version 1.0 2019年1月15日
 * @see FinNetProductDurationController
 * @since 1.0
 */
@RestController
@RequestMapping("/FinNetProductDuration")
public class FinNetProductDurationController{

    @Autowired
    private FinNetProductDurationService finNetProductDurationService;
    
    /**
     * 产品代码的联想下拉框事件
     *
     * @return 
     * @see
     * @since 1.0
     */
    @PostMapping("getFinprodMarketIdList")
    public List<SelectOptionDto> getFinprodMarketIdList(@RequestBody Map<String,Object> map){
        return finNetProductDurationService.getFinprodMarketIdList(map);
    }
    
    /**
     * 查询净值信息
     * @param map
     * @return
     */
    @PostMapping("getNetValueInfo")
    public FinNetProductDurationDto getNetValueInfo(@RequestBody Map<String,Object> map){
        return finNetProductDurationService.getNetValueInfo(map);
    }
    /**
     * 查询净值信息
     * @param map
     * @return
     */
    @PostMapping("getTdyBuyShareAmt")
    public BigDecimal getTdyBuyShareAmt(@RequestBody Map<String,Object> map){
        return finNetProductDurationService.getTdyBuyShareAmt(map);
    }
    /**
     * 查询净值信息
     * @param map
     * @return
     */
    @PostMapping("getPurSpeed")
    public Long getPurSpeed( @RequestBody String finprodId){
        return finNetProductDurationService.getPurSpeed(finprodId);
    }
    /**
     * 查询净值信息
     * @param map
     * @return
     */
    @PostMapping("getAfterTdySellShareAmt")
    public BigDecimal getAfterTdySellShareAmt(@RequestBody Map<String,Object> map){
        return finNetProductDurationService.getAfterTdySellShareAmt(map);
    }
    
    /**
     * 查询净值化存续期信息
     * @param map
     * @return
     */
    @PostMapping("selectInfo")
    public FinNetProductDurationDto selectInfo(@RequestBody Map<String,Object> map){
        return finNetProductDurationService.selectInfo(map);
    }
    
    @PostMapping("queryBonusRule")
    public FinCashPlanDto queryBonusRule(@RequestBody Map<String,Object> map){
        return finNetProductDurationService.queryBonusRule(map);
    }
    
    @PostMapping("queryAfterTrdFinProductDealTotal")
    public List<TrdFinProductDealTotal> queryAfterTrdFinProductDealTotal(@RequestBody FinNetProductDurationDto dto){
        return finNetProductDurationService.queryAfterTrdFinProductDealTotal(dto);
    }
    
    @PostMapping("queryAfterAssetTrdFinProductDealTotal")
    public List<TrdFinProductDealTotal> queryAfterAssetTrdFinProductDealTotal(@RequestBody FinNetProductDurationDto dto){
        return finNetProductDurationService.queryAfterAssetTrdFinProductDealTotal(dto);
    }
    
    @PostMapping("queryMaxDateBokPositionPrd")
    public BokPositionPrd queryMaxDateBokPositionPrd(@RequestBody FinNetProductDurationDto dto){
        return finNetProductDurationService.queryMaxDateBokPositionPrd(dto);
    }
	/**
	 * add by dingjunfeng 产品在做提前终止和产品到期之前 需要判断资产有没有全部处置
	 * @param dto
	 * @return
	 */
    @PostMapping("queryMaxDatePtlSecPosition")
	public 	Integer queryMaxDatePtlSecPosition(@RequestBody FinNetProductDurationDto dto){
		return finNetProductDurationService.queryMaxDatePtlSecPosition(dto);
	}
    /**
	 * add by dingjunfeng 产品在做提前终止和产品到期之前 需要判断资产有没有全部处置
	 * @param dto
	 * @return
	 */
    @PostMapping("queryIsExistSubTrade")
	public 	Integer queryIsExistSubTrade(@RequestBody Map<String,Object> map){
		return finNetProductDurationService.queryIsExistSubTrade(map);
	}
    /**
     * 根据金融产品代码查询分层金额明细
     * add by wm 2019-06-24
     * @param map 参数
     * @return 分层金额明细
     */
    @PostMapping("queryLayAmtDetailByFinprodId")
    public List<FinNetProductDurationLayerDto> queryLayAmtDetailByFinprodId(@RequestBody Map<String, Object> map){
        return finNetProductDurationService.queryLayAmtDetailByFinprodId(map);
    }
    /**
     * 根据金融产品代码查询分层金额明细
     * add by wm 2019-06-24
     * @param bussId 业务编号
     * @return 分层金额明细
     */
    @PostMapping("queryLayAmtDetailByBussId")
    public List<FinNetProductDurationLayerDto> queryLayAmtDetailByBussId(@RequestBody Map<String, Object> map){
        return finNetProductDurationService.queryLayAmtDetailByBussId(map);
    }
    /**
     * 获取货币型产品分红方式
     *
     * @return 
     * @see
     * @since 1.0
     */
    @PostMapping("getFinprodPayType")
    public List<String> getFinprodPayType(@RequestBody Map<String,Object> map){
        return finNetProductDurationService.getFinprodPayType(map);
    }
    
    @PostMapping("queryFinProductInfo")
	public PrdSaleParam queryFinProductInfo(@RequestBody Map<String, Object> map){
		return finNetProductDurationService.queryFinProductInfo(map);
	}
    /*
     * 获取现金流信息
     */
    @PostMapping("getFinCashInfo")
	public Map<String, Object> getFinCashInfo(@RequestBody Map<String, Object> map){
		return finNetProductDurationService.getFinCashInfo(map);
	}
    /*
     * 删除金融产品代码删除金融产品标的拓展表（FinTradeProductAdd）
     */
    @PostMapping("deleteFinTradeProductAddByFinprodId")
	public int deleteFinTradeProductAddByFinprodId(@RequestBody Map<String, Object> map){
		return finNetProductDurationService.deleteFinTradeProductAddByFinprodId(map);
	}
    /*
     * 删除业绩报酬计算规则(FIN_FLOAT_CALC_RULE)		
     */
    @PostMapping("deleteFinFloatCalcRuleByFinprodId")
	public int deleteFinFloatCalcRuleByFinprodId(@RequestBody Map<String, Object> map){
		return finNetProductDurationService.deleteFinFloatCalcRuleByFinprodId(map);
	}
    /*
     * 删除业绩报酬计算规则明细(FIN_FLOAT_CALC_RULE_DETAIL)		
     */
    @PostMapping("deleteFinFloatCalcRuleDetailByFinprodId")
	public int deleteFinFloatCalcRuleDetailByFinprodId(@RequestBody Map<String, Object> map){
		return finNetProductDurationService.deleteFinFloatCalcRuleDetailByFinprodId(map);
	}
    /*
     * 删除业绩基准表数据(FIN_BASE_RULE_INFO)		
     */
    @PostMapping("deleteFinBaseRuleInfoByFinprodId")
	public int deleteFinBaseRuleInfoByFinprodId(@RequestBody Map<String, Object> map){
		return finNetProductDurationService.deleteFinBaseRuleInfoByFinprodId(map);
	}
    /**
     * 获取期限值
     */
    @PostMapping("getTermDaysList")
	public List<SelectOptionDto> getTermDaysList(@RequestBody Map<String, Object> map){
		return finNetProductDurationService.getTermDaysList(map);
	}
    /**
     * 获取期次信息
     */
    @PostMapping("getPeriodsList")
	public List<SelectOptionDto> getPeriodsList(@RequestBody Map<String, Object> map){
		return finNetProductDurationService.getPeriodsList(map);
	}
    /**
     * 存续期新建产品赎回画面期次信息查询
     */
    @PostMapping("getPeriodInfoList")
	public List<FinNetProductDurationDto> getPeriodInfoList(@RequestBody Map<String, Object> map){
    	List<FinNetProductDurationDto> list = finNetProductDurationService.getPeriodInfoList(map);
    	return list;
	}
    /**
     * 存续期新建产品赎回画面期次信息查询(交易中数据)
     */
    @PostMapping("getPeriodInfoListIntrade")
	public List<FinNetProductDurationDto> getPeriodInfoListIntrade(@RequestBody Map<String, Object> map){
    	List<FinNetProductDurationDto> list = finNetProductDurationService.getPeriodInfoListIntrade(map);
    	return list;
	}
    /**
     * 通过交易编号获取分支序号
     */
    @PostMapping("getTrdProductDealByTradeId")
	public TrdProductDeal getTrdProductDealByTradeId(@RequestBody Map<String, Object> map){
    	return finNetProductDurationService.getTrdProductDealByTradeId(map);
	}
    /**
     * 通过期次代码获取finTradeProduct
     */
    @PostMapping("getFinTradeProductByPeriodId")
	public FinTradeProduct getFinTradeProductByPeriodId(@RequestBody Map<String, Object> map){
    	return finNetProductDurationService.getFinTradeProductByPeriodId(map);
	}
    /**
     * 根据选择的净值日查询单位净值
     */
    @PostMapping("getTrdValueByCdate")
	public BokPositionPrd getTrdValueByCdate(@RequestBody Map<String, Object> map){
    	return finNetProductDurationService.getTrdValueByCdate(map);
	}
    
    @RequestMapping("checkId")
    public long checkId(String tradeId) {
    	return finNetProductDurationService.checkId(tradeId);
    }
    /**
     * 查询产品 查询日期最近的净值信息-货币型产品分红专用
     * @param map 参数
     * @return
     */
    @PostMapping("getNetValueInfoForHB")
    public FinNetProductDurationDto getNetValueInfoForHB(@RequestBody Map<String,Object> map){
        return finNetProductDurationService.getNetValueInfoForHB(map);
    }
}
