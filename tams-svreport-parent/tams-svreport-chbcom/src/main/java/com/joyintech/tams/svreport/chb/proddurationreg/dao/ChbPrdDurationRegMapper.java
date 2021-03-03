package com.joyintech.tams.svreport.chb.proddurationreg.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.common.base.entity.ChbPrdBonusDeal;
import com.joyintech.tams.common.base.entity.ChbPrdDeal;
import com.joyintech.tams.common.base.entity.ChbPrdDealSub;
import com.joyintech.tams.common.base.entity.ChbPrdFeeDeal;
import com.joyintech.tams.common.base.entity.ChbPrdPeriodYield;
import com.joyintech.tams.svreport.chb.proddurationreg.dto.ChbPrdDurationDeal;
import com.joyintech.tams.svreport.chb.proddurationreg.dto.ProdDurationReg001Dto;
import com.joyintech.tams.svreport.position.dto.ChbPrdPosition;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbPrdDurationRegMapper {
	
	/**
     * 查询满足条件的产品（封闭式）
     * 
     * @param batchDate 跑批日期
     * @return 返回产品集合
     * @see
     * @since 1.0
     */
//    List<ChbPrdDurationDeal> getProdIdsByDealSub(@Param("batchDate") String batchDate,@Param("prodIdList")List<String> prodIdList);
	  List<ChbPrdDurationDeal> getProdIdsByDealSub(Map<String, Object> pageParam);

    /**
     * 查询满足条件的产品（开放式）
     * 
     * @param batchDate 跑批日期
     * @return 返回产品集合
     * @see
     * @since 1.0
     */
//    List<ChbPrdDurationDeal> getOpenProdIdsByDealSub(@Param("batchDate") String batchDate,@Param("prodIdList")List<String> prodIdList, @Param("openProdMdateConf") String openProdMdateConf);
	List<ChbPrdDurationDeal> getOpenProdIdsByDealSub(Map<String, Object> pageParam);

	/**
     * 获取已报送的信息
     * 
     * @param batchDate 跑批日期
     * @param list 传入参数
     * @return 报送信息
     * @see
     * @since 1.0
     */
    List<ChbPrdDurationDeal> getSendedSubList(@Param("batchDate") Date batchDate,@Param("prodIdList")List<String> prodIdList);
    /**
     * 获取报送表中内容
     * 
     * @param list 传入参数
     * @return 报送信息
     * @see
     * @since 1.0
     */
    List<ChbPrdDurationDeal> getProdDealSubList(@Param("list") List<ChbPrdDurationDeal> list);
    /**
     * 获取交易信息
     * 
     * @param list 传入参数
     * @return 交易列表
     * @see
     * @since 1.0
     */
    List<ChbPrdDeal> getAllPrdDeal(@Param("list") List<ChbPrdDurationDeal> list);
    /**
     * 获取费用列表
     * 
     * @param list 传入参数
     * @return 费用列表
     * @see
     * @since 1.0
     */
    List<ChbPrdFeeDeal> getAllPrdFeeList(@Param("list") List<ChbPrdDurationDeal> list);

	int insertBatch(@Param("resultList") List<ChbPrdDealSub> resultList);

	int deleteBatch(@Param("resultList") List<ChbPrdDealSub> resultList);
	
	int deleteBatchByprodId(@Param("resultList") List<ChbPrdDealSub> resultList);

	int updateBatch(@Param("resultList") List<ChbPrdDealSub> resultList);

	/**
     * 获取实现收益率
     * 
     * @param prodId 产品代码
     * @param date 报价日
     * @param type 01 实现收益率 02 最新收益率
     * @return 收益率
     * @see
     * @since 1.0
     */
	ChbPrdPeriodYield getReaYield(@Param("prodId") String prodId,
                           @Param("date") Date date, @Param("type") String type);
	
	/**
     * 获取产品余额以及份额，以字符串拼接返回
     * 
     * @param prodId 产品代码
     * @param orgCode 机构代码
     * @param date 截止日
     * @return 余额以及份额
     * @see
     * @since 1.0
     */
    List<ChbPrdPosition> getBalance(@Param("prodId") String prodId,@Param("date") Date date);

    /**
     * 获取产品赎回收益(针对于净值型产品按照移动平均来计算，其他模式则根据实际金额)
     * 
     * @param prodId 产品代码
     * @param ccy 币种
     * @param date 截止日
     * @return 收益
     * @see
     * @since 1.0
     */
    List<ChbPrdPosition> getIntAmt(@Param("prodId") String prodId, @Param("ccy") String ccy, @Param("date") Date date);
	
	/**
	 * 是否是分层产品
	 * @param prodId
	 * @param orgCode
	 * @return
	 */
	int isLevProd(@Param("prodId") String prodId);

	/**
	 * 获取子产品的认购交易数据
	 * @param prodId
	 * @param orgCode
	 * @return
	 */
	List<ChbPrdDeal> getSubPrdDeal(@Param("prodId") String prodId);

	/**
	 * 获取产品的累计净值
	 * @param prodId
	 * @param orgCode
	 * @param quotationDate
	 * @return
	 */
	BigDecimal getTotalUnitBonusAmount(@Param("prodId") String prodId, @Param("date") Date date);

	void insertPrdDealSub(ChbPrdDealSub dl);
	
	List<ProdDurationReg001Dto>  selectWanAmt(ChbPrdBonusDeal  deal);
}