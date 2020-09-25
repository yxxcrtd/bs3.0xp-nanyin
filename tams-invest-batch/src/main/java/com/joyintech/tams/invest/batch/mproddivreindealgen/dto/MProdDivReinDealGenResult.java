/*
 * 文件名：ResetBaseRuleInfoResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.mproddivreindealgen.dto;

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.TrdAsynHandleStatus;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;

/**
 * NeedToWriteEntity
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年2月16日
 * @see MProdDivReinDealGenResult
 * @since 1.0
 */
public class MProdDivReinDealGenResult extends NeedToWriteEntity{

	/**
	 * 删除用DTo
	 */
	private List<MProdDivReinDealDelDto> delDtos;
	
	/**
	 * 金融产品交易
	 */
	private List<TrdProductDeal> trdProductDeals;
	/**
	 * 金融产品交易附加
	 */
	private List<TrdProductDealAdd> trdProductDealAdds;
	
	/**
	 * 入账信息
	 */
	private List<BokDealAccounting> bokDealAccountings;
	/**
	 * 入账信息明细
	 */
	private List<BokDealAccountingDetail> bokDealAccountingDetails;
	/**
	 * 金融产品交易汇总表
	 */
	private TrdFinProductDealTotal trdFinProductDealTotal;
	/**
	 * 金融产品交易汇总扩展表
	 */
	private PaTrdFinProductDealTotal paTrdFinProductDealTotal;
	/**
	 * 组合交易
	 */
	private PtlTrade ptlTrade;
	/**
	 * 交易异步处理状态
	 */
	private TrdAsynHandleStatus trdAsynHandleStatus;
	/**
	 * 组合持仓流程
	 */
	private List<PtlSecPositionFlowDto> ptlSecPositionFlows;
	
	public List<MProdDivReinDealDelDto> getDelDtos() {
		return delDtos;
	}

	public void setDelDtos(List<MProdDivReinDealDelDto> delDtos) {
		this.delDtos = delDtos;
	}

	public List<TrdProductDeal> getTrdProductDeals() {
		return trdProductDeals;
	}

	public void setTrdProductDeals(List<TrdProductDeal> trdProductDeals) {
		this.trdProductDeals = trdProductDeals;
	}

	public List<TrdProductDealAdd> getTrdProductDealAdds() {
		return trdProductDealAdds;
	}

	public void setTrdProductDealAdds(List<TrdProductDealAdd> trdProductDealAdds) {
		this.trdProductDealAdds = trdProductDealAdds;
	}

	public List<BokDealAccounting> getBokDealAccountings() {
		return bokDealAccountings;
	}

	public void setBokDealAccountings(List<BokDealAccounting> bokDealAccountings) {
		this.bokDealAccountings = bokDealAccountings;
	}

	public List<BokDealAccountingDetail> getBokDealAccountingDetails() {
		return bokDealAccountingDetails;
	}

	public void setBokDealAccountingDetails(List<BokDealAccountingDetail> bokDealAccountingDetails) {
		this.bokDealAccountingDetails = bokDealAccountingDetails;
	}

	public TrdFinProductDealTotal getTrdFinProductDealTotal() {
		return trdFinProductDealTotal;
	}

	public void setTrdFinProductDealTotal(TrdFinProductDealTotal trdFinProductDealTotal) {
		this.trdFinProductDealTotal = trdFinProductDealTotal;
	}

	public PaTrdFinProductDealTotal getPaTrdFinProductDealTotal() {
		return paTrdFinProductDealTotal;
	}

	public void setPaTrdFinProductDealTotal(PaTrdFinProductDealTotal paTrdFinProductDealTotal) {
		this.paTrdFinProductDealTotal = paTrdFinProductDealTotal;
	}

	public PtlTrade getPtlTrade() {
		return ptlTrade;
	}

	public void setPtlTrade(PtlTrade ptlTrade) {
		this.ptlTrade = ptlTrade;
	}

	public TrdAsynHandleStatus getTrdAsynHandleStatus() {
		return trdAsynHandleStatus;
	}

	public void setTrdAsynHandleStatus(TrdAsynHandleStatus trdAsynHandleStatus) {
		this.trdAsynHandleStatus = trdAsynHandleStatus;
	}

	public List<PtlSecPositionFlowDto> getPtlSecPositionFlows() {
		return ptlSecPositionFlows;
	}

	public void setPtlSecPositionFlows(List<PtlSecPositionFlowDto> ptlSecPositionFlows) {
		this.ptlSecPositionFlows = ptlSecPositionFlows;
	}
    
}