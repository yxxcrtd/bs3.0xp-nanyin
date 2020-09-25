package com.joyintech.interf.bankname.sale.dao;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.interf.bankname.sale.dto.BonusPlanDto;
import com.joyintech.interf.bankname.sale.dto.FinProdSendInfoDto;
import com.joyintech.interf.bankname.sale.dto.FinProductPayIntShowDto;
import com.joyintech.interf.bankname.sale.dto.InfProdCommonDto;
import com.joyintech.interf.bankname.sale.dto.MarketIssueDto;
import com.joyintech.interf.bankname.sale.dto.ProdBaseInfoDto;
import com.joyintech.interf.bankname.sale.dto.SaleProdAccrueDto;
import com.joyintech.interf.bankname.sale.dto.SaleAlloDto;
import com.joyintech.interf.bankname.sale.dto.SaleBonusCollectDto;
import com.joyintech.interf.bankname.sale.dto.SaleAddReduceDto;
import com.joyintech.interf.bankname.sale.dto.SaleRedeemDto;
import com.joyintech.tams.invest.common.dto.product.addreduce.ReduceInfoDto;
import com.joyintech.tams.invest.product.productduration.establish.dto.FinProductEstablish002Dto;
import com.joyintech.tams.invest.product.productduration.netproductbonusscheme.dto.FinNetProductBonusSchemeDto;
import com.joyintech.tams.invest.product.productduration.netproductduration.dto.FinNetProductDurationDto;

/**
 * 销售系统数据查询
 * 
 * @author 吴鼎
 * @version 1.0 2019年7月4日
 * @see SaleInterfMapper
 * @since 1.0
 */
@Repository
public interface SaleInterfMapper {
		
    /**
     * 获取产品基本信息数据 
     * @return List<ProdBaseInfoDto>
     * @see
     * @since 1.0
     */
	ProdBaseInfoDto queryProdInfoByFinprodId(String finprodId);
	
	List<ProdBaseInfoDto> queryProdInfoByDate(String date);
	
    /**
     * 获取分红方案数据 
     * 
     * @return List<ValBokBookdetailBatch>
     * @see
     * @since 1.0
     */
	List<BonusPlanDto> queryBonusPlanByFinprodId(String finprodId);
	
	List<BonusPlanDto> queryBonusPlanByDate(String date);

	/**
     * 获取行情下发数据 
     * 
     * @return List<MarketIssueDto>
     * @see
     * @since 1.0
     */
	List<MarketIssueDto> queryMarketIssueByDate(String finprodId);
	
	/**
     * 将产品要素信息插入临时表
     */
	void insertProductInfoToTemp(ProdBaseInfoDto prodBaseInfoDto);

	/**
     * 将分红方案信息插入临时表
     */
	void insertBonusPlanToTemp(BonusPlanDto bonusPlanDto);

	/**
     * 将行情信息插入临时表
     */
	void insertMarketIssueToTemp(MarketIssueDto marketIssueDto);

	
	List<SaleAlloDto> queryAlloByDate(String date);

	List<SaleRedeemDto> queryRedeemByDate(String date);

	List<SaleAddReduceDto> queryAddReduceByDate(String date);

	List<SaleBonusCollectDto> queryBonusCollectByDate(String date);

	List<SaleProdAccrueDto> queryProdAccrueByDate(String date);

	String getProdProfitType(String finprodCode);
	
	FinProductEstablish002Dto queryBusinessDataByFinProduct(String finprodCode);

	FinNetProductDurationDto queryNetBusinessDataByFinProduct(String finprodCode);
	
	/**
     * 净值产品入库时，查询相关的产品信息
     * @param map
     * @return
     */
    List<InfProdCommonDto> getFinprodMarketIdList(String finprodCode);
    
    /**
     * 根据产品代码以及到期结束日查询金额以及应付利息金额
     * @param param
     * @return
     */
    List<ReduceInfoDto> findReduceInfoData(Map<String, Object> param);
    
    /**
     * 查询对应的付息计划
     * @param finProductPayIntShowDto
     * @return
     */
    FinProductPayIntShowDto queryCashInfo(FinProductPayIntShowDto finProductPayIntShowDto);

   
	FinNetProductBonusSchemeDto selectInfo(Map<String, Object> param);

	// 根据产品代码查询是否存在此产品
	String queryProdidByfinprodCode(String finprodCode);

	void insertFinProdSendInfo(FinProdSendInfoDto sendInfoModel);

	// 根据适配器ID查询接口分类
	String queryAdapterInfo(int adapterID);
	
	// 根据目标表查询适配器分类
	int queryAdapterID(String targetTable);
	
	BigDecimal queryNetValue(@Param("queryDate")Date queryDate
			   , @Param("finprodId")String finprodId);
	
}
