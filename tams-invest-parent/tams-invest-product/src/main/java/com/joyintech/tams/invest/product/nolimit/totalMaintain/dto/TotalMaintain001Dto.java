package com.joyintech.tams.invest.product.nolimit.totalMaintain.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.ValidateGroupNew;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.tams.invest.product.nolimit.totalMaintain.validator.TotalMaintainValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;


import java.math.BigDecimal;

/**
 * @program: TotalMaintain001Dto
 * @description: 非标限额实体类
 * @author: jyl
 * @create: 2019-12-19 11:33
 **/
@PageId("TotalMaintain001")
@ValidLogic(targetClass = TotalMaintainValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class, ValidateGroupNew.class})
public class TotalMaintain001Dto extends BaseEntity implements ProcessEntity {
    /**
     * 生效日
     */
    @NotEmpty(fieldName = "TotalMaintain001.effDate")
    private String effDate;
    /**
     * 非标资产总限额
     */
    @NotEmpty(fieldName = "TotalMaintain001.nonStandardLimit")
    private BigDecimal nonStandardLimit;
    /**
     * 非标资产总比例
     */
    @NotEmpty(fieldName = "TotalMaintain001.nonStandardRotio")
    private BigDecimal nonStandardRotio;
    /**
     * 计划资产总量
     */
    private BigDecimal totalPlannedAssets;
    /**
     * 操作类型
     */
    private String operateType;

    @PrimaryKey(value = Field.PK_1)
    public String getEffDate() {
        return effDate;
    }

    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }

    public BigDecimal getNonStandardLimit() {
        return nonStandardLimit;
    }

    public void setNonStandardLimit(BigDecimal nonStandardLimit) {
        this.nonStandardLimit = nonStandardLimit;
    }

    public BigDecimal getNonStandardRotio() {
        return nonStandardRotio;
    }

    public void setNonStandardRotio(BigDecimal nonStandardRotio) {
        this.nonStandardRotio = nonStandardRotio;
    }

    public BigDecimal getTotalPlannedAssets() {
        return totalPlannedAssets;
    }

    public void setTotalPlannedAssets(BigDecimal totalPlannedAssets) {
        this.totalPlannedAssets = totalPlannedAssets;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
}
