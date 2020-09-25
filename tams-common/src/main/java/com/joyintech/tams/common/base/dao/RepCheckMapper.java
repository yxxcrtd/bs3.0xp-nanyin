package com.joyintech.tams.common.base.dao;


import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepCheck;
import com.joyintech.tams.common.base.entity.RepCheckKey;

@Repository
public interface RepCheckMapper {
    int deleteByPrimaryKey(RepCheckKey key);

    int insert(RepCheck record);

    int insertSelective(RepCheck record);

    int insertBasicByBasicInfo(RepCheck record);

    RepCheck selectByPrimaryKey(RepCheckKey key);

    int updateByPrimaryKeySelective(RepCheck record);

    int updateByPrimaryKey(RepCheck record);
}