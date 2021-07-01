/*
 * 文件名：TrdFundInvestTradeMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.fund.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.fund.dto.TrdFundInvestTradeDto;
import com.joyintech.service.trade.fund.dto.WorkFlowDto;
import com.joyintech.tams.common.base.entity.WflStlPayOrders;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.component.business.trade.dto.BussTrdTransfenpeiDto;

/**
 * 基金投资交易mapper
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年10月19日
 * @see TrdFundInvestTradeMapper
 * @since 1.0
 */
@Component
public interface TrdFundInvestTradeMapper {
    //    资产推荐方 新增 zhuyongkang 20191023 end
    /**
     * 构建推荐方下拉框
     *
     * @param param 参数
     * @return 下拉框查询结果
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getAssetRecommandOrg(Map<String, Object> param);
    //    资产推荐方 新增 zhuyongkang 20191023 end
    /**
     * 
     * 分页查询
     * <功能详细描述>
     * @param paraMap
     * @return
     * @see
     * @since 1.0
     */
    List<TrdFundInvestTradeDto> selectDeal(Map<String, Object> paraMap);

    /**
     * 资产代码联想下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodId(Map<String, Object> param);
    /**
     * 
     * 根据交易编码删除金融产品交易业务流程表
     * <功能详细描述>
     * @param tradId
     * @return
     * @see
     * @since 1.0
     */
    int deleteWflTrdProductDeal(@Param("tradId") String tradId);
    /**
     * 
     * 根据交易编码删除金融产品交易附属业务流程表
     * <功能详细描述>
     * @param tradId
     * @return
     * @see
     * @since 1.0
     */
    int deleteWflTrdProductDealAdd(@Param("tradId") String tradId);
    /**
     * 
     * 根据交易编码删除金融产品交易扩展业务流程表
     * <功能详细描述>
     * @param tradId
     * @return
     * @see
     * @since 1.0
     */
    int deleteWflPaTrdProductDeal(@Param("tradId") String tradId);
    /**
     * 
     * 根据交易编码删除划款业务流程表
     * <功能详细描述>
     * @param tradId
     * @return
     * @see
     * @since 1.0
     */
    int deleteWflStlPayOrders(@Param("tradId") String tradId);
    /**
     * 
     * 通过组合代码获取产品登记编码
     * <功能详细描述>
     * @param portfolioId
     * @return
     * @see
     * @since 1.0
     */
    String selProdCode(Map<String, Object> map);
    /**
     * 
     * 查询收盘价
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal selCloPrice(Map<String, Object> map);
    /**
     * 
     * 更新资产负债报送状态
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    int updateRep(Map<String, Object> map);
    /**
     * 
     * 根据工作流代码删除金融产品交易业务流程表
     * <功能详细描述>
     * @param workflowId
     * @return
     * @see
     * @since 1.0
     */
    int deleteWflTrdProductDealByWfl(@Param("workflowId") String workflowId);
    /**
     * 
     * 根据工作流代码删除金融产品交易附属业务流程表
     * <功能详细描述>
     * @param workflowId
     * @return
     * @see
     * @since 1.0
     */
    int deleteWflTrdProductDealAddByWfl(@Param("workflowId") String workflowId);
    /**
     * 
     * 根据工作流代码删除金融产品交易扩展业务流程表
     * <功能详细描述>
     * @param workflowId
     * @return
     * @see
     * @since 1.0
     */
    int deleteWflPaTrdProductDealByWfl(@Param("workflowId") String workflowId);
    /**
     * 
     * 根据工作流代码删除划款指令业务流程表
     * <功能详细描述>
     * @param workflowId
     * @return
     * @see
     * @since 1.0
     */
    int deleteWflStlPayOrdersByWfl(@Param("workflowId") String workflowId);
    /**
     * 
     * 查询反显数据
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    TrdFundInvestTradeDto getTrdFundDto(Map<String, Object> map);
    /**
     * 
     * 查询单位净值
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal getPrice(Map<String, Object> map);
    /**
     * 查询待结转份额
     * @param map
     * @return
     */
    BigDecimal getcarryAmt(Map<String,Object> map);
    /**
     * 
     * 查询交易确认信息
     * <功能详细描述>
     * @param bTradeId
     * @return
     * @see
     * @since 1.0
     */
    Integer selectConfirmInfo(Map<String, Object> param);
    
