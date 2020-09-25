package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.DisPoiSub;
import org.springframework.stereotype.Repository;

@Repository
public interface DisPoiSubMapper {
    int deleteByPrimaryKey(String id);

    int insert(DisPoiSub record);

    int insertSelective(DisPoiSub record);

    int insertBasicByBasicInfo(DisPoiSub record);

    DisPoiSub selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DisPoiSub record);

    int updateByPrimaryKey(DisPoiSub record);
}