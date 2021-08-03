package com.joyintech.tams.invest.batch.cfetsmanager.updatetrader.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.MstCfetsCustomerInfo;

@Repository
public interface UpdateTraderMapper {
	
	/**
	 * 查询在交易对手中不存在的银行间机构
	 * @return
	 */
	List<MstCfetsCustomerInfo> selectCustomerNew(String batchDate);

	/**
	 * 查询在交易对手中的非法人机构
	 * @return
	 */
	int selectCustomer01(Map<String, Object> map);

	/**
	 * 查询在交易对手中的法人机构
	 * @return
	 */
	int selectCustomer02(Map<String, Object> map);
}
