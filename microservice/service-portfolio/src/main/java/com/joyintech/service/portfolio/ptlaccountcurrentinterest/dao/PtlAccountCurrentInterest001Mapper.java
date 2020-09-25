/*
 * 文件名：PtlAccountCurrentInterest001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlaccountcurrentinterest.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.ptlaccountcurrentinterest.dto.MarkRate;
import com.joyintech.service.portfolio.ptlaccountcurrentinterest.dto.PtlAccountCurrentInterest001Dto;

/**
 * 账户活期利率Mapper
 * @author fanshuanghu
 * @version 1.0 2018年10月11日
 * @see PtlAccountCurrentInterest001Mapper
 * @since 1.0
 */
public interface PtlAccountCurrentInterest001Mapper {
    /**
     * 获取账号
     * @param param 模糊查询参数
     * @return 下拉框实体类集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getBusiAcctNo(Map<String,Object> map );
    /**
     * 分页查询
     * @param map 请求参数
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    List<PtlAccountCurrentInterest001Dto> selectForPage(Map<String,Object> map);
    /**
     * 获取浮动利率（复合基准）
     * @return 下拉框实体类
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getcurrentRate();
    /**
     * 删除利率表数据
     * @param dto 账户实体类
     * @return 是否
     * @see
     * @since 1.0
     */
    int deleteFinRateInfo(PtlAccountCurrentInterest001Dto dto);
    /**
     * 删除现金流信息
     * @param dto 账户实体类
     * @return 是否
     * @see
     * @since 1.0
     */
    int deleteFinCashInfo(PtlAccountCurrentInterest001Dto dto);
    /**
     * 删除规则表数据
     * @param dto 账户实体类
     * @return 是否
     * @see
     * @since 1.0
     */
    int deleteFinCashCalcRule(PtlAccountCurrentInterest001Dto dto); 
    /**
     * 删除现金流信息表数据
     * @param dto 账户实体类
     * @return 是否
     * @see
     * @since 1.0
     */
    int updateFinCashInfo(PtlAccountCurrentInterest001Dto dto);  
    /**
     * 更新利率信息表
     * @param dto 账户实体类
     * @return 是否
     * @see
     * @since 1.0
     */
    int updateFinRateInfo(PtlAccountCurrentInterest001Dto dto);
    /**
     * 更新规则表
     * @param dto 账户实体类
     * @return 是否
     * @see
     * @since 1.0
     */
    int updateFinCashCalcRule(PtlAccountCurrentInterest001Dto dto);
    /**
     * 插入到现金流信息表
     * @param dto 账户实体类
     * @return 是否
     * @see
     * @since 1.0
     */
    int insertFinCashInfo(PtlAccountCurrentInterest001Dto dto);
    /**
     * 插入到现金流表
     * @param dto 账号实体类
     * @return 是否
     * @see
     * @since 1.0
     */
    int insertFinRateInfo(PtlAccountCurrentInterest001Dto dto);
    /**
     * 插入到规则表
     * @param dto 账户实体类
     * @return 是否
     * @see
     * @since 1.0
     */
    int insertFinCashCalcRule(PtlAccountCurrentInterest001Dto dto);
    /**
     * 获取初始化信息
     * @param map 集合
     * @return 账户实体类
     * @see 
     * @since 1.0
     */
    PtlAccountCurrentInterest001Dto getInitInfo(Map<String,Object> map);
    /**
     * 获取基准成分
     * @param map 集合
     * @return 实体类
     * @see
     * @since 1.0
     */
    List<MarkRate> getBenchmarkInfo(Map<String,Object> map);
    /**
     * 获取利率
     * @param map 集合
     * @return 利率
     * @see
     * @since 1.0
     */
    BigDecimal getIndexIntRate(Map<String,Object> map);
    /**
     * 获取指数的利率
     * @param map 集合
     * @return 利率
     * @see
     * @since 1.0
     */
    BigDecimal getIntRate(Map<String,Object> map);
    /**
     * 获取曲线的利率
     * @param map 集合
     * @return 利率
     * @see
     * @since 1.0
     */
    BigDecimal getInterRate(Map<String,Object> map);
    /**
     * 获取最新的生效日
     * @param acctNo 账号
     * @return 日期
     * @see
     * @since 1.0
     */
    Date getLastEffDate(@Param("acctNo")String acctNo);
    /**
     * 统计存在的信息
     * @param map 待校验信息
     * @return 统计结果
     * @see
     * @since 1.0
     */
    int countExistsData(Map<String,Object> map);
    /**
     * 分页查询
     * @param map 请求参数
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    List<PtlAccountCurrentInterest001Dto> ratePage(Map<String,Object> map);
    
    /**
     * 获取利率数量
     * @param map 现金流代码
     * @return 数量
     * @see
     * @since 1.0
     */
    int getRateCount(Map<String,Object> map);

    /**
     * 根据金融产品代码查询生效日最新的活期利率信息
     * @param map 集合
     * @return 账户实体类
     * @see 
     * @since 1.0
     */
    PtlAccountCurrentInterest001Dto getInitInfoLastFinprodId(Map<String,Object> map);
}
