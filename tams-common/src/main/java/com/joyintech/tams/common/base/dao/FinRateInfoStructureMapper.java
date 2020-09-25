package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinRateInfoStructure;
import com.joyintech.tams.common.base.entity.FinRateInfoStructureKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinRateInfoStructureMapper {
    int deleteByPrimaryKey(FinRateInfoStructureKey key);

    int insert(FinRateInfoStructure record);

    int insertSelective(FinRateInfoStructure record);

    int insertBasicByBasicInfo(FinRateInfoStructure record);

    FinRateInfoStructure selectByPrimaryKey(FinRateInfoStructureKey key);

    int updateByPrimaryKeySelective(FinRateInfoStructure record);

    int updateByPrimaryKey(FinRateInfoStructure record);
}