package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MockResultInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface MockResultInfoMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(MockResultInfo record);

    int insertSelective(MockResultInfo record);

    int insertBasicByBasicInfo(MockResultInfo record);

    MockResultInfo selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(MockResultInfo record);

    int updateByPrimaryKey(MockResultInfo record);
}