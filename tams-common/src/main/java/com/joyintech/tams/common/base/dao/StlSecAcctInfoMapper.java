package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StlSecAcctInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface StlSecAcctInfoMapper {
    int deleteByPrimaryKey(String secAcctId);

    int insert(StlSecAcctInfo record);

    int insertSelective(StlSecAcctInfo record);

    int insertBasicByBasicInfo(StlSecAcctInfo record);

    StlSecAcctInfo selectByPrimaryKey(String secAcctId);

    int updateByPrimaryKeySelective(StlSecAcctInfo record);

    int updateByPrimaryKey(StlSecAcctInfo record);
}