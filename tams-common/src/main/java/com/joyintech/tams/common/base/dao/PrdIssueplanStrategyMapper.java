package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PrdIssueplanStrategy;
import com.joyintech.tams.common.base.entity.PrdIssueplanStrategyKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdIssueplanStrategyMapper {
    int deleteByPrimaryKey(PrdIssueplanStrategyKey key);

    int insert(PrdIssueplanStrategy record);

    int insertSelective(PrdIssueplanStrategy record);

    int insertBasicByBasicInfo(PrdIssueplanStrategy record);

    PrdIssueplanStrategy selectByPrimaryKey(PrdIssueplanStrategyKey key);

    int updateByPrimaryKeySelective(PrdIssueplanStrategy record);

    int updateByPrimaryKey(PrdIssueplanStrategy record);
}