package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PrdIssueplanAssetDatail;
import com.joyintech.tams.common.base.entity.PrdIssueplanAssetDatailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdIssueplanAssetDatailMapper {
    int deleteByPrimaryKey(PrdIssueplanAssetDatailKey key);

    int insert(PrdIssueplanAssetDatail record);

    int insertSelective(PrdIssueplanAssetDatail record);

    int insertBasicByBasicInfo(PrdIssueplanAssetDatail record);

    PrdIssueplanAssetDatail selectByPrimaryKey(PrdIssueplanAssetDatailKey key);

    int updateByPrimaryKeySelective(PrdIssueplanAssetDatail record);

    int updateByPrimaryKey(PrdIssueplanAssetDatail record);
}