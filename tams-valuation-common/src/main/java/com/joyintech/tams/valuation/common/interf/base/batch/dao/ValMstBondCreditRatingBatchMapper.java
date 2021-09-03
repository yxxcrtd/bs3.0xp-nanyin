package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMstBondCreditRating;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValMstBondCreditRatingBatchMapper {

    int insertBatch(List<ValMstBondCreditRating> list);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param listInsert
     * @see
     * @since 1.0
     */
    int deleteBatch(List<ValMstBondCreditRating> list);
}
