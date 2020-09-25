package com.joyintech.service.product.nolimit.totalMaintain.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;

/**
 * @program: TotalMaintain001Dto
 * @description: 非标限额实体类
 * @author: jyl
 * @create: 2019-12-19 11:33
 **/
public class TotalMaintain001Dto extends BaseEntity implements ProcessEntity {
    /**
     * 生效日
     */
    private String effDate;
    /**
     * 非标资产总限额
     */
    private BigDecimal nonStandardLimit;
    /**
     * 非标资产总比例
     */
    private BigDecimal nonStandardRotio;
    /**
     * 计划资产总量
     */
    private BigDecimal totalPlannedAssets;
    /**
     * 操作类型
     */
    private String operateType;

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
