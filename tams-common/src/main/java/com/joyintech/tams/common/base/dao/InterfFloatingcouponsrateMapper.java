package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfFloatingcouponsrate;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfFloatingcouponsrateMapper {
    int deleteByPrimaryKey(String objectId);

    int insert(InterfFloatingcouponsrate record);

    int insertSelective(InterfFloatingcouponsrate record);

    int insertBasicByBasicInfo(InterfFloatingcouponsrate record);

    InterfFloatingcouponsrate selectByPrimaryKey(String objectId);

    int updateByPrimaryKeySelective(InterfFloatingcouponsrate record);

    int updateByPrimaryKey(InterfFloatingcouponsrate record);
}