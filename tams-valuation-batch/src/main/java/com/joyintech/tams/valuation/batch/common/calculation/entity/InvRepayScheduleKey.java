/*
 * 文件名：InvRepayScheduleKey.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.calculation.entity;

import com.joyintech.framework.base.BaseEntity;

/**
 * 还本计划
 * 
 * @author 王波
 * @version 1.0 2018年6月1日
 * @see InvRepayScheduleKey
 * @since 1.0
 */
public class InvRepayScheduleKey extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 计划代码
     */
    private String scheId;

    /**
     * 取得 计划代码
     * 
     * @return 计划代码
     */
    public String getScheId() {
        return scheId;
    }

    /**
     * 设置 计划代码
     * 
     * @param scheId 计划代码
     */
    public void setScheId(String scheId) {
        this.scheId = scheId == null ? null : scheId.trim();
    }
}
