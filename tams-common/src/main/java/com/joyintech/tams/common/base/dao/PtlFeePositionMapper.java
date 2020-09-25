package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlFeePosition;
import com.joyintech.tams.common.base.entity.PtlFeePositionKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlFeePositionMapper {
    int deleteByPrimaryKey(PtlFeePositionKey key);

    int insert(PtlFeePosition record);

    int insertSelective(PtlFeePosition record);

    int insertBasicByBasicInfo(PtlFeePosition record);

    PtlFeePosition selectByPrimaryKey(PtlFeePositionKey key);

    int updateByPrimaryKeySelective(PtlFeePosition record);

    int updateByPrimaryKey(PtlFeePosition record);
}