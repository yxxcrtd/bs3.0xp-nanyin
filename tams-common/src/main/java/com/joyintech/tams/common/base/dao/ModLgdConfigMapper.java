package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModLgdConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface ModLgdConfigMapper {
    int insert(ModLgdConfig record);

    int insertSelective(ModLgdConfig record);

    int insertBasicByBasicInfo(ModLgdConfig record);
}