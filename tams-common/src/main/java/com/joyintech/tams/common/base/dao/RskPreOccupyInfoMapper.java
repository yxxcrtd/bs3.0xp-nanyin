package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskPreOccupyInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface RskPreOccupyInfoMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(RskPreOccupyInfo record);

    int insertSelective(RskPreOccupyInfo record);

    int insertBasicByBasicInfo(RskPreOccupyInfo record);

    RskPreOccupyInfo selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(RskPreOccupyInfo record);

    int updateByPrimaryKey(RskPreOccupyInfo record);
}