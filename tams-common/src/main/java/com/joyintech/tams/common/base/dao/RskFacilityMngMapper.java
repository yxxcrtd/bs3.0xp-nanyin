package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskFacilityMng;
import org.springframework.stereotype.Repository;

@Repository
public interface RskFacilityMngMapper {
    int deleteByPrimaryKey(String facilityId);

    int insert(RskFacilityMng record);

    int insertSelective(RskFacilityMng record);

    int insertBasicByBasicInfo(RskFacilityMng record);

    RskFacilityMng selectByPrimaryKey(String facilityId);

    int updateByPrimaryKeySelective(RskFacilityMng record);

    int updateByPrimaryKey(RskFacilityMng record);
}