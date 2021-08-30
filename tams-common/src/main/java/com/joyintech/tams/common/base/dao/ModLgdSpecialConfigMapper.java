package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModLgdSpecialConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface ModLgdSpecialConfigMapper {
    int insert(ModLgdSpecialConfig record);

    int insertSelective(ModLgdSpecialConfig record);

    int insertBasicByBasicInfo(ModLgdSpecialConfig record);
}