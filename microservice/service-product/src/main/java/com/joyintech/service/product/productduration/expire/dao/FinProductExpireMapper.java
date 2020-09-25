/**
 * 
 */
package com.joyintech.service.product.productduration.expire.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.product.common.dto.FinProductCommonInfoDto;
import com.joyintech.service.product.productduration.expire.dto.FinProductExpire001Dto;
import com.joyintech.service.product.productduration.expire.dto.FinProductExpireBranchInfoDto;
import com.joyintech.service.product.productduration.expire.dto.FinProductExpireFeeInfoDto;
import com.joyintech.service.product.productduration.expire.dto.FinProductExpireMInfoDto;
import com.joyintech.tams.invest.common.dto.product.addreduce.ReduceInfoDto;

/**
 * @author zhangliang
 *
 */
@Repository
public interface FinProductExpireMapper {
    
    /**
     * 查询待处理的费用计划信息
     * 
     * @param map 参数
     * @return 费用计划信息
     */
    List<FinProductExpire001Dto> selectForPage(Map<String, Object> map);

    
    /** 根据金融产品代码查询到期信息
     * @param map 参数
     * @return 到期信息
     */
    List<FinProductExpireMInfoDto> queryMInfoListByFinprodId(Map<String, Object> map);
    
    /** 根据金融产品代码查询到期信息
     * @param map 参数
     * @return 到期信息
     */
    List<FinProductExpireMInfoDto> queryMInfoListByFinprodIdAdd(Map<String, Object> map);
    
    /** 根据金融产品代码查询费用信息
     * @param map 参数
     * @return 费用信息
     */
    List<FinProductExpireFeeInfoDto> queryFeeInfoListByFinprodId(Map<String, Object> map);
    
    /** 根据金融产品代码查询分行信息
     * @param map 参数
     * @return 分行信息
     */
    List<FinProductExpireBranchInfoDto> queryBranchInfoListByFinprodId(Map<String, Object> map);
    
    /** 根据交易编号查询到期信息
     * @param map 参数
     * @return 到期信息
     */
    List<FinProductExpireMInfoDto> queryMInfoListByTradeId(Map<String, Object> map);
    
    /** 根据交易编号查询费用信息
     * @param map 参数
     * @return 费用信息
     */
    List<FinProductExpireFeeInfoDto> queryFeeInfoListByTradeId(Map<String, Object> map);
    
    /** 根据交易编号查询分行信息
     * @param tradeId 交易编号
     * @return 分行信息
     */
    List<FinProductExpireBranchInfoDto> queryBranchInfoListByTradeId(String tradeId);
    FinProductCommonInfoDto queryProdInfoByMdate(Map<String, Object> map);
    
    List<ReduceInfoDto> queryInterest(Map<String, Object> map);
    
    /**
     * 界面总汇表编号查询 到期信息
     * <功能详细描述>
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    FinProductExpire001Dto queryProdExpireByTotleTradeId(@Param("tradeId") String tradeId, @Param("finprodType") String finprodType);
    /**
     * 查询到期、赎回的本金交易明细（由于当做赎回、到期交易前已付息，故利息为0，收益型产品利息为0时不存付息的那笔交易，故取本金交易列表）
     * add by wm
     * @param map
     * @return
     */
    List<ReduceInfoDto> queryPrinAmt(Map<String, Object> map);
    /**
     * 通过产品代码查询是否已做过到期交易
     * @param map 条件
     * @return 返回结果
     */
    Integer isExpireTradeByFinprodId(Map<String, Object> map);
}
