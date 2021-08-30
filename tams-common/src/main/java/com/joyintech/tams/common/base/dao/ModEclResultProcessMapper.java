package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModEclResultProcess;
import org.springframework.stereotype.Repository;

@Repository
public interface ModEclResultProcessMapper {
    int insert(ModEclResultProcess record);

    int insertSelective(ModEclResultProcess record);

    int insertBasicByBasicInfo(ModEclResultProcess record);
}