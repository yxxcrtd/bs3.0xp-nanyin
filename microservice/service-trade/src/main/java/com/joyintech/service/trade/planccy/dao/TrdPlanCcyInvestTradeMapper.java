/*
 * 文件名：TrdPlanCcyInvestTradeMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.planccy.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.planccy.dto.TrdPlanCcyInvestTradeDto;
import com.joyintech.tams.component.business.trade.dto.BussTrdTransfenpeiDto;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2019年1月7日
 * @see TrdPlanCcyInvestTradeMapper
 * @since 1.0
 */
@Component
public interface TrdPlanCcyInvestTradeMapper {
    /**
     * 
     * 分页查询
     * <功能详细描述>
     * @param paraMap
     * @return
     * @see
     * @since 1.0
     */
    List<TrdPlanCcyInvestTradeDto> selectDeal(Map<String, Object> paraMap);

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
    TrdPlanCcyInvestTradeDto getTrdFundDto(Map<String, Object> map);
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
    TrdPlanCcyInvestTradeDto getTrdFundDtoForAccount(Map<String, Object> map);
    /**
     * 
     * 查询撤销数据是否有后继交易
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    Integer revokeCheck(TrdPlanCcyInvestTradeDto dto);
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
    List<TrdPlanCcyInvestTradeDto>  selectEntrustAmt(Map<String, Object> map);

    /**
     * 多组合交易查询
     * @param param
     * @return
     */
	List<TrdPlanCcyInvestTradeDto> selectAllTrade(Map<String, Object> param);

	/**
	 * 多组合交易确认页面组合分配列表查询
	 * @param param
	 * @return
	 */
	List<BussTrdTransfenpeiDto> selectTrdTransfenpeiList(Map<String, Object> param);
	
	/**
	 * 查询已完成委托金额
	 * @param param
	 * @return
	 */
	List<BigDecimal> getFinPrintAmt(Map<String, Object> param);
	
	/**
	 * 查询已完成委托数量
	 * @param param
	 * @return
	 */
	List<BigDecimal> getFinShareAmt(Map<String, Object> param);

	/**
	 * 查询总的委托金额/数量
	 * @param param
	 * @return
	 */
	Map<String, BigDecimal> getEntrustAmtByEntrustId(Map<String, Object> param);
}
