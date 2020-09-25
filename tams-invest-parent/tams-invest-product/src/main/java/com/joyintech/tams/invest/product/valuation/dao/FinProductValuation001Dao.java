package com.joyintech.tams.invest.product.valuation.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.invest.product.valuation.dto.AssetDealInfoDto;
import com.joyintech.tams.invest.product.valuation.dto.FinProductValuation001Dto;
import com.joyintech.tams.invest.product.valuation.dto.FinProductValuationDetail001Dto;
import com.joyintech.tams.invest.product.valuation.dto.ProdDealInfoDto;
import com.joyintech.tams.invest.product.valuation.dto.ProdFeeInfoDto;
import com.joyintech.tams.invest.product.valuation.dto.ProdInfoDto;

public interface FinProductValuation001Dao {

	void deleteValuation();

	void deleteValuationDetail();

	/**
	 * 查询估值信息
	 * 
	 * @param pageParam
	 * @return
	 */
	List<FinProductValuation001Dto> selectForPage(Map<String, Object> pageParam);

	/**
	 * 查询产品信息
	 * 
	 * @param prodcode
	 * @return
	 */
	ProdInfoDto selectProdInfo(String prodcode);

	/**
	 * 查询存续本金
	 * 
	 * @param prodcode
	 * @param vldate
	 * @return
	 */
	List<ProdDealInfoDto> selectDurationProd(@Param("prodlist") List<String> prodlist, @Param("vldate") Date vldate);

	/**
	 * 剩余份额
	 * 
	 * @param prodid
	 * @param vldate
	 * @return
	 */
	BigDecimal selectProdRemainQty(@Param("prodcode") String prodcode, @Param("vldate") Date vldate);

	/**
	 * 查询投资端估值日交易
	 * 
	 * @param prodInfo
	 * @param vldate
	 * @return
	 */
	List<AssetDealInfoDto> selectAssetDealInfo(@Param("prodInfo") ProdInfoDto prodInfo, @Param("vldate") Date vldate);

	/**
	 * 获取valueid
	 * 
	 * @return
	 */
	Long selectValueid();

	/**
	 * 插入估值信息
	 * 
	 * @param dto
	 */
	void insertValue(FinProductValuation001Dto dto);

	/**
	 * 插入资产信息
	 * 
	 * @param assetInfoList
	 * @param pvalueid
	 */
	void insertAssetInfoDetail(@Param("assetInfoList") List<FinProductValuationDetail001Dto> assetInfoList,
			@Param("pvalueid") Long pvalueid);

	/**
	 * 查询产品费用信息 (计提式费用)
	 * 
	 * @param prodInfo
	 * @param vldate
	 * @return
	 */
	List<ProdFeeInfoDto> selectFeeInfo(@Param("prodInfo") ProdDealInfoDto prodInfo, @Param("vldate") Date vldate);

	/**
	 * 插入费用信息
	 * 
	 * @param feeInfoList
	 * @param pvalueid
	 */
	void insertFeeInfoDetail(@Param("feeInfoList") List<FinProductValuationDetail001Dto> feeInfoList,
			@Param("pvalueid") Long pvalueid);

	/**
	 * 查询累计未付费用
	 * 
	 * @param prodcode
	 * @param feetype
	 * @param cdate
	 * @return
	 */
	BigDecimal selectFeePosition(@Param("prodcode") String prodcode, @Param("feetype") String feetype,
			@Param("cdate") Date cdate);

	/**
	 * 查询债券市价
	 * @param secid 债券代码
	 * @param vldate 估值日
	 * @param vlsrc 估值来源
	 * @return
	 */
	Map<String, BigDecimal> selectMktprice(@Param("secid") String secid, @Param("vldate") Date vldate,
			@Param("vlsrc") String vlsrc);

	/**
	 * 获取T-1日持仓
	 * @param ptlid
	 * @param finprodid
	 * @param vldate
	 * @return
	 */
	BigDecimal selectBeforePosition(@Param("ptlid") String ptlid, @Param("finprodid") String finprodid,
			@Param("cdate") Date cdate);

	/**
	 * 获取T-1日估值
	 * @param ptlid
	 * @param finprodid
	 * @param cdate
	 * @return
	 */
	Map<String, BigDecimal> selectBeforeValue(@Param("ptlid") String ptlid, @Param("finprodid") String finprodid,
			@Param("cdate") Date cdate);

	/**
	 * 获取T-1日 净值,万份收益
	 * @param finprodid
	 * @param cdate
	 * @return
	 */
	BigDecimal selectBeforePrice(@Param("finprodid") String finprodid, @Param("cdate") Date cdate);

	/**
	 * 获取单位分红金额
	 * @param finprodid
	 * @param sdate
	 * @param edate
	 * @return
	 */
	BigDecimal selectUnitBonus(@Param("finprodid") String finprodid, @Param("sdate") Date sdate,
			@Param("edate") Date edate);

	/**
	 * 查询净值
	 * @param finprodid
	 * @param vldate
	 * @return
	 */
	BigDecimal selectBeforeNetValue(@Param("finprodid") String finprodid, @Param("cdate") Date cdate);

}
