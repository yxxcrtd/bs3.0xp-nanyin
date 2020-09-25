/*
 * 文件名：PayhsrcsecinfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.wind.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 业务数据查询
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月11日
 * @see BusinessMapper
 * @since 1.0
 */
@Repository
public interface BusinessMapper {

    /**
     * 根据市场代码取得金融产品代码
     * 
     * @param marketId 市场代码
     * @param typeList 金融产品类型
     * @return 金融产品代码
     * @see
     * @since 1.0
     */
    String selectFinProdId(@Param("marketId") String marketId,
                           @Param("typeList") List<String> typeList);

    /**
     * 查询现金流代码
     * 
     * @param finProdId 金融产品代码
     * @param cashType 现金流二级类型
     * @return 现金流代码
     * @see
     * @since 1.0
     */
    String selectCashId(@Param("finProdId") String finProdId, @Param("cashType") String cashType);

    /**
     * 查询机构ID
     * 
     * @param windId wind代码
     * @return 机构ID
     */
    String selectComId(@Param("windId") String windId);

    /**
     * 查询机构ID
     * 
     * @param compName 机构名称
     * @return 机构ID
     */
    String selectComIdByName(@Param("compName") String compName);
}
