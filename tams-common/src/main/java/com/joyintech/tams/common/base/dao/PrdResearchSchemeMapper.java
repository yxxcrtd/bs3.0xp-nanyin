package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PrdResearchScheme;
import com.joyintech.tams.common.base.entity.PrdResearchSchemeWithBLOBs;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdResearchSchemeMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(PrdResearchSchemeWithBLOBs record);

    int insertSelective(PrdResearchSchemeWithBLOBs record);

    int insertBasicByBasicInfo(PrdResearchSchemeWithBLOBs record);

    PrdResearchSchemeWithBLOBs selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(PrdResearchSchemeWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PrdResearchSchemeWithBLOBs record);

    int updateByPrimaryKey(PrdResearchScheme record);
}