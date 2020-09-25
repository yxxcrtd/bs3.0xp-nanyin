/*
1 * 文件名：FinProductPayInt001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.payint.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.product.addreduce.ReduceInfoDto;
import com.joyintech.tams.invest.product.payint.validator.FinProductPayInt002DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 产品中间付息新建dto
 *
 * @author etliu
 * @version 1.0 2018年12月29日
 * @see FinProductPayInt001Dto
 * @since 1.0
 */
@PageId("FinProductPayInt003")
@ValidLogic(targetClass = FinProductPayInt002DtoValidator.class)
public class FinProductPayInt003Dto extends FinProductPayInt002Dto implements ProcessEntity {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = -6490393343772318265L;

	@Override
	@PrimaryKey(PrimaryKey.Field.PK_1)
	public String getFinprodId() {
		return super.getFinprodId();
	}

	@PrimaryKey(PrimaryKey.Field.PK_5)
	public String getBusiType() {
		return super.getBusiType();
	}
	/**
	 * 获取 PayintEndDateStr
	 * 
	 * @return PayintEndDateStr.
	 */
	public String getPayintStartDateStr() {
		  return DateUtil.format(getPayintStartDate());
	}
		 
	/**
	 * 利息明细信息数据
	 */
	private List<ReduceInfoDto> intDetailDataList;
	/**
	 * 划款指令
	 */
	private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

