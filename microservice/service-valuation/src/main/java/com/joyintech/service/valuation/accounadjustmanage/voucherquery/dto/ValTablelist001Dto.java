/*
 * 文件名：ValAssetbook001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.voucherquery.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 场景代码（业务类型）Dto
 * 
 * @author weipeng
 * @version 1.0 2018年5月5日
 * @see ValTablelist001Dto
 * @since 1.0
 */
public class ValTablelist001Dto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 会计场景代码，关联会计场景表
     */
    private String tableId;

    /**
     * 会计场景名称
     */
    private String tableName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 会计场景代码，关联会计场景表
     * 
     * @return 会计场景代码，关联会计场景表
     */
    public String getTableId() {
        return tableId;
    }

    /**
     * 设置 会计场景代码，关联会计场景表
     * 
     * @param tableId
     *            会计场景代码，关联会计场景表
     */
    public void setTableId(String tableId) {
        this.tableId = tableId == null ? null : tableId.trim();
    }

    /**
     * 取得 会计场景名称
     * 
     * @return 会计场景名称
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 设置 会计场景名称
     * 
     * @param tableName
     *            会计场景名称
     */
    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    /**
     * 取得 备注
     * 
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