    /**
     * 
     * 查询交易确认流程中信息
     * <功能详细描述>
     * @param bTradeId
     * @return
     * @see
     * @since 1.0
     */
    Integer selectConfirmInfoWfl(Map<String, Object> param);
    /**
     * 
     * 查询业务流程中交易确认信息
     * <功能详细描述>
     * @param bTradeId
     * @return
     * @see
     * @since 1.0
     */
    Integer selectWflConfirmInfo(@Param("bTradeId") String bTradeId);
    /**
     * 
     * 查询资产代码的交易方式
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    Map<String, Object> getPurSpeed(Map<String, Object> param);
    /**
     * 
     * 查询到账交易是否做过流水匹配
     * <功能详细描述>
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    Integer selRedemption(@Param("tradeId") String tradeId);
    /**
     * 
     * 取得业务流程表中到账交易的数据
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    TrdFundInvestTradeDto getTrdFundDtoForAccount(Map<String, Object> map);
    /**
     * 
     * 查询撤销数据是否有后继交易
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    Integer revokeCheck(TrdFundInvestTradeDto dto);
    /**
     * 
     * 查询业务流程表中划款指令
     * <功能详细描述>
     * @param bizCode
     * @param value
     * @return
     * @see
     * @since 1.0
     */
    List<BussTrdTransferOrderBase> selectOrdersByBizCode(@Param("bizCode")String bizCode, @Param("IE_FLAG_02") String value);
    /**
     * 
     * 通过赎回确认交易编号查询赎回到账交易编号
     * <功能详细描述>
     * @param tradId
     * @return
     * @see
     * @since 1.0
     */
    Map<String, Object> selectAccountTrdId(Map<String, Object> map);
    /**
     * 
     * 通过赎回确认交易编号查询流程中赎回到账交易编号
     * <功能详细描述>
     * @param tradId
     * @return
     * @see
     * @since 1.0
     */
    Map<String, Object> selectAccountTrdIdinwfl(@Param("tradId") String tradId);
    /**
     * 
     * 查询委托可用金额
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    List<TrdFundInvestTradeDto> selectEntrustAmt(Map<String, Object> map);
    /**
     * 
     * 查询资产管理人
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    String selectManeger(@Param("issuer") String issuer);
    
    /**
     * 
     * 通过产品代码查询资产管理人
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    String selectFManeger(@Param("finprodId") String finprodId);
    /**
     * 
     * 查询是否有后续交易
     * <功能详细描述>
     * @param bTradeId
     * @return
     * @see
     * @since 1.0
     */
    List<WorkFlowDto> selectTradeAfter(Map<String, Object> param);

    void insertPtlTradeByBusiNo(@Param("tradeId") String tradeId, @Param("updateUser") String updateUser);
    // 场内基金改造 add by renping 20191117 start
    /**
     * 查询实时赎回基金交易的后续交易编号
     * 
     * @param param 参数
     * @return 实时赎回基金交易的后续交易编号
     * @see
     * @since 1.0
     */
    List<String> selectFollowTradeInfo(Map<String, Object> param);
    // 场内基金改造 add by renping 20191117 end

    /**
     * 取得业务流程表中到账交易的数据(接口用)
     *
     * @param map 参数
     * @return 到账交易的数据
     * @see
     * @since 1.0
     */
    TrdFundInvestTradeDto selectWflDealOfRecv(Map<String, Object> map);
    
    /**
     * 取得业务流程表中指令的数据(接口用)
     *
     * @param tradeId 参数
     * @return 到账交易的指令数据
     * @see
     * @since 1.0
     */
    List<WflStlPayOrders> selectOrdersByTradeId(@Param("tradeId") String tradeId);

    /**
     * 取得业务流程表中指令的数据(接口用)
     *
     * @param tradeId 参数
     * @return 到账交易的指令数据
     * @see
     * @since 1.0
     */
    List<BussTrdTransfenpeiDto> selectTrdTransfenpeiList(Map<String, Object> param);
    
	BigDecimal getPriceByTradeId(String tradeId);
	
	BigDecimal getPriceByWorkflowId(String workflowId);

    /**
     * 查询待结转份额
     * @param map
     * @return
     */
    BigDecimal getcarryAmtNew(Map<String,Object> map);
}