	public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
		return bussTrdTransferOrder;
	}

	public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
		this.bussTrdTransferOrder = bussTrdTransferOrder;
	}

	/**
	 * 交易编号
	 */
	private String tradeId;

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	/**
	 * 操作类型
	 */
	private String operateType;
	/**
	 * 元素Id
	 */
	private String elementId;

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	/**
	 * 画面模式
	 */
	private String pageModel;

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	/**
	 * 撤销原因
	 */
	private String revokeReason;

	public String getRevokeReason() {
		return revokeReason;
	}

	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
	}

	/**
	 * 有效状态
	 */
	private String validStatus;

	/**
	 * 有效状态
	 */
	private String validStatusText;

	/**
	 * 业务编号
	 */
	private String bussId;

	/**
	 * 组合代码
	 */
	private String portfolioId;

	/**
	 * 产品简称
	 */
	private String finprodAbbr;

	/**
	 * 销售部门
	 */
	private String saleDepartment;

	/**
	 * 销售部门
	 */
	private String saleDepartmentText;

	/**
	 * 本金金额
	 */
	private BigDecimal prinAmt;

	/**
	 * 应付客户收益
	 */
	private BigDecimal theoryCusProfit;

	/**
	 * 实付客户收益
	 */
	private BigDecimal actCusProfit;

	/**
	 * 币种
	 */
	private String ccy;

	/**
	 * 是否分层
	 */
	private String isLay;

	/**
	 * 产品登记编码
	 */
	private String prodRegistCode;
	/**
	 * 处理模式
	 */
	private String dealMode;

	public List<ReduceInfoDto> getIntDetailDataList() {
		return intDetailDataList;
	}

	public void setIntDetailDataList(List<ReduceInfoDto> intDetailDataList) {
		this.intDetailDataList = intDetailDataList;
	}
	/**
	 * 产品款型
	 */
	private String prodType;

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	/**
	 * 获取 validStatus
	 *
	 * @return validStatus.
	 */
	public String getValidStatus() {
		return validStatus;
	}

	/**
	 * 设置 validStatus
	 *
	 * @param validStatus validStatus
	 */
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

	/**
	 * 获取 bussId
	 *
	 * @return bussId.
	 */
	public String getBussId() {
		return bussId;
	}

	/**
	 * 设置 bussId
	 *
	 * @param bussId bussId
	 */
	public void setBussId(String bussId) {
		this.bussId = bussId;
	}

	/**
	 * 获取 portfolioId
	 *
	 * @return portfolioId.
	 */
	public String getPortfolioId() {
		return portfolioId;
	}

	/**
	 * 设置 portfolioId
	 *
	 * @param portfolioId portfolioId
	 */
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	/**
	 * 产品名称
	 */
	private String finprodName;

	public String getFinprodName() {
		return finprodName;
	}

	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}

	/**
	 * 产品起息日
	 */
	private Date Vdate;

	/**
	 * 产品到期日
	 */
	private Date Mdate;

	public Date getVdate() {
		return Vdate;
	}

	public void setVdate(Date vdate) {
		Vdate = vdate;
	}

	public Date getMdate() {
		return Mdate;
	}

	public void setMdate(Date mdate) {
		Mdate = mdate;
	}

	/**
	 * 收益类型
	 */
	private String profitType;
	/**
	 * 付息方式
	 */
	private String payIntRule;

	public String getPayIntRule() {
		return payIntRule;
	}

	public void setPayIntRule(String payIntRule) {
		this.payIntRule = payIntRule;
	}

	/**
	 * 获取 profitType
	 *
	 * @return profitType.
	 */
	public String getProfitType() {
		return profitType;
	}

	/**
	 * 设置 profitType
	 *
	 * @param profitType profitType
	 */
	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	/**
	 * 
	 * /** 获取 prodAbbr
	 *
	 * @return prodAbbr.
	 */

	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	/**
	 * 获取 saleDepartment
	 *
	 * @return saleDepartment.
	 */
	public String getSaleDepartment() {
		return saleDepartment;
	}

	/**
	 * 设置 saleDepartment
	 *
	 * @param saleDepartment saleDepartment
	 */
	public void setSaleDepartment(String saleDepartment) {
		this.saleDepartment = saleDepartment;
	}

	/**
	 * 获取 prinAmt
	 *
	 * @return prinAmt.
	 */
	public BigDecimal getPrinAmt() {
		return prinAmt;
	}

	/**
	 * 设置 prinAmt
	 *
	 * @param prinAmt prinAmt
	 */
	public void setPrinAmt(BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}

	/**
	 * 获取 theoryCusProfit
	 *
	 * @return theoryCusProfit.
	 */
	public BigDecimal getTheoryCusProfit() {
		return theoryCusProfit;
	}

	/**
	 * 设置 theoryCusProfit
	 *
	 * @param theoryCusProfit theoryCusProfit
	 */
	public void setTheoryCusProfit(BigDecimal theoryCusProfit) {
		this.theoryCusProfit = theoryCusProfit;
	}

	/**
	 * 获取 actCusProfit
	 *
	 * @return actCusProfit.
	 */
	public BigDecimal getActCusProfit() {
		return actCusProfit;
	}

	/**
	 * 设置 actCusProfit
	 *
	 * @param actCusProfit actCusProfit
	 */
	public void setActCusProfit(BigDecimal actCusProfit) {
		this.actCusProfit = actCusProfit;
	}

	/**
	 * 获取 ccy
	 *
	 * @return ccy.
	 */
	public String getCcy() {
		return ccy;
	}

	/**
	 * 设置 ccy
	 *
	 * @param ccy ccy
	 */
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	/**
	 * 获取 isLay
	 * 
	 * @return isLay.
	 */
	public String getIsLay() {
		return isLay;
	}

	/**
	 * 设置 isLay
	 * 
	 * @param isLay isLay
	 */
	public void setIsLay(String isLay) {
		this.isLay = isLay;
	}

	/**
	 * 获取 validStatusText
	 *
	 * @return validStatusText.
	 */
	public String getValidStatusText() {
		validStatusText = SysDataUtil.getNameFromDataDict(DictCode.画面数据状态, validStatus);
		return validStatusText;
	}

	/**
	 * 设置 validStatusText
	 *
	 * @param validStatusText validStatusText
	 */
	public void setValidStatusText(String validStatusText) {
		this.validStatusText = validStatusText;
	}

	/**
	 * 获取 saleDepartmentText
	 *
	 * @return saleDepartmentText.
	 */
	public String getSaleDepartmentText() {
		saleDepartmentText = SysDataUtil.getNameFromDataDict(DictCode.销售部门, saleDepartment);
		return saleDepartmentText;
	}

	/**
	 * 设置 saleDepartmentText
	 *
	 * @param saleDepartmentText saleDepartmentText
	 */
	public void setSaleDepartmentText(String saleDepartmentText) {
		this.saleDepartmentText = saleDepartmentText;
	}

	/**
	 * 获取 prodRegistCode
	 * 
	 * @return prodRegistCode.
	 */
	public String getProdRegistCode() {
		return prodRegistCode;
	}

	/**
	 * 设置 prodRegistCode
	 * 
	 * @param prodRegistCode prodRegistCode
	 */
	public void setProdRegistCode(String prodRegistCode) {
		this.prodRegistCode = prodRegistCode;
	}

	/**
	 * 获取 dealMode
	 * 
	 * @return dealMode.
	 */
	public String getDealMode() {
		return dealMode;
	}

	/**
	 * 设置 dealMode
	 * 
	 * @param dealMode dealMode
	 */
	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}

}
