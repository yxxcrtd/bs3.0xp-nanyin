package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import com.joyintech.tams.common.base.entity.FinDealTarget;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface FinDealTargetBatchMapper {
    int insertBatch(List<FinDealTarget> list);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param FinDealPledge
     * @see
     * @since 1.0
     */
    int deleteBatch(List<FinDealTarget> list);
}