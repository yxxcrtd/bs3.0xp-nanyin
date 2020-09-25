/*
 * 文件名：InterfTcms002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.tcms002.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.interf.tcms002.entity.InFlowEntity;
import com.joyintech.service.interf.tcms002.entity.InterfTcms002InEntity;
import com.joyintech.service.interf.tcms002.entity.InterfTcms002OutEntity;

/**
 * Tcms002Mapper
 * 
 * @author changwenbin
 * @version 1.0 2018年11月12日
 * @see InterfTcms002Mapper
 * @since 1.0
 */
@Repository
public interface InterfTcms002Mapper {

    /**
     * 处理模式为0的查询
     * 
     * @param entity TCMS002输入
     * @return TCMS002输出
     * @see
     * @since 1.0
     */
    InterfTcms002OutEntity selectChkmode(InterfTcms002InEntity entity);

    /**
     * 查询金融产品对应费率
     * 
     * @param finprodid 金融产品代码
     * @param yieldname 费率名（数据字典code）
     * @return 金融产品的对应费率
     * @see
     * @since 1.0
     */
    BigDecimal selectYield(@Param("finproid") String finprodid,
                           @Param("yieldname") String yieldname);

    /**
     * 查询产品流程entity
     * 
     * @param prodid 产品代码
     * @return 状态code
     * @see
     * @since 1.0
     */
    InFlowEntity selectStatus(@Param("proid") String prodid);

    /**
     * 查询产品说明书路径
     * 
     * @param finprodid 金融产品代码
     * @return 文件路径
     * @see
     * @since 1.0
     */
    String selectProFile(@Param("finproid") String finprodid);

}
