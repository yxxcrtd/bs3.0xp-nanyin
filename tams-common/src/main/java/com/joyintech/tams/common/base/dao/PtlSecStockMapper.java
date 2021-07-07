package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlSecStock;
import com.joyintech.tams.common.base.entity.PtlSecStockKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlSecStockMapper {
    int deleteByPrimaryKey(PtlSecStockKey key);

    int insert(PtlSecStock record);

    int insertSelective(PtlSecStock record);

    int insertBasicByBasicInfo(PtlSecStock record);

    PtlSecStock selectByPrimaryKey(PtlSecStockKey key);

    int updateByPrimaryKeySelective(PtlSecStock record);

    int updateByPrimaryKey(PtlSecStock record);
}