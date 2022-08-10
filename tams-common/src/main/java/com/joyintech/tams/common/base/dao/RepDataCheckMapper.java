package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepDataCheck;
import org.springframework.stereotype.Repository;

@Repository
public interface RepDataCheckMapper {
    int deleteByPrimaryKey(RepDataCheck key);

    int insert(RepDataCheck record);

    int insertSelective(RepDataCheck record);

    int insertBasicByBasicInfo(RepDataCheck record);

    RepDataCheck selectByPrimaryKey(RepDataCheck key);

    int updateByPrimaryKeySelective(RepDataCheck record);

    int updateByPrimaryKey(RepDataCheck record);
}