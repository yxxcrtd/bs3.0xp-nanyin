/*
 * 文件名：TrdInvestDealQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年9月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.investtrade.investdealquery.dto;

import com.joyintech.tams.common.base.entity.WflTrdProductDeal;

import java.util.List;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList; 
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.invest.trade.investtrade.investdealquery.validator.TrdDealQueryValidator;

/**
 * 交易查询dto
 * 〈功能详细描述〉
 * @author wangyang1
 * @version 1.0 2019年9月19日
 * @see TrdDealQueryDto
 * @since 1.0
 */
@ValidLogic(targetClass = TrdDealQueryValidator.class)
public class TrdDealQueryDto extends WflTrdProductDeal{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	  /**
     * 选中的数据
     */
    private transient List<TrdDealQueryDto> selectedItem;
    
    /**
     * 操作类型
     */
    private String operateType;
    
	/**
	 * 有效状态
	 */
	private String validStatus;
	/**
	 * 委托代码
	 */
	private String entrustId;
	/**
	 * 投资组合代码
	 */
	private String portfolioId;
	/**
	 * 组合名称
	 */
	private String portfolioName;
	/**
	 * 市场代码
	 */
	private String finprodMarketId;
	/**
	 * 金融产品简称
	 */
	private String finprodAbbr;
	/**
	 * 回购拆借方向
	 */
	private String backLendDirection;
	 
    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<TrdDealQueryDto> getSelectedItem() {
        return selectedItem;
    }
    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<TrdDealQueryDto> selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }
    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
    /**
	 * 得到有效状态
	 * @return
	 */
	public String getValidStatus() {
		return validStatus;
	}
	/**
	 * 有效状态转换
	 * @return
	 */
	public String getValidStatusVal(){
	    return SysDataUtil.getNameFromDataDict(DictCode.交易流程状态, this.getValidStatus());
	}
	/**
	 * 设置有效状态
	 * @param validStatus
	 */
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	/**
	 * 得到委托代码
	 * @return
	 */
	public String getEntrustId() {
		return entrustId;
	}
	/**
	 * 设置委托代码
	 * @param entrustId
	 */
	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}
	/**
	 * 得到投资组合代码
	 * @return
	 */
	public String getPortfolioId() {
		return portfolioId;
	}
	/**
	 * 设置投资组合代码
	 * @param portfolioId
	 */
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	/**
	 * 得到组合名称
	 * @return
	 */
	public String getPortfolioName() {
		return portfolioName;
	}
	/**
	 * 设置组合名称
	 * @param portfolioName
	 */
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
	/**
	 * 得到市场代码
	 * @return
	 */
	public String getFinprodMarketId() {
		return finprodMarketId;
	}
	/**
	 * 设置市场代码
	 * @param finprodMarketId
	 */
	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}
	/**
	 * 得到金融产品简称
	 * @return
	 */
	public String getFinprodAbbr() {
		return finprodAbbr;
	}
	/**
	 * 设置金融产品简称
	 * @param finprodAbbr
	 */
	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}
	/**
     * 金融产品类型转换
     * @return
     */
    public String getFinprodType2Val(){
	    return SysDataUtil.getNameFromDataDict(DictCode.平安资产类型, super.getFinprodType2());
	}
    /**
     * 交易场所转换
     * @return
     */
	public String getTradeMarketVal(){
	    return SysDataUtil.getNameFromDataDict(DictCode.交易场所, super.getTradeMarket());
	}
	/**
     * 交易方向转换
     * @return
     */
	public String getPsVal(){
	    if(StringUtil.equals(super.getPs(), DictCodeList.回购拆借方向_拆入.getValue())
                || StringUtil.equals(super.getPs(), DictCodeList.回购拆借方向_拆出.getValue())
                || StringUtil.equals(super.getPs(), DictCodeList.回购拆借方向_正回购.getValue())
                || StringUtil.equals(super.getPs(), DictCodeList.回购拆借方向_逆回购.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, super.getPs());
        }else{
            return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
        }
	}
	/**
	 * 交易市场转换
	 * @return
	 */
	public String getTradeMarketModeVal(){
	    return SysDataUtil.getNameFromDataDict(DictCode.交易市场, super.getTradeMarketMode());
	}
	/**
	 * 币种转换
	 * @return
	 */
	public String getCcyVal(){
	    return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
	}
	/**
	 * 交易日期str
	 * @return
	 */
	public String getTradeDateVal(){
	    return DateUtil.format(super.getTradeDate());
	}
	/**
	 * 交割日期str
	 * @return
	 */
	public String getSettleDateVal(){
        return DateUtil.format(super.getSettleDate());
    }
	/**
     * 得到回购拆借方向
     * @return
     */
    public String getBackLendDirection() {
        return backLendDirection;
    }
    /**
     * 设置回购拆借方向
     * @param backLendDirection
     */
    public void setBackLendDirection(String backLendDirection) {
        this.backLendDirection = backLendDirection;
    }
	
}
