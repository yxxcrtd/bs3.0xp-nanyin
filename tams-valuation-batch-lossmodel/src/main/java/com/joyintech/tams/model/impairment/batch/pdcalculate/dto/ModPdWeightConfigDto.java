package com.joyintech.tams.model.impairment.batch.pdcalculate.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;

/**
 * @Description:资产交易信息Dto
 * @auther: yuzj
 * @date: 2021/7/15
 * @param: 
 * @return: 
 */
public class ModPdWeightConfigDto  {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 乐观权重
     */
    private BigDecimal optimisticWeight;
    /**
     * 基础权重
     */
    private BigDecimal basicWeight;
    /**
     * 悲观权重
     */
    private BigDecimal pessimismWeight;

    public BigDecimal getOptimisticWeight() {
        return optimisticWeight;
    }

    public void setOptimisticWeight(BigDecimal optimisticWeight) {
        this.optimisticWeight = optimisticWeight;
    }

    public BigDecimal getBasicWeight() {
        return basicWeight;
    }

    public void setBasicWeight(BigDecimal basicWeight) {
        this.basicWeight = basicWeight;
    }

    public BigDecimal getPessimismWeight() {
        return pessimismWeight;
    }

    public void setPessimismWeight(BigDecimal pessimismWeight) {
        this.pessimismWeight = pessimismWeight;
    }
}
