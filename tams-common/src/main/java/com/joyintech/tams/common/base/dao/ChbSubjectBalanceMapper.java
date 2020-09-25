package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSubjectBalance;

public interface ChbSubjectBalanceMapper {
    int deleteByPrimaryKey(String produuid);

    int insert(ChbSubjectBalance record);

    int insertSelective(ChbSubjectBalance record);

    ChbSubjectBalance selectByPrimaryKey(String produuid);

    int updateByPrimaryKeySelective(ChbSubjectBalance record);

    int updateByPrimaryKey(ChbSubjectBalance record);
}