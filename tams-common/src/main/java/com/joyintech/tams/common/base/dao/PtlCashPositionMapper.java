package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlCashPosition;
import com.joyintech.tams.common.base.entity.PtlCashPositionKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlCashPositionMapper {
    int deleteByPrimaryKey(PtlCashPositionKey key);

    int insert(PtlCashPosition record);

    int insertSelective(PtlCashPosition record);

    int insertBasicByBasicInfo(PtlCashPosition record);

    PtlCashPosition selectByPrimaryKey(PtlCashPositionKey key);

    int updateByPrimaryKeySelective(PtlCashPosition record);

    int updateByPrimaryKey(PtlCashPosition record);
}