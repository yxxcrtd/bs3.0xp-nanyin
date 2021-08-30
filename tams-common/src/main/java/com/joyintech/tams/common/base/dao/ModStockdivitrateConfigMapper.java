package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModStockdivitrateConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface ModStockdivitrateConfigMapper {
    int insert(ModStockdivitrateConfig record);

    int insertSelective(ModStockdivitrateConfig record);

    int insertBasicByBasicInfo(ModStockdivitrateConfig record);
}