package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModGradePdConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface ModGradePdConfigMapper {
    int insert(ModGradePdConfig record);

    int insertSelective(ModGradePdConfig record);

    int insertBasicByBasicInfo(ModGradePdConfig record);
}