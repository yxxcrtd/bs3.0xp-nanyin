/*
 * 文件名：MstTradeOpponent001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年8月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.msttradeopponent.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.msttradeopponent.dto.MstTradeOpponentDto;

/**
 * 交易对手管理Mapper
 * @author fanshuanghu
 * @version 1.0 2018年8月20日
 * @see BasicMstTradeOpponent001Mapper
 * @since 1.0
 */
@Repository
public interface    BasicMstTradeOpponent001Mapper {
    /**
     * 查询交易对手类型
     * @param map 查询参数
     * @return 下拉框实体类集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryCounterType(Map<String, Object> map);
    /**
     * 分页查询 非内部交易对手(二级法人修改)
     * @param map 分页查询参数
     * @return 交易对手实体类集合
     * @see
     * @since 1.0
     */
    List<MstTradeOpponentDto> queryForPage(Map<String,Object> map);
    /**
     * 根据主键进行删除
     * @param dto 交易对手实体类
     * @return 数值判断是否删除成功
     * @see
     * @since 1.0
     */
    int deleteMstCounterParty(MstTradeOpponentDto dto);
    /**
     * 删除交易对手账号表信息
     * @param dto 交易对手实体类
     * @return 是否成功
     * @see
     * @since 1.0
     */
    int deleteCounterAcct(MstTradeOpponentDto dto);
    /**
     * 删除标的类金融信息表
     * @param dto 交易对手实体类
     * @return 是否成功
     * @see
     * @since 1.0
     */
    int deleteFinProduct(MstTradeOpponentDto dto);
    /**
     * 删除金融产品分类表信息
     * @param dto 交易对手实体类
     * @return 是否成功
     * @see
     * @since 1.0
     */
    int deleteFinProductType(MstTradeOpponentDto dto);
    /**
     * 删除现金流信息
     * @param dto 交易对手实体类
     * @return 是否成功
     * @see
     * @since 1.0
     */
    int deleteFinCashInfo(MstTradeOpponentDto dto);
    /**
     * 删除现金流计划
     * @param dto 交易对手实体类
     * @return 是否成功
     * @see
     * @since 1.0
     */
    int deleteFinCashPlan(MstTradeOpponentDto dto);
    /**
     * 删除现金流计算规则表数据
     * @param dto 交易对手实体类
     * @return 是否成功
     * @see
     * @since 1.0
     */
    int deleteFinCashCalcRule(MstTradeOpponentDto dto);
    /**
     * 自己管理计划报送信息
     * @param dto 交易对手实体类
     * @return 是否成功
     * @see
     * @since 1.0
     */
    int deleteRepAssetMngPlan(MstTradeOpponentDto dto);
    
    /**
     * 获取账号信息
     * @return 下拉框列表
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getAccount(Map<String,Object> param);
    /**
     * 获取账号类型
     * @return 下拉框实体类列表
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getAccountType();
    /**
     * 通过账户清算表的网点账号获取实体类
     * @param map 账户清算表的网店账号
     * @return 账户清算表的网点账号
     * @see
     * @since 1.0
     */
    MstTradeOpponentDto getDto(Map<String,Object> map);
    /**
     * 
     * @return 下拉框实体类
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getAccountTypeInfo();
    /**
     * 
     * @return 下拉框实体类
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getCustomerId(@Param("param") String param);
    List<SelectOptionDto> getCustomerIdMarket(@Param("param") String param);
    /**
     * 
     * @return 下拉框实体类
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getCustomerId1(@Param("param") String param);
    /**
     * 
     * @return 下拉框实体类
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFeeId(Map<String,Object> map);
    /**
     * 交易对手查找
     * @param counter 交易对手
     * @return 是否存在
     * @see
     * @since 1.0
     */
    int selectCounter(@Param("counterId")String counter);
    /**
     * 金融产品查找
     * @param dto 交易对手实体类
     * @return 是否存在
     * @see
     * @since 1.0
     */
    int selectFinProduct(MstTradeOpponentDto dto);
    /**
     * 账号信息
     * @param map 账号
     * @return 账号信息
     * @see
     * @since 1.0
     */
    StlCapacctInfo getCctInfo(Map<String,Object>map);
    
    List<SuggestDto> getCounterName(@Param("param") String param);
    List<SelectOptionDto> getCounterId(@Param("param") String param);
    List<SelectOptionDto> getOfficeId(@Param("param") String param);
    List<SelectOptionDto> getMarketId(Map<String, Object> map);
    int deletePaFinProduct(MstTradeOpponentDto dto);

    List<MstTradeOpponentDto> selectCounterIdByCustomer(Map<String, Object> map);
    
    int deleteFinProductAcct(MstTradeOpponentDto dto);
}
