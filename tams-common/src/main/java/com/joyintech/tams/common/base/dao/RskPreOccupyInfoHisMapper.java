package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskPreOccupyInfoHis;
import org.springframework.stereotype.Repository;

@Repository
public interface RskPreOccupyInfoHisMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(RskPreOccupyInfoHis record);

    int insertSelective(RskPreOccupyInfoHis record);

    int insertHisByBasicInfo(RskPreOccupyInfoHis record);

    RskPreOccupyInfoHis selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(RskPreOccupyInfoHis record);

    int updateByPrimaryKey(RskPreOccupyInfoHis record);
}