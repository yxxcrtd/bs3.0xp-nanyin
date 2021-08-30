package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModValBatchParams;
import org.springframework.stereotype.Repository;

@Repository
public interface ModValBatchParamsMapper {
    int insert(ModValBatchParams record);

    int insertSelective(ModValBatchParams record);

    int insertBasicByBasicInfo(ModValBatchParams record);
}