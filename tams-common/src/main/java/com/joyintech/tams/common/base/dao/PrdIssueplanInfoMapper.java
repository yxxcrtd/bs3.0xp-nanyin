package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PrdIssueplanInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdIssueplanInfoMapper {
    int deleteByPrimaryKey(String prdIssueplanCode);

    int insert(PrdIssueplanInfo record);

    int insertSelective(PrdIssueplanInfo record);

    int insertBasicByBasicInfo(PrdIssueplanInfo record);

    PrdIssueplanInfo selectByPrimaryKey(String prdIssueplanCode);

    int updateByPrimaryKeySelective(PrdIssueplanInfo record);

    int updateByPrimaryKey(PrdIssueplanInfo record);
}