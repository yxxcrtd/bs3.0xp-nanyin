package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskBusinessRedisConfig;
import com.joyintech.tams.common.base.entity.RskBusinessRedisConfigKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RskBusinessRedisConfigMapper {
    int deleteByPrimaryKey(RskBusinessRedisConfigKey key);

    int insert(RskBusinessRedisConfig record);

    int insertSelective(RskBusinessRedisConfig record);

    int insertBasicByBasicInfo(RskBusinessRedisConfig record);

    RskBusinessRedisConfig selectByPrimaryKey(RskBusinessRedisConfigKey key);

    int updateByPrimaryKeySelective(RskBusinessRedisConfig record);

    int updateByPrimaryKey(RskBusinessRedisConfig record);
}