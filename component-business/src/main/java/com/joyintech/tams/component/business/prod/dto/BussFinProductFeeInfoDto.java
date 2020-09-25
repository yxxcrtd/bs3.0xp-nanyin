/*
 * 文件名：BussFinProductFeeInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.MstFeeinfo;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Rate;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.component.business.prod.validator.BussFinProductFeeInfoValidator;
import com.joyintech.tams.constant.DictCode;

/**
 * 费用基本信息
 *
 * @author 张亮
 * @version 1.0 2018年10月9日
 * @see BussFinProductFeeInfoDto
 * @since 1.0
 */
@ValidLogic(targetClass = BussFinProductFeeInfoValidator.class)
public class BussFinProductFeeInfoDto extends MstFeeinfo {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 7066454186364900617L;

    /**
     * 主画面传入的pageId
     */
    private String pageId;

    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 交易日历
     */
    private String dealCalId;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 收益类型
     */
    private String profitType;


    /**
     * 费率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal feeRate;

    /**
     * 资产净值日
     */
    private String netAssetValueDay;

    /**
     * 首次计提日
     */
    private Date provisionFirstDay;

    /**
     * 付费规则
     */
    private String payFeeRule;

    /**
     * 计费营业准则
     */
    private String calcFeeTradeRule;

    /**
     * 付费营业日准则
     */
    private String payFeeTradeRule;

    /**
     * 计提结束日
     */
    private Date eAccrDate;

    /**
     * 首次计费结束日
     */
    private Date firstCalEndDate;

    /**
     * 付费计划列表
     */
    private List<FinCashPlan> finCashPlanList;

    /**
     * 费用列表
     */
    @Valid
    private List<BussFinProductFeeInfoDto> bussFinProductFeeInfoDtoList;

    /**
     * 收取方式
     */
    private String collectType;
    
    /**
     * 款型
     */
    private String prodStyle;
    /**
     * 首次计费基数
     */
    private String firstBaseType;
    /**
     * 产品代码
     */
    private String finprodId;
    /**
     *  判断费用类型是否重复使用
     */
    private String prodStyleAndFeeType;
    
    private String prodStyleAndFeeTypeNew;
    
    public String getDealCalId() {
        return dealCalId;
    }

    public void setDealCalId(String dealCalId) {
        this.dealCalId = dealCalId;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    @Rate(fieldName = "BussFinProductFeeInfo.feeRate", maxDecBit = 4)
    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    public String getNetAssetValueDay() {
        return netAssetValueDay;
    }

    public void setNetAssetValueDay(String netAssetValueDay) {
        this.netAssetValueDay = netAssetValueDay;
    }

    public Date getProvisionFirstDay() {
        return provisionFirstDay;
    }

    public void setProvisionFirstDay(Date provisionFirstDay) {
        this.provisionFirstDay = provisionFirstDay;
    }

    public String getPayFeeRule() {
        return payFeeRule;
    }

    public void setPayFeeRule(String payFeeRule) {
        this.payFeeRule = payFeeRule;
    }

    public String getCalcFeeTradeRule() {
        return calcFeeTradeRule;
    }

    public void setCalcFeeTradeRule(String calcFeeTradeRule) {
        this.calcFeeTradeRule = calcFeeTradeRule;
    }

    public String getPayFeeTradeRule() {
        return payFeeTradeRule;
    }

    public void setPayFeeTradeRule(String payFeeTradeRule) {
        this.payFeeTradeRule = payFeeTradeRule;
    }

    public List<FinCashPlan> getFinCashPlanList() {
        return finCashPlanList;
    }

    public void setFinCashPlanList(List<FinCashPlan> finCashPlanList) {
        this.finCashPlanList = finCashPlanList;
    }

    /**
     * 获取 bussFinProductFeeInfoDtoList
     * @return bussFinProductFeeInfoDtoList.
     */
    public List<BussFinProductFeeInfoDto> getBussFinProductFeeInfoDtoList() {
        return bussFinProductFeeInfoDtoList;
    }

    /**
     * 设置 bussFinProductFeeInfoDtoList
     * @param bussFinProductFeeInfoDtoList bussFinProductFeeInfoDtoList
     */
    public void setBussFinProductFeeInfoDtoList(List<BussFinProductFeeInfoDto> bussFinProductFeeInfoDtoList) {
        this.bussFinProductFeeInfoDtoList = bussFinProductFeeInfoDtoList;
    }


    /**
     * 获取 cashId
     * @return cashId.
     */
    public String getCashId() {
        return cashId;
    }


    /**
     * 设置 cashId
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    /**
     * 获取 pageId
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 pageId
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getFeeTypeStr() {
     // 一体化对应etliu start
//        return SysDataUtil.getNameFromDataDict(DictCode.费用类型, this.getFeeType());
        return SysDataUtil.getNameFromDataDict(DictCode.费用品种类型, this.getFeeType());
     // 一体化对应etliu end
    }

    public String getBasicBillStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.计费基数_费用品种, this.getBasicBill());
    }

    public String getBasisStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.计息基础, this.getBasis());
    }

    /**
     * 获取 collectType
     * @return collectType.
     */
    public String getCollectType() {
        return collectType;
    }

    /**
     * 设置 collectType
     * @param collectType collectType
     */
    public void setCollectType(String collectType) {
        this.collectType = collectType;
    }

    /**
     * 获取 eAccrDate
     * @return eAccrDate.
     */
    public Date geteAccrDate() {
        return eAccrDate;
    }

    /**
     * 设置 eAccrDate
     * @param eAccrDate eAccrDate
     */
    public void seteAccrDate(Date eAccrDate) {
        this.eAccrDate = eAccrDate;
    }

    /**
     * 获取 firstCalEndDate
     * @return firstCalEndDate.
     */
    public Date getFirstCalEndDate() {
        return firstCalEndDate;
    }

    /**
     * 设置 firstCalEndDate
     * @param firstCalEndDate firstCalEndDate
     */
    public void setFirstCalEndDate(Date firstCalEndDate) {
        this.firstCalEndDate = firstCalEndDate;
    }

    
    /**
     * 获取 prodStyle
     * @return prodStyle.
     */
    public String getProdStyle() {
        return prodStyle;
    }

    
    /**
     * 设置 prodStyle
     * @param prodStyle prodStyle
     */
    public void setProdStyle(String prodStyle) {
        this.prodStyle = prodStyle;
    }
    
    public String getProdStyleAndFeeType(){
        this.setProdStyleAndFeeType(this.prodStyle + this.getFeeId());
        return this.prodStyleAndFeeType;
    }

    
    /**
     * 设置 prodStyleAndFeeType
     * @param prodStyleAndFeeType prodStyleAndFeeType
     */
    private void setProdStyleAndFeeType(String prodStyleAndFeeType) {
        this.prodStyleAndFeeType = prodStyleAndFeeType;
    }

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}
	 public String getProdStyleAndFeeTypeNew(){
	        this.setProdStyleAndFeeTypeNew(this.prodStyle + this.getFeeType());
	        return this.prodStyleAndFeeTypeNew;
	    }

	    
	    /**
	     * 设置 prodStyleAndFeeTypeNew
	     * @param prodStyleAndFeeTypeNew prodStyleAndFeeTypeNew
	     */
	    private void setProdStyleAndFeeTypeNew(String prodStyleAndFeeTypeNew) {
	        this.prodStyleAndFeeTypeNew = prodStyleAndFeeTypeNew;
	    }

		public String getFirstBaseType() {
			return firstBaseType;
		}

		public void setFirstBaseType(String firstBaseType) {
			this.firstBaseType = firstBaseType;
		}
	
}
