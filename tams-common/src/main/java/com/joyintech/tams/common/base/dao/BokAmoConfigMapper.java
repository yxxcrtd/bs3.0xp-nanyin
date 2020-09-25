package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokAmoConfig;
import com.joyintech.tams.common.base.entity.BokAmoConfigKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokAmoConfigMapper {
    int deleteByPrimaryKey(BokAmoConfigKey key);

    int insert(BokAmoConfig record);

    int insertSelective(BokAmoConfig record);

    int insertBasicByBasicInfo(BokAmoConfig record);

    BokAmoConfig selectByPrimaryKey(BokAmoConfigKey key);

    int updateByPrimaryKeySelective(BokAmoConfig record);

    int updateByPrimaryKey(BokAmoConfig record);
}