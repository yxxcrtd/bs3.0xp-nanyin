package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlSecReceivables;
import com.joyintech.tams.common.base.entity.PtlSecReceivablesKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PtlSecReceivablesMapper {
    int deleteByPrimaryKey(PtlSecReceivablesKey key);

    int insert(PtlSecReceivables record);

    int insertSelective(PtlSecReceivables record);

    int insertBasicByBasicInfo(PtlSecReceivables record);

    PtlSecReceivables selectByPrimaryKey(PtlSecReceivablesKey key);

    int updateByPrimaryKeySelective(PtlSecReceivables record);

    int updateByPrimaryKey(PtlSecReceivables record);
}