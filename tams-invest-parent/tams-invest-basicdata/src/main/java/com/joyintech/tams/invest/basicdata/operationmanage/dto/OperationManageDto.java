/*
 * 文件名：OperationManageDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2019年2月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.operationmanage.dto;

import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.dto.ProcessEntity;

public class OperationManageDto extends StlPayOrders implements ITableRow, ProcessEntity{

	/**
	 * 流水号
	 */
	private String seqno;
	
	/**
	 * 组合代码
	 */
	private String portfolioId;
	
	/**
	 * 账号
	 */
	private String acctId;
	
	/**
	 * 日期
	 */
	private String cdate;
	
	/**
	 * 币种
	 */
	private String ccy;
	
	/**
	 * 数量	
	 */
	private String amount;
	
	/**
	 * 证券管理账户/通道代码
	 */
	private String secAcctId;
	
	/**
	 * 金融产品代码
	 */
	private String finprodId;
	
	/**
	 * 金融资产类型
	 */
	private String finprodType;
	
	/**
	 * 持仓类型
	 */
	private String hodingType;
	
	/**
	 * 业务类型
	 */
	private String bussType;

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	
	public String getCcyStr(){
		return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCcy());
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSecAcctId() {
		return secAcctId;
	}

	public void setSecAcctId(String secAcctId) {
		this.secAcctId = secAcctId;
	}

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getHodingType() {
		return hodingType;
	}

	public void setHodingType(String hodingType) {
		this.hodingType = hodingType;
	}
	
	public String getHodingTypeStr(){
		return SysDataUtil.getNameFromDataDict(DictCode.持仓类型, this.getHodingType());
	}
	public String getFinprodType() {
		return this.finprodType;
	}
	public String getFinprodTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.平安资产类型, this.getFinprodType());
	}

	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}
	public String getBussType() {
		return this.bussType;
	}
	public String getBussTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.平安业务类型, this.getBussType());
	}

	public void setBussType(String bussType) {
		this.bussType = bussType;
	}
	
	public String getYesOrNo(){
		if(this.getHodingType() != null){
			if(this.getHodingType().equals("02")){
				return SysDataUtil.getNameFromDataDict(DictCode.是否, "Y");
			}else{
				return SysDataUtil.getNameFromDataDict(DictCode.是否, "N");
			}
		}
		else{
			return null;
		}
	}
	
	public String getPsStr(){
		return SysDataUtil.getNameFromDataDict(DictCode.收支方向, super.getPs());
	}
	
	public String getPayTypeStr(){
//		return SysDataUtil.getNameFromDataDict(DictCode.划款类型, super.getPayType());
	    return SysDataUtil.getNameFromDataDict(DictCode.平安划款类型, super.getPayType());
	}
	
	public String getSettleStatusStr(){
		return SysDataUtil.getNameFromDataDict(DictCode.结算状态, super.getSettleStatus());
	}
	
	public String getOrderStatusStr(){
		return SysDataUtil.getNameFromDataDict(DictCode.指令状态, super.getOrderStatus());
	}
}
