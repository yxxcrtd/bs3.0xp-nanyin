/*
 * 文件名：ProdOpenPlanInfo001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年11月23日
 * 修改内容：新建
 * 系统名称：中债报送系统
 */
 
package com.joyintech.tams.report.submitmaintenance.prodfactorinfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfoKey;
import com.joyintech.tams.common.base.entity.ChbPrdDeclareSub;
import com.joyintech.tams.common.base.entity.ChbPrdIssueSub;
import com.joyintech.tams.common.base.entity.ChbPrdOrgDeclareSub;
import com.joyintech.tams.common.base.entity.ChbSysOrganization;
import com.joyintech.tams.report.submitmaintenance.prodfactorinfo.dto.ProdFactorInfo001Dto;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author liuertong
 * @version 1.0 2017年12月15日
 * @see ProdFactorInfo001Mapper
 * @since 1.0
 */
@Repository
public interface ProdFactorInfo001Mapper {
    /**
     * 查询
     * <功能详细描述>
     * @param param 页面参数
     * @return 查询的结果
     * @see
     * @since 1.0
     */
    List<ProdFactorInfo001Dto> queryForPage(Map<String, Object> param);
    /**
     * 根据参数列表进行删除 （删除数据状态为有效的数据）
     * <功能详细描述>
     * @param map 参数list
     * @see
     * @since 1.0
     */
    void deleListE(Map<String, Object> map);
    /**
     * 检查产品信息表是否已存在数据
     * <功能详细描述>
     * @param key 主键
     * @return 是否已存在数据
     * @see
     * @since 1.0
     */
    List<ProdFactorInfo001Dto> selectByPrimaryKey(ChbPrdBaseinfoKey key);
    /**
     * 更新重复数据 
     * <功能详细描述>
     * @param item1 更新值
     * @see
     * @since 1.0
     */
    void updateDtoEByPrimaryKey(ChbPrdBaseinfo item1);
    /**
     * 检查登记表是否已存在数据
     * <功能详细描述>
     * @param key 主键
     * @return 是否已存在数据
     * @see
     * @since 1.0
     */
    List<ProdFactorInfo001Dto> selectByPrimaryKey1(ProdFactorInfo001Dto key);
    /**
     * 更新产品个人申报登记
     * <功能详细描述>
     * @param item 更新值
     * @see
     * @since 1.0
     */
    void updateDtoEByPrimaryKey1(ChbPrdDeclareSub item);
    /**
     * 检查登记表是否已存在数据
     * <功能详细描述>
     * @param key 主键
     * @return 是否已存在数据
     * @see
     * @since 1.0
     */
    List<ProdFactorInfo001Dto> selectByPrimaryKey2(ProdFactorInfo001Dto key);
    /**
     * 更新产品个人发行登记
     * <功能详细描述>
     * @param item 更新值
     * @see
     * @since 1.0
     */
    void updateDtoEByPrimaryKey2(ChbPrdIssueSub item);
    /**
     * 检查登记表是否已存在数据
     * <功能详细描述>
     * @param key 主键
     * @return 是否已存在数据
     * @see
     * @since 1.0
     */
    List<ProdFactorInfo001Dto> selectByPrimaryKey3(ProdFactorInfo001Dto key);
    /**
     * 更新产品机构发行登记
     * <功能详细描述>
     * @param item 更新值
     * @see
     * @since 1.0
     */
    void updateDtoEByPrimaryKey3(ChbPrdOrgDeclareSub item);
    /**
     * 产品是否已发生产品交易或费用交易
     * <功能详细描述>
     * @param dto ProdFactorInfo001Dto
     * @return 数量
     * @see
     * @since 1.0
     */
    int isDeal(ProdFactorInfo001Dto dto);
    /**
     * 删除个人申报登记信息
     * <功能详细描述>
     * @param key 业务主键
     * @see
     * @since 1.0
     */
    void delete1(ProdFactorInfo001Dto key);
    /**
     * 删除个人发行登记信息 
     * <功能详细描述>
     * @param key2 业务主键
     * @see
     * @since 1.0
     */
    void delete2(ProdFactorInfo001Dto key2);
    /**
     * 删除机构及私人银行产品发行登记信息
     * <功能详细描述>
     * @param key3 业务主键
     * @see
     * @since 1.0
     */
    void delete3(ProdFactorInfo001Dto key3);
    /**
     * 获取所有产品
     * <功能详细描述>
     * @return 所有产品
     * @see
     * @since 1.0
     */
    List<ProdFactorInfo001Dto> getAllProdList();
    /**
     * oracle通过表明获取表结构
     * @param tableName 表明
     * @return 表结构list
     * @see
     * @since 1.0
     */
    List<String> getTableColumnList(@Param("tableName") String tableName);
    /**
     * 获取所有产品
     * <功能详细描述>
     * @param list 传入参数
     * @return 所有产品
     * @see
     * @since 1.0
     */
    List<ChbPrdBaseinfo> getAllProdListByKeys(@Param("list") List<ChbPrdBaseinfo> list);
    /**
     * 批量插入基础信息
     * @param list 需要插入的list
     * @return 插入成功个数
     * @see
     * @since 1.0
     */
    int insertBatchPrd(@Param("list") List<ChbPrdBaseinfo> list);
    /**
     * 批量更新基础信息
     * @param list 需要更新list
     * @return 更新成功条数
     * @see
     * @since 1.0
     */
    int updateBatchByPrimaryKey(@Param("list") List<ChbPrdBaseinfo> list);
    /**
     * 
     * 获取系统存储的所有有效的登记银行代码
     * @return 登记银行代码集合
     * @see
     * @since 1.0
     */
    List<ChbSysOrganization> getRegistBankCode();
}
