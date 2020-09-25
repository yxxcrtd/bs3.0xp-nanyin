/*
 * 文件名：PtlActivityPeriod001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlactivityperiod.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.portfolio.ptlactivityperiod.dto.PtlActivityPeriod001Dto;

/**
 * 活期结息Mapper
 * @author fanshuanghu
 * @version 1.0 2018年9月17日
 * @see PtlActivityPeriod001Mapper
 * @since 1.0
 */
public interface PtlActivityPeriod001Mapper {
    /**
     * 分页查询
     * @param map 查询参数
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    List<PtlActivityPeriod001Dto> selectForPage(Map<String,Object> map);
    /**
     * 获取组合代码
     * @param map 请求参数
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getPortfolioId(Map<String,Object> map);
    /**
     * 获取网点账号
     * @param map 请求参数
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    List<SuggestDto> getBusiNo(Map<String,Object> map);
    /**
     * 获取组合代码联想框
     * @param map 请求参数
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    List<SuggestDto> getPortfolioIdSug(Map<String,Object> map);
    /**
     * 获取网点账号
     * @param map 请求参数
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getBusiAcctNo(Map<String,Object> map);
    /**
     * 获取开户机构
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @param page
     * @param length
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getBankDep(Map<String,Object> map);
    /**
     * 获取组合名称
     * @param map 请求参数
     * @return 组合名称
     * @see
     * @since 1.0
     */
    String getportFolioName(Map<String,Object> map);
    /**
     * 获取账户
     * @param map 请求参数
     * @return 账户
     * @see
     * @since 1.0
     */
    String getAcctNo(Map<String,Object> map);
    /**
     * 获取账户名称
     * @param map 请求参数
     * @return 账户名称
     * @see
     * @since 1.0
     */
    String getAcctName(Map<String,Object> map);
    /**
     * 获取划款指令ID
     * @param tradeId 交易编号
     * @return 划款指令
     * @see
     * @since 1.0
     */
    String getOrderId(@Param("tradeId") String tradeId);
    /**
     * 获取上次收息日
     * @param map 账号
     * @return 日期字符串
     * @see
     * @since 1.0
     */
    String getVDate(Map<String,Object> map);
    /**
     * 获取划款信息
     * @param tradeId 交易编号
     * @return 划款实体类
     * @see
     * @since 1.0
     */
    List<BussTrdTransferOrderDto> selectBussTrdTransferOrder(Map<String,Object> map);
    /**
     * 获取交易对手列表
     * @param map 请求参数
     * @return 交易对手列表
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getCounterAcct(Map<String,Object> map);
    /**
     * 获取报送登记编号
     * @param map 组合代码（市场代码）
     * @return 登记编号
     * @see
     * @since 1.0
     */
    List<String> getProdRegistCode(Map<String,Object> map);
    /**
     * 获取账号
     * @param map 请求参数
     * @return 账号
     * @see
     * @since 1.0
     */
    String getRepAcctNo(Map<String,Object> map);
    
    /**
     * 划款金额默认为理论计提金额
     * @param paramMap 请求参数集合
     * @return 理论计提金额
     * @see
     * @since 1.0
     */
    BigDecimal initTradeAmt(Map<String,Object> paramMap);
    
    /**
     * 查询明细信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<PtlActivityPeriod001Dto> findDetailsPage(Map<String,Object> map);
    
    
    /**
     * 通过业务编号查询明细信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<PtlActivityPeriod001Dto> findDetailsByBusiNo(Map<String,Object> map);
    
    /**
     * 子组合明细
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<PtlActivityPeriod001Dto> findChildrenPortfolioDetails(Map<String,Object> map);
}
