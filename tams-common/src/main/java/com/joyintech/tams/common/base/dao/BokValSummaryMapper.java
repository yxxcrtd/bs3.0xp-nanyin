package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokValSummary;
import com.joyintech.tams.common.base.entity.BokValSummaryKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokValSummaryMapper {
    int deleteByPrimaryKey(BokValSummaryKey key);

    int insert(BokValSummary record);

    int insertSelective(BokValSummary record);

    int insertBasicByBasicInfo(BokValSummary record);

    BokValSummary selectByPrimaryKey(BokValSummaryKey key);

    int updateByPrimaryKeySelective(BokValSummary record);

    int updateByPrimaryKey(BokValSummary record);
}