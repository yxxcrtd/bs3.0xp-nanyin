/*
 * 文件名：TrdCommonBaseMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.trade.dao;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.common.dto.TrdStaSecInfoDto;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 交易基础组件调用Mapper
 *
 * @author 刘二通
 * @version 1.0 2018年11月2日
 * @see TrdCommonBaseMapper
 * @since 1.0
 */
@Repository
public interface TrdCommonBaseMapper {

    /**
     * 查询产品登记编码
     *
     * @param paramMap 参数
     * @return 产品登记编码
     * @see
     * @since 1.0
     */
    String selectProdRegistCode(Map<String, Object> paramMap);

    /**
     * 根据工作流代码查询金融产品交易附属业务流程
     * @param workflowId 工作流代码
     * @return 金融产品交易附属业务流程信息
     * @see
     * @since 1.0
     */
    List<WflTrdProductDealAdd> selectWflTrdProductDealAdd(@Param("workflowId") String workflowId);

    /**
     * 根据工作流代码查询指令编号列表
     * @param workflowId 工作流代码
     * @return 指令编号列表
     * @see
     * @since 1.0
     */
    List<WflStlPayOrders> selectOrderIdList(@Param("workflowId") String workflowId);

    /**
     * 利用金融产品代码作为条件把资产负债报送信息表中的报送状态更改为是
     *
     * @param paramMap 参数
     * @return 更新结果
     * @see
     * @since 1.0
     */
    int updateRepAssetDebt(Map<String, Object> paramMap);

    /**
     * 检索已质押标准券额度
     *
     * @param portfolioId  组合代码
     * @param secAcctId    证券账户
     * @param searchDate   检索日期（yyyy-MM-dd）
     * @param isWithInvAim 检索项目是否包含检索投资目的
     * @return 已质押标准券信息Dto列表
     * @see
     * @since 1.0
     */
    List<TrdStaSecInfoDto> selectPledgeStandardPrinAmt(Map<String, Object> paramMap);
    

	/**
     * 根据工作流代码查询金融产品交易流程
     * @param workflowId 工作流代码
     * @return 金融产品交易流程信息
     * @see
     * @since 1.0
     */
    List<AllTradeCommonInputDto> selectWflTrdProductDeal(Map<String, Object> param);

    // 2.0自动生成反向交易 bug修改 start
    /**
     * 查询系统参数
     *
     * @return 系统参数
     */
    String selectParam(Map<String, Object> param);
    // 2.0自动生成反向交易 bug修改 end
    
    /**
     * 指令接收交易录单根据委托代码查询流程中已录入金额
     * @param entrustId String
     * @return BigDecimal
     * @author wangguiqi
     * @since 1.0
     */
	ArrayList<BigDecimal> queryWflPrinAmt(Map<String, Object> map);
    /**
     * 指令接收交易录单根据委托代码查询委托金额
     * @param entrustId String
     * @return BigDecimal
     * @author wangguiqi
     * @since 1.0
     */
	BigDecimal queryEntrustAmtByEntrustId(String entrustId);

    List<PtlTrade> findPtlTradeByTradeIds(List<TrdProductDeal> trdProductDeals1);

    List<TrdProductDealAdd> findDealAddByTradeIds(List<TrdProductDeal> trdProductDeals1);

    List<TrdProductDeal> findDealByTotalIdForInterf(Map<String, Object> map);

    void deleteRepTradeByTradeId(@Param("tradeId")String bizCode);

    void inertRevokeFeeDetailByTradeId(@Param("bizCode") String bizCode, @Param("updateUser")String updateUser);

    void insertRevokePtlTrade(@Param("deal")PtlTrade deal, @Param("updateUser")String updateUser);

    void insertRevokeTrdProductDealAdd(@Param("bizCode")String bizCode, @Param("updateUser")String updateUser);

    void insertRevokePaTrdProductDeal(@Param("bizCode")String bizCode, @Param("updateUser")String updateUser);

    List<StlPayOrders> findOrdersByOrderId(@Param("tradeId") String bizCode);

    List<TrdProductDeal> findDealByTotalId(Map<String, Object> map);

    void inertRevokeFeeTotalByTradeId(@Param("bizCode")String bizCode, @Param("updateUser")String updateUser);

    List<String> findSubTotalTradeId(@Param("bizCode")String bizCode);

    List<PtlTrade> findPtlTradeByBusiNo(String busiNo);

    TrdProductDeal selectByTotleTradeId(Map<String, Object> map);

    void insertSelectFileInfo(Map<String, Object> params);

    void insertSelectFileInfoByFileNo(Map<String, Object> params);

    List<WflTrdProductDealAdd> checkEtrToTrdInFlow(String entrustId);

    String selectWorkflowId(String bizCode);

    boolean isNotActPortfolio(String portfolioId);

    List<String> checkTotleWflData(String findKey);
    
    // add by zhangrui1 20200511 判断转仓交易 start
    /**
     * 是否组合代码，可用此方法判断交易对手是否内部交易对手
     * 
     * @param portfolioId 组合代码/交易对手
     * @return true:是组合代码/内部交易对手
     */
    boolean isPortfolioId(String portfolioId);
    // add by zhangrui1 20200511 判断转仓交易 end

    /**
     * 根据交易编号查询另一笔未撤销的转仓交易信息
     * add by hpp 2020-09-17
     * @param tradeId
     * @return
     */
    TrdProductDeal selectZCDeal(@Param("tradeId") String tradeId);

    /**
     * 根据交易编号查询已撤销数据条数
     *
     * @param paramMap 参数
     * @return 已撤销数据条数
     * @see
     * @since 1.0
     */
    int selectRevokedDataCount(Map<String, Object> paramMap);


    List<BigDecimal> selectProAvailableCash(Map<String, Object> paramMap);
}
