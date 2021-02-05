package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlSecPositionDetail;
import com.joyintech.tams.common.base.entity.PtlSecPositionDetailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlSecPositionDetailMapper {
    int deleteByPrimaryKey(PtlSecPositionDetailKey key);

    int insert(PtlSecPositionDetail record);

    int insertSelective(PtlSecPositionDetail record);

    int insertBasicByBasicInfo(PtlSecPositionDetail record);

    PtlSecPositionDetail selectByPrimaryKey(PtlSecPositionDetailKey key);

    int updateByPrimaryKeySelective(PtlSecPositionDetail record);

    int updateByPrimaryKey(PtlSecPositionDetail record);
}