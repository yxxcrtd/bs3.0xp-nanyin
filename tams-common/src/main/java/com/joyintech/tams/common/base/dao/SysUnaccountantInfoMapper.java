package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SysUnaccountantInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUnaccountantInfoMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(SysUnaccountantInfo record);

    int insertSelective(SysUnaccountantInfo record);

    int insertBasicByBasicInfo(SysUnaccountantInfo record);

    SysUnaccountantInfo selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(SysUnaccountantInfo record);

    int updateByPrimaryKey(SysUnaccountantInfo record);
}