package com.joyintech.tams.invest.batch.cfetsmanager.updatetrader.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.MstCfetsCustomerInfo;

@Repository
public interface UpdateTraderMapper {
	
	/**
	 * 查询在交易对手中不存在的银行间机构
	 * @return
	 */
	List<MstCfetsCustomerInfo> selectCustomerNew(String batchDate);
}
