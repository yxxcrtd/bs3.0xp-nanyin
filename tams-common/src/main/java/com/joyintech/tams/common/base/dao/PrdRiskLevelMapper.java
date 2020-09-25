package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PrdRiskLevel;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdRiskLevelMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(PrdRiskLevel record);

    int insertSelective(PrdRiskLevel record);

    int insertBasicByBasicInfo(PrdRiskLevel record);

    PrdRiskLevel selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(PrdRiskLevel record);

    int updateByPrimaryKey(PrdRiskLevel record);
}