/*
 * 文件名：FinProductModule002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年8月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.PaBranchSaleDetail;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PrdMdateAdjust;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.StlSecAcctInfo;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.common.base.entity.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;

/**
 * 产品端共通mapper
 *
 * @author 强成西
 * @version 1.0 2018年9月06日
 * @see ProductCommonMapper
 * @since 1.0
 */
@Repository
public interface ProductCommonMapper {
	List<SuggestDto> getProdId(Map<String, Object> pageParam);

	List<SuggestDto> getGroupId(Map<String, Object> pageParam);

	/**
	 * 查询交易对手
	 * 
	 * @param param
	 *            参数map
	 * @return 交易对手
	 * @see
	 * @since 1.0
	 */
	List<SelectOptionDto> queryCounterpartyInfo(Map<String, Object> param);

	/**
	 * 交易日历下拉框
	 * 
	 * @param param
	 *            参数map
	 * @return 交易日历
	 * @see
	 * @since 1.0
	 */
	List<SelectOptionDto> queryCalendarInfoList(Map<String, Object> map);

	/**
	 * 删除信息披露信息
	 * 
	 * @param finprodId
	 *            金融产品代码
	 * @return 删除件数
	 */
	int deletePrdFileByFinprodId(@Param("finprodId") String finprodId);
    
    /**
     * 产品代码联想下拉框
     * 
     * @param map 参数map
     * @return 产品信息
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryProdIdInfo(Map<String, Object> map);
    
    
    /**
     * 产品代码联想下拉框(发行成功)
     * 
     * @param map 参数map
     * @return 产品信息
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryProdIdInfoIssueSuc(Map<String, Object> map);
    
    /**
     * 费用账户联想下拉框
     * 
     * @param map 参数map
     * @return 费用账户
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryFeeAccountList(Map<String, Object> map);
    
    /**
     * 费用代码联想下拉框
     * 
     * @param map 参数map
     * @return 费用信息
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryFeeInfoList(Map<String, Object> map);
    
    /**
     * 托管名称下拉框
     * 
     * @param map 参数map
     * @return 托管名称
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryTrusterCustomerName(Map<String, Object> map);
    
	List<SelectOptionDto> selectByUserName(Map<String, Object> param);
	List<SelectOptionDto> selectByUserName1(Map<String, Object> param);
	
	List<SelectOptionDto> getProductUserName(Map<String, Object> param);
	
	List<SelectOptionDto> getCustomerName(Map<String, Object> param);

    /**
     * 根据交易编号查询流程中数据条数
     *
     * @param tradeId 交易编号
     * @return 流程中数据条数
     * @see
     * @since 1.0
     */
    int selectInFlowDataCount(@Param("tradeId") String tradeId);

    /**
     * 根据交易编号查询已撤销数据条数
     *
     * @param paramMap 参数
     * @return 已撤销数据条数
     * @see
     * @since 1.0
     */
    int selectRevokedDataCount(Map<String, Object> paramMap);
    
    /** 根据汇总交易编号划款指令信息
     * @param tradeId 汇总交易编号
     * @return 划款指令信息
     */
    List<StlPayOrders> queryStlPayOrdersByTradeId(@Param("tradeId") String tradeId);
    
    /** 根据汇总交易编号查询交易信息
     * @param tradeId 汇总交易编号
     * @return 交易信息
     */
    List<TrdProductDeal> queryTrdProductDealByTotleTradeId(@Param("tradeId") String tradeId);
    
    /** 根据汇总交易编号查询交易附属信息
     * @param tradeId 汇总交易编号
     * @return 交易附属信息
     */
    List<TrdProductDealAdd> queryTrdProductDealAddByTotleTradeId(@Param("tradeId") String tradeId);
    
    /** 根据汇总交易编号查询金融产品交易扩展
     * @param tradeId 汇总交易编号
     * @return 金融产品交易扩展
     */
    List<PaTrdProductDeal> queryPaTrdProductDealByTotleTradeId(@Param("tradeId") String tradeId);
    
    /** 根据汇总交易编号查询理财分行销售明细
     * @param tradeId 汇总交易编号
     * @return 理财分行销售明细
     */
    List<PaBranchSaleDetail> queryPaBranchSaleDetailByTotleTradeId(@Param("tradeId") String tradeId);
    
    /** 根据汇总交易编号修改金融产品交易的撤销状态
     * @param isCancle 是否撤销
     * @param tradeId 汇总交易编号
     * @return DB
     */
    int updateTrdProductDealByTotleTradeId(@Param("isCancle") String isCancle, @Param("tradeId") String tradeId);
    
    /** 根据汇总交易编号查询组合交易信息
     * @param tradeId 汇总交易编号
     * @return 组合交易信息
     */
    List<PtlTrade> queryPtlTradeByTotleTradeId(@Param("tradeId") String tradeId);


    /**
     * 根据汇总交易编号查询费用明细列表
     * @param tradeId
     * @return
     */
	List<TrdTradeFeeDetail> queryTrdTradeFeeDetailByTotleTradeId(String tradeId);

    
    int deleteProductAcct(@Param("finprodId") String finprodId);
    /**
     * 获取子产品
     * @param finprodId
     * @return
     */
    List<String> getSubProd(@Param("finprodId") String finprodId);

	List<TrdFinProductDealTotal> queryTrdFinProductDealTotalByModel(TrdFinProductDealTotal trdFinProductDealTotal);

	List<PrdMdateAdjust> queryPrdMdateAdjustByModel(PrdMdateAdjust prdMdateAdjust);
    List<StlSecAcctInfo> getAllStlSecAcctInfo();
    /**
     * 获取所有用户
     * @return 所属部门集合
     * @see
     * @since 1.0
     */
	List<User> getAllUser();
	
	/**
	 * 删除证券账户
	 * @param portfolioId
	 * @return
	 */
	int deletePtlSecAcct(@Param("portfolioId") String portfolioId);
	/**
	 * 删除组合权限
	 * @param portfolioId
	 * @return
	 */
	int deletePtlPortfolioAuthority(@Param("portfolioId") String portfolioId);
	/**
	 * 删除组合账户
	 */
	int deletePtlCapacctInfo(@Param("portfolioId") String portfolioId);
	/**
	 * 删除组合现金流信息
	 * @param portfolioId
	 * @param finProdType
	 * @return
	 */
	int deletePtlCashInfo(@Param("portfolioId") String portfolioId,@Param("finProdType") String finProdType);
	/**
	 * 删除  BokBookSet 表
	 * @param finprodId
	 * @return
	 */
	int deleteBokBookSet(@Param("finprodId") String finprodId);

	/**
	 * 获取系统中浮动差额中收feeid
	 * @return 浮动差额中收feeid
	 * @see
	 * @since 1.0
	 */
	String getFeeIdByFeeType(@Param("feeId")String feeId);
	/**
	 * 获取行内标识码
	 * @param param 传入条件
	 * @return
	 */
	List<SelectOptionDto> queryRepFinprodInsId(Map<String, Object> param);
	/**
	 * 删除组合账户
	 */
	int deletePtlCapacctInfoByKey(Map<String,Object> map);

	/**
	 * 获取产品款型下拉框
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<SelectOptionDto> getProdStyle(Map<String, Object> param);
	
	List<SelectOptionDto> queryTrusteeId(Map<String, Object> map);
}