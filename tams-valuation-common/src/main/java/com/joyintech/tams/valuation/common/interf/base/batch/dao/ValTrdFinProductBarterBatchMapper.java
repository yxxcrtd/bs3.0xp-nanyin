package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import com.joyintech.tams.common.base.entity.TrdFinProductBarter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValTrdFinProductBarterBatchMapper {
    int insertBatch(List<TrdFinProductBarter> list);
}
