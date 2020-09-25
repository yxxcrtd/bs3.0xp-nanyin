package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokAcctSubjectInfo;
import com.joyintech.tams.common.base.entity.BokAcctSubjectInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokAcctSubjectInfoMapper {
    int deleteByPrimaryKey(BokAcctSubjectInfoKey key);

    int insert(BokAcctSubjectInfo record);

    int insertSelective(BokAcctSubjectInfo record);

    int insertBasicByBasicInfo(BokAcctSubjectInfo record);

    BokAcctSubjectInfo selectByPrimaryKey(BokAcctSubjectInfoKey key);

    int updateByPrimaryKeySelective(BokAcctSubjectInfo record);

    int updateByPrimaryKey(BokAcctSubjectInfo record);
}