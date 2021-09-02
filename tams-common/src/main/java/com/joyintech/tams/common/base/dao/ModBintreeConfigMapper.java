package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModBintreeConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface ModBintreeConfigMapper {
    int insert(ModBintreeConfig record);

    int insertSelective(ModBintreeConfig record);

    int insertBasicByBasicInfo(ModBintreeConfig record);
}