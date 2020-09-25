/*
 * 文件名：IIndividLimit.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.indic.entity;

import java.io.Serializable;

/**
 * 个性化特殊计算阈值存储结构
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月28日
 * @see IIndividLimit
 * @since 1.0
 */
public interface IIndividLimit extends Serializable {

    /**
     * 取得 预警条件
     * 
     * @return 预警条件
     * @see
     * @since 1.0
     */
    String getWarnCond();

    /**
     * 取得 审批条件
     * 
     * @return 审批条件
     * @see
     * @since 1.0
     */
    String getApproveCond();

    /**
     * 取得 违规条件
     * 
     * @return 违规条件
     * @see
     * @since 1.0
     */
    String getFoulCond();

    /**
     * 取得 阈值条件（用于判断指标趋势）
     * 
     * @return 阈值条件
     * @see
     * @since 1.0
     */
    String getLimitCond();
}
