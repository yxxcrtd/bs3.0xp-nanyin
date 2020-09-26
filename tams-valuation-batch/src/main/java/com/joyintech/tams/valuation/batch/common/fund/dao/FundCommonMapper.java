package com.joyintech.tams.valuation.batch.common.fund.dao;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.MstCounterAcct;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FundCommonMapper {
	MstCounterAcct querySubscriptionConfirmationDeal(Map<String,Object> map);

	/**
	 * 根据交易编码查询标的信息
	 * @param map
	 * @return
	 */
	List<FinProduct> queryTargetInfo(Map<String,Object> map);
}
