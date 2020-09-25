/*
 * 文件名：InterfLc0007Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0007.dao;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.joyintech.service.interf.ilc0007.entity.InterfLc0007InEntity;
import com.joyintech.service.interf.ilc0007.entity.InterfLc0007OutEntity;

/**
 * Lc0007mapper
 * 
 * @author changwenbin
 * @version 1.0 2018年11月10日
 * @see InterfLc0007Mapper
 * @since 1.0
 */
@Repository
public interface InterfLc0007Mapper {

    /**
     * 判断 放款指令编号 与 业务品种编号 是否正确
     * 
     * @param entity Lc0007输入
     * @return 查询个数
     * @see
     * @since 1.0
     */
    int selectCmsInfoCnt(InterfLc0007InEntity entity);

    /**
     * 查询借据金额
     * 
     * @param entity Lc0007输入
     * @return Lc007接口输出
     * @see
     * @since 1.0
     */
    InterfLc0007OutEntity selectDuebillAmt(InterfLc0007InEntity entity);

    /**
     * 查询借据余额数量
     * 
     * @param entity Lc0007输入
     * @return 借据余额
     * @see
     * @since 1.0
     */
    BigDecimal selectDuebillBalanceAmount(InterfLc0007InEntity entity);

    /**
     * 查询借据余额百元面值
     * 
     * @param entity Lc0007输入
     * @return 百元面值
     * @see
     * @since 1.0
     */
    BigDecimal selectFaceValue(InterfLc0007InEntity entity);
}
