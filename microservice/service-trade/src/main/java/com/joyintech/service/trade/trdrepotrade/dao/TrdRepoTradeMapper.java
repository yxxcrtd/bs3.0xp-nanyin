/*
 * 文件名：TrdRepoTradeMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.trdrepotrade.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.trade.trdrepotrade.dto.InterfOpicsTradRepoDto;
import com.joyintech.service.trade.trdrepotrade.dto.TrdRepoTradeDto;
import com.joyintech.service.trade.trdrepotrade.dto.TrdRepoTradeReferenceDto;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.InterfTradRepoasst;
import com.joyintech.tams.common.base.entity.PtlSecPositionFlow;
import com.joyintech.tams.common.base.entity.TrdMatchInfo;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;

/**
 * 回购资产交易微服务TrdRepoTradeMapper
 */
@Repository
public interface TrdRepoTradeMapper {

    /**
     * 回购资产交易查询画面，查询回购资产交易信息列表
     *
     * @param pageParam pageParam
     * @return List<TrdRepoTradeDto>
     */
    List<TrdRepoTradeDto> selectForPage(Map<String, Object> pageParam);

    /**
     * 根据交易编号查询回购资产交易信息
     *
     * @param param tradeId
     * @return TrdRepoTradeDto
     */
    TrdRepoTradeReferenceDto getTrdRepoTradeDto(Map<String, Object> param);

    /**
     * 根据交易编号检索组合持仓流水（持仓类型为实际）
     *
     * @param param param
     * @return List<PtlSecPositionFlowDto>
     * @see
     * @since 1.0
     */
    List<PtlSecPositionFlow> selectPtlSecPositionFlow(Map<String, Object> param);

    /**
     * 根据工作流代码，查询交易编号
     *
     * @param workflowId 工作流代码
     * @return 交易编号
     * @see
     * @since 1.0
     */
    String selectTradeIdByWorkflowId(@Param("workflowId") String workflowId);

    /**
     * 根据交易编号，查询流程中数据条数
     *
     * @param tradeId 交易编号
     * @return 流程中数据条数
     * @see
     * @since 1.0
     */
    int selectInFlowDataCount(@Param("tradeId") String tradeId);

    /**
     * 根据交易编号，查询已撤销数据条数
     *
     * @param paramMap 参数
     * @return 已撤销数据条数
     * @see
     * @since 1.0
     */
    int selectRevokedDataCount(Map<String, Object> paramMap);

    /**
     * 根据组合代码，查询证券托管户：联想框文本框
     *
     * @param param
     * @return
     */
    List<SuggestDto> getSecManageAcctId(Map<String, Object> param);

    /**
     * 组合代码、证券托管户、交易日、到期日改变时，查询可使用标准券额度
     *
     * @param params params
     * @return 可使用标准券额度
     */
    BigDecimal getAvaStandardStockPosition(Map<String, Object> params);

    /**
     * 查询副总裁/总裁助理授权额度
     *
     * @param params params
     * @return 副总裁/总裁助理授权额度
     */
    BigDecimal getVPOrPAuthAmount(Map<String, Object> params);

    /**
     * 查询正回购交易质押券信息
     *
     * @param selectParam selectParam
     * @return 正回购交易质押券信息
     */
    List<Map<String, Object>> selectFinDealPledge(Map<String, Object> selectParam);

    /**
     * 查询债券cstp交易下拉框
     * @param param 输入参数
     * @return
     */
    List<SelectOptionDto> queryRepoCstpInfoList(Map<String, Object> param);

    /**
     * 根据成交单号查询CSTP交易信息
     *
     * @param fedealno 成交单号
     * @return CSTP交易信息
     * @see
     * @since 1.0
     */
    InterfOpicsTradRepoDto queryRepoCstpInfoByNo(@Param("fedealno") String fedealno,
                                                 @Param("entrustId") String entrustId,
                                                 @Param("preTradeId") String preTradeId,
                                                 @Param("prodType") String prodType,
                                                 @Param("isCstpSure") String isCstpSure);

    /**
     * 根据成交单号查询回购抵押品关联
     *
     * @param fedealno 成交单号
     * @return 回购抵押品关联
     * @see
     * @since 1.0
     */
    List<InterfTradRepoasst> queryRepoAssetByNo(@Param("fedealno") String fedealno);
    /**
     * 获取质押券其他信息
     * @param othInputMap 参数
     * @return 质押券信息
     */
    PledgeBondInfoDto getPledgeBondInfoDto(Map<String, Object> othInputMap);
    /**
     * 查询债券信息
     * @param param 输入参数
     * @return
     */
    FinProduct querySecinfo(Map<String, Object> param);
    
    List<TrdProductDeal> getTrdProductDealByFTradeId(@Param("fTradeId") String fTradeId, @Param("finprodType2") String finprodType2);

    List<WflTrdProductDeal>  getWftTrdProductDealByFTradeId(@Param("fTradeId") String fTradeId,@Param("finprodType2") String finprodType2,@Param("workflowId") String workflowId);

    /**
     * 获取换出质押券信息
     * @param parm 参数
     * @return 质押券信息
     */
    List<PledgeBondInfoDto> getOutPledgeInfo(Map<String, Object> parm);
    
    /**
     * 根据金融产品代码获取债券名称
     * <功能详细描述>
     * @param finId
     * @return
     * @see
     * @since 1.0
     */
    List<String> getBondNames(String finId);
    /**
     * 根据交易编号，查询是否是到期续做产品的数据
     *
     * @param param 参数
     * @return由到期续做产生的交易数据条数
     * @see
     * @since 1.0
     */
    int isDqTrdProductInfo(Map<String, Object> param);

    FinProduct getFinProduct(@Param("finprodMarketId") String finprodMarketId);

    BigDecimal queryLendinQty(Map<String, Object> params);
    
    /** 查询委托的剩余额度
     * @param param 参数
     * @return 
     */
    BigDecimal selectDoEntrustMoney(Map<String, Object> param);

    List<TrdMatchInfo> selectPreTradeId(Map<String, Object> params);

    /**
     * add by yangwenjun 20201123 根据业务编号查询合作资产
     * @param tradeId
     * @return
     */
    String getChlId(String tradeId);
}
