package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrSecStockConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrSecStockConfigMapper {
    int deleteByPrimaryKey(String configId);

    int insert(EtrSecStockConfig record);

    int insertSelective(EtrSecStockConfig record);

    int insertBasicByBasicInfo(EtrSecStockConfig record);

    EtrSecStockConfig selectByPrimaryKey(String configId);

    int updateByPrimaryKeySelective(EtrSecStockConfig record);

    int updateByPrimaryKey(EtrSecStockConfig record);
}