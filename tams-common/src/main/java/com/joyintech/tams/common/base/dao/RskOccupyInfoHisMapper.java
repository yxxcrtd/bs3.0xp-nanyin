package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskOccupyInfoHis;
import org.springframework.stereotype.Repository;

@Repository
public interface RskOccupyInfoHisMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(RskOccupyInfoHis record);

    int insertSelective(RskOccupyInfoHis record);

    int insertHisByBasicInfo(RskOccupyInfoHis record);

    RskOccupyInfoHis selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(RskOccupyInfoHis record);

    int updateByPrimaryKey(RskOccupyInfoHis record);
}