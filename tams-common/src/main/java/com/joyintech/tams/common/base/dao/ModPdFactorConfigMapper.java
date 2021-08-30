package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModPdFactorConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface ModPdFactorConfigMapper {
    int insert(ModPdFactorConfig record);

    int insertSelective(ModPdFactorConfig record);

    int insertBasicByBasicInfo(ModPdFactorConfig record);
}