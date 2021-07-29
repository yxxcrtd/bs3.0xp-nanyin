package com.joyintech.tams.invest.common.asset.dao;

import com.joyintech.tams.invest.common.asset.dto.AssetGenerateEntry;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *  金融产品共通
 * @author 裴家俊
 * @version 1.0 2021年7月20日
 * @see AssetGenerateMapper
 * @since 1.0
 */
@Repository
public interface AssetGenerateMapper {

	/**
	 * 查询债券的面值等其它信息
	 * <功能详细描述>
	 * @param finprodId
	 * @see
	 * @since 1.0
	 */
	AssetGenerateEntry qryFaceOrOtherInf(String finprodId);

	/**
     * 查询汇率
	 * @param basisCcy
     * @param quotCcy
     * @param valDate
     * @return
     */
	BigDecimal getExchangeRate(@Param("basisCcy") String basisCcy, @Param("quotCcy") String quotCcy, @Param("valDate") Date valDate);


	/**
	 * 查询债券估值行情
	 * @param paramMap
	 * @return
	 */
	List<Map> getBondValuation(Map<String,Object> paramMap);

	/**
	 * 查询产品最新净资产
	 * @param paramMap
	 * @return
	 */
	List<Map> getAstNetVal(Map<String,Object> paramMap);

	/**
	 * 查询指定产品证券账户
	 * @param paramMap
	 * @return
	 */
	List<Map> getStlAccount(Map<String,Object> paramMap);
}
