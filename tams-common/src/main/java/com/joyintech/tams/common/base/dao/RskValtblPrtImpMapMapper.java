package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskValtblPrtImpMap;
import org.springframework.stereotype.Repository;

@Repository
public interface RskValtblPrtImpMapMapper {
    int deleteByPrimaryKey(String ptlId);

    int insert(RskValtblPrtImpMap record);

    int insertSelective(RskValtblPrtImpMap record);

    int insertBasicByBasicInfo(RskValtblPrtImpMap record);

    RskValtblPrtImpMap selectByPrimaryKey(String ptlId);

    int updateByPrimaryKeySelective(RskValtblPrtImpMap record);

    int updateByPrimaryKey(RskValtblPrtImpMap record);
}