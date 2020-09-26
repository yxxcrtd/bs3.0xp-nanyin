/*
 * 文件名：GridRowDto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：何阳阳
 * 修改时间：2016年11月30日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.common.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 表格的行数据
 * 
 * @author 何阳阳
 * @version 1.0 2016年11月30日
 * @see GridRowDtoImpl
 * @since 1.0
 */
public class GridRowDtoImpl extends BaseEntity implements GridRowDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -5283371096174516665L;

    /**
     * Grid行 ID
     */
    private String rowId;

    /**
     * 排序
     */
    private int order;

    /**
     * 获取 rowId
     * 
     * @return rowId.
     */
    public String getRowId() {
        return rowId;
    }

    /**
     * 设置 rowId
     * 
     * @param rowId rowId
     */
    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    /**
     * 获取 order
     * 
     * @return order.
     */
    public int getOrder() {
        return order;
    }

    /**
     * 设置 order
     * 
     * @param order order
     */
    public void setOrder(int order) {
        this.order = order;
    }
}
