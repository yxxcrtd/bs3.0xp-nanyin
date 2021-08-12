/*
 * 文件名：RskRelatedPartyDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年10月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.namelistmanage.relatedparty.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RskRelatedParty;

import javax.validation.constraints.DecimalMin;


/**
 * 关联方管理
 * 
 * @author zhangrui1
 * @version 1.0 2020年10月14日
 * @see RskRelatedPartyDto
 * @since 1.0
 */
public class RskRelatedPartyDto extends RskRelatedParty {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 5829402671743305927L;

    /**
     * 行号
     */
    private int row;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 选中的记录
     */
    private List<RskRelatedPartyDto> selectedItem;

    /**
     * 获取 操作类型
     * 
     * @return 操作类型.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 操作类型
     * 
     * @param operateType 操作类型
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 选中的记录
     * 
     * @return 选中的记录.
     */
    public List<RskRelatedPartyDto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 选中的记录
     * 
     * @param selectedItem 选中的记录
     */
    public void setSelectedItem(List<RskRelatedPartyDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 获取 行号
     * 
     * @return 行号.
     */
    public int getRow() {
        return row;
    }

    /**
     * 设置 行号
     * 
     * @param row 行号
     */
    public void setRow(int row) {
        this.row = row;
    }

    @NotEmpty(fieldName = "RskRelatedParty001.limitAmt",groups = {ValidateGroupEdit.class})
    public BigDecimal getLimitAmt() {
        return super.getLimitAmt();
    }
}
