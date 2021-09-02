package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModPdWeightConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface ModPdWeightConfigMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(ModPdWeightConfig record);

    int insertSelective(ModPdWeightConfig record);

    int insertBasicByBasicInfo(ModPdWeightConfig record);

    ModPdWeightConfig selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ModPdWeightConfig record);

    int updateByPrimaryKey(ModPdWeightConfig record);
}