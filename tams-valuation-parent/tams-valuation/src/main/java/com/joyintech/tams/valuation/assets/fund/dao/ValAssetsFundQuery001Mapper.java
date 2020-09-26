/*
 * 文件名：ValAssetsFundQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assets.fund.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.tams.valuation.assets.fund.dto.ValAssetsFundQuery001Dto;

/**
 * 基金类资产查询
 * 
 * @author zhangwei
 * @version 1.0 2018年7月19日
 * @see ValAssetsFundQuery001Mapper
 * @since 1.0
 */
public interface ValAssetsFundQuery001Mapper {

    /** 根据条件查询列表 */
    List<ValAssetsFundQuery001Dto> selectList(Map<String, Object> params);

    /**
     * 根据资产代码查询该资产是否存在交易（修改、删除校验）
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    boolean checkFindprodId(Map<String, Object> params);

    /**
     * 删除标的类金融产品表信息
     * 
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    int deleteByFindprodId(@Param("finprodId") String finprodId);

    /**
     * 删除标的类金融产品扩展表信息
     * 
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    int deleteByFindprodId2(@Param("finprodId") String finprodId);

    /**
     * 删除金融产品分类表
     * 
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    int deleteByFindprodId3(@Param("finprodId") String finprodId);

    /**
     * 删除现金流信息表
     * 
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    int deleteByFindprodId4(@Param("finprodId") String finprodId);

    /**
     * 删除现金流支付规则表
     * 
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    int deleteByFindprodId5(@Param("finprodId") String finprodId);

    /**
     * 删除现金流计划表
     * 
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    int deleteByFindprodId6(@Param("finprodId") String finprodId);
    
    /**
     * 判断是否在流程中的数据
     * <一句话功能简述>
     * <功能详细描述>
     * @param processMark
     * @return
     */
    List<String> getBusinessDataInFlow(ProcessMark processMark);

}
