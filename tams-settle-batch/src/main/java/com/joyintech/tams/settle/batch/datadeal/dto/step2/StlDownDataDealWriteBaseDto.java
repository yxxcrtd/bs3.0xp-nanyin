package com.joyintech.tams.settle.batch.datadeal.dto.step2;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.common.base.entity.SettleTransData;
import com.joyintech.tams.common.base.entity.SettleTransDataAdd;
import com.joyintech.tams.common.base.entity.SettleTransDataPledge;
import com.joyintech.tams.common.base.entity.SettleTransTgMapping;

/**
 * 清算数据DTO（step2 processor用）
 * 
 * @author 陈卓
 * @date 2020/7/7
 * @see StlDownDataDealWriteBaseDto
 * @since 1.0
 */
public class StlDownDataDealWriteBaseDto {
    
	// 成交数据明细表
	SettleTransData settleTransData;
	
	// 成交数据明细附属表
	SettleTransDataAdd settleTransDataAdd;
	
	// 成交数据明细质押品信息表
	List<SettleTransDataPledge> settleTransDataPledgeList;
	
	// 成交数据与投管交易关系
	SettleTransTgMapping settleTransTgMapping;
	
	public StlDownDataDealWriteBaseDto() {
		settleTransData = new SettleTransData();
		settleTransDataAdd = new SettleTransDataAdd();
		settleTransDataPledgeList = new ArrayList<SettleTransDataPledge>();
		settleTransTgMapping = new SettleTransTgMapping();
	}

	public SettleTransData getSettleTransData() {
		return settleTransData;
	}

	public void setSettleTransData(SettleTransData settleTransData) {
		this.settleTransData = settleTransData;
	}

	public SettleTransDataAdd getSettleTransDataAdd() {
		return settleTransDataAdd;
	}

	public void setSettleTransDataAdd(SettleTransDataAdd settleTransDataAdd) {
		this.settleTransDataAdd = settleTransDataAdd;
	}

	public List<SettleTransDataPledge> getSettleTransDataPledgeList() {
		return settleTransDataPledgeList;
	}

	public void setSettleTransDataPledgeList(List<SettleTransDataPledge> settleTransDataPledgeList) {
		this.settleTransDataPledgeList = settleTransDataPledgeList;
	}

	public SettleTransTgMapping getSettleTransTgMapping() {
		return settleTransTgMapping;
	}

	public void setSettleTransTgMapping(SettleTransTgMapping settleTransTgMapping) {
		this.settleTransTgMapping = settleTransTgMapping;
	}
}
