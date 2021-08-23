/*
 * 文件名：BussSettleOrderCommMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年09月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.settle.order.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;

@Repository
public interface BussSettleOrderCommMapper {
    /**
     * 检索产品关联资金账户共通
     * @param portfolioId 组合代码
     * @param ccy 币种
     * @param capacctType 资金账户类型
     * @return 产品关联资金账户
     */
    List<StlCapacctInfo> selectProdCapacctInfoList(@Param("portfolioId") String portfolioId, @Param("ccy") String ccy, @Param("capacctType") String capacctType);

    /**
     * 检索收益类型
     * @param finprodId 金融产品代码
     * @return 收益类型
     */
    String selectProfitType(@Param("finprodId") String finprodId);

    /**
     * 检索交易关联资金账户共通
     * @param secManageAcctId 证券托管户ID
     * @param ccy 币种
     * @return 交易关联资金账户
     */
    List<StlCapacctInfo> selectDealCapacctInfoList(@Param("secManageAcctId") String secManageAcctId, @Param("ccy") String ccy);

    /**
     * 检索交易对手资金账户共通
     * @param counterId 交易对手ID
     * @param ccy
     * @return 交易对手资金账户
     */
    List<StlCapacctInfo> selectCounterCapacctInfoList(@Param("counterId") String counterId, @Param("acctType") String acctType, @Param("ccy") String ccy);

    /**
     * 检索第一层通道资金账户共通
     * @param finprodId 金融产品代码
     * @return 第一层通道资金账户
     */
    List<StlCapacctInfo> selectFirstChannelCapacctInfoList(@Param("finprodId") String finprodId, @Param("acctType") String acctType);

    /**
     * 检索是否保本共通
     * @param portfolioId 组合代码
     * @return 是否保本
     */
    String selectIsGuarantee(@Param("portfolioId") String portfolioId);

    /**
     * 检索资产提供行
     * @param finprodId 金融产品代码
     * @return 资产提供行
     */
    String selectAssetProvider(@Param("finprodId") String finprodId);

    List<BussTrdTransferOrderBase> selectOrdersByBizCode(@Param("bizCode")String bizCode, @Param("IE_FLAG_02") String value);

    /**
     * 收付款账户下拉框
     * @param paramMap 过滤参数
     * @return 下拉框列表
     */
    List<SelectOptionDto> queryAcctList(Map<String, Object> paramMap);

    List<String> findSubTotalTradeId(String bizCode);

    List<String> findSubWflTotalTradeId(String bizCode);

    List<BussTrdTransferOrderBase> selectWflOrdersByBizCode(@Param("bizCode")String bizCode, @Param("IE_FLAG_02") String value);

    /**
     * 检索交易对手的交易对手类别
     * @param counterId 交易对手代码
     * @return 交易对手类别
     */
    String findCounterType(@Param("counterId") String counterId);
    
    /**
     * 根据账号查询清算账户表
     * 
     * @param ccy 币种
     * @return 清算账户表
     * @see
     * @since 1.0
     */
    StlCapacctInfo queryStlCapacctInfoByBusiAcctNo(@Param("ccy") String ccy, @Param("busiAcctNo") String busiAcctNo);

    /**
     * 检索交易场所
     * @param param Map<String, Object>
     * @return 交易场所
     */
    String selectTradeMarket(Map<String, Object> param);

    /**
     * 检索账户来源
     * @param param Map<String, Object>
     * @return 账户来源
     */
    String selectSecAcctSource(Map<String, Object> param);

    Boolean isNaibuCounter(String counterId);

    String getPayType(String chlId);

    List<StlCapacctInfo> selectChlCapacctInfoList(String chlId);

    List<String> findSubTradeId(String bizCode);

    List<String> findSubWflTradeId(String bizCode);


    /**
     * 资金的划拨管理，银证转账的收款方账户，应该关联系统中的证券托管户配置的交易结算户；
     * 证银转账，付款方账户关联系统中证券托管户配置的交易结算户
     * @param portfolioId 组合代码
     * @return 证券托管户配置的交易结算户
     */
    List<StlCapacctInfo> getPtlSecTrdAcctNo(@Param("portfolioId") String portfolioId);

    List<StlCapacctInfo> selectProdCapacctInfoListForProd(@Param("finprodId") String finprodId, @Param("ccy") String ccy, @Param("capacctType") String value);

    /**
     * 查询通道资金账户
     * add by jiangpeng on 20210510 start YHZGCPB20210115001 通道增值税缴纳
     * @param chlId
     * @param ccy
     * @param value
     * @return
     */
    List<StlCapacctInfo> selectChlAcctNoInfoList(@Param("chlId") String chlId, @Param("ccy") String ccy, @Param("capacctType") String value);

    List<BussTrdTransferOrderBase> selectOrdersByBizCodeByBTradeId(@Param("bizCode")String bizCode, @Param("IE_FLAG_02") String value);
}
