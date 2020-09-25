package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskDimEntrustInfoHis;
import org.springframework.stereotype.Repository;

@Repository
public interface RskDimEntrustInfoHisMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(RskDimEntrustInfoHis record);

    int insertSelective(RskDimEntrustInfoHis record);

    int insertHisByBasicInfo(RskDimEntrustInfoHis record);

    RskDimEntrustInfoHis selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(RskDimEntrustInfoHis record);

    int updateByPrimaryKey(RskDimEntrustInfoHis record);
}