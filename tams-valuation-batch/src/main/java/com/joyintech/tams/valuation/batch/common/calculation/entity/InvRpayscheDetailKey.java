/*
 * 文件名：InvRpayscheDetailKey.java
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
 * 还本计划明细 
 * 
 * @author 王波
 * @version 1.0 2018年6月1日
 * @see InvRpayscheDetailKey
 * @since 1.0
 */
public class InvRpayscheDetailKey extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 明细代码
     */
    private String detailId;

    /**
     * 取得 明细代码
     * @return 明细代码
     */
    public String getDetailId() {
        return detailId;
    }

    /**
     * 设置 明细代码
     * @param detailId 明细代码
     */
    public void setDetailId(String detailId) {
        this.detailId = detailId == null ? null : detailId.trim();
    }
}
