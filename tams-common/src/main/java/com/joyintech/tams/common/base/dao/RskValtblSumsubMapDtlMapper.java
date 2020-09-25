package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskValtblSumsubMapDtl;
import com.joyintech.tams.common.base.entity.RskValtblSumsubMapDtlKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RskValtblSumsubMapDtlMapper {
    int deleteByPrimaryKey(RskValtblSumsubMapDtlKey key);

    int insert(RskValtblSumsubMapDtl record);

    int insertSelective(RskValtblSumsubMapDtl record);

    int insertBasicByBasicInfo(RskValtblSumsubMapDtl record);

    RskValtblSumsubMapDtl selectByPrimaryKey(RskValtblSumsubMapDtlKey key);

    int updateByPrimaryKeySelective(RskValtblSumsubMapDtl record);

    int updateByPrimaryKey(RskValtblSumsubMapDtl record);
}