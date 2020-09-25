package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskOccupyInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface RskOccupyInfoMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(RskOccupyInfo record);

    int insertSelective(RskOccupyInfo record);

    int insertBasicByBasicInfo(RskOccupyInfo record);

    RskOccupyInfo selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(RskOccupyInfo record);

    int updateByPrimaryKey(RskOccupyInfo record);
}