package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfEmailconfig;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfEmailconfigMapper {
    int insert(InterfEmailconfig record);

    int insertSelective(InterfEmailconfig record);

    int insertBasicByBasicInfo(InterfEmailconfig record);
}