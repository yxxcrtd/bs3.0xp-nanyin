package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskValtblSumsubMap;
import org.springframework.stereotype.Repository;

@Repository
public interface RskValtblSumsubMapMapper {
    int deleteByPrimaryKey(String id);

    int insert(RskValtblSumsubMap record);

    int insertSelective(RskValtblSumsubMap record);

    int insertBasicByBasicInfo(RskValtblSumsubMap record);

    RskValtblSumsubMap selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RskValtblSumsubMap record);

    int updateByPrimaryKey(RskValtblSumsubMap record);
}