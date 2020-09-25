package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokSceneconfig;
import com.joyintech.tams.common.base.entity.BokSceneconfigKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokSceneconfigMapper {
    int deleteByPrimaryKey(BokSceneconfigKey key);

    int insert(BokSceneconfig record);

    int insertSelective(BokSceneconfig record);

    int insertBasicByBasicInfo(BokSceneconfig record);

    BokSceneconfig selectByPrimaryKey(BokSceneconfigKey key);

    int updateByPrimaryKeySelective(BokSceneconfig record);

    int updateByPrimaryKey(BokSceneconfig record);
}