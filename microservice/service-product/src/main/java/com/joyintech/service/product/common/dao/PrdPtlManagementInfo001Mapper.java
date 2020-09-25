/*
 * 文件名：PtlManagementInfo001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.common.dto.FundAccountInfoDto;
import com.joyintech.service.product.common.dto.NodeDto;
import com.joyintech.service.product.common.dto.PtlManagementInfoDto;
import com.joyintech.service.product.common.dto.SecuritiesAccountInfoDto;


/**
 * 组合管理Mapper
 * @author fanshuanghu
 * @version 1.0 2018年9月7日
 * @see PrdPtlManagementInfo001Mapper
 * @since 1.0
 */
@Repository
public interface PrdPtlManagementInfo001Mapper {
    /**
     * 分页查询
     * @param map 查询参数
     * @return 实体类集合
     * @see
     * @since 1.0
     */
    List<PtlManagementInfoDto> selectForPage(Map<String, Object> map);
    /**
     * 删除组合包明细表数据
     * @param portfolioId 组合代码
     * @return 是否
     * @see
     * @since 1.0
     */
    int deleteRskPortfolioPackageDtlByportId(@Param("portfolioId") String portfolioId);
    /**
     * 删除组合表信息
     * @param dto 组合管理实体类
     * @return 是否
     * @see
     * @since 1.0
     */
    int deletePtlPortfolioByPrimaryKey(PtlManagementInfoDto dto);
    /**
     * 获取组合包代码
     * @param map 各个维度及维度对应的值
     * @return 维度代码
     * @see
     * @since 1.0
     */
    List<String> selectPtlBagId(Map<String, Object> map);
    /**
     * 删除组合拓展表信息
     * @param dto 组合管理实体类
     * @return 删除组合拓展信息
     * @see
     * @since 1.0
     */
    int deletePaPtlPortfolioByPrimaryKey(PtlManagementInfoDto dto);
    /**
     * 删除组合清算账户表信息
     * @param portfolioId 组合代码
     * @return 是否
     * @see
     * @since 1.0
     */
    int deletePtlCapacctInfoByPrimaryKey(@Param("portfolioId") String portfolioId);
    /**
     * 删除组合证券账户表信息
     * @param portfolioId 组合代码
     * @return 是否
     * @see
     * @since 1.0
     */
    int deletePtlSecAcctByPrimaryKey(@Param("portfolioId") String portfolioId);
    /**
     * 组合估值参数表信息
     * @param dto 组合管理实体类
     * @return 是否
     * @see
     * @since 1.0
     */
    int deletePtlValParamByPrimaryKey(PtlManagementInfoDto dto);
    /**
     * 删除组合权限表信息
     * @param dto 组合管理实体类
     * @return 是否
     * @see
     * @since 1.0
     */
    int deletePtlPortfolioAuthorityByPrimaryKey(PtlManagementInfoDto dto);
    /**
     * 插入数据到组合
     * @param dto 组合管理实体类
     * @return 插入的条数
     * @see
     * @since 1.0
     */
    int insertPtlPortfolio(PtlManagementInfoDto dto);
    /**
     * 插入数据到组合扩展
     * @param dto 组合管理实体类
     * @return 插入的条数
     * @see
     * @since 1.0
     */
    int insertPaPtlPortfolio(PtlManagementInfoDto dto);
    /**
     * 插入数据到组合估值参数
     * @param dto 组合管理实体类
     * @return 插入的条数
     * @see
     * @since 1.0
     */
    int insertPtlValParam(PtlManagementInfoDto dto);
    /**
     * 插入数据到组合清算账户表
     * @param dto 组合管理实体类
     * @return 插入的条数
     * @see
     * @since 1.0
     */
    int insertPtlCapacctInfo(FundAccountInfoDto dto);
    /**
     * 插入数据到组合证券账户
     * @param dto 组合管理实体类
     * @return 插入的条数
     * @see
     * @since 1.0
     */
    int insertPtlSecAcct(SecuritiesAccountInfoDto dto);
    /**
     * 获取资金账户账号
     * @param param 请求参数
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getAcctNo(Map<String, Object> param);
    /**
     * 获取账号信息
     * @param acctNo 账号
     * @param ccy 币种
     * @return 清算账户表实体类
     * @see
     * @since 1.0
     */
    StlCapacctInfo getCctInfo(@Param("acctNo") String acctNo, @Param("ccy") String ccy);
    /**
     * 获取证券账户账号
     * @param param 模糊查询参数
     * @return 下拉框实体类集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getSecAcctId(Map<String, Object> param);
    /**
     * 获取证券账户信息
     * @param secAcctNo 查询参数
     * @return 下拉框实体类集合
     * @see
     * @since 1.0
     */
    SecuritiesAccountInfoDto getSecAcctInfo(@Param("secAcctNo") String secAcctNo);
    /**
     * 获取证券账户
     * @param map 请求参数
     * @return 账户
     * @see
     * @since 1.0
     */
    List<String> getsecId(Map<String, Object> map);
    /**
     * 获取资金信息
     * @param map 请求参数（账号）
     * @return 资金信息
     * @see
     * @since 1.0
     */
    List<FundAccountInfoDto> getFunInfo(Map<String, Object> map);
    /**
     * 获取初始化信息
     * @param map 请求参数
     * @return 实体类
     * @see
     * @since 1.0
     */
    PtlManagementInfoDto getBaseInfo(Map<String, Object> map);
    /**
     * 获取用户信息
     * @param map 请求参 数
     * @return userId集合
     * @see
     * @since 1.0
     */
    List<String> getUserIdInfo(Map<String, Object> map);
    /**
     * 删除用户
     * @param portfolioId 组合代码
     * @return 是否
     * @see
     * @since 1.0
     */
    int deleteUserId(@Param("portfolioId") String portfolioId);
    /**
     * 删除用户
     * @param userId 用户名
     * @see
     * @since 1.0
     */
    void deleteByUserId(@Param("userId") String userId);
    /**
     * 获取组合代码实体类集 合
     * @param map 请求参数
     * @return 组合代码下拉框集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getPortfolioId(Map<String, Object> map);
    /**
     * 获取已分配用户
     * @param map 组合代码
     * @return 已分配用户集合
     * @see
     * @since 1.0
     */
    List<PtlManagementInfoDto> selectUserNameList(Map<String, Object> map);
    /**
     * 组合列表集合
     * @param map 请求参数
     * @return 组合列表
     * @see
     * @since 1.0
     */
    List<PtlManagementInfoDto> getPortPage(Map<String, Object> map);
    /**
     * 获取组合集合
     * @param map 请求参数
     * @return 组合集合
     * @see
     * @since 1.0
     */
    List<PtlPortfolio> getSelectedport(Map<String, Object> map);
    /**
     * 获取所属机构
     * @return 所属机构集合
     * @see
     * @since 1.0
     */
    List<NodeDto> getInstitu();
    /**
     * 获取所属部门
     * @return 所属部门集合
     * @see
     * @since 1.0
     */
    List<NodeDto> getDep();
    /**
     * 获取用户
     * @return 用户集合
     * @see
     * @since 1.0
     */
    List<NodeDto> getUser();
    /**
     * 删除现金流信息
     * @param finprodId 组合代码
     * @param finprodType 组合类型
     * @return 是否
     * @see
     * @since 1.0
     */
    int deleteFinCashInfoByFinprodId(@Param("finprodId") String finprodId, @Param("finprodType") String finprodType);


    /**
     *
     * 组合删除校验
     * <功能详细描述>
     * @param map
     * @return 查询出使用组合交易数
     * @see
     * @since 1.0
     */
    Integer deleteCheck(Map<String, Object> map);}
