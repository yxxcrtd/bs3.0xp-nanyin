package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokBooksetPlan;

@Repository
public interface BokBooksetPlanBatchMapper {

    int insertBatch(List<BokBooksetPlan> list);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param rstValFinProductType
     * @see
     * @since 1.0
     */
    int deleteBatch(List<BokBooksetPlan> list);
}