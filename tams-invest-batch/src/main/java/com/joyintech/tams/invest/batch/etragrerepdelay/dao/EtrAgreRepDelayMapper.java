package com.joyintech.tams.invest.batch.etragrerepdelay.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinTradeProduct;

@Repository
public interface EtrAgreRepDelayMapper {
	
	/**
	 * 查询到期产品
	 * @param param
	 * @return
	 */
	List<FinTradeProduct> getExpireFinprodId(Map<String, Object> param);
	
	/**
	 * 更新交易类金融产品交易表
	 * @param finTradeProduct
	 */
	void updateFinTradeProduct(FinTradeProduct finTradeProduct);
	
	/**
	 * 更新金融产品交易表
	 * @param finTradeProduct
	 */
	void updateTrdProdctDeal(FinTradeProduct finTradeProduct);
	
	/**
	 * 更新金融产品临时交易表
	 * @param finTradeProduct
	 */
	void updateTrdTrdPrdTempDeal(FinTradeProduct finTradeProduct);
}
