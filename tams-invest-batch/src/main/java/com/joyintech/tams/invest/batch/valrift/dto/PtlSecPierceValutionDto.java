/*
 * 文件名：PtlSecPierceValutionDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.valrift.dto;

import java.math.BigDecimal;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.PtlSecPierceValution;

/**
 *  穿透后组合估值
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月28日
 * @see PtlSecPierceValutionDto
 * @since 1.0
 */
public class PtlSecPierceValutionDto extends PtlSecPierceValution{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    private BigDecimal positionRatio = new BigDecimal(1);

    /**
     * 公允价值变动
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal tdyFloatIngpl;
    
    /**
     * 获取 positionRatio
     * @return positionRatio.
     */
    public BigDecimal getPositionRatio() {
        return positionRatio;
    }

    
    /**
     * 设置 positionRatio
     * @param positionRatio positionRatio
     */
    public void setPositionRatio(BigDecimal positionRatio) {
        this.positionRatio = positionRatio;
    }

    public BigDecimal getTdyFloatIngpl() {
        return tdyFloatIngpl;
    }

    public void setTdyFloatIngpl(BigDecimal tdyFloatIngpl) {
        this.tdyFloatIngpl = tdyFloatIngpl;
    }

}
