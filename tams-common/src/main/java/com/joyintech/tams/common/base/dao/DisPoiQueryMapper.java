package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.DisPoiQuery;
import org.springframework.stereotype.Repository;

@Repository
public interface DisPoiQueryMapper {
    int deleteByPrimaryKey(String id);

    int insert(DisPoiQuery record);

    int insertSelective(DisPoiQuery record);

    int insertBasicByBasicInfo(DisPoiQuery record);

    DisPoiQuery selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DisPoiQuery record);

    int updateByPrimaryKey(DisPoiQuery record);
}