package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.DisPoiTab;
import org.springframework.stereotype.Repository;

@Repository
public interface DisPoiTabMapper {
    int deleteByPrimaryKey(String tabNum);

    int insert(DisPoiTab record);

    int insertSelective(DisPoiTab record);

    int insertBasicByBasicInfo(DisPoiTab record);

    DisPoiTab selectByPrimaryKey(String tabNum);

    int updateByPrimaryKeySelective(DisPoiTab record);

    int updateByPrimaryKey(DisPoiTab record);
}